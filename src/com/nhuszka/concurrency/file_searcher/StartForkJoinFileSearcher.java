package com.nhuszka.concurrency.file_searcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

import com.nhuszka.concurrency.file_searcher.fork_join.SearchFileTask;

public class StartForkJoinFileSearcher {
	
	// TODO inputs from web
	public static final String SEARCH_TEXT = "aaa";
	public static final String SEARCH_EXTENSION = ".txt";

	private static final String ROOT_DIRECTORY = "D:\\Read";
	
	public static void main(String[] args) {
		Collection<File> searchResults = new ArrayList<>();
		
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new SearchFileTask(searchResults, new File(ROOT_DIRECTORY)));
		
		for (File file : searchResults) {
			System.out.println(file.getAbsolutePath());
		}
	}
}