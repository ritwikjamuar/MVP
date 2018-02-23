package ritwik.sample.mvp.login;

public interface LoginActivityMVP {
	interface View {
		String getFirstName ();
		String getLastName ();
		void setFirstName ( String firstName );
		void setLastName ( String lastName );
		void showInputError ();
		void showUserSavedMessage ();
		void showUserNotAvailable ();
	}

	interface Presenter {
		void setView ( LoginActivityMVP.View view );
		void loginButtonClicked ();
		void getCurrentUser ();
		void saveUser ();
	}

	interface Model {
		void createUser ( String name, String lastName );
		User getUser ();
	}
}