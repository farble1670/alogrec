package org.jtb.alogrec;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

public class RouterActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent;
		LogFilePref lfp = new LogFilePref(this);
		if (!lfp.isSet()) {
			if (!Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				intent = new Intent(this, ExtActivity.class);
			} else {
				LogFile logFile = new LogFile();
				lfp.setFile(logFile.getFile());
				try {
					logFile.create();
					intent = new Intent(this, StartActivity.class);
				} catch (IOException e) {
					intent = new Intent(this, FailActivity.class);					
				}
			}			
		} else {
			intent = new Intent(this, StopActivity.class);
		}
		startActivity(intent);
		finish();
	}

}
