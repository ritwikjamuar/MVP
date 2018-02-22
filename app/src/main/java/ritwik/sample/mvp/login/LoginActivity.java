package ritwik.sample.mvp.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import ritwik.sample.mvp.R;
import ritwik.sample.mvp.application.App;
import ritwik.sample.mvp.utilities.ConstantMethods;

public class LoginActivity
		extends AppCompatActivity
		implements LoginActivityMVP.View {
	// Views.
	@BindView ( R.id.activity_login_edit_text_first_name ) EditText mEtFirstName;
	@BindView ( R.id.activity_login_edit_text_last_name ) EditText mEtLastName;

	// Other Components.
	@Inject LoginActivityMVP.Presenter mPresenter;

	@Override protected void onCreate ( Bundle savedInstanceState ) {
		super.onCreate ( savedInstanceState );
		setContentView ( R.layout.activity_login );
		initializeComponents ();
	}

	private void initializeComponents () {
		( ( App ) getApplication () ).getComponent ().inject ( LoginActivity.this );
		ButterKnife.bind ( LoginActivity.this );
	}

	@Override protected void onResume () {
		super.onResume ();
		mPresenter.setView ( LoginActivity.this );
		mPresenter.getCurrentUser ();
	}

	@OnClick ( R.id.activity_login_relative_layout_login ) void login () { mPresenter.loginButtonClicked (); }

	@Override public String getFirstName () { return mEtFirstName.getText ().toString (); }
	@Override public String getLastName () { return mEtLastName.getText ().toString (); }
	@Override public void setFirstName ( String firstName ) { mEtFirstName.setText ( firstName ); }
	@Override public void setLastName ( String lastName ) { mEtLastName.setText ( lastName ); }

	/*@Override public void showUserNotAvailable () {
		ConstantMethods.showToastMessage ( LoginActivity.this, "User not available" );
	}*/

	@Override public void showInputError () {
		ConstantMethods.showToastMessage ( LoginActivity.this, "Error in Input" );
	}

	@Override public void showUserSavedMessage () {
		ConstantMethods.showToastMessage ( LoginActivity.this, "User Saved" );
	}
}