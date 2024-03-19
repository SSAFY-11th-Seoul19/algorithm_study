
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// boj 집합의 표현
public class Main {
    static final int UNION = 0; // 합집합
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stk;
    static StringBuilder sb = new StringBuilder();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        parent = new int[N+1];
        // 처음에 parent 배열은 parent[i] = i 형태로 자기자신을 가리킨다
        for (int i=0; i<N+1; i++) {
            parent[i] = i;
        }

        for (int i=0; i<M; i++) {
            stk = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            if (command == UNION) {
                union(a, b); // 합집합
            } else {
                if (find(a) == find(b)) sb.append("YES").append("\n"); // 교집합 확인
                else sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
    }

    // x의 부모를 찾는 연산
    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    // x > y의 경우, x의 부모를 y의 부모로 치환하는 연산 (y > x의 경우 반대)
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            if (x > y) parent[x] = y;
            else parent[y] = x;
        }
    }
}
