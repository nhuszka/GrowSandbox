package com.nhuszka.concurrency.file_searcher.algorithm;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.nhuszka.concurrency.file_searcher.StartFileSearcher;

public class DirectorySearcher implements Searcher {

	@Override
	public Collection<File> search(File file) {
		Collection<File> files = new ArrayList<>();
		for (File dir : getSubDirectories(file)) {
			files.addAll(search(dir));
		}

		for (File subFile : getSubFiles(file)) {
			files.addAll(new ConcreteFileSearcher().search(subFile));
		}
		return files;
	}

	private Collection<File> getSubDirectories(File file) {
		return getSubElements(file, item -> item.isDirectory());
	}

	private Collection<File> getSubFiles(File file) {
		return getSubElements(file, createFileFilter());
	}

	private Collection<File> getSubElements(File file, FileFilter filtering) {
		return Arrays.asList(file.listFiles(filtering));
	}

	private FileFilter createFileFilter() {
		return file -> !file.isDirectory() && file.getName().endsWith(StartFileSearcher.SEARCH_EXTENSION);
	}
}
