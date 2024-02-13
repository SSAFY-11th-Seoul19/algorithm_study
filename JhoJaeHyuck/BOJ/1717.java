import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // Union-Find 전용 N+1 사이즈 배열
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // command가 0이면 그룹 만들기만
            if (command == 0) {
                makeGroup(a, b);
                continue;
            }

            // 같은 그룹인지 체크한 결과 저장
            if (isSameGroup(a, b)) {
                sb.append("YES\n");
            }
            else {
                sb.append("NO\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static int findParent(int a) {
        // 부모와 자신이 같으면 끝이니까 return
        if (arr[a] == a) {
            return a;
        }
        // 재귀 이왕 하는김에 parent를 싹다 바꿔오면서 return하기
        return arr[a] = findParent(arr[a]);
    }

    static boolean isSameGroup(int a, int b) {
        // 부모와 부모를 비교해서 같으면 true
        if (findParent(a) == findParent(b)) {
            return true;
        }
        return false;
    }

    static void makeGroup(int a, int b) {
        // 그룹을 만들 때에도 부모끼리 묶어야한다
        arr[findParent(b)] = findParent(a);
    }
}
