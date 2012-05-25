package org.dda.tribhasha.list.data;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.dda.tribhasha.R;

public class DynamicTextController {

	private TextView tv;
	private String text;
	private static final int DELAY=300;
	
	private Handler mHandler = new Handler();
	
	
	private View.OnClickListener mStartListener = new View.OnClickListener() {
		public void onClick(View v) {
			mHandler.removeCallbacks(mUpdateTimeTask);
			mHandler.postDelayed(mUpdateTimeTask, DELAY);
		}
	};
	

	public Runnable mUpdateTimeTask = new Runnable() {

		@Override
		public void run() {

			CharSequence textViewText = tv.getText();

			
			char[] arrys = text.toCharArray();
			char[] tvCArray = textViewText.toString().toCharArray();

			if(arrys.length!=tvCArray.length){
				tv.append(""+arrys[tvCArray.length]);
				mHandler.postDelayed(this, DELAY);
			}else{
				mHandler.removeCallbacks(this);
			}
			
			
		}
	};

	
	

	public DynamicTextController(TextView tv, String text) {
		super();
		this.tv = tv;
		this.text = text;
	}


	public TextView getTv() {
		return tv;
	}


	public String getText() {
		return text;
	}


	public View.OnClickListener getmStartListener() {
		return mStartListener;
	}


	public Runnable getmUpdateTimeTask() {
		return mUpdateTimeTask;
	}
	
	
	
	
	
}
