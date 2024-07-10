import java.util.*;
import java.io.*;

public class BOJ_20006_랭킹전_대기열 {
    static int p, m, l;   // 플레이어 수, 방의 정원, 플레이어의 레벨
    static String n; // 플레이어 닉네임

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i=0; i<p; i++) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            n = st.nextToken();
          
            }
        }
    }

