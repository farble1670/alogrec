package org.jtb.alogrec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import android.content.Context;
import android.util.Log;

public class LogcatRecorder implements Runnable {

	private boolean running = false;
	private Writer writer;
	private boolean prepared = false;

	public LogcatRecorder(Context context, File logFile) {
		try {
			writer = new BufferedWriter(new FileWriter(logFile, true), 8192);
		} catch (IOException e) {
			Log.e("alogcat", "could not open file for writing: " + logFile);
			return;
		}

		prepared = true;
	}

	public boolean isPrepared() {
		return prepared;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		running = true;

		BufferedReader br = null;
		Process p = null;

		try {
			p = Runtime.getRuntime().exec(new String[] { "logcat" });

			br = new BufferedReader(new InputStreamReader(p.getInputStream()),
					8192);

			String line;
			while (running && (line = br.readLine()) != null) {
				writer.write(line);
				writer.write("\n");
			}
		} catch (IOException e) {
			Log.e("alogrec", "error recording log", e);
		} finally {
			running = false;

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					Log.e("alogcat", "error closing stream", e);
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					Log.e("alogcat", "error closing stream", e);
				}
			}
			if (p != null) {
				p.destroy();
			}
		}
	}

}
