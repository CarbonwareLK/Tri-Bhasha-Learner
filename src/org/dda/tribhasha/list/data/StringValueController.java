package org.dda.tribhasha.list.data;

import java.lang.reflect.Field;

import org.dda.tribhasha.R;
import org.dda.tribhasha.R.array;
import org.dda.tribhasha.R.string;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

public class StringValueController {

	private static int nextID = 0;
	public static final String LOCAL_ENG = "_en";
	public static final String LOCAL_SINHALA = "_si";
	public static final String LOCAL_TAMIL = "_ta";
	public static final String LOCAL_TAMIL_P = "_tap";
	public static final String LOCAL_SINHALA_P = "_sip";
	public static final String LOCAL_GROUP = "grp";
	//
	private static final String tag = StringValueController.class.getName()
			+ " ";

	/***
	 * 
	 * Get Next Key
	 * 
	 * @param resources
	 * @param next
	 * @return
	 */
	public String getnextValue(Resources resources, int next) {
		String keyArray[] = resources.getStringArray(R.array.keymap);

		String selectedKey = keyArray[next];
		//Log.i(tag + 38, selectedKey);

		return selectedKey;
	}

	/***
	 * 
	 * For get Localized value form given key and local
	 * 
	 * @param resources
	 * @param key
	 * @param local
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public String getValue(Resources resources, String key, String local)
			throws IllegalArgumentException, IllegalAccessException {
		//Log.i(tag + 56, key);
		//Log.i(tag + 57, local);
		
		String returnValue = resources.getString(getIntValue(resources, key,local));

		return returnValue;
	}

	/**
	 * For get Localized value int value form given key and local
	 * 
	 * @param resources
	 * @param key
	 * @param local
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public int getIntValue(Resources resources, String key,String local)
			throws IllegalArgumentException, IllegalAccessException {
		int returnValue = 0;

		Field fields[] = R.string.class.getFields();

		for (Field field : fields) {

			String name = field.getName();

			if (name.startsWith(key) && name.endsWith(local)) {

				returnValue = field.getInt(new R.string());
				//Log.i(tag + 87, name);
				break;
			} else {
				//Log.i(tag + 90, name);
			}

		}

		return returnValue;
	}

	/**
	 * 
	 * Get Iamge Values
	 * 
	 * @param resources
	 * @param key
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public int getImageValue(Resources resources, String key)
			throws IllegalArgumentException, IllegalAccessException {
		int returnValue = 0;

		Field fields[] = R.drawable.class.getFields();

		for (Field field : fields) {

			String name = field.getName();
			Log.i(tag + "117", name);
			Log.i(tag+"118",key);

			if (name.startsWith(LOCAL_GROUP) && name.endsWith(key)) {

				returnValue = field.getInt(new R.drawable());
				//Log.i(tag + 118, name);
				break;
			} else {
				//Log.i(tag + 126, name);
			}

		}

		return returnValue;
	}

	/**
	 * 
	 * 
	 * Get all Localized Values as a string
	 * 
	 * @param resources
	 * @param local
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public String[] getArrayForSelectedLocal(Resources resources, String local)
			throws IllegalArgumentException, IllegalAccessException {

		String array[] = resources.getStringArray(R.array.keymap);
		String values[] = new String[array.length];

		for (int i = 0; i < values.length; i++) {
			String key = array[i];
			String value = getValue(resources, key, local);
			values[i] = value;
		}

		return values;
	}

	public String getStringValue(Resources resources, String value) throws IllegalArgumentException, IllegalAccessException {

		String reValue = null;
		Field fields[] = R.string.class.getFields();

		for (Field field : fields) {

			String name = field.getName();
			//Log.i(tag + "168", name);
			//Log.i(tag+"169",value);

			if (name.equals(value)) {

				int returnValue = field.getInt(new R.drawable());
				reValue=resources.getString(returnValue);
				//Log.i(tag + 175, name);
				break;
			} else {
				//Log.i(tag + 178, name);
			}

		}
		return reValue;
	}

	
}
