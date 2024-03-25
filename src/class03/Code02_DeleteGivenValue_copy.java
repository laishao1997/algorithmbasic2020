package class03;

public class Code02_DeleteGivenValue_copy {

	public static class Node {
		public int value;
		public Node next;

		public Node(int v) {
			this.value = v;
		}
	}

	// head = removeValue(head, 2);
	public static Node removeValue(Node head, int num) {
		//来到第一个不需要删的位置
		while(head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}

		Node pre = head;
		Node cur = head;
		while(cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}




	public static Node removeValue2(Node head, int num) {
		while (head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		Node cur = head;
		Node pre = head;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}
}
