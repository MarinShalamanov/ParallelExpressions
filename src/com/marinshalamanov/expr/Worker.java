package com.marinshalamanov.expr;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.marinshalamanov.expr.ast.ASTNode;

public class Worker implements Runnable {

	private ConcurrentLinkedQueue<ASTNode> queue;
	private Object notifyOnDone;
	private boolean finished;
	private String name;
	
	public Worker(String name, 
			ConcurrentLinkedQueue<ASTNode> queue, 
			Object notfyOnDone) {
		this.name = name;
		this.queue = queue;
		this.notifyOnDone = notfyOnDone;
		this.finished = false;
	}
	
	public boolean hasFinished() {
		return finished;
	}
	
	@Override
	public void run() {
		logMessage("Started.");
		
		while(true) {
			ASTNode node = queue.poll();
			
			if(node == null) break;
			
			// logMessage("Starting new task.");
			node.calc();
		}
		
		finished = true;
		synchronized(notifyOnDone) {
			notifyOnDone.notify();
		}
		
		logMessage("Finished.");
	}
	
	private void logMessage(String msg) {
		System.out.println("[Worker " + name + "] " + msg);
	}
}
