package src.class40;

public class Code08_PrintStar_copy {

	public static void printStar(int N) {
		int leftUp = 0;
		int rightDown = N - 1;
		char[][] arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = ' ';
			}
		}
		while (leftUp <= rightDown) {
			set(arr, leftUp, rightDown);
			leftUp += 2;
			rightDown -= 2;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void set(char[][] m, int leftUp, int rightDown) {
		for (int col = leftUp; col < rightDown; col++) {
			m[leftUp][col] = '*';
		}
		for (int row = leftUp; row < rightDown; row++) {
			m[row][rightDown] = '*';
		}
		for (int col = rightDown - 1; col > leftUp; col--) {
			m[rightDown][col] = '*';
		}
		for (int row = rightDown - 1; row > leftUp + 1; row--) {
			m[row][leftUp + 1] = '*';
		}
	}

	public static void main(String[] args) {
		printStar(6);
	}

}
