package src.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code03_DoubleEndsQueueToStackAndQueue_copy {

	public static class Node<T> {
		public T value;
		public Node<T> last;
		public Node<T> next;

		public Node(T data) {
			value = data;
		}
	}

	public static class DoubleEndsQueue<T> {
		private Node head;
		private Node tail;

		public void addFromHead(T value) {
			Node<T> cur = new Node(value);
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				cur.next = head;
				head.last = cur;
				head = cur;
			}
		}

		public void addFromBottom(T value) {
			Node<T> cur = new Node(value);
			if (tail == null) {
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				cur.next = tail;
				tail = cur;
			}
		}

		public T popFromHead() {
			if (head == null) {
				return null;
			}
			Node<T> cur = head;
			if (head == tail) {
				tail = null;
				head = null;
			} else {
				head = head.next;
				head.last = null;
				cur.next = null;
			}
			return cur.value;
		}

		public T popFromBottom() {
			if (tail == null) {
				return null;
			}
			Node<T> cur = tail;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				tail = tail.last;
				tail.next = null;
				cur.last = null;
			}
			return cur.value;
		}

		public boolean isEmpty() {
			return head == null;
		}

	}

	public static class MyStack<T> {
		private DoubleEndsQueue<T> queue;

		public MyStack() {
			queue = new DoubleEndsQueue<T>();
		}

		public void push(T value) {
			queue.addFromHead(value);
		}

		public T pop() {
			return queue.popFromHead();
		}

		public boolean isEmpty() {
			return queue.isEmpty();
		}

	}

	public static class MyQueue<T> {
		private DoubleEndsQueue<T> queue;

		public MyQueue() {
			queue = new DoubleEndsQueue<T>();
		}

		public void push(T value) {
			queue.addFromHead(value);
		}

		public T poll() {
			return queue.popFromBottom();
		}

		public boolean isEmpty() {
			return queue.isEmpty();
		}

	}

	public static boolean isEqual(Integer o1, Integer o2) {
		if (o1 == null && o2 != null) {
			return false;
		}
		if (o1 != null && o2 == null) {
			return false;
		}
		if (o1 == null && o2 == null) {
			return true;
		}
		return o1.equals(o2);
	}

	public static void main(String[] args) {
		int oneTestDataNum = 100;
		int value = 10000;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			MyStack<Integer> myStack = new MyStack<>();
			MyQueue<Integer> myQueue = new MyQueue<>();
			Stack<Integer> stack = new Stack<>();
			Queue<Integer> queue = new LinkedList<>();
			for (int j = 0; j < oneTestDataNum; j++) {
				int nums = (int) (Math.random() * value);
				if (stack.isEmpty()) {
					myStack.push(nums);
					stack.push(nums);
				} else {
					if (Math.random() < 0.5) {
						myStack.push(nums);
						stack.push(nums);
					} else {
						if (!isEqual(myStack.pop(), stack.pop())) {
							System.out.println("oops!");
						}
					}
				}
				int numq = (int) (Math.random() * value);
				if (queue.isEmpty()) {
					myQueue.push(numq);
					queue.offer(numq);
				} else {
					if (Math.random() < 0.5) {
						myQueue.push(numq);
						queue.offer(numq);
					} else {
						if (!isEqual(myQueue.poll(), queue.poll())) {
							System.out.println("oops!");
						}
					}
				}
			}
		}
		System.out.println("finish!");
	}

}
