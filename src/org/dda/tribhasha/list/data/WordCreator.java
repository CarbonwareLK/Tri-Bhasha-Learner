package org.dda.tribhasha.list.data;

import android.content.res.Resources;

public class WordCreator {

	private static StringValueController controller;

	private WordCreator() {
		
	}
	
	public static Word createWord(String keyname,Resources resources) throws IllegalArgumentException, IllegalAccessException{
		Word word=new Word();
		controller = new StringValueController();
		word.setKeyname(keyname);
		word.setKey_en(controller.getValue(resources, keyname,
				StringValueController.LOCAL_ENG));
		word.setKey_si(controller.getValue(resources, keyname,
				StringValueController.LOCAL_SINHALA));
		word.setKey_sip(controller.getValue(resources, keyname,
				StringValueController.LOCAL_SINHALA_P));
		word.setKey_ta(controller.getValue(resources, keyname,
				StringValueController.LOCAL_TAMIL));
		word.setKey_tap( controller.getValue(resources, keyname,
				StringValueController.LOCAL_TAMIL_P));
		String group = null;
		try {
			group = controller.getValue(resources, keyname,
					StringValueController.LOCAL_GROUP);
		} catch (Exception e) {
			if(group==null){
				group="random";
			}
		}
		
		word.setGroup(group);
		word.setGroup_name(controller.getStringValue(resources, word.getGroup()));
	
		return word;
	}
	
}
