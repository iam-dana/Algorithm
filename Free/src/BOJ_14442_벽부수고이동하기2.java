import java.io.*;
import java.util.*;

public class BOJ_14442_벽부수고이동하기2 {
    static int N, M, K, ans=-1, x, y, nx, ny;
    static int[][] map;
    static boolean[][][] visited;
    static Queue<Node> q;
    static String[] str;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Node {
        int x, y, dist, boom;
        public Node(int x, int y, int dist, int boom) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.boom = boom;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K+1];
        for (int i=0; i<N; i++) {
            str = br.readLine().split("");
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            x = now.x;
            y = now.y;
            if (x == N-1 && y == M-1) {
                ans = now.dist;
                return;
            }

            for (int i=0; i<4; i++) {
                nx = x+dx[i];
                ny = y+dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == 0 && !visited[nx][ny][now.boom]) {
                    visited[nx][ny][now.boom] = true;
                    q.add(new Node(nx, ny, now.dist+1, now.boom));
                } else {
                    if (now.boom < K && !visited[nx][ny][now.boom+1]) {
                        visited[nx][ny][now.boom+1] = true;
                        q.add(new Node(nx, ny, now.dist+1, now.boom+1));
                    }
                }
            }
        }
    }
}
