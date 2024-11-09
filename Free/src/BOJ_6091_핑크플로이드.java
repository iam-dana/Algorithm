import java.io.*;
import java.util.*;

public class BOJ_6091_핑크플로이드 {
    static int N, tmp;
    static int[] parents;
    static Queue<Node> q;
    static List<Node> list;
    static List[] ans;
    static class Node implements Comparable<Node>{
        int start, end, cost;
        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    ", cost=" + cost +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        q = new PriorityQueue<>();
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) parents[i] = i;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = i + 1; j <= N; j++) {
                tmp = Integer.parseInt(st.nextToken());
                q.add(new Node(i, j, tmp));
            }
        }

        list = new ArrayList<>();
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (find(now.start) != find(now.end)) {
                union(now.start, now.end);
                list.add(new Node(now.start, now.end, 0));
            }
        }
        ans = new ArrayList[N];
        for (int i=0; i<N; i++) ans[i] = new ArrayList<Integer>();

        for (Node node : list) {
            ans[node.start-1].add(node.end);
            ans[node.end-1].add(node.start);
        }

        for (List a : ans) {
            Collections.sort(a);
            sb.append(a.size());
            for (Object ele: a) {
                sb.append(" ");
                sb.append(ele);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int find(int a) {
        if (parents[a] != a) parents[a] = find(parents[a]);
        return parents[a];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) parents[a] = parents[b];
        else  parents[b] = parents[a];
    }
}

