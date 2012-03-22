package org.jtb.alogrec;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

class LogFilePref {
	protected Context context;
	protected SharedPreferences mPrefs;

	LogFilePref(Context context) {
		this.context = context;
		mPrefs = PreferenceManager.getDefaultSharedPreferences(context);		
	}

	File getFile() {
		if (!mPrefs.contains("logFile")) {
			return null;
		}
		return new File(mPrefs.getString("logFile", null));		
	}

	void setFile(File f) {
		mPrefs.edit().putString("logFile", f.toString()).commit();
	}

	void clearFile() {
		mPrefs.edit().remove("logFile").commit();
	}

	boolean isSet() {
		return mPrefs.contains("logFile");
	}
	
	@Override
	public String toString() {
		File f = getFile();
		if (f == null) {
			return null;
		}
		return f.toString();
	}
}
