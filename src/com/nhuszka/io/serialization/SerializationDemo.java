package com.nhuszka.io.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationDemo {
	
	public static void main(String[] args) {
		if (args.length == 1) {
			runDemo(args[0]);
		} 
	}

	private static void runDemo(String filePath) {
		SerializationDemo serialization = new SerializationDemo();
		serialization.serialize(filePath);
		SerializableBean deserializedBean = serialization.deserialize(filePath);
		System.out.println(deserializedBean);
	}
	
	private void serialize(String filePath) {
		try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			stream.writeObject(createSerializableBean());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SerializableBean createSerializableBean() {
		SerializableBean bean = new SerializableBean();
		bean.setStr("This must be serializable");
		bean.setNumber(11);
		return bean;
	}
	
	private SerializableBean deserialize(String filePath) {
		try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filePath))) {
			return (SerializableBean) stream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
