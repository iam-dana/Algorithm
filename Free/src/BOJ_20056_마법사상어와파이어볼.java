import java.util.*;
import java.io.*;

public class BOJ_20056_마법사상어와파이어볼 {
    static int N, M, K, r, c, m, s, d;
    static ArrayList<FireBall> list;
    static ArrayList<FireBall> newFireBalls;
    static ArrayList<int[]> fires;
    static int[][] board;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static class FireBall {
        int r, c, m, d, s;
        public FireBall(int r, int c, int m, int d, int s) {
            this.r=r;
            this.c=c;
            this.m=m;
            this.d=d;
            this.s=s;
        }
        @Override
        public String toString() {
            return r+" "+c+" "+m+" "+d+" "+s;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());  // 격자
        M = Integer.parseInt(st.nextToken());  // 파이어볼의 개수
        K = Integer.parseInt(st.nextToken());

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            list.add(new FireBall(r, c, m, d, s));
        }

    }
    static void spread() {
        board = new int[N][N];
        fires = new ArrayList<>();
        newFireBalls = new ArrayList<>();

        for (FireBall fireBall : list) {
            int nr = fireBall.r+dx[fireBall.d];
            int nc = fireBall.c+dy[fireBall.d];
            if (nr >= N || nr < 0 || nc >= N || nc < 0) continue;
            if (board[nr][nc] == 1) {
                fires.add(new int[]{nr, nc});
            } else if (board[nr][nc] == 0){
                board[nr][nc] += 1;
            }



        }
    }
}
