package src.class40;

public class Code05_PrintMatrixSpiralOrder_copy {

	public static void spiralOrderPrint(int[][] matrix) {
		int tR = 0;
		int tC = 0;
		int dR = matrix.length - 1;
		int dC = matrix[0].length - 1;
		while (tR <= dR && tC <= dC) {
			printEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}

	public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
		if (tR == dR) {
			while (tC != dC) {
				System.out.print("   " + m[tR][tC]);
				tC++;
			}
			System.out.print("   " + m[tR][tC]);
		} else if (tC == dC) {
			while (tR != dR) {
				System.out.print("   " + m[tR][tC]);
				tR++;
			}
			System.out.print("   " + m[tR][tC]);
		} else {
			int curC = tC;
			int curR = tR;
			while (curC != dC) {
				System.out.print("   " + m[curR][curC++]);
			}
			while (curR != dR) {
				System.out.print("   " + m[curR++][curC]);
			}
			while (curC != tC) {
				System.out.print("   " + m[curR][curC--]);
			}
			while (curR != tR) {
				System.out.print("   " + m[curR--][curC]);
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}

}
