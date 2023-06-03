package src.class16;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Code02_DFS_copy {

	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		Set<Node> set = new HashSet<>();
		stack.push(node);
		set.add(node);
		System.out.println("" + node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node n : cur.nexts) {
				if (!set.contains(n)) {
					stack.push(cur);
					stack.push(n);
					set.add(n);
					System.out.println("" + node.value);
					break;
				}
			}
		}
	}

	public static class Node {
		public int value;
		public Node[] nexts;
	}
	

}
