package src.class10;

import java.util.Stack;

public class Code03_UnRecursiveTraversalBT_copy {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		Stack<Node> pre = new Stack<>();
		pre.add(head);
		while (!pre.isEmpty()) {
			Node node = pre.pop();
			System.out.println("先序遍历：" + node.value);
			if (node.right != null) {
				pre.push(node.right);
			}
			if (node.left != null) {
				pre.push(node.left);
			}
		}
		System.out.println();
	}

	public static void in(Node cur) {
		if (cur != null) {
			Stack<Node> in = new Stack<>();
			while (!in.isEmpty() || cur != null) {
				if (cur != null) {
					in.push(cur);
					cur = cur.left;
				} else {
					cur = in.pop();
					System.out.println("中序遍历" + cur.value);
					cur = cur.right;
				}
			}
		}
		System.out.println();
	}

	public static void pos1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> s1 = new Stack<>();
			Stack<Node> s2 = new Stack<>();
			s1.push(head);
			while (!s1.isEmpty()) {
				Node cur = s1.pop();
				s2.push(cur);
				if (cur.left != null) {
					s1.push(cur.left);
				}
				if (cur.right != null) {
					s1.push(cur.right);
				}
			}
			while (!s2.isEmpty()) {
				System.out.println("后续遍历：" + s2.pop().value);
			}
		}
		System.out.println();
	}

	public static void pos2(Node h) {
		if (h == null) {
			return;
		}
		Stack<Node> pos = new Stack<>();
		pos.push(h);
		while (!pos.isEmpty()) {
			Node cur = pos.peek();
			if (cur.left != null && cur.left != h && cur.right != h) {
				pos.push(cur.left);
			} else if (cur.right != null && cur.right != h) {
				pos.push(cur.right);
			} else {
				System.out.println("后续遍历：" + pos.pop().value);
				h = cur;
			}
		}
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos1(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
	}

}
