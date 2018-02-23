package ritwik.sample.mvp.application;

import android.app.Application;

import ritwik.sample.mvp.login.LoginModule;

public class App extends Application {
	private ApplicationComponent mComponent;

	@Override public void onCreate () {
		super.onCreate ();
		initializeApplicationComponent ();
	}

	@SuppressWarnings ( "deprecation" ) private void initializeApplicationComponent () {
		mComponent =
				DaggerApplicationComponent
						.builder ()
						.applicationModule ( new ApplicationModule ( App.this ) )
						.loginModule ( new LoginModule () )
						.build ();
	}

	public ApplicationComponent getComponent () { return mComponent; }
}