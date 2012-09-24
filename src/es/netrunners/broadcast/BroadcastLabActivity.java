package es.netrunners.broadcast;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadcastLabActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btnSetAlarm = (Button) findViewById(R.id.btnsetAlarm);
		btnSetAlarm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.SECOND, 15);

				Intent intent = new Intent(getApplicationContext(),
						AlarmReceiver.class);
				intent.setAction("Start");
				PendingIntent sender = PendingIntent.getBroadcast(
						getApplicationContext(), 0, intent, 0);

				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
						sender);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (this.getIntent().getExtras() != null) {
			Boolean notif = getIntent().getExtras().getBoolean("Notification");
			NotificationManager nm;
			nm = (NotificationManager) this
					.getSystemService(Context.NOTIFICATION_SERVICE);
			if (notif != null && notif)
				nm.cancel(1);
		}
	}
}