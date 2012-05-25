package org.dda.tribhasha.list.data;

import java.io.Serializable;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

public class Word implements Parcelable {

	

	private String keyname;
	private String group;
	private String group_name;
	private String key_en;
	private String key_si;
	private String key_sip;
	private String key_ta;
	private String key_tap;

	public Word(){
	}

	public Word(Parcel in) {
		readFromParcel(in);
	}

	private void readFromParcel(Parcel in) {

		this.keyname = in.readString();
		this.key_en = in.readString();
		this.key_si = in.readString();
		this.key_sip = in.readString();
		this.key_ta = in.readString();
		this.key_tap = in.readString();
		this.group = in.readString();
		this.group_name = in.readString();
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Word createFromParcel(Parcel in) {
			return new Word(in);
		}

		public Word[] newArray(int size) {
			return new Word[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(keyname);
		dest.writeString(key_en);
		dest.writeString(key_si);
		dest.writeString(key_sip);
		dest.writeString(key_ta);
		dest.writeString(key_tap);
		dest.writeString(group);
		dest.writeString(group_name);
		
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public String getKey_en() {
		return key_en;
	}

	public void setKey_en(String key_en) {
		this.key_en = key_en;
	}

	public String getKey_si() {
		return key_si;
	}

	public void setKey_si(String key_si) {
		this.key_si = key_si;
	}

	public String getKey_sip() {
		return key_sip;
	}

	public void setKey_sip(String key_sip) {
		this.key_sip = key_sip;
	}

	public String getKey_ta() {
		return key_ta;
	}

	public void setKey_ta(String key_ta) {
		this.key_ta = key_ta;
	}

	public String getKey_tap() {
		return key_tap;
	}

	public void setKey_tap(String key_tap) {
		this.key_tap = key_tap;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Word ["+" keyname=" + keyname
				+ ", group=" + group + ", group_name=" + group_name
				+ ", key_en=" + key_en + ", key_si=" + key_si + ", key_sip="
				+ key_sip + ", key_ta=" + key_ta + ", key_tap=" + key_tap + "]";
	}
	
	

}
