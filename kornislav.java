import java.util.*;

public class kornislav {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(sc.nextInt());
		pq.add(sc.nextInt());
		pq.add(sc.nextInt());
		pq.add(sc.nextInt());
		int a = pq.poll();
		pq.poll();
		int b = pq.poll();
		System.out.println(a*b);
	}
}