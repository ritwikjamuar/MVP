package ritwik.sample.mvp;

import org.junit.Before;
import org.junit.Test;

import ritwik.sample.mvp.login.LoginActivityMVP;
import ritwik.sample.mvp.login.LoginActivityPresenter;
import ritwik.sample.mvp.login.User;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterTests {
	LoginActivityMVP.Model mMockingModel;
	LoginActivityMVP.View mMockingView;
	LoginActivityPresenter mMockingPresenter;
	User user;

	@Before public void setup () {
		mMockingModel = mock ( LoginActivityMVP.Model.class );
		user = new User ( "Black", "Panther" );
		/*when ( mMockingModel.getUser () ).thenReturn ( user );*/
		mMockingView = mock ( LoginActivityMVP.View.class );
		mMockingPresenter = new LoginActivityPresenter ( mMockingModel );
		mMockingPresenter.setView ( mMockingView );
	}

	@Test public void noInteractionWithView () {
		mMockingPresenter.getCurrentUser ();
		verifyZeroInteractions ( mMockingView );
	}
}