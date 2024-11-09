import java.io.*;
import java.util.*;

public class BOJ_1504_특정한최단경로 {
    static int N, E, a, b, c, v1, v2, answer=Integer.MAX_VALUE;
    static int[] distance;
    static ArrayList<Node>[] graph;
    static Queue<Node> q;
    static class Node implements Comparable<Node>{
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }

        @Override
        public String toString() {
            return end+" "+cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for (int i=0; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        if (dijkstra(1)[v1] != Integer.MAX_VALUE && dijkstra(v1)[v2] != Integer.MAX_VALUE && dijkstra(v2)[N] != Integer.MAX_VALUE) {
            answer = Math.min(answer, dijkstra(1)[v1]+dijkstra(v1)[v2]+dijkstra(v2)[N]);
        }
        if (dijkstra(1)[v2] != Integer.MAX_VALUE && dijkstra(v2)[v1] != Integer.MAX_VALUE && dijkstra(v1)[N] != Integer.MAX_VALUE) {
            answer = Math.min(answer, dijkstra(1)[v2]+dijkstra(v2)[v1]+dijkstra(v1)[N]);
        }
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
    static int[] dijkstra(int start) {
        q = new PriorityQueue<>();
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (distance[now.end] < now.cost) continue;
            for (Node next : graph[now.end]) {
                if (distance[next.end] > now.cost+next.cost) {
                    distance[next.end] = now.cost+next.cost;
                    q.add(new Node(next.end, now.cost+next.cost));
                }
            }
        }
        return distance;
    }
}
