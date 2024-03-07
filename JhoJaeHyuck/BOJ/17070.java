import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, ans;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        func(1, 2, 0);

        System.out.println(ans);
    }

    static void func(int row, int col, int state) {
        if (row == N && col == N) {
            ans++;
            return;
        }

        for (int dir = 0; dir < 3; dir++) {
            // 직각 이동 안됨
            if (dir == 0 && state == 1) continue;
            if (dir == 1 && state == 0) continue;

            // 오른쪽
            if (dir == 0 && col < N && arr[row][col + 1] == 0) {
                func(row, col + 1, 0);
            }
            // 아래쪽
            if (dir == 1 && row < N && arr[row + 1][col] == 0) {
                func(row + 1, col, 1);
            }
            // 대각선
            if (dir == 2 && row < N && col < N && arr[row + 1][col + 1] == 0) {
                if (arr[row + 1][col] != 0 || arr[row][col + 1] != 0) continue; // 벽에 긁힘
                func(row + 1, col + 1, 2);
            }
        }
    }
}
