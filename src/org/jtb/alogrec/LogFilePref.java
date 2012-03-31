package org.jtb.alogrec;

import java.io.File;

import android.content.Context;
import android.content.SharedPreferences;
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
		String lf = mPrefs.getString("logFile", null);
		if (lf == null) {
			return null;
		}
		return new File(lf);		
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
