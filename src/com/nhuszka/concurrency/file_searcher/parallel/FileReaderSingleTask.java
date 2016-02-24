package com.nhuszka.concurrency.file_searcher.parallel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

import com.nhuszka.concurrency.file_searcher.StartParallelFileSearcher;
import com.nhuszka.concurrency.file_searcher.parallel.shared.FilesWithLogs;

public class FileReaderSingleTask extends AbstractFileSearcherTask implements Runnable {

	public FileReaderSingleTask(File file, FilesWithLogs filesWithLogs, CyclicBarrier barrier) {
		super(file, filesWithLogs, barrier);
	}

	@Override
	public void search() {
		if (isMatch(file)) {
			StartParallelFileSearcher.log("Added: " + file.getAbsolutePath(), filesWithLogs);
			filesWithLogs.add(file);
		} else {
			StartParallelFileSearcher.log("Skipped: " + file.getAbsolutePath(), filesWithLogs);
		}
		signDone();
	}

	private boolean isMatch(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			return reader
					.lines()
					.anyMatch(item -> item.contains(StartParallelFileSearcher.SEARCH_TEXT));
		} catch (FileNotFoundException e) {
			// TODO nicer exception handling
			e.printStackTrace();
		} catch (IOException e) {
			// TODO nicer exception handling
			e.printStackTrace();
		}
		return false;
	}
}
