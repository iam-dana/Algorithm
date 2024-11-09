import java.io.*;
import java.util.*;

public class BOJ_1922_네트워크연결 {
    static int N, M, a, b, c, answer = 0;
    static Queue<Node> q;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        q = new PriorityQueue<>();
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) parents[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            q.add(new Node(a, b, c));
        }

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (find(now.a) != find(now.b)) {
                union(now.a, now.b);
                answer += now.cost;
            }
        }
        System.out.println(answer);
    }

    static int find(int a) {
        if (a != parents[a]) parents[a] = find(parents[a]);
        return parents[a];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parents[b] = parents[a];
        else parents[a] = parents[b];
    }

    static class Node implements Comparable<Node> {
        int a, b, cost;

        public Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

}
