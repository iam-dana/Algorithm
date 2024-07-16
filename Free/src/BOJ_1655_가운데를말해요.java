import java.util.*;
import java.io.*;

public class BOJ_1655_가운데를말해요 {
    static int N, num;
    static Queue<Integer> maxHeap;
    static Queue<Integer> minHeap;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        maxHeap = new PriorityQueue<>((o1,o2) -> o2-o1);  // left
        minHeap = new PriorityQueue<>();  // right

        for(int i=0; i<N; i++) {
            num = Integer.parseInt(br.readLine());
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.add(num);
                if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(minHeap.poll());
                }
            } else {
                minHeap.add(num);
                if (maxHeap.peek() > minHeap.peek()) {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(minHeap.poll());
                }
            }
            System.out.println(maxHeap.peek());
        }
    }
}
