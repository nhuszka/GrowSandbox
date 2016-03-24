package com.nhuszka.io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {
	
	public static void main(String[] args) {
		try {
			new ChannelDemo().run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void run() throws FileNotFoundException, IOException {
		RandomAccessFile aFile = new RandomAccessFile("d:\\error_handling_info.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(110);

		int bytesRead = inChannel.read(buffer);  //read into buffer
		while (bytesRead != -1) {

			System.out.println("\nRead\n" + bytesRead);
			buffer.flip(); // switch buffer between reading/writing mode

			while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get()); // read 1 byte at a time
			}

			buffer.clear(); // to make it read for writing, we clear the whole buffer
			
//			buffer.compact();
// compact: similar to clear, but clears only the data we've already read
// unread data is moved to the beginning of buffer			

			bytesRead = inChannel.read(buffer);
			
// another way of writing
//			buffer.put((byte) 127); 
		}
		aFile.close();
	}
}
