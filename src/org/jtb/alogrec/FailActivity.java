package org.jtb.alogrec;

import java.io.File;

import org.jtb.alogrec.donate.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class FailActivity extends Activity {
	private TextView logFileText;
	private Button cancelButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fail);

		logFileText = (TextView) this.findViewById(R.id.dir_text);
		logFileText.setText(new LogFilePref(this).getFile().toString());
		cancelButton = (Button) this.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LogFilePref lfp = new LogFilePref(FailActivity.this);
				new File(lfp.toString()).delete();
				lfp.clearFile();
				finish();
			}
		});
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