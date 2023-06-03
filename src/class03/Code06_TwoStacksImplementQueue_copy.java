package src.class03;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue_copy {

	public static class TwoStacksQueue {
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		// push栈向pop栈倒入数据
		private void pushToPop() {
			if (!stackPop.isEmpty()) {
				return;
			}
			while (!stackPush.isEmpty()){
				stackPop.push(stackPush.pop());
			}
		}

		public void add(int pushInt) {
			stackPush.add(pushInt);
		}

		public int poll() {
			pushToPop();
			if (stackPop.isEmpty()) {
				System.out.println("队列空了");
				return -1;
			}
			return stackPop.pop();
		}

		public int peek() {
			pushToPop();
			if (stackPop.isEmpty()) {
				System.out.println("队列空了");
				return -1;
			}
			return stackPop.peek();
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
