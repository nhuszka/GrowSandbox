package com.nhuszka.concurrency.file_searcher.fork_join;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.RecursiveAction;

import com.nhuszka.concurrency.file_searcher.StartForkJoinMultiThreadFileSearcher;

public class SearchFileTask extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	private final FilesWithLogs filesWithLogs;
	private final File file;


	public SearchFileTask(FilesWithLogs filesWithLogs, File file) {
		this.filesWithLogs = filesWithLogs;
		this.file = file;
	}

	@Override
	protected void compute() {
		if (file.isFile()) {
			if (isMatch(file)) {
				filesWithLogs.add(file);
			}
		} else { // directory
			DirectorySearchTaskFactory subTaskFactory = new DirectorySearchTaskFactory(filesWithLogs, file);
			invokeAll(subTaskFactory.createSubTasks());
		}
	}

	private boolean isMatch(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			return reader
					.lines()
					.anyMatch(item -> item.contains(StartForkJoinMultiThreadFileSearcher.SEARCH_TEXT));
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