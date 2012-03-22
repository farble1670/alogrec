package org.jtb.alogrec;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;

class LogFile {
	private static final SimpleDateFormat LOG_FILE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");
	private static final File DIR = new File(
			Environment.getExternalStorageDirectory(), "alogrec");

	private File file;

	LogFile() {
		file = new File(DIR, "alogrec." + LOG_FILE_FORMAT.format(new Date())
				+ ".log");
	}

	void create() throws IOException {
		boolean exists = DIR.exists();
		boolean mk = DIR.mkdirs();
		
		if (!DIR.exists() && !DIR.mkdirs()) {
			throw new IOException("could not establish log directory: " + DIR);
		}
		if (!file.createNewFile()) {
			throw new IOException("could not create log file: " + file);
		}
	}

	File getFile() {
		return file;
	}

	@Override
	public String toString() {
		return file.toString();
	}
}
