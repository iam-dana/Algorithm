import java.io.*;
import java.util.*;

public class BOJ_16236_아기상어 {
    static int N, num, sharkSize, sharkX, sharkY, fish, ans;
    static boolean flag, flag2;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static Queue<Node> q;
    static class Node implements Comparable<Node>{
        int x, y, dist;

        public Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o1) {
            if (this.dist != o1.dist) return this.dist-o1.dist;
            else if (this.x != o1.x) return this.x - o1.x;
            else return this.y-o1.y;
        }

        @Override
        public String toString() {
            return x+" "+y+" "+dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                num = Integer.parseInt(st.nextToken());
                if (num == 9) {
                    sharkX = i;
                    sharkY = j;
                    continue;
                }
                map[i][j] = num;
            }
        }
        sharkSize = 2;
        fish = 0;
        ans = 0;

        do {
            flag2 = false;
            bfs();
        } while (flag2);

        if (!flag) System.out.println(0);
        else System.out.println(ans);
    }

    static void bfs() {
        visited = new boolean[N][N];
        visited[sharkX][sharkY] = true;
        q = new PriorityQueue<>();
        q.add(new Node(sharkX, sharkY, 0));

        while (!q.isEmpty()){
            Node now = q.poll();
            if (map[now.x][now.y] != 0 && map[now.x][now.y] < sharkSize) {
                map[now.x][now.y] = 0;
                fish += 1;
                ans += now.dist;
                flag = true;
                flag2 = true;
                sharkX = now.x;
                sharkY = now.y;
                break;
            }
            for (int i=0; i<4; i++) {
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if (0 > nx || nx >= N || 0 > ny || ny >= N || visited[nx][ny] || map[nx][ny] > sharkSize) continue;
                q.add(new Node(nx, ny, now.dist+1));
                visited[nx][ny] = true;
            }
        }
        if (fish == sharkSize) {
            sharkSize += 1;
            fish = 0;
        }
    }
}
