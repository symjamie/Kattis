import java.util.*;

class verify_this_your_majesty {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean[] a = new boolean[20000]; // col
		boolean[] b = new boolean[20000]; // row
		boolean[] c = new boolean[20000]; // \
		boolean[] d = new boolean[20000]; // /
		int n = sc.nextInt();
		for (int i = 0; i < 20000; ++i) a[i] = b[i] = c[i] = d[i] = true;
		for (int i = 0; i < n; ++i) {
			int x = sc.nextInt(); //col
			int y = sc.nextInt(); //row
			if (a[x] && b[y] && c[x + y] && d[y - x + n]) {
				a[x] = b[y] = c[x + y] = d[y - x + n] = false;
			} else {
				System.out.println("INCORRECT");
				return;
			}
			
		}
		System.out.println("CORRECT");
	}
}