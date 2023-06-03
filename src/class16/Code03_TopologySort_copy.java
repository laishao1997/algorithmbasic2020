package src.class16;

import java.util.*;

public class Code03_TopologySort_copy {

	// directed graph and no loop
	public static List<Node> sortedTopology(Graph graph) {
		//一个节点 剩余的入度
		Map<Node, Integer> inMap = new HashMap<>();
		Queue<Node> zoreInQueue = new LinkedList<>();
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				zoreInQueue.add(node);
			}
		}
		List<Node> ans = new ArrayList<>();
		while (!zoreInQueue.isEmpty()) {
			Node cur = zoreInQueue.poll();
			ans.add(cur);
			for (Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1);
				if (inMap.get(next) == 0) {
					zoreInQueue.add(next);
				}
			}
		}
		return ans;
	}
}
