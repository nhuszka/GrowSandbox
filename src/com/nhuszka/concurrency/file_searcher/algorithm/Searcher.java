package com.nhuszka.concurrency.file_searcher.algorithm;

import java.io.File;
import java.util.Collection;

public interface Searcher {

	Collection<File> search(File file);
}
