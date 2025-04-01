package class09;

/**
 * Z字形打印矩阵
 */
public class Code06_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		int aR = 0; //A的行号
		int aC = 0; //A的列号
		int bR = 0; //B的行号
		int bC = 0; //B的列号
		int endR = matrix.length - 1; //最后的行号
		int endC = matrix[0].length - 1; //最后的列号
		boolean fromUp = false; //是不是从右上往左下打印
		while (aR != endR + 1) { //A的行号，不等于最后的行号+1，A到了终点
			printLevel(matrix, aR, aC, bR, bC, fromUp);
			aR = (aC == endC ? aR + 1 : aR); //A的列数到最后一列，A的行号才加1
			aC = (aC == endC ? aC : aC + 1); //A的列数到最后一列，A的列数不变，否则A的列数加1
			bC = (bR == endR ? bC + 1 : bC); //B的行数到最后一行，B的列数加1，否则B的列数不变
			bR = (bR == endR ? bR : bR + 1); //B的行数到最后一行，B的行数不变，否则B的行数加1
			fromUp = !fromUp; //打印方向
		}
		System.out.println();
	}
	
	/**
	 * 告诉你斜线的两端是A和B，方向也告诉你，就可以打印了
	 * @param m
	 * @param tR
	 * @param tC
	 * @param dR
	 * @param dC
	 * @param f
	 */
	public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, 
			boolean f) {
		if (f) {
			while (tR != dR + 1) { //右上往左下打印
				System.out.print(m[tR++][tC--] + " ");
			}
		} else {
			while (dR != tR - 1) { //左下往右上打印
				System.out.print(m[dR--][dC++] + " ");
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
		printMatrixZigZag(matrix);
	}
}
