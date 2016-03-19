package com.nhuszka.io.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class StreamsDemo {

	public static void main(String[] args) {
		if (args.length == 1) {
			runDemo(args[0]);
		}
	}

	private static void runDemo(String filePath) {
		try {
			StreamsDemo streams = new StreamsDemo();
			streams.demoInputOutputStream(filePath);
			streams.demoWriterReader(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void demoInputOutputStream(String filePath) throws IOException {
		try (OutputStream outputStream = new FileOutputStream(filePath)) {
			outputStream.write("stream".getBytes());
		}
		
		try (InputStream inputStream = new FileInputStream(filePath)) {
			int data = inputStream.read();
			while (data != -1) {
				System.out.println((char)data);
				data = inputStream.read();
			}
		}
	}

	private void demoWriterReader(String filePath) throws IOException {
		try (Writer writer = new FileWriter(filePath)) {
			writer.write(" rw");
		}
		
		try (Reader reader = new FileReader(filePath)) {
			int data = reader.read();
			while (data != -1) {
				System.out.println((char)data);
				data = reader.read();
			}
		}
	}
}
