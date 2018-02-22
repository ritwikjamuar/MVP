package ritwik.sample.mvp.application;

import javax.inject.Singleton;

import dagger.Component;

import ritwik.sample.mvp.login.LoginActivity;
import ritwik.sample.mvp.login.LoginModule;

@Singleton @Component ( modules = { ApplicationModule.class, LoginModule.class } ) public interface ApplicationComponent {
	void inject ( LoginActivity target );
}