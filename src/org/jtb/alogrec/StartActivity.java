package org.jtb.alogrec;

import java.io.File;

import org.jtb.alogrec.donate.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends Activity {
	private Button startButton;
	private Button cancelButton;
	private LogFilePref logFilePref;
	private TextView logFileText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start);
		logFilePref = new LogFilePref(this);

		startButton = (Button) this.findViewById(R.id.start_button);
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				PartialLock.acquire(StartActivity.this);
				Intent svcIntent = new Intent(StartActivity.this,
						RecordService.class);
				svcIntent.putExtra(RecordService.EXTRA_START_RECORD, true);
				svcIntent.putExtra(RecordService.EXTRA_LOG_FILE,
						logFilePref.toString());
				startService(svcIntent);

				finish();
			}
		});
		cancelButton = (Button) this.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LogFilePref lfp = new LogFilePref(StartActivity.this);
				new File(lfp.toString()).delete();
				lfp.clearFile();
				finish();
			}
		});

		logFileText = (TextView) this.findViewById(R.id.log_file_text);
		logFileText.setText(logFilePref.getFile().toString());
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

}