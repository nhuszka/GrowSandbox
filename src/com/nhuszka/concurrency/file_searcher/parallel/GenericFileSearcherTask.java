package com.nhuszka.concurrency.file_searcher.parallel;

import java.io.File;
import java.util.concurrent.CyclicBarrier;

import com.nhuszka.concurrency.file_searcher.parallel.shared.FilesWithLogs;

public class GenericFileSearcherTask extends AbstractFileSearcherTask {

	public GenericFileSearcherTask(File file, FilesWithLogs filesWithLogs, CyclicBarrier barrier) {
		super(file, filesWithLogs, barrier);
	}

	@Override
	public void search() {
		Runnable searchTask = file.isDirectory()
				? new DirectorySearcherParallelTask(file, filesWithLogs, barrier)
				: new FileReaderSingleTask(file, filesWithLogs, barrier);
				
		new Thread(searchTask).start();
		signDone();
	}
}
