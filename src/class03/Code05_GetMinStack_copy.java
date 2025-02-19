package src.class03;

import java.util.Stack;

public class Code05_GetMinStack_copy {
	public static class MyStack1{
		public static Stack<Integer> dataStack;
		public static Stack<Integer> minStack;

		public MyStack1() {
			dataStack = new Stack<>();
			minStack = new Stack<>();
		}

		public void push(int value) {
			if (minStack.isEmpty()) {
				minStack.push(value);
			} else if (value < this.getmin()) {
				minStack.push(value);
			}
			dataStack.push(value);
		}

		public int pop() {
			if (dataStack.isEmpty()) {
				System.out.println("栈为空了");
			}
			int value = dataStack.pop();
			if (value == getmin()) {
				this.dataStack.pop();
			}
			return value;
		}

		public int getmin() {
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
