import java.util.*;
import java.io.*;

public class BOJ_2665_미로만들기 {
    static int n, black, nx, ny, ans=-1;
    static String[] str;
    static String[][] graph;
    static boolean[][][] visited;
    static Deque<Node> q;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x, y, count;
        public Node (int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new String[n][n];
        for (int i=0; i<n; i++) {
            str = br.readLine().split("");
            for (int j=0; j<n; j++) {
                graph[i][j] = str[j];
                if(graph[i][j].equals("0")) black++;
            }
        }
        for (int i=0; i<=black; i++) {
            bfs(i);
            if (ans >= 0) break;
        }
        System.out.println(ans);
    }
    static void bfs(int limit) {
        visited = new boolean[n][n][limit+1];
        q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == n-1 && now.y == n-1) {
                ans = now.count;
                return;
            }
            for (int i=0; i<4; i++) {
                nx = now.x+dx[i];
                ny = now.y+dy[i];
                if (0 > nx || nx >= n || 0 > ny || ny >= n) continue;
                if (graph[nx][ny].equals("1") && !visited[nx][ny][now.count]) {
                    q.add(new Node(nx, ny, now.count));
                    visited[nx][ny][now.count] = true;
                } else if (now.count < limit && graph[nx][ny].equals("0") && !visited[nx][ny][now.count+1]) {
                    q.add(new Node(nx, ny, now.count+1));
                    visited[nx][ny][now.count+1] = true;
                }
            }
        }
    }
}
