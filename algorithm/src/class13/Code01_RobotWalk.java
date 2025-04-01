package class13;

/**
 * 机器人走步数
 */
public class Code01_RobotWalk {

	/**
	 * 方式一
	 * @param N
	 * @param M
	 * @param K
	 * @param P
	 * @return
	 */
	public static int way1(int N, int M, int K, int P) {
		//参数无效直接返回0
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		//总共N个位置，从M点出发，还剩K步，返回最终能达到P的方法数
		return walk(N, M, K, P);
	}
	
	/**
	 * 
	 * @param N 位置为1~N，固定参数
	 * @param cur 当前在cur位置，可变参数
	 * @param rest 还剩rest步没有走，可变参数
	 * @param P 最终目标位置是P，固定参数
	 * @return 返回可走的方法数
	 */
	public static int walk(int N, int cur, int rest, int P) {
		// 如果没有剩余步数了，当前的cur位置就是最后的位置
		// 如果最后的位置停在P上，那么之前做的移动是有效的
		// 如果最后的位置没停在P上，那么之前做的一定是无效的
		if (rest == 0) {
			return cur == P ? 1 : 0;
		}
		// 如果还有rest步要走，而当前的cur位置在1位置上，那么当前这步只能从1走向2
		// 后续的过程就是，来到2位置上，还剩rest-1步要走
		if (cur == 1) {
			return walk(N, 2, rest - 1, P);
		}
		// 如果还有rest步要走，而当前的cur位置在N位置上，那么当前这步只能从N走向N-1
		// 后续的过程就是，来到N-1位置上，还剩rest-1步要走
		if (cur == N) {
			return walk(N, N - 1, rest - 1, P);
		}
		// 如果还有rest步要走，而当前的cur位置在中间位置，那么当前这步可以走向左，也可以走向右
		// 走向左之后，后续的过程就是，来到cur-1位置上，还剩rest-1步要走
		// 走向右之后，后续的过程就是，来到cur+1位置上，还剩rest-1步要走
		// 走向左、走向右是截然不同的方法，所以总方法数要都算上
		return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
	}
	
	/**
	 * 方式二
	 * @param N
	 * @param M
	 * @param K
	 * @param P
	 * @return
	 */
	public static int way2(int N, int M, int K, int P) {
		//参数无效直接返回0
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		//dp表把1~N位置，剩余1~K步，所有的结果装下
		int[][] dp = new int[N + 1][K + 1];
		//初始化dp表
		for (int row = 0; row <= N; row++) {
			for (int col = 0; col <= K; col++) {
				dp[row][col] = -1;
			}
		}
		
		//总共N个位置，从M点出发，还剩K步，返回最终能达到P的方法数
		return walkCache(N, M, K, P, dp);
	}
	
	/**
	 * 会有重复计算的过程，增加缓存（其实就是动态规划-计划搜索）
	 * 我想把所有cur和rest的组合，返回的结果，加入到缓存里
	 * @param N
	 * @param cur
	 * @param rest
	 * @param P
	 * @return
	 */
	public static int walkCache(int N, int cur, int rest, int P, int[][] dp) {

		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		
		if (rest == 0) {
			dp[cur][rest] = (cur == P ? 1 : 0);
			return dp[cur][rest];
		}

		if (cur == 1) {
			dp[cur][rest] = walkCache(N, 2, rest - 1, P, dp);
			return dp[cur][rest];
		}

		if (cur == N) {
			dp[cur][rest] = walkCache(N, N - 1, rest - 1, P, dp);
			return dp[cur][rest];
		}

		dp[cur][rest] = walkCache(N, cur + 1, rest - 1, P, dp) + walkCache(N, cur - 1, rest - 1, P, dp);
		return dp[cur][rest];
	}
	
	public static void main(String[] args) {
		int N = 7;
		int M = 3;
		int P = 2;
		int K = 3;
		System.out.println(way1(N,M,K,P));
		System.out.println("====================");
		System.out.println(way2(N,M,K,P));
	}
}
