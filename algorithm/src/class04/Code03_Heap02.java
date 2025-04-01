package class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 动态数组作为堆
 * 堆排序
 * 修改值后动态调整
 */
public class Code03_Heap02 {

	public static class MyHeap<T> {
		//用动态数组做堆
		private ArrayList<T> heap;
		//记录样本在堆上什么位置的表
		//用空间换查询时间
		private HashMap<T, Integer> indexMap;
		//堆的大小，初始化0
		private int heapSize;
		//这个样本类型的比较器
		private Comparator<? super T> comparator;
		
		public MyHeap(Comparator<? super T> com) {
			heap = new ArrayList<>();
			indexMap = new HashMap<>();
			heapSize = 0;
			comparator = com;
		}
		
		public boolean isEmpty() {
			return heapSize == 0;
		}
		
		public int size() {
			return heapSize;
		}
		
		public boolean contains(T key) {
			return indexMap.containsKey(key);
		}
		
		public void push(T value) {
			heap.add(value);
			indexMap.put(value, heapSize);
			heapInsert(heapSize++);
		}
		
		public T pop() {
			T ans = heap.get(0);
			int end = heapSize - 1;
			swap(0, end);
			heap.remove(end);
			indexMap.remove(ans);
			heapify(0, --heapSize);
			return ans;
		}
		
		public void resign(T value) {
			//找到这个元素在堆上的位置i
			//它的父节点是(i-1)/2
			//它的左边子树是2*i+1
			//它的右边子树是2*i+2
			int valueIndex = indexMap.get(value);
			//heapInsert是往上移动
			heapInsert(valueIndex);
			//heapify是往下移动，两个只中一个
			heapify(valueIndex, heapSize);
		}
		
		private void heapInsert(int index) {
			//比较器比较我自己和我的父元素，怎么比大小
			while(comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
				//我大于我的父元素，交换
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}
		
		private void heapify(int index, int heapSize) {
			int left = index * 2 + 1;
			while(left < heapSize) {
				int largest = (left + 1 < heapSize) && (comparator.compare(heap.get(left + 1), heap.get(left)) < 0)
						? left + 1
						: left;
				largest = comparator.compare(heap.get(largest), heap.get(index)) < 0 
						? largest
						: index;
				if (largest == index) {
					break;
				}
				swap(largest, index);
				index = largest;
				left = index * 2 + 1;
				
			}
		}
		
		/**
		 * 交换堆中i位置和j位置的数
		 */
		private void swap(int i, int j) {
			T o1 = heap.get(i);
			T o2 = heap.get(j);
			//heap中交换
			heap.set(i, o2);
			heap.set(j, o1);
			//indexMap中也交换
			indexMap.put(o1, j);
			indexMap.put(o2, i);
		}
		
	}
	
	public static class Student {
		public int classNo;
		public int age;
		public int id;
		
		public Student(int c, int a, int i) {
			classNo = c;
			age = a;
			id = i;
		}
	}
	
	public static class StudentComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.age - o2.age;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("====================");
		
		Student s1 = new Student(2, 50, 111111);
		Student s2 = new Student(1, 60, 222222);
		Student s3 = new Student(6, 10, 333333);
		Student s4 = new Student(3, 20, 444444);
		Student s5 = new Student(7, 72, 555555);
		Student s6 = new Student(1, 14, 666666);
		
		MyHeap<Student> myHeap = new MyHeap<>(new StudentComparator());
		
		myHeap.push(s1);
		myHeap.push(s2);
		myHeap.push(s3);
		myHeap.push(s4);
		myHeap.push(s5);
		myHeap.push(s6);
		
		s2.age = 6;
		myHeap.resign(s2);
		s4.age = 12;
		myHeap.resign(s4);
		s5.age = 10;
		myHeap.resign(s5);
		s6.age = 84;
		myHeap.resign(s6);
		
		while(!myHeap.isEmpty()) {
			Student cur = (Student)myHeap.pop();
			System.out.println(cur.classNo + "," + cur.age + "," + cur.id);
		}
		
		System.out.println("====================");
	}
}
