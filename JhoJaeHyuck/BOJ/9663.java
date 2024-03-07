import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] col;
    static int N, ans;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        col = new int[N];

        backtrack(0);

        bw.write(String.valueOf(ans));
        bw.flush();
    }

    static void backtrack(int level) {
        if (level == N) {
            ans++;
            return;
        }

        for (int i = 0; i < N; i++) {
            col[level] = i;
            if (check(level)) {
                backtrack(level + 1);
            }
        }
    }

    static boolean check(int level) {
        for (int i = 0; i < level; i++) {
            if (col[i] == col[level] || Math.abs(col[level] - col[i]) == level - i) {
                return false;
            }
        }
        return true;
    }
}
