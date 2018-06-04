package com.marinshalamanov.expr.ast;

import java.util.ArrayList;
import java.util.List;

public class AST {
	private ASTNode root;
	
	public AST(ASTNode root) {
		this.root = root;
	}
	
	public ASTNode getRoot() {
		return root;
	}
	
	public List<ASTNode> topologicalSort() {
		List<ASTNode> bfsOrder = new ArrayList<>();
		bfsOrder.add(root);
		for (int i = 0; i < bfsOrder.size(); i++) {
			ASTNode curr = bfsOrder.get(i);
			if (curr instanceof Operation) {
				bfsOrder.add(((Operation)curr).getArg1());
				bfsOrder.add(((Operation)curr).getArg2());
			}
		}
		
		
		List<ASTNode> topo = new ArrayList<>(bfsOrder.size());

		for(int i = bfsOrder.size()-1; i >= 0; i--) {
			ASTNode curr = bfsOrder.get(i);
			topo.add(curr);
		}
		
		return topo;
	}
}
