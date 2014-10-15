package com.example.midterm.review;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	public static final String TAG = MainActivity.class.getSimpleName();
	public static final String KEY_NUM_TICKETS = "midterm.review.key_num_tickets";
	private static final String KEY_TOTAL_COST = "midterm.review.key_total_cost";
	private static final String KEY_IS_ORDER = "midterm.review.key_is_order";
	private static final float UNIT_COST = 9.99f;

	Button buttonProceedCheckout;
	Button buttonOrder;
	TextView textCostTotal;
	Spinner spinnerNumTickets;

	private int numTickets;
	private float totalCost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");
		setContentView(R.layout.activity_main);
		getReferences();
		setListeners();
		if (savedInstanceState != null) {
			numTickets = savedInstanceState.getInt(KEY_NUM_TICKETS, 1);
			totalCost = savedInstanceState.getFloat(KEY_TOTAL_COST, UNIT_COST);
			if (savedInstanceState.getBoolean(KEY_IS_ORDER, false)) {
				buttonProceedCheckout.setVisibility(View.GONE);
				buttonOrder.setVisibility(View.VISIBLE);
			}
			
		} else {
			numTickets = 1;
			totalCost = UNIT_COST;
		}
		updateScreen();

	}

	private void getReferences() {
		Log.d(TAG, "getReferences()");
		buttonProceedCheckout = (Button) findViewById(R.id.buttonProceedCheckout);
		buttonOrder = (Button) findViewById(R.id.buttonOrder);
		textCostTotal = (TextView) findViewById(R.id.textCostTotal);
		spinnerNumTickets = (Spinner) findViewById(R.id.spinnerNumTickets);
	}

	private void setListeners() {
		spinnerNumTickets
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						Log.d(TAG,
								"onItemSelected Spinner Listener, position: "
										+ position);
						numTickets = position + 1;
						totalCost = (float) UNIT_COST * numTickets;
						updateScreen();

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}

				});

		buttonProceedCheckout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, ConfirmActivity.class);
				i.putExtra(KEY_NUM_TICKETS, numTickets);
				startActivityForResult(i, 1);
				

			}
		});
		
		buttonOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), R.string.text_successful_purchase, Toast.LENGTH_SHORT).show();
				resetLayout();
				
				
			}
		});

	}

	private void updateScreen() {
		Log.d(TAG, "updateScreen()");
		spinnerNumTickets.setSelection(numTickets-1);
		textCostTotal.setText("$" + String.format("%.2f", totalCost));

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
		outState.putInt(KEY_NUM_TICKETS, numTickets);
		outState.putFloat(KEY_TOTAL_COST, totalCost);
		if (buttonOrder.isShown()) {
			outState.putBoolean(KEY_IS_ORDER, true);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.d(TAG, "onActivityResult");
		
		if (resultCode == RESULT_OK) {
			buttonProceedCheckout.setVisibility(View.GONE);
			buttonOrder.setVisibility(View.VISIBLE);
		}
	}
	
	private void resetLayout() {
		numTickets = 1;
		totalCost = UNIT_COST;
		buttonOrder.setVisibility(View.GONE);
		buttonProceedCheckout.setVisibility(View.VISIBLE);
		updateScreen();
	}


}
