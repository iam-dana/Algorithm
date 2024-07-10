import java.util.*;
import java.io.*;

public class BOJ_11501_주식 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];
            for (int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int now = Integer.MIN_VALUE;
            long ans = 0;

            for (int i=N-1; i>=0; i--) {
                if (arr[i] > now) {
                    now = arr[i];
                } else if (arr[i] < now) {
                    ans += now - arr[i];
                }
            }
            System.out.println(ans);
        }
    }
}
