package com.nhuszka.concurrency.file_searcher;

import java.io.File;
import java.util.Collection;

import com.nhuszka.concurrency.file_searcher.recursive_single_thread.FileSearcher;

public class StartFileSearcher {

	// TODO inputs from web
	public static final String SEARCH_TEXT = "aaa";
	public static final String SEARCH_EXTENSION = ".txt";

	private static final String ROOT_DIRECTORY = "D:\\Read";

	public static void main(String[] args) {
		Collection<File> files = new FileSearcher().search(new File(ROOT_DIRECTORY));
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
	}
}
