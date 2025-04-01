package class04;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 自定义比较器和比较器怎么使用
 */
public class Code01_Comparator_01 {

	public static class Student {
		public String name;
		public int id;
		public int age;
		
		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}
	
	/**
	 * id上升比较器
	 */
	public static class IdAscendingComparator implements Comparator<Student> {

		// 返回负数的时候，第一个参数排在前面
		// 返回正数的时候，第二个参数排在前面
		// 返回0的时候，谁在前面无所谓
		@Override
		public int compare(Student o1, Student o2) {
			return o1.id - o2.id;
		}
		
	}
	
	/**
	 * id下降比较器
	 */
	public static class IdDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.id - o1.id;
		}
		
	}
	
	/**
	 * age上升比较器
	 */
	public static class AgeAscendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.age - o2.age;
		}
		
	}
	
	/**
	 * age下降比较器
	 */
	public static class AgeDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.age - o1.age;
		}
		
	}
	
	/**
	 * age上升id上升比较器
	 */
	public static class AgeShengIdSheng implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.age != o2.age ? (o1.age - o2.age) : (o1.id - o2.id);
		}
		
	}
	
	/**
	 * id上升age下降比较器
	 * 先按照id排序，id小的，放前面
	 * id一样，age大的，放前面
	 */
	public static class IdInAgeDe implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.id != o2.id ? (o1.id - o2.id) : (o2.age - o1.age);
		}
		
	}
	
	public static void printStudents(Student[] students) {
		for (Student student : students) {
			System.out.println("name: " + student.name + ", id: " + student.id + ", age: " + student.age);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("====================");
		
		Student student1 = new Student("A", 2, 20);
		Student student2 = new Student("B", 3, 21);
		Student student3 = new Student("C", 1, 22);
		
		Student[] students = new Student[] {student1, student2, student3};
		
		// 比较器怎么使用
		Arrays.sort(students, new IdAscendingComparator());
		
		printStudents(students);
		System.out.println("====================");
	}
}
