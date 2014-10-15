package com.example.midterm.review;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActivity extends Activity {
	
	private final static String TAG = ConfirmActivity.class.getSimpleName();
	
	Button buttonConfirm;
	Button buttonCancel;
	TextView textQuantity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");
		setContentView(R.layout.activity_confirm);
		getReferences();
		setListeners();
		textQuantity.setText(String.format("%d", getIntent().getIntExtra(MainActivity.KEY_NUM_TICKETS, 1)));
		
	}
	
	private void getReferences() {
		Log.d(TAG, "getReferences()");
		buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
		buttonCancel = (Button) findViewById(R.id.buttonCancel);
		textQuantity = (TextView) findViewById(R.id.textQuantity);
		
	}
 	private void setListeners() {
 		
 		buttonConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "buttonConfirm listener");
				setResult(RESULT_OK);
				finish();
			}
		});
 		
 		buttonCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "buttonCancel listener");
				setResult(RESULT_CANCELED);
				finish();
				
			}
		});
		
	}
}
