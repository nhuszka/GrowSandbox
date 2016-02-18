package com.nhuszka.concurrency.file_searcher.recursive_single_thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.nhuszka.concurrency.file_searcher.StartFileSearcher;

public class ConcreteFileSearcher implements Searcher {

	@Override
	public Collection<File> search(File file) {
		Collection<File> result = new ArrayList<>();
		if (isMatch(file)) {
			result.add(file);
		}
		return result;
	}

	private boolean isMatch(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			return reader
					.lines()
					.anyMatch(item -> item.contains(StartFileSearcher.SEARCH_TEXT));
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
