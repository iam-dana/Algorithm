import java.io.*;
import java.util.*;

public class BOJ_22866_탑보기 {
    static int N, size, height[], near[], cnt[];
    static Stack<Integer> stack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        height = new int[N+1];
        near = new int[N+1];
        cnt = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            near[i] = -100_000;
        }

        stack = new Stack<>();
        for(int i=1; i<=N; i++){
            while(!stack.isEmpty() && height[stack.peek()] <= height[i]) stack.pop();
            cnt[i] = stack.size();
            if(cnt[i] > 0) near[i] = stack.peek();
            stack.push(i);
        }

        stack = new Stack<>();
        for(int i=N; i>0; i--){
            while(!stack.isEmpty() && height[stack.peek()] <= height[i]) stack.pop();
            size = stack.size();
            cnt[i] += size;
            if(size > 0 && stack.peek()-i < i-near[i]) near[i] = stack.peek();
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            sb.append(cnt[i]);
            if (cnt[i] > 0) sb.append(" ").append(near[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }


}
