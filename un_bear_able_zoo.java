import java.util.*;
import java.io.*;

class un_bear_able_zoo {
	public static void main(String[] args) throws IOException{
		int list = 1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TreeMap<String, Integer> tm;
		String line;
		while ((line = br.readLine()) != null) {
			tm = new TreeMap<String, Integer>();
			int n = Integer.parseInt(line);
			if (n == 0) break;
			for (int i = 0; i<n; i++) {
				line = br.readLine();
				String[] temp = line.split(" ");
				String name = temp[temp.length-1].toLowerCase();
				if (tm.containsKey(name)) {
					int count = tm.get(name);
					count++;
					tm.put(name, count);
				}
				else{
					tm.put(name, 1);
				}
			}
			sb.append("List " + list + ":\n");
			for (Map.Entry<String,Integer> entry : tm.entrySet()) {
				String key = entry.getKey();
		        Integer value = entry.getValue();
		        sb.append(key + " | " + value.toString() + "\n");
		   	}
			list++;
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
}