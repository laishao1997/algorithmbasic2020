package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code07_TwoQueueImplementStack_copy {

	public static class TwoQueueStack<T> {
		public Queue<T> queue;
		public Queue<T> help;

		public TwoQueueStack() {
			queue = new LinkedList<>();
			help = new LinkedList<>();
		}

		public void push(T v) {
			this.queue.add(v);
		}

		public T poll() {
			if (isEmpty()) {
				throw new RuntimeException("kong l");
			}
			pushTo();
			T t = queue.poll();
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			return t;
		}

		private void pushTo() {
			if (queue.isEmpty()) {
				return;
			}
			while (queue.size() != 1) {
				help.add(queue.poll());
			}
		}

		public T peek() {
			if (isEmpty()) {
				throw new RuntimeException("kong l");
			}
			pushTo();
			T t = queue.poll();
			help.add(t);
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			return t;
		}

		public boolean isEmpty() {
			return queue.isEmpty() && help.isEmpty();
		}
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
		Stack<Integer> test = new Stack<>();
		int testTime = 1000000;
		int max = 1000000;
		for (int i = 0; i < testTime; i++) {
			if (myStack.isEmpty()) {
				if (!test.isEmpty()) {
					System.out.println("Oops");
				}
				int num = (int) (Math.random() * max);
				myStack.push(num);
				test.push(num);
			} else {
				if (Math.random() < 0.25) {
					int num = (int) (Math.random() * max);
					myStack.push(num);
					test.push(num);
				} else if (Math.random() < 0.5) {
					if (!myStack.peek().equals(test.peek())) {
						System.out.println("Oops");
					}
				} else if (Math.random() < 0.75) {
					if (!myStack.poll().equals(test.pop())) {
						System.out.println("Oops");
					}
				} else {
					if (myStack.isEmpty() != test.isEmpty()) {
						System.out.println("Oops");
					}
				}
			}
		}

		System.out.println("test finish!");

	}

}
