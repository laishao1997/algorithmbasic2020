package src.class16;

import src.class13.Code05_LowestLexicography_copy;

import java.util.*;

// OJ链接：https://www.lintcode.com/problem/topological-sorting
public class Code03_TopologicalOrderDFS2_copy {

	// 不要提交这个类
	public static class DirectedGraphNode {
		public int label;
		public ArrayList<DirectedGraphNode> neighbors;

		public DirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<DirectedGraphNode>();
		}
	}

	// 提交下面的
	public static class Record {
		public DirectedGraphNode node;
		public long nodes;

		public Record(DirectedGraphNode n, long o) {
			node = n;
			nodes = o;
		}
	}

	public static class MyComparator implements Comparator<Record> {

		@Override
		public int compare(Record o1, Record o2) {
			return o1.nodes == o2.nodes ? 0 : (int) (o2.nodes - o1.nodes);
		}
	}

	public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Record> nodesMap = new HashMap<>();
		for (DirectedGraphNode node : graph) {
			f(node, nodesMap);
		}
		ArrayList<Record> records = new ArrayList<>();
		for (Record record : nodesMap.values()) {
			records.add(record);
		}
		records.sort(new MyComparator());
		ArrayList<DirectedGraphNode> ans = new ArrayList<>();
		for (Record record : records) {
			ans.add(record.node);
		}
		return ans;
	}

	// 当前来到cur点，请返回cur点所到之处，所有的点次！
	// 返回（cur，点次）
	// 缓存！！！！！order   
	//  key : 某一个点的点次，之前算过了！
	//  value : 点次是多少
	public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
		if (order.containsKey(cur)) {
			return order.get(cur);
		}
		long nodes = 0;
		for (DirectedGraphNode next : cur.neighbors) {
			nodes += f(next, order).nodes;
		}
		Record record = new Record(cur, nodes + 1);
		order.put(cur, record);
		return record;
	}

}
