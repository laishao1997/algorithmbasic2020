package class03;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue_copy {

	public static class TwoStacksQueue {

		private Stack<Integer> pushStack;
		private Stack<Integer> popStack;
		public TwoStacksQueue() {
			pushStack = new Stack<>();
			popStack = new Stack<>();
		}

		public void add(int t) {
			this.pushStack.push(t);
		}

		public int poll() {
			if (this.pushStack.isEmpty() && this.popStack.isEmpty()) {
				throw new RuntimeException("队列空了");
			}
			if (!this.popStack.isEmpty()) {
				return this.popStack.pop();
			} else {
				this.popPushToPop();
				return this.popStack.pop();
			}
		}

		private void popPushToPop() {
			if (pushStack.isEmpty()) {
				return;
			}
			while (!pushStack.isEmpty()) {
				popStack.push(pushStack.pop());
			}
		}

		public int peek() {
			if (this.pushStack.isEmpty() && this.popStack.isEmpty()) {
				throw new RuntimeException("队列空了");
			}
			if (!this.popStack.isEmpty()) {
				return this.popStack.peek();
			} else {
				this.popPushToPop();
				return this.popStack.peek();
			}
		}

		public boolean isEmpty() {
			return popStack.isEmpty() && pushStack.isEmpty();
		}
	}

	public static void main(String[] args) {
		TwoStacksQueue test = new TwoStacksQueue();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
	}

}
