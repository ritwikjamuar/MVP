package ritwik.sample.mvp.utilities;

import android.content.Context;
import android.widget.Toast;

public class ConstantMethods {
	public static void showToastMessage ( Context context, String message ) {
		Toast.makeText ( context, message, Toast.LENGTH_SHORT ).show ();
	}
}