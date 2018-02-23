package ritwik.sample.mvp;

import org.junit.Before;
import org.junit.Test;

import ritwik.sample.mvp.login.LoginActivityMVP;
import ritwik.sample.mvp.login.LoginActivityPresenter;
import ritwik.sample.mvp.login.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterTests {
	private LoginActivityMVP.Model mMockingModel;
	private LoginActivityMVP.View mMockingView;
	private LoginActivityPresenter mMockingPresenter;
	private User user;

	@Before public void setup () {
		mMockingModel = mock ( LoginActivityMVP.Model.class );
		user = new User ( "Black", "Panther" );
		when ( mMockingModel.getUser () ).thenReturn ( user );
		mMockingView = mock ( LoginActivityMVP.View.class );
		mMockingPresenter = new LoginActivityPresenter ( mMockingModel );
		mMockingPresenter.setView ( mMockingView );
	}

	/*@Test public void noInteractionWithView () {
		mMockingPresenter.getCurrentUser ();
		verifyZeroInteractions ( mMockingView );
	}*/

	@Test public void loadTheUserFromRepositoryWhenValidUserIsPresent () {
		when ( mMockingModel.getUser () ).thenReturn ( user );
		mMockingPresenter.getCurrentUser ();

		// Verify View Interactions.
		verify ( mMockingView, times (1 ) ).setFirstName ( "Black" );
		verify ( mMockingView, times ( 1 ) ).setLastName ( "Panther" );
		verify ( mMockingView, never () ).showInputError ();
	}

	@Test public void shouldShowErrorMessageWhenUserIsNull () {
		// Set-up Model mock.
		when ( mMockingModel.getUser () ).thenReturn ( null );
		mMockingPresenter.getCurrentUser ();

		// Verify Model Interactions.
		verify ( mMockingModel, times ( 1 ) ).getUser ();

		// Verify View Interactions.
		verify ( mMockingView, never () ).setFirstName ( "Black" );
		verify ( mMockingView, never () ).setLastName ( "Panther" );
		verify ( mMockingView, times ( 1 ) ).showUserNotAvailable ();
	}

	@Test public void shouldCreateErrorMessageIfFieldsAreEmpty () {
		// Set-up View mock.
		when ( mMockingView.getFirstName () ).thenReturn ( "" );

		// Save the user.
		mMockingPresenter.saveUser ();

		// Verify View Interactions.
		verify ( mMockingView, times ( 1 ) ).getFirstName ();
		/*verify ( mMockingView, never () ).getLastName ();*/
		verify ( mMockingView, times ( 1 ) ).showInputError ();

		when ( mMockingView.getFirstName () ).thenReturn ( "Bruce" );
		when ( mMockingView.getLastName () ).thenReturn ( "" );

		// Save the user.
		mMockingPresenter.saveUser ();

		// Verify View Interactions.
		verify ( mMockingView, times ( 2 ) ).getFirstName ();
		verify ( mMockingView, times ( 2 ) ).getLastName ();
		verify ( mMockingView, times ( 2 ) ).showInputError ();
	}
}