import java.util.*;
import java.io.*;

class no_duplicates {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashSet<String> hs = new HashSet<String>();
		while(sc.hasNext()) {
			String str = sc.next();
			if (hs.contains(str)){
				System.out.println("no");
				System.exit(0);
			}
			hs.add(str);
		}
		System.out.println("yes");
	}
}