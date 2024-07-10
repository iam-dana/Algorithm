import java.io.*;
import java.util.*;

public class BOJ_14940_쉬운최단거리 {
    static int n, m, startX, startY, nx, ny;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Node> q;
    static class Node {
        int x;
        int y;

        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x+" "+y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }
        bfs();
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (graph[i][j] == 1) {
                    if (Math.abs(i-startX) + Math.abs(j-startY) != 1) {
                        graph[i][j] = -1;
                    }
                }
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
    }

    static void bfs() {
        q = new ArrayDeque<>();
        visited = new boolean[n][m];
        q.add(new Node(startX, startY));
        graph[startX][startY] = 0;
        visited[startX][startY] = true;
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i=0; i<4; i++) {
                nx = now.x+dx[i];
                ny = now.y+dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && graph[nx][ny] != 0) {
                    if (!visited[nx][ny]) {
                        graph[nx][ny] += graph[now.x][now.y];
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny));
                    }
                }
            }
        }
    }
}
