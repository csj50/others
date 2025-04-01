package class09;

/**
 * 回形打印矩阵
 */
public class Code05_PrintMatrixSpiralOrder {

	public static void sprialOrderPrint(int[][] matrix) {
		int tR = 0;
		int tC = 0;
		int dR = matrix.length - 1;
		int dC = matrix[0].length - 1;
		while (tR <= dR && tC <= dC) {
			printEdge(matrix, tR++, tC++, dR--, dC--); //左上角点往右下方移动，右下角点往左上方移动
		}
	}
	
	/**
	 * 
	 * @param m
	 * @param tR 左上角点的行号
	 * @param tC 左上角点的列号
	 * @param dR 右下角点的行号
	 * @param dC 右下角点的列号
	 */
	public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
		if (tR == dR) { //是一条横线时
			for (int i = tC; i <= dC; i++) {
				System.out.print(m[tR][i] + " ");
			}
		} else if (tC == dC) { //是一条竖线时
			for (int i = tR; i <= dR; i++) {
				System.out.print(m[i][tC] + " ");
			}
		} else {
			int curC = tC;
			int curR = tR;
			while (curC != dC) {
				System.out.print(m[tR][curC] + " ");
				curC++;
			}
			while (curR != dR) {
				System.out.print(m[curR][dC] + " ");
				curR++;
			}
			while (curC != tC) {
				System.out.print(m[dR][curC] + " ");
				curC--;
			}
			while (curR != tR) {
				System.out.print(m[curR][tC] + " ");
				curR--;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}
		};
		sprialOrderPrint(matrix);
	}
}
