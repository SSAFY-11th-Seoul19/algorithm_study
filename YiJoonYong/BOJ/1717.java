import java.io.*;

public class Main {
    static int[] nodeInfo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]); // n+1개의 집합
        int m = Integer.parseInt(split[1]); // 입력 연산의 개수

        nodeInfo = new int[n+1];
        for (int i = 0; i <= n; i++) {
            nodeInfo[i] = i;
        }

        for (int time = 1; time <= m; time++) {
            split = br.readLine().split(" ");
            char cmd = split[0].charAt(0);
            int a = Integer.parseInt(split[1]);
            int b = Integer.parseInt(split[2]);

            if (cmd == '0') {
                union(a, b);
                continue;
            }
            sb.append((find(a) == find(b)) ? "YES" : "NO").append('\n');

        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void union(int a, int b) {
        if (a == b) {
            return;
        }

        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return;
        }

        nodeInfo[rootB] = rootA;
    }

    public static int find(int node) {
        int child = node;
        int parent = nodeInfo[child];
        while (parent != child) {
            child = parent;
            parent = nodeInfo[child];
        }
        nodeInfo[node] = parent;
        return parent;
    }
}