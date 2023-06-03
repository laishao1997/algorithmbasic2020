package src.class09;

import java.util.HashMap;

// 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/
public class Code04_CopyListWithRandom_copy_copy {

	public static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	public static Node copyRandomList1(Node head) {
		// key 老节点
		// value 新节点
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.val));
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			// cur 老
			// map.get(cur) 新
			// 新.next ->  cur.next克隆节点找到
			map.get(cur).next = map.get(cur.next);
			map.get(cur).random = map.get(cur.random);
			cur = cur.next;
		}
		return map.get(head);
	}

	public static Node copyRandomList2(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		while (cur != null) {
			next = cur.next;
			cur.next = new Node(cur.val);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node copy = null;
		while (cur != null) {
			next = cur.next.next;
			copy = cur.next;
			copy.random = cur.random == null ? null : cur.random.next;
			cur = next;
		}
		cur = head;
		Node ans = cur.next;
		while (cur != null) {
			next = cur.next.next;
			copy = cur.next;
			copy.next = next == null ? null : next.next;
			cur.next = next;
			cur = next;
		}
		return ans;
	}

}
