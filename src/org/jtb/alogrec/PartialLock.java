package org.jtb.alogrec;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class PartialLock {
	private static PowerManager.WakeLock lock;

	private static PowerManager.WakeLock getLock(Context context) {
		if (lock == null) {
			PowerManager mgr = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);

			lock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "org.jtb.alogrec.lock");
			lock.setReferenceCounted(true);
		}
		return lock;
	}

	public static synchronized void acquire(Context context) {
		WakeLock wakeLock = getLock(context);
		if (!wakeLock.isHeld()) {
			wakeLock.acquire();
		}
	}

	public static synchronized void release() {
		if (lock == null) {
			Log
					.w("alogrec",
							"release attempted, but wake lock was null");
		} else {
			if (lock.isHeld()) {
				lock.release();
			} else {
				Log.w("alogrec",
						"release attempted, but wake lock was not held");
			}
		}
	}
}
