package com.nhuszka.fun;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileDuplicationFinder {

	Map<Long, List<File>> filesByLength = new HashMap<Long, List<File>>();

	public static void main(String[] args) {
		String root = "d:\\Pictures"; // folder to search for file duplications
		new FileDuplicationFinder().start(root);
	}

	public void start(String root) {
		fillFilesByLength(new File(root));

		List<String> duplicationsPaths = computeDuplicationsPaths();
		System.out.println("Number of duplications: " + duplicationsPaths.size());
		
		Collections.sort(duplicationsPaths);
		for (String path : duplicationsPaths) {
			System.out.println(path);
		}
	}
	
	private void fillFilesByLength(final File folder) {
		for (final File fule : folder.listFiles()) {
			if (fule.isDirectory()) {
				fillFilesByLength(fule);
			} else {
				categorizeByLength(fule);
			}
		}
	}

	private void categorizeByLength(final File file) {
		long length = file.length();
		List<File> filesWithSameLength = !filesByLength.containsKey(length)
				? new ArrayList<>()
				: filesByLength.get(length);
		filesWithSameLength.add(file);
		filesByLength.put(length, filesWithSameLength);
	}

	private List<String> computeDuplicationsPaths() {
		List<String> paths = new ArrayList<>();
		for (Entry<Long, List<File>> entry : filesByLength.entrySet()) {
			List<File> filesWithSameLength = entry.getValue();
			if (filesWithSameLength.size() > 1) {
				String path = filesWithSameLength.toString();
				path = path.replaceAll("\\[", "");
				path = path.replaceAll("\\]", "");

				paths.add(path);
			}
		}
		return paths;
	}
}
