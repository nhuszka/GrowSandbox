package com.nhuszka.concurrency.file_searcher;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.nhuszka.concurrency.file_searcher.parallel.GenericFileSearcherTask;
import com.nhuszka.concurrency.file_searcher.parallel.shared.FilesWithLogs;

public class StartParallelFileSearcher {

	// TODO inputs from web
	public static final String SEARCH_TEXT = "aaa";
	public static final String SEARCH_EXTENSION = ".txt";

	private static final String ROOT_DIRECTORY = "D:\\Read";

	public static void log(String str, FilesWithLogs files) {
		files.log(str);
	}

	public static void main(String[] args) {
		final FilesWithLogs filesWithLogs = createSharedFileContainer();
		final CyclicBarrier barrier = new CyclicBarrier(2, getReadFilesAndLogsAction(filesWithLogs));

		log("Start MAIN thread to search in " + ROOT_DIRECTORY + "\n", filesWithLogs);

		File root = new File(ROOT_DIRECTORY);
		GenericFileSearcherTask rootSearcher = new GenericFileSearcherTask(root, filesWithLogs, barrier);
		rootSearcher.search();
	}

	private static FilesWithLogs createSharedFileContainer() {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		return new FilesWithLogs(new ArrayList<>(), lock);
	}

	private static Runnable getReadFilesAndLogsAction(final FilesWithLogs filesWithLogs) {
		return () -> {
			log("\nWohoo! MAIN thread gets everything!\n", filesWithLogs);

			for (String log : filesWithLogs.getLogs()) {
				System.out.println(log);
			}

			System.out.println("--- Result files start ---");
			for (File file : filesWithLogs.getFiles()) {
				System.out.println(file.getAbsolutePath());
			}
			System.out.println("--- Result files end ---");

			System.out.println("\nMAIN thread exits...");
		};
	}
}
