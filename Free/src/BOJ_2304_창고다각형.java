import java.io.*;
import java.util.*;

public class BOJ_2304_창고다각형 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            arr[i][0] = L;
            arr[i][1] = H;
        }

        Arrays.sort(arr, (o1, o2) -> {
            return o1[0]-o2[0];
        });

//        System.out.println(Arrays.deepToString(arr));

        int max = 0;
        int maxL = 0;
        int highPoint = 0;
        int ans = 0;

        for (int i=0; i<N; i++) {
            if (arr[i][1] >= max) {
                ans += max*(arr[i][0]-maxL);
                max = arr[i][1];
                maxL = arr[i][0];
                highPoint = i;
            }
        }

        maxL = arr[N-1][0];
        max = arr[N-1][1];
        for (int i=N-1; i>=highPoint; i--) {
            if (arr[i][1] >= max) {
                ans += max*(maxL-arr[i][0]);
                max = arr[i][1];
                maxL = arr[i][0];
            }
        }
        ans += arr[highPoint][1];
        System.out.println(ans);
    }
}
