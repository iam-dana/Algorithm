import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16933_벽부수고이동하기3 {
    static int N, M, K, nx, ny, ans = -1;
    static String[] str;
    static int[][] map;
    static boolean[][][] visited;
    static Queue<Node> q;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split("");
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(str[j]);
        }
        visited = new boolean[N][M][K + 1];
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        q = new ArrayDeque<>();
        q.add(new Node(0, 0, 1, 0, true));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == N - 1 && now.y == M - 1) {
                ans = now.dist;
                return;
            }
            for (int i = 0; i < 4; i++) {
                nx = now.x + dx[i];
                ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] == 0) {
                    if (!visited[nx][ny][now.boom]) {
                        q.add(new Node(nx, ny, now.dist + 1, now.boom, !now.isDay));
                        visited[nx][ny][now.boom] = true;
                    }
                } else {
                    if (now.isDay && now.boom < K && !visited[nx][ny][now.boom + 1]) {
                        q.add(new Node(nx, ny, now.dist + 1, now.boom + 1, false));
                        visited[nx][ny][now.boom + 1] = true;
                    } else if (!now.isDay && now.boom < K) {
                        q.add(new Node(now.x, now.y, now.dist + 1, now.boom, true));
                    }
                }
            }
        }
    }

    static class Node {
        int x, y, dist, boom;
        boolean isDay;

        public Node(int x, int y, int dist, int boom, boolean isDay) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.boom = boom;
            this.isDay = isDay;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + dist + " " + boom + " " + isDay;
        }
    }
}

