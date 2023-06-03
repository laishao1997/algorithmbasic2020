package class25;

import java.util.Stack;

// 测试链接：https://leetcode.com/problems/largest-rectangle-in-histogram
public class Code03_LargestRectangleInHistogram {

	public static int largestRectangleArea1(int[] heights) {
		if (heights == null || heights.length == 0) {
			return 0;
		}
		int maxArea = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < heights.length; i++) {
			while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
				int j = stack.pop();
				int k = stack.isEmpty() ? -1 : stack.peek();
				int curArea = (i - k - 1) * heights[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (heights.length - k - 1) * heights[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

	public static int largestRectangleArea2(int[] heights) {
		if (heights == null || heights.length == 0) {
			return 0;
		}
		int N = heights.length;
		int[] stack = new int[N];
		int si = -1;
		int maxArea = 0;
		for (int i = 0; i < heights.length; i++) {
			while (si != -1 && heights[i] <= heights[stack[si]]) {
				int j = stack[si--];
				int k = si == -1 ? -1 : stack[si];
				int curArea = (i - k - 1) * heights[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack[++si] = i;
		}
		while (si != -1) {
			int j = stack[si--];
			int k = si == -1 ? -1 : stack[si];
			int curArea = (heights.length - k - 1) * heights[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

}
