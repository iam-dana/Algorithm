import java.io.*;
import java.util.*;

public class BOJ_1138_한줄로서기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] ans = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=0; i<N; i++) {
            int j = 0;
            while (true) {
                if (arr[i] == 0 && ans[j] == 0) {
                    ans[j] = i+1;
                    break;
                } else if (ans[j] == 0) {
                    arr[i] -= 1;
                }
                j++;
            }
        }
        for (int i=0; i<N; i++) {
            System.out.print(ans[i]+" ");
        }
    }
}
