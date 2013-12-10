package com.google.zxing.client.android.receive;

/**
 * 开机事件接收器
 * 
 * @author Administrator
 * 
 */
import com.google.zxing.client.android.ui.LoginActivity;
import com.google.zxing.client.android.ui.PreferenceAcitivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class BootReceive extends BroadcastReceiver {

	public final String TAG = BootReceive.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();
		System.out.println("BootReceive :is BootReceive" + action);
		// PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
		// new Intent(context, LoginActivity.class),
		// PendingIntent.FLAG_UPDATE_CURRENT);
		//
		// long time = SystemClock.elapsedRealtime();
		// AlarmManager alarmManager = (AlarmManager) context
		// .getSystemService(Context.ALARM_SERVICE);

		// alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, time,
		// pendingIntent);
		// alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, time,
		// 5 * 1000, pendingIntent);
		if (action.equals(Intent.ACTION_BOOT_COMPLETED)) { //
			// Intent serviceIntent = new Intent(context, LoginActivity.class);
			// context.startService(serviceIntent);
			// context.startActivity(serviceIntent);
			// try {
			// Thread.sleep(3000);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			// Intent intent1 = new Intent(context, PreferenceAcitivity.class);
			// context.startActivity(intent1);
		}
	}

}