package src.class14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Code05_UnionFind_copy {

	public static class Node<V> {
		V value;

		public Node(V v) {
			value = v;
		}
	}

	public static class UnionFind<V> {
		private Map<V, Node<V>> valueMap;
		private Map<Node<V>, Node<V>> parentsMap;
		private Map<Node<V>, Integer> sizeMap;

		public UnionFind(List<V> values) {
			valueMap = new HashMap<>();
			parentsMap = new HashMap<>();
			sizeMap = new HashMap<>();
			for (V v : values) {
				Node<V> node = new Node<>(v);
				valueMap.put(v, node);
				parentsMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		// 给你一个节点，请你往上到不能再往上，把代表返回
		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> stack = new Stack<>();
			while (cur != parentsMap.get(cur)) {
				stack.push(cur);
				cur = parentsMap.get(cur);
			}
			if (!stack.isEmpty()) {
				parentsMap.put(stack.pop(), cur);
			}
			return cur;
		}

		public boolean isSameSet(V a, V b) {
			return findFather(valueMap.get(a)) == findFather(valueMap.get(b));
		}

		public void union(V a, V b) {
			Node<V> aHead = findFather(valueMap.get(a));
			Node<V> bHead = findFather(valueMap.get(b));
			if (aHead != bHead) {
				int aSize = sizeMap.get(aHead);
				int bSize = sizeMap.get(bHead);
				Node<V> big = aSize >= bSize ? aHead : bHead;
				Node<V> small = big == aHead ? bHead : aHead;
				parentsMap.put(small, big);
				sizeMap.put(big, aSize + bSize);
				sizeMap.remove(small);
			}
		}

		public int sets() {
			return sizeMap.size();
		}

	}
}
