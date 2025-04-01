package class08;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对的最大快乐值
 */
public class Code09_MaxHappy {

	public static class Employee {
		public int happy;
		public List<Employee> nexts;
		
		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}
	}
	
	public static int maxHappy1(Employee boss) {
		if (boss == null) {
			return 0;
		}
		return process1(boss, false);
	}
	
	public static int process1(Employee cur, boolean up) {
		if (up) {
			int ans = 0;
			for (Employee next : cur.nexts) {
				ans += process1(next, false);
			}
			return ans;
		} else {
			int p1 = cur.happy;
			int p2 = 0;
			for (Employee next : cur.nexts) {
				p1 += process1(next, true);
				p2 += process1(next, false);
			}
			return Math.max(p1, p2);
		}
	}
	
	public static int maxHappy2(Employee boss) {
		if (boss == null) {
			return 0;
		}
		Info all = process2(boss);
		return Math.max(all.yes, all.no);
	}
	
	public static class Info {
		public int yes; //这个节点来的情况下整棵树的快乐最大值
		public int no; //这个节点不来的情况下整棵树的快乐最大值
		
		public Info(int y, int n) {
			yes = y;
			no = n;
		}
	}
	
	public static Info process2(Employee x) {
		if (x.nexts.isEmpty()) { //没有下级了，整棵树就你一个人
			return new Info(x.happy, 0);
		}
		int yes = x.happy; //这个节点来
		int no = 0; //这个节点不来
		for (Employee next : x.nexts) {
			Info nextInfo = process2(next); //对每一个下级都调递归函数要来它的信息
			yes += nextInfo.no; //如果x是来的，则累加这个下级节点不来的子树的快乐最大值
			no += Math.max(nextInfo.yes, nextInfo.no); //如果x是不来的，则每一个下级节点yes和no都做max累加到x的no上
		}
		return new Info(yes, no);
	}
	
	// for test
	public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
		if (Math.random() < 0.02) {
			return null;
		}
		Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
		genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
		return boss;
	}
	
	// for test
	public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
		if (level > maxLevel) {
			return;
		}
		int nextsSize = (int) (Math.random() * (maxNexts + 1));
		for (int i = 0; i < nextsSize; i++) {
			Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
			e.nexts.add(next);
			genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
		}
	}
	
	public static void main(String[] args) {
		int maxLevel = 4;
		int maxNexts = 7;
		int maxHappy = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
			if (maxHappy1(boss) != maxHappy2(boss)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
