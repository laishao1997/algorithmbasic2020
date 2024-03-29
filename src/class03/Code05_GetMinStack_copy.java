package class03;

import java.util.Stack;

public class Code05_GetMinStack_copy {

	public static class MyStack1 {
		private Stack<Integer> dataStack;
		private Stack<Integer> minStack;

		public MyStack1() {
			dataStack = new Stack<>();
			minStack = new Stack<>();
		}

		public void push(int v) {
			dataStack.push(v);
			if (minStack.isEmpty()) {
				minStack.push(v);
			} else if (v <= this.getmin()){
				minStack.push(v);
			} else {
				int peek = minStack.peek();
				minStack.push(peek);
			}
		}

		public Integer pop() {
			if (dataStack.isEmpty()) {
				throw new RuntimeException("空了");
			}
			minStack.pop();
			return dataStack.pop();
		}

		public Integer getmin() {
			if (this.minStack.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return minStack.peek();
		}
	}

	public static class MyStack2 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack2() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum < this.getmin()) {
				this.stackMin.push(newNum);
			} else {
				int newMin = this.stackMin.peek();
				this.stackMin.push(newMin);
			}
			this.stackData.push(newNum);
		}

		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			this.stackMin.pop();
			return this.stackData.pop();
		}

		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	public static void main(String[] args) {
		MyStack1 stack1 = new MyStack1();
		stack1.push(3);
		System.out.println(stack1.getmin());
		stack1.push(4);
		System.out.println(stack1.getmin());
		stack1.push(1);
		System.out.println(stack1.getmin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getmin());

		System.out.println("=============");

		MyStack1 stack2 = new MyStack1();
		stack2.push(3);
		System.out.println(stack2.getmin());
		stack2.push(4);
		System.out.println(stack2.getmin());
		stack2.push(1);
		System.out.println(stack2.getmin());
		System.out.println(stack2.pop());
		System.out.println(stack2.getmin());
	}

}
