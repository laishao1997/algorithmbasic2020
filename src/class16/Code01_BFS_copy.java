package src.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Code01_BFS_copy {

	// 从node出发，进行宽度优先遍历
	public static void bfs(Node start) {
		if (start == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		Set<Node> set = new HashSet<>();
		queue.add(start);
		set.add(start);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println("" + cur.value);
			for (Node node : cur.nexts) {
				if (!set.contains(node)) {
					set.add(node);
					queue.add(node);
				}
			}
		}
	}

	public static class Node {
		public int value;
		public Node[] nexts;
	}

}
