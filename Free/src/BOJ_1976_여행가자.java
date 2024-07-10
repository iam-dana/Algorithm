import java.io.*;
import java.util.*;

public class BOJ_1976_여행가자 {
    static int N, M;
    static int[][] map;
    static int[] plan;
    static String ans = "YES";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        plan = new int[M];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i==j) map[i][j] = 1;
            }
        }


        for (int k=1; k<=N; k++) {
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (map[i][k] == 1 && map[k][j] == 1){
                        map[i][j] = 1;
                    }
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        for (int i=1; i<M; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (map[start][now] == 0) {
                ans = "NO";
                break;
            }
        }

        System.out.println(ans);
    }
}
