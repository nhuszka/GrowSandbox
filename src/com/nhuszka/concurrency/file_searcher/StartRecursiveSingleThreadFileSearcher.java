package com.nhuszka.concurrency.file_searcher;

import java.io.File;
import java.util.Collection;

import com.nhuszka.concurrency.file_searcher.recursive_single_thread.FileSearcher;

public class StartRecursiveSingleThreadFileSearcher {

	// TODO inputs from web
	public static final String SEARCH_TEXT = "ez";
	public static final String SEARCH_EXTENSION = ".mp3";

	private static final String ROOT_DIRECTORY = "D:\\English";

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Collection<File> files = new FileSearcher().search(new File(ROOT_DIRECTORY));
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
		System.out.println("TIME (ms): " + (System.currentTimeMillis() - startTime));
	}
}
