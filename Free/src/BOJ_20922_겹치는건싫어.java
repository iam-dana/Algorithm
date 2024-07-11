import java.io.*;
import java.util.*;

public class BOJ_20922_겹치는건싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] num = new int[100001];
        int ans = 0;
        int left = 0;
        int right = 0;

        while (right < N){
           while (right < N && num[arr[right]]+1 <= K) {
               num[arr[right]] += 1;
               right += 1;
           }
           ans = Math.max(ans, right-left);
           num[arr[left]] -= 1;
           left += 1;
        }
        System.out.println(ans);
    }
}
