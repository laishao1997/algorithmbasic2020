package class03;

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
		public Node<T> head;
		public Node<T> tail;


		public void addFromHead(T t) {
			Node<T> node = new Node<>(t);
			if (head == null) {
				head = node;
				tail = node;
			} else {
				node.next = head;
				head.last = node;
				head = node;
			}
		}

		public void addFromTail(T t) {
			Node<T> node = new Node<>(t);
			if (tail == null) {
				head = node;
				tail = node;
			} else {
				node.last = tail;
				tail.next = node;
				tail = node;
			}
		}

		public T popFromHead(){
			if (isEmpty()) {
				return null;
			}
			Node<T> res = head;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
				res.next = null;
				head.last = null;
			}
			return res.value;
		}

		public T popFromTail(){
			if (isEmpty()) {
				return null;
			}
			Node<T> res = tail;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				tail = tail.last;
				res.last = null;
				tail.next = null;
			}
			return res.value;
		}

		public boolean isEmpty() {
			return head == null;
		}

	}

	public static class MyStack<T> {
		public DoubleEndsQueue<T> stack;

		public MyStack() {
			stack = new DoubleEndsQueue<>();
		}

		public void push(T t){
			stack.addFromHead(t);
		}

		public T pop() {
			return stack.popFromHead();
		}

		public boolean isEmpty() {
			return stack.isEmpty();
		}
	}

	public static class MyQueue<T> {
		public DoubleEndsQueue<T> queue;

		public MyQueue() {
			queue = new DoubleEndsQueue<>();
		}

		public void push(T t) {
			queue.addFromHead(t);
		}

		public T poll() {
			return queue.popFromTail();
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
