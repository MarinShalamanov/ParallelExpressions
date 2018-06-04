package com.marinshalamanov.expr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.marinshalamanov.expr.ast.AST;
import com.marinshalamanov.expr.ast.ASTNode;

public class ASTExecutor {
	public BigInteger compute(AST ast, int numThreads) {
		List<ASTNode> topo= ast.topologicalSort();
		
		ConcurrentLinkedQueue<ASTNode> queue 
			= new ConcurrentLinkedQueue<>(topo);
		
		List<Worker> workers = new ArrayList<>();
		
		for(int i = 0; i < numThreads; i++) {
			String workerName = Integer.toString(i);
			Worker worker = new Worker(workerName, queue, this);
			workers.add(worker);
			new Thread(worker).start();
		}
		
		
		synchronized(this) {
		while(!hasAllFinished(workers)) {
			try {		
				wait();
				System.out.println("Notified");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}
		
		System.out.println("All workers finished.");
		
		return ast.getRoot().calc();
	}
	
	private boolean hasAllFinished(List<Worker> workers) {
		for(Worker w : workers)
			if(!w.hasFinished())
				return false;
		
		return true;
	}
}
