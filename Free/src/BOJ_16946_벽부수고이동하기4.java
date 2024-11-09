import java.io.*;
import java.util.*;

public class BOJ_16946_벽부수고이동하기4 {
    static int N, M, nx, ny, count, num, tmp;
    static int[][] graph;
    static int[][] ans;
    static String[] str;
    static List<int[]> walls;
    static Map<Integer, Integer> map;
    static Set<Integer> set;
    static Queue<Node> q;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        ans = new int[N][M];
        walls = new ArrayList<>();
        map = new HashMap<>();
        for (int i=0; i<N; i++) {
            str = br.readLine().split("");
            for (int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(str[j]);

            }
        }
        findMap();
        bfs();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) sb.append(ans[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void findMap() {
        num = 2;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (graph[i][j] == 1) walls.add(new int[]{i, j});
                else {
                    if (graph[i][j] == 0) {
                        count = 1;
                        graph[i][j] = num;
                        q = new LinkedList<>();
                        q.add(new Node(i, j));
                        while (!q.isEmpty()) {
                            Node now = q.poll();
                            for (int k=0; k<4; k++) {
                                nx = now.x+dx[k];
                                ny = now.y+dy[k];
                                if (0 > nx || nx >= N || 0 > ny || ny >= M) continue;
                                if (graph[nx][ny] == 0) {
                                    graph[nx][ny] = num;
                                    count++;
                                    q.add(new Node(nx, ny));
                                }
                            }
                        }
                        map.put(num, count);
                        num++;
                    }
                }
            }
        }
    }
    static void bfs() {
        visited = new boolean[N][M];
        for (int[] wall : walls) {
            set = new HashSet<>();
            visited[wall[0]][wall[1]] = true;
            for (int i=0; i<4; i++) {
                nx = wall[0]+dx[i];
                ny = wall[1]+dy[i];
                if (0 > nx || nx >= N || 0 > ny || ny >= M) continue;
                if (graph[nx][ny] > 1 && !visited[nx][ny]) {
                    set.add(graph[nx][ny]);
                }
            }
            tmp = 1;
            for (int s : set) tmp += map.get(s);
            ans[wall[0]][wall[1]] = tmp%10;
        }
    }
}
