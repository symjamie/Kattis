import java.util.*;
import java.io.*;

class cookie_selection {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> small = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> big = new PriorityQueue<Integer>();
		String line;
        int dia;
		while((line = br.readLine()) != null){
        	if (line.equals("#")) System.out.println(big.poll());
        	else {
        		dia = Integer.parseInt(line);
                if (big.size() == 0) {
                    big.add(dia);
                    continue;
                }
                else {
                    if (dia < big.peek()) small.add(dia);
                    else big.add(dia);
                }
        	}
            if (small.size() > big.size()) big.add(small.poll());
            else if (big.size() > small.size() + 1) small.add(big.poll());
        }
    }  

}