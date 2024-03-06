import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map;
    static int N, count;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        map = new char[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j+1] = line.charAt(j << 1);
            }
        }

        recur(1, 2, '-');
        System.out.println(count);
    }

    public static void recur(int r, int c, char status) {   // 0:- 1:| 2:\
        if (r < 1 || N < r || c < 1 || N < c) {
            return;
        }
        if (map[r][c] == '1') {
            return;
        }
        if (r == N && c == N) {
            if (status == '\\') {
                if (map[r - 1][c] == '1' || map[r][c - 1] == '1') {
                    return;
                }
            }
            count++;
            return;
        }

        switch (status) {
            case '-':
                recur(r, c+1, '-');
                recur(r+1, c+1, '\\');
                break;
            case '|':
                recur(r+1, c, '|');
                recur(r+1, c+1, '\\');
                break;
            case '\\':
                if (map[r-1][c] == '1' || map[r][c-1] == '1') {
                    return;
                }
                recur(r, c+1, '-');
                recur(r+1, c, '|');
                recur(r+1, c+1, '\\');
                break;
        }
    }
}
