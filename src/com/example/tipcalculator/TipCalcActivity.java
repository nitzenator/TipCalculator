package com.example.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class TipCalcActivity extends Activity {
	private static final String TAG = "TipCalcActivity";
	private static final DecimalFormat DEC_FORMAT = new DecimalFormat("$##.##");
	private static final double DEFAULT_CHECK_AMOUNT = 50.00;
	private static final double DEFAULT_TIP_PERCENT = 0.15;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calc);
		TextView checkAmntView = (TextView) findViewById(R.id.check_amount);
		TextView tipAmntView = (TextView) findViewById(R.id.tip_amount);
		setCheckAmount(checkAmntView, DEFAULT_CHECK_AMOUNT);
		setTipAmount(tipAmntView, DEFAULT_TIP_PERCENT * DEFAULT_CHECK_AMOUNT);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calc, menu);
		return true;
	}
	
	private void setCheckAmount(TextView v, double amount){
		v.setText(getResources().getString(R.string.check_amount,
				Double.toString(amount)
				));
	}
	
	private void setTipAmount(TextView v, double amount){
		v.setText(
				getResources().getString(R.string.tip_amount,
						DEC_FORMAT.format(amount)
						)
		);
	}
	public void displayTip(View v){
		
		TextView tipAmntView = (TextView) findViewById(R.id.tip_amount);
		TextView checkAmntView = (TextView) findViewById(R.id.check_amount);
		double checkAmnt = 0;
		try{
			checkAmnt = Double.parseDouble(checkAmntView.getText().toString());
		}catch(NumberFormatException nfe){
			//Not a valid number
			Toast toast = Toast.makeText(this, "Not a valid amount", Toast.LENGTH_LONG);
			toast.show();
			
		}
		
		switch (v.getId()) {
			case R.id.percent_10:
				setTipAmount(tipAmntView, 0.10 * checkAmnt);
				break;
			
			case R.id.percent_15:
				setTipAmount(tipAmntView, 0.15 * checkAmnt);
				break;
			
			case R.id.percent_20:
				setTipAmount(tipAmntView, 0.20 * checkAmnt);
				break;
			
			case R.id.submit:
				double tipPercent = 0;
				TextView tipPercentView = (TextView) findViewById(R.id.percent_tip);
				try{
					tipPercent = Double.parseDouble(tipPercentView.getText().toString());
					if(tipPercent < 0 || tipPercent > 100){
						Toast toast = Toast.makeText(this, "Tip percent should be between 0 and 100", Toast.LENGTH_SHORT);
						toast.show();
					}
					else setTipAmount(tipAmntView, 0.01 * tipPercent * checkAmnt);
				}catch(NumberFormatException nfe){
					//Not a valid number
					Toast toast = Toast.makeText(this, "Not a valid tip percent", Toast.LENGTH_SHORT);
					toast.show();
				}
				
				
			default:
				break;
		}
		
	}

}
