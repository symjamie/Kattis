import java.io.*;
import java.util.*;

class bacon_eggs_and_spam {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TreeMap<String,TreeSet<String>> tm;
		TreeSet<String> sold, order;
		String line;
		int n;
		String[] temp;

		while(!(line = br.readLine()).equals("0")) {
			n = Integer.parseInt(line);
			sold = new TreeSet<String>();
			tm = new TreeMap <String,TreeSet<String>>();
			for (int i = 0; i < n; i++) {
				temp = br.readLine().split(" ");
				order = new TreeSet<String>();
				for (int j = 1; j < temp.length; j++) {
					sold.add(temp[j]);
					order.add(temp[j]);
				}
				tm.put(temp[0], order);
			}
			for(String item : sold) {
				sb.append(item);
				tm.forEach((cust, foods) -> {
					if (foods.contains(item)) {
						sb.append(" ");
						sb.append(cust);
					}
				});
				sb.append("\n");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());

	}


}