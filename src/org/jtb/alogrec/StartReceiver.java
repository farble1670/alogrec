package org.jtb.alogrec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		LogFilePref lfp = new LogFilePref(context);
		if (!lfp.isSet()) {
			return;
		}

		PartialLock.acquire(context);
		Intent svcIntent = new Intent(context, RecordService.class);
		svcIntent.putExtra(RecordService.EXTRA_START_RECORD, true);
		svcIntent.putExtra(RecordService.EXTRA_LOG_FILE, lfp.toString());
		context.startService(svcIntent);
	}

}
