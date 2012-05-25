package org.dda.tribhasha;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.dda.tribhasha.R;
import org.dda.tribhasha.list.data.DynamicTextController;
import org.dda.tribhasha.list.data.StringValueController;
import org.dda.tribhasha.list.data.Word;

public class LocalizedTestApp extends Activity {
	/** Called when the activity is first created. */

	private DynamicTextController sinhalaTextController;
	private DynamicTextController tamilTextController;
	private DynamicTextController motherLangHelpController;
	//
	private StringValueController valueController = new StringValueController();
	private SaveSettingsHandler settingsHandler = new SaveSettingsHandler();
	private String option_key;
	private int selectedLang = 0;
	private Activity curruntActivity;
	//
	private int NEXT = 0;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.localizedtestapp);

		option_key = getString(R.string.opt_key_word);
		try {
			String value = settingsHandler.getValues(option_key, "0", this);
			selectedLang = Integer.parseInt(value);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Word viewValue = (Word) getIntent().getExtras().getParcelable("word");

		// NEXT = viewValue;

		try {
			createView(viewValue, false);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Button btNext = (Button) findViewById(R.id.button3);
		// btNext.setOnClickListener(nextButtonListner);

		// Button btPre = (Button) findViewById(R.id.button4);
		// / btPre.setOnClickListener(preButtonListner);

		curruntActivity = this;
	}

	private static final String tag = LocalizedTestApp.class.getName();

	private void createView(Word word, boolean calculated)
			throws IllegalArgumentException, IllegalAccessException {

		Log.i(tag+"82", word.toString());
		// Build English
		String englishText = word.getKey_en();
		TextView tvEnglish = (TextView) findViewById(R.id.tvone);
		tvEnglish.setText(englishText);

		// Build Sinhala
		String sinhalaText = word.getKey_si();

		TextView sinhalaTv = (TextView) findViewById(R.id.tvtwo);
		sinhalaTv.setText("");
		Typeface font = Typeface.createFromAsset(getAssets(), "amal.TTF");
		sinhalaTv.setTypeface(font);

		sinhalaTextController = new DynamicTextController(sinhalaTv,
				sinhalaText);

		// Build Tamil
		String tamilText = word.getKey_ta();
		TextView tamilTv = (TextView) findViewById(R.id.tvthree);
		tamilTv.setText("");

		Typeface tamilTypeface=Typeface.createFromAsset(getAssets(), "BhashaSETTSinhalaTamil.ttf");
        tamilTv.setTypeface(tamilTypeface);
		
		tamilTextController = new DynamicTextController(tamilTv, tamilText);

		Button button = (Button) findViewById(R.id.button1);

		// Build Helper Text View
		TextView tvHelper = (TextView) findViewById(R.id.tvfour);
		tvHelper.setText("");
		String helpText = "";
		OnClickListener motherLangTextOnClickListener = null;
		Runnable runnableMotherLang = null;
		String buttonText = "";

		/**
		 * 1 mean tamil 0 mean sinhala
		 */
		if (selectedLang == 1) {
			helpText = word.getKey_sip();
			motherLangTextOnClickListener = tamilTextController
					.getmStartListener();
			runnableMotherLang = sinhalaTextController.mUpdateTimeTask;
			buttonText = "Tamil";
			tamilTv.setTextColor(Color.parseColor("#105516"));

		} else if (selectedLang == 0) {
			helpText = word.getKey_tap();
			runnableMotherLang = tamilTextController.mUpdateTimeTask;
			motherLangTextOnClickListener = sinhalaTextController
					.getmStartListener();
			buttonText = "Sinhala";
			sinhalaTv.setTextColor(Color.parseColor("#105516"));
		}

		motherLangHelpController = new DynamicTextController(tvHelper, helpText);

		runOnUiThread(runnableMotherLang);
		runOnUiThread(motherLangHelpController.mUpdateTimeTask);
		button.setOnClickListener(motherLangTextOnClickListener);
		button.setText(buttonText);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
	}

}