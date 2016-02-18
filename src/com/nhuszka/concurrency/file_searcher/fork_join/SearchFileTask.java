package com.nhuszka.concurrency.file_searcher.fork_join;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.RecursiveAction;

import com.nhuszka.concurrency.file_searcher.StartForkJoinMultiThreadFileSearcher;

public class SearchFileTask extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	private final Collection<File> files;
	private final File file;
	
	
	public SearchFileTask(Collection<File> searchResults, File file) {
		this.files = searchResults;
		this.file = file;
	}

	@Override
	protected void compute() {
		if (file.isFile()) {
			if (isMatch(file)) {
				files.add(file);
			}
		} else { // directory
			DirectorySearchTaskFactory subTaskFactory = new DirectorySearchTaskFactory(files, file);
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