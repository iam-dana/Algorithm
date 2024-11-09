import java.io.*;
import java.util.*;

public class BOJ_16235_나무재테크 {
    static int N, M, K, x, y , age;
    static int[][] map, food;
    static List<Tree> trees = new ArrayList<>();
    static List<Tree> newTrees;
    static Queue<Tree> deadTrees = new ArrayDeque<>();
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Tree {
        int x, y, age;
        boolean dead;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public String toString() {
            return x+" "+y+" "+age+" "+dead;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        food = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = 5;
                food[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x-1, y-1, age));
        }

        Collections.sort(trees, (t1, t2) -> t1.age-t2.age);
        for (int i=0; i<K; i++) {
            spring();
            summer();
            fall();
            winter();
        }
        System.out.println(trees.size());
    }

    public static void spring() {
        for (int i=0; i<trees.size(); i++) {
            Tree tree = trees.get(i);
            if (map[tree.x][tree.y] < tree.age) {
                deadTrees.add(new Tree(tree.x, tree.y, tree.age));
                tree.dead = true;
                continue;
            }
            map[tree.x][tree.y] -= tree.age;
            tree.age++;
        }
    }

    public static void summer() {
        while (!deadTrees.isEmpty()) {
            Tree deadTree = deadTrees.poll();
            map[deadTree.x][deadTree.y] += deadTree.age/2;
        }
    }

    public static void fall() {
        newTrees = new ArrayList<>();
        for (int i=0; i<trees.size(); i++) {
            Tree tree = trees.get(i);
            if (tree.dead) continue;
            if (tree.age%5 != 0) continue;
            for (int j=0; j<8; j++) {
                int nx = tree.x+dx[j];
                int ny = tree.y+dy[j];
                if (0 > nx || nx >= N || 0 > ny || ny >= N) continue;
                newTrees.add(new Tree(nx, ny, 1));
            }
        }
        for (Tree tree : trees) {
            if (tree.dead) continue;
            newTrees.add(tree);
        }
        trees = newTrees;
    }

    public static void winter() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                map[i][j] += food[i][j];
            }
        }
    }
}
