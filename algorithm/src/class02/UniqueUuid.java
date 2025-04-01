package class02;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

/**
 * 生成唯一uuid
 */
public class UniqueUuid {

	public static HashSet<String> set = new HashSet<>();
	public static int i = 0;
	
	public static String generateUniqueUuid() {
		String uuid1 = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
		String uuid2 = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
		
		String uuid = uuid1 + uuid2;
		
		Random random = new Random();
		uuid = uuid + String.valueOf(random.nextInt(90) + 10);
		
		while (set.contains(uuid)) {
			System.out.println(uuid);
			System.out.println("有重复！！！");
			//uuid1 = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
		}
		//System.out.println(uuid);
		set.add(uuid);
		
		return uuid;
	}
	
	public static void main(String[] args) {
		for (int i=0; i<=50000000; i++) {
			generateUniqueUuid();
			i++;
			System.out.println(i);
		}
		
		System.out.println("生成完成！！！");
		
	}
}
