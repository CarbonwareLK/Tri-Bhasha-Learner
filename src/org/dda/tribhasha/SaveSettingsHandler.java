package org.dda.tribhasha;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyStore.LoadStoreParameter;
import java.util.Calendar;
import java.util.Properties;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SaveSettingsHandler {

	private static final String FILENAME = "dewdurapixamalan_opt_1.properties";
	private static final String tag = SaveSettingsHandler.class.getName();
	private final Properties properties = new Properties();
	private Activity activity;

	

	/**
	 * 
	 * Use this for save values
	 * 
	 * 
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void saveValues(String key, String value, Activity activity)
			throws IOException {
		this.activity = activity;
		FileOutputStream outputStream = activity.openFileOutput(FILENAME,
				Context.MODE_APPEND);
		properties.setProperty(key, value);
		properties.store(outputStream, Calendar.getInstance().getTime()
				.toString());

	}

	/**
	 * Use this for get saved values
	 * 
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	public String getValues(String key,String defaultValue ,Activity activity) throws IOException {
		this.activity=activity;
		String value = "0";
		try {
			FileInputStream fileInputStream = activity.openFileInput(FILENAME);
			properties.load(fileInputStream);
			value = properties.getProperty(key,defaultValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}

}
