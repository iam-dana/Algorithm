import java.io.*;
import java.util.*;

public class BOJ_1522_문자열교환 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char [] a = br.readLine().toCharArray();
        int aCount = 0;
        int ans = Integer.MAX_VALUE;

        for (int i=0; i<a.length; i++) {
            if (a[i] == 'a') aCount++;
        }

        for (int i=0; i<a.length; i++) {
            int bCount = 0;
            for (int j=i; j<aCount+i; j++) {
                if (a[j % a.length] == 'b') bCount++;
            }
            ans = Math.min(ans, bCount);
        }
        System.out.println(ans);
    }
}
