import java.util.*;
import java.io.*;

public class BOJ_1446_지름길 {
    static int N, D, start, end, dist, now;
    static int[] road;
    static List<List<Node>> list;
    static class Node {
        int dist, cost;
        public Node(int dist, int cost) {
            this.dist = dist;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        road = new int[100001];
        list = new ArrayList<>();
        for (int i=0; i<=100000; i++) {
            road[i] = i;
            list.add(new ArrayList<>());
        }

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            list.get(start).add(new Node(end, dist));
        }

        dijkstra(0);
        System.out.println(road[D]);
    }

    static void dijkstra(int start) {
        if (start > D) return;

        if (road[start+1] > road[start]+1) road[start+1] = road[start]+1;

        for (int i=0; i<list.get(start).size(); i++) {
            if (road[start]+list.get(start).get(i).cost < road[list.get(start).get(i).dist]) {
                road[list.get(start).get(i).dist] = road[start]+list.get(start).get(i).cost;
            }
        }
        dijkstra(start+1);
    }
}
