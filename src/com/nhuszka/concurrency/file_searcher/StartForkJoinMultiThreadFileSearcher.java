package com.nhuszka.concurrency.file_searcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

import com.nhuszka.concurrency.file_searcher.fork_join.SearchFileTask;

public class StartForkJoinMultiThreadFileSearcher {

	// TODO inputs from web
	public static final String SEARCH_TEXT = "ez";
	public static final String SEARCH_EXTENSION = ".mp3";

	private static final String ROOT_DIRECTORY = "D:\\English";

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Collection<File> searchResults = new ArrayList<>();

		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new SearchFileTask(searchResults, new File(ROOT_DIRECTORY)));

		for (File file : searchResults) {
			System.out.println(file.getAbsolutePath());
		}
		System.out.println("TIME (ms): " + (System.currentTimeMillis() - startTime));
	}
}