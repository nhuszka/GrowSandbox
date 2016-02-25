package com.nhuszka.concurrency.file_searcher;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.nhuszka.concurrency.file_searcher.fork_join.FilesWithLogs;
import com.nhuszka.concurrency.file_searcher.fork_join.SearchFileTask;

public class StartForkJoinMultiThreadFileSearcher {

	// TODO inputs from web
	public static final String SEARCH_TEXT = "ez";
	public static final String SEARCH_EXTENSION = ".mp3";

	private static final String ROOT_DIRECTORY = "D:\\English";

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		FilesWithLogs filesWithLogs = createSharedFileContainer();

		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new SearchFileTask(filesWithLogs, new File(ROOT_DIRECTORY)));

		for (File file : filesWithLogs.getFiles()) {
			System.out.println(file.getAbsolutePath());
		}
		System.out.println("TIME (ms): " + (System.currentTimeMillis() - startTime));
	}

	private static FilesWithLogs createSharedFileContainer() {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		return new FilesWithLogs(new ArrayList<>(), lock);
	}
}