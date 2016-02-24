package com.nhuszka.concurrency.file_searcher.parallel.shared;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FilesWithLogs {

	private final List<File> files;
	private final ReentrantReadWriteLock lock;
	private final Lock readLock;
	private final Lock writeLock;
	private final List<String> logs;

	public FilesWithLogs(List<File> files, ReentrantReadWriteLock lock) {
		this.files = files;
		this.lock = lock;
		readLock = lock.readLock();
		writeLock = lock.writeLock();
		logs = new ArrayList<>();
	}

	public void add(File file) {
		writeLock.lock();
		try {
			files.add(file);
		} finally {
			writeLock.unlock();
		}
	}

	public void log(String message) {
		writeLock.lock();
		try {
			logs.add(message);
		} finally {
			writeLock.unlock();
		}
	}

	public List<File> getFiles() {
		readLock.lock();
		try {
			return new ArrayList<>(files);
		} finally {
			readLock.unlock();
		}
	}

	public List<String> getLogs() {
		readLock.lock();
		try {
			return new ArrayList<>(logs);
		} finally {
			readLock.unlock();
		}
	}
}
