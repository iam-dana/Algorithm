import java.io.*;
import java.util.*;

public class BOJ_3015_오아시스재결합 {
    static int N, P;
    static long answer;
    static Stack<Node> champagneSuperUglyCunt = new Stack<>();
    static class Node {
        int height, cnt;
        public Node(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++) {
            P = Integer.parseInt(br.readLine());
            Node node = new Node(P, 1);

            while (!champagneSuperUglyCunt.isEmpty() && champagneSuperUglyCunt.peek().height <= P) {
                Node pop = champagneSuperUglyCunt.pop();
                answer += pop.cnt;
                if (pop.height == P) node.cnt += pop.cnt;
            }

            if (!champagneSuperUglyCunt.empty()) answer += 1;
            champagneSuperUglyCunt.push(node);
        }
        System.out.println(answer);
    }
}
