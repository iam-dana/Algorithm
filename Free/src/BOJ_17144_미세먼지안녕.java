import java.util.*;
import java.io.*;

public class BOJ_17144_미세먼지안녕 {
    static int R, C, T, nx, ny, tmp, next, ans=0;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};  // 아래, 위, 오른쪽, 왼쪽
    static int[] dy = {0, 0, 1, -1};
    static List<Node> machine = new ArrayList<>();
    static List<Node> dust = new ArrayList<>();
    static List<Integer> spreadDir;
    static List<Node> newDust;
    static class Node {
        int x, y, amount;

        public Node(int x, int y, int amount) {
            this.x=x;
            this.y=y;
            this.amount=amount;
        }

        public Node(int x, int y) {
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString() {
            return x+" "+y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                   machine.add(new Node(i, j));
                } else if (map[i][j] != 0) {
                    dust.add(new Node(i, j, map[i][j]));
                }
            }
        }
        while (T-->0) {
            spread();
            print();
            System.out.println();
            clean();
            print();
            System.out.println();
        }

        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (map[i][j] != -1 && map[i][j] != 0) {
                    ans += map[i][j];
                }
            }
        }

        System.out.println(ans);
    }

    static void print() {
        for (int i=0; i<R; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static void spread() {
        newDust = new ArrayList<>();
        for (int i=0; i<dust.size(); i++) {
            Node now = dust.get(i);
            spreadDir = new ArrayList<>();
            for (int j=0; j<4; j++) {
                nx = now.x+dx[j];
                ny = now.y+dy[j];
                if (0 > nx || nx >= R || 0 > ny || ny >= C || map[nx][ny]==-1) continue;
                spreadDir.add(j);
            }
            for (int d : spreadDir) {
                map[now.x+dx[d]][now.y+dy[d]] += now.amount/5;
            }
            map[now.x][now.y] -= (now.amount/5)*spreadDir.size();
        }
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (map[i][j] != 0 && map[i][j] != -1) {
                    newDust.add(new Node(i, j, map[i][j]));
                }
            }
        }
        dust = newDust;
    }

    static void clean() {
        Node m = machine.get(0);
        nx = m.x;
        ny = m.y+1;
        tmp = map[nx][ny];
        map[nx][ny] = 0;
        while (ny < C-1) {
            ny += 1;
            next = map[nx][ny];
            map[nx][ny] = tmp;
            tmp = next;
        }

        while (nx > 0) {
            nx -= 1;
            next = map[nx][ny];
            map[nx][ny] = tmp;
            tmp = next;
        }

        while (ny > 0) {
            ny -= 1;
            next = map[nx][ny];
            map[nx][ny] = tmp;
            tmp = next;
        }

        while (nx < m.x-1) {
            nx += 1;
            next = map[nx][ny];
            map[nx][ny] = tmp;
            tmp = next;
        }

        m = machine.get(1);
        nx = m.x;
        ny = m.y+1;
        tmp = map[nx][ny];
        map[nx][ny] = 0;
        while (ny < C-1) {
            ny += 1;
            next = map[nx][ny];
            map[nx][ny] = tmp;
            tmp = next;
        }

        while (nx < R-1) {
            nx += 1;
            next = map[nx][ny];
            map[nx][ny] = tmp;
            tmp = next;
        }

        while (ny > 0) {
            ny -= 1;
            next = map[nx][ny];
            map[nx][ny] = tmp;
            tmp = next;
        }

        while (nx > m.x+1) {
            nx -= 1;
            next = map[nx][ny];
            map[nx][ny] = tmp;
            tmp = next;
        }
    }
}

