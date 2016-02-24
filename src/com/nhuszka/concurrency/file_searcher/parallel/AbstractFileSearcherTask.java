package com.nhuszka.concurrency.file_searcher.parallel;

import java.io.File;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.nhuszka.concurrency.file_searcher.parallel.shared.FilesWithLogs;

public abstract class AbstractFileSearcherTask implements Runnable {

	protected final File file;
	protected final FilesWithLogs filesWithLogs;
	protected final CyclicBarrier barrier;

	protected AbstractFileSearcherTask(File file, FilesWithLogs filesWithLogs, CyclicBarrier barrier) {
		this.file = file;
		this.filesWithLogs = filesWithLogs;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		search();
		// signDone cannot be called here!
	}

	protected void signDone() {
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public abstract void search();
}
