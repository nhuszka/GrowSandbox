package com.nhuszka.concurrency.file_searcher.recursive_single_thread;

import java.io.File;
import java.util.Collection;

public class FileSearcher implements Searcher {

	@Override
	public Collection<File> search(File file) {
		if (file.isDirectory()) {
			return new DirectorySearcher().search(file);
		}
		return new ConcreteFileSearcher().search(file);
	}
}
