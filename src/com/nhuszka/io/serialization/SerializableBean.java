package com.nhuszka.io.serialization;

import java.io.Serializable;

public class SerializableBean implements Serializable {

	private static final long serialVersionUID = -6150416051460667400L;
	
	private String str;
	private transient int number; // this data won't be serialized
	
	public String getStr() {
		return str;
	}
	
	public void setStr(String str) {
		this.str = str;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "SerializableBean [str=" + str + ", number=" + number + "]";
	}
}
