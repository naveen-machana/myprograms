package org.naveen.testprograms;

import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CopyFiles {
	
	private final Lock targetFolderLock = new ReentrantLock();
	
	public void getJarFiles(File root, List<File> filesToCopy) {
		if (root == null) return;
		if (root.isFile() && root.getName().endsWith(".jar")) {
			filesToCopy.add(root);
		}
		
		else {
			File[] childFiles = root.listFiles();
			if (childFiles != null) {
				for (File current : childFiles) {
					getJarFiles(current, filesToCopy);
				}
			}			
		}		
	}
	
	private class CopyTask implements Callable<Void> {
		
		private File sourceFile;
		private File targetFolder;
		
		CopyTask(File sourceFile, File targetFolder) {
			this.sourceFile = sourceFile;
			this.targetFolder = targetFolder;
		}
		
		@Override
		public Void call() throws Exception {
			try {
				targetFolderLock.lock();
				Files.copy(sourceFile.toPath(), targetFolder.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING, 
																						StandardCopyOption.COPY_ATTRIBUTES});
			} finally {
				targetFolderLock.unlock();								
			}			
			return null;
		}	
		
		public File getSourceFile() {
			return sourceFile;
		}
		
		public String getJarName() {
			return sourceFile.toPath().toString();
		}
	}
	
	public Map<Future<Void>, CopyTask> copy(String sourceFile, String targetFolder) {
		return copy(new File(sourceFile), new File(targetFolder));
	}
	
	public Map<Future<Void>, CopyTask> copy(File source, File targetFolder) {
		if (!source.exists()) {
			throw new RuntimeException("Source file/folder does not exist");
		}
		
		if (!targetFolder.exists()) {
			throw new RuntimeException("Target folder does not exist");
		}
		
		if (targetFolder.isFile()) {
			throw new RuntimeException("Target should be a folder");
		}
		
		List<File> jarfiles = new LinkedList<File>();
		getJarFiles(source, jarfiles);
		
		System.out.println("number of processors available: " + Runtime.getRuntime().availableProcessors());
		
		ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		Map<Future<Void>, CopyTask> copyTasksSubmitted = new IdentityHashMap<>();
		for (File file : jarfiles) {
			CopyTask task = new CopyTask(file, targetFolder);
			Future<Void> taskResult = threadpool.submit(task);
			copyTasksSubmitted.put(taskResult, task);
		}
		
		long filesSize = 0;
		
		for (Future<Void> task : copyTasksSubmitted.keySet()) {
			try {
				task.get();
				if (task.isDone()) {
					System.out.println(copyTasksSubmitted.get(task).getJarName() + " completed");
					filesSize += copyTasksSubmitted.get(task).getSourceFile().getTotalSpace();
				}				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		threadpool.shutdownNow();
		
		System.out.println("Total size: " + filesSize);
		
		Iterator<Future<Void>> tasksIterator = copyTasksSubmitted.keySet().iterator();
		while(tasksIterator.hasNext()) {
			Future<Void> copyTask = tasksIterator.next();
			if (copyTask.isDone()) {
				tasksIterator.remove();
			}
		}
		
		return copyTasksSubmitted;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CopyFiles c = new CopyFiles();
		Map<Future<Void>, CopyTask> failedTasks = c.copy("C:/apache-tomcat-7.0.55", "F:/jars");
		
		for (Future<Void> task : failedTasks.keySet()) {
			System.out.println(failedTasks.get(task) + " failed");
		}
	}

}
