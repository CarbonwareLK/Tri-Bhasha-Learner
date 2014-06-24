package org.dda.tribhasha;

import java.io.IOException;
import java.util.ArrayList;

import org.dda.tribhasha.R.array;
import org.dda.tribhasha.list.adapter.ExpandableListAdapter;
import org.dda.tribhasha.list.data.StringValueController;
import org.dda.tribhasha.list.data.Word;
import org.dda.tribhasha.list.data.WordCreator;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.style.BulletSpan;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class Tri_Bhasha_Learner_Activity extends Activity implements Runnable {
	private static final String tag = Tri_Bhasha_Learner_Activity.class
			.getName();
	private String option_key;
	private SaveSettingsHandler settingsHandler = new SaveSettingsHandler();

	private int selectedItem = 0;

	private ExpandableListAdapter adapter;
	//
	private StringValueController stringValueController;

	public Tri_Bhasha_Learner_Activity() {
		stringValueController = new StringValueController();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		    AdView adView = (AdView) this.findViewById(R.id.adView);
		    AdRequest adRequest = new AdRequest.Builder().build();
		    adView.loadAd(adRequest);
		
		option_key = getString(R.string.opt_key_word);

		try {

			getSelectedValue();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Retrive the ExpandableListView from the layout
		ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);

		listView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent,
					View convertView, int groupPosition, int childPosition,
					long arg4) {

				Word word = (Word) adapter.getChild(groupPosition,
						childPosition);

				Intent intentNext = new Intent(
						Tri_Bhasha_Learner_Activity.this,
						LocalizedTestApp.class);

				intentNext.putExtra("word", word);

				startActivity(intentNext);

				return false;
			}
		});

		listView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1,
					int arg2, long arg3) {
				return false;
			}
		});

		// Initialize the adapter with blank groups and children
		// We will be adding children on a thread, and then update the ListView
		adapter = new ExpandableListAdapter(this, new ArrayList<String>(),
				new ArrayList<ArrayList<Word>>());

		// Set this blank adapter to the list view
		listView.setAdapter(adapter);

		// This thread randomly generates some vehicle types
		// At an interval of every 2 seconds
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		final String[] ITEMS = getResources().getStringArray(R.array.keymap);
		int count = 0;

		while (count != ITEMS.length) {

			try {
				adapter.addItem(WordCreator.createWord(ITEMS[count],
						getResources()));

			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Notify the adapter
			handler.sendEmptyMessage(1);

			count++;

		}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			adapter.notifyDataSetChanged();
			super.handleMessage(msg);
		}

	};

	private int getSelectedValue() throws IOException {
		String value = settingsHandler.getValues(option_key, "0", this);
		selectedItem = Integer.parseInt(value);
		return selectedItem;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection

		switch (item.getItemId()) {
		case R.id.menuitem_selectlang: {

			try {
				dialogOptionBuilder();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		}
		case R.id.menuitem_aboutus: {
			dialogAboutBuilder();
			return true;
		}

		case R.id.menuitem_rateus: {
			rate();
			return true;
		}

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	public void rate() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri
				.parse("market://details?id=org.dda.tribhasha"));
		startActivity(intent);
	}

	public void dialogOptionBuilder() throws IOException {

		final CharSequence[] items = { getString(R.string.opdialog_rb_sinhala),
				getString(R.string.opdialog_rb_tamil) };

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Your Mother Language");
		builder.setSingleChoiceItems(items, getSelectedValue(),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

						try {
							settingsHandler.saveValues(option_key, item + "",
									Tri_Bhasha_Learner_Activity.this);

							dialog.dismiss();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public void dialogAboutBuilder() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("About Us");
		builder.setMessage(getString(R.string.aboutus));
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(this, R.style.CustomTheme));
		builder.setTitle(R.string.app_name);
		builder.setMessage("Are you sure you want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Tri_Bhasha_Learner_Activity.this.finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

}
