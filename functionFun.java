import java.util.*;
import java.io.*;

public class functionFun {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        String[] dom, codom, map;
        int n, i, nCodom;
        boolean inj, surj, notFun;
        while ((line = br.readLine()) != null) {
            notFun = false;
            inj = true;
            surj = false;
            nCodom = 0;
            HashMap<String, String> hm = new HashMap<String, String>();
            dom = line.split(" ");
            codom = br.readLine().split(" ");
            n = Integer.parseInt(br.readLine());
            for (i = 0; i < n; ++i) {
                map = br.readLine().split(" -> ");
                if (hm.containsKey(map[0])) notFun = true;
                if (hm.containsValue(map[1])) inj = false;
                else ++nCodom;
                hm.put(map[0], map[1]);
            }
            if (nCodom == codom.length - 1) surj = true;
            if (notFun) sb.append("not a function\n");
            else {
                if (inj) {
                    if (surj) sb.append("bijective\n");
                    else sb.append("injective\n");
                } else {
                    if (surj) sb.append("surjective\n");
                    else sb.append("neither injective nor surjective\n");
                }
            }
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
    }
}