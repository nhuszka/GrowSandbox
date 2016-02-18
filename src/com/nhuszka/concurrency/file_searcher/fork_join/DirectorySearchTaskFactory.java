package com.nhuszka.concurrency.file_searcher.fork_join;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.nhuszka.concurrency.file_searcher.StartForkJoinMultiThreadFileSearcher;

public class DirectorySearchTaskFactory {
	
	private final Collection<File> files;
	private final File file;
	
	public DirectorySearchTaskFactory(Collection<File> searchResults, File file) {
		this.files = searchResults;
		this.file = file;
	}
	
	public Collection<SearchFileTask> createSubTasks() {
		Collection<SearchFileTask> subTasks = new ArrayList<>();
		for (File subFile : getSubFiles(file)) {
			subTasks.add(new SearchFileTask(files, subFile));
		}
		for (File dir : getSubDirectories(file)) {
			subTasks.add(new SearchFileTask(files, dir));
		}
		return subTasks;
	}
	
	private Collection<File> getSubFiles(File file) {
		return getSubElements(file, createFileFilter());
	}
	
	private Collection<File> getSubDirectories(File file) {
		return getSubElements(file, item -> item.isDirectory());
	}
	
	private Collection<File> getSubElements(File file, FileFilter filtering) {
		return Arrays.asList(file.listFiles(filtering));
	}

	private FileFilter createFileFilter() {
		return file -> !file.isDirectory() && file.getName().endsWith(StartForkJoinMultiThreadFileSearcher.SEARCH_EXTENSION);
	}
}
