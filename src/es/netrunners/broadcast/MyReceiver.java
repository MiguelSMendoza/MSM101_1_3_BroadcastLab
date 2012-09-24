package es.netrunners.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.provider.Settings;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String msg;
		if (isAirplaneModeOn(context))
			msg = "AIRPLANE MODE ACTIVATED !!!";
		else
			msg = "AIRPLANE MODE DEACTIVATED !!!";

		Vibrator vib = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		vib.vibrate(2000);
	}

	private static boolean isAirplaneModeOn(Context context) {
		return Settings.System.getInt(context.getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, 0) != 0;
	}

}
