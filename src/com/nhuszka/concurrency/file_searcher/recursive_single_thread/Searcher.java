package com.nhuszka.concurrency.file_searcher.recursive_single_thread;

import java.io.File;
import java.util.Collection;

public interface Searcher {

	Collection<File> search(File file);
}
