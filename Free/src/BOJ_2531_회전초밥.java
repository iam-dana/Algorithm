import java.util.*;
import java.io.*;

public class BOJ_2531_회전초밥 {
    static int N, d, k, c, num, belt[], sushi[], left, right, tmp, ans;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 접시 수
        d = Integer.parseInt(st.nextToken());  // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken());  // 연속해서 먹는 접시의 수
        c = Integer.parseInt(st.nextToken());  // 쿠폰 번호

        belt = new int[N];
        sushi = new int[d+1];

        for (int i=0; i<N; i++) {
            num = Integer.parseInt(br.readLine());
            belt[i] = num;
        }

        tmp = 0;
        for (int i=0; i<k; i++) {
            sushi[belt[i]] += 1;
            if (sushi[belt[i]] == 1) {
                tmp += 1;
            }
        }
        ans = tmp;
        left = 0;
        right = k-1;

        while (left <= N-1) {
            sushi[belt[left]] -= 1;
            if (sushi[belt[left]] == 0) tmp -= 1;

            left += 1;
            right = (left+k-1)%N;

            sushi[belt[right]] += 1;
            if (sushi[belt[right]] == 1) tmp += 1;

            if (sushi[c] == 0) ans = Math.max(ans, tmp+1);
            else ans = Math.max(ans, tmp);
        }
        System.out.println(ans);
    }
}
