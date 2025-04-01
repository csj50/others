package class10;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 会议宣讲场次安排
 */
public class Code04_BestArrange {

	public static class Program {
		public int start; //开始时间
		public int end; //结束时间
		
		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	/**
	 * 暴力穷举
	 * @param programs
	 * @return
	 */
	public static int bestArrange1(Program[] programs) {
		if (programs == null || programs.length == 0) {
			return 0;
		}
		return process(programs, 0, 0);
	}
	
	/**
	 * 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议在programs可以自由安排
	 * @param programs 还剩什么会议
	 * @param done 之前已经安排了多少会议数量
	 * @param timeLine 现在时间点来到了什么
	 * @return 返回能安排的最多会议数量
	 */
	public static int process(Program[] programs, int done, int timeLine) {
		if (programs.length == 0) { //剩余会议数量是0
			return done;
		}
		//还有会议可以选择
		int max = done;
		//当前安排的会议是什么会，每一个都枚举
		//for循环是遍历了每一个会议并产生选择该会议下产生的最大值，并与之前最大值比较
		for (int i = 0; i < programs.length; i++) {
			if (programs[i].start >= timeLine) { //会议的开始时间比当前时间晚，可以安排
				Program[] next = copyButExcept(programs, i);
				//剩下的会议，已经安排的会议数量+1，i号会议的结束时间
				max = Math.max(max, process(next, done + 1, programs[i].end));
			}
		}
		return max;
	}
	
	/**
	 * 在programs里把i号会议删掉，返回一个新的剩下的会议
	 * @param programs
	 * @param i
	 * @return
	 */
	public static Program[] copyButExcept(Program[] programs, int i) {
		Program[] ans = new Program[programs.length - 1];
		int index = 0;
		for (int k = 0; k < programs.length; k++) {
			if (k != i) {
				ans[index++] = programs[k];
			}
		}
		return ans;
	}
	
	public static int bestArrange2(Program[] programs) {
		Arrays.sort(programs, new ProgramComparator());
		int timeLine = 0;
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			if (timeLine <= programs[i].start) {
				result++;
				timeLine = programs[i].end;
			}
		}
		return result;
	}
	
	/**
	 * 比较器，根据谁的结束时间早排序
	 */
	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}
	}
	
	// for test
	public static Program[] generatePrograms(int programSize, int timeMax) {
		Program[] ans = new Program[(int)(Math.random() * (programSize + 1))];
		for (int i = 0; i < ans.length; i++) {
			int r1 = (int) (Math.random() * (timeMax + 1));
			int r2 = (int) (Math.random() * (timeMax + 1));
			if (r1 == r2) {
				ans[i] = new Program(r1, r1 + 1);
			} else {
				ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		int programSize = 12;
		int timeMax = 20;
		int timeTimes = 1000000;
		for (int i = 0; i < timeTimes; i++) {
			Program[] programs = generatePrograms(programSize, timeMax);
			if (bestArrange1(programs) != bestArrange2(programs)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
