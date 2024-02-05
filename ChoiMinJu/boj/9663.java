import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// boj6603 - 로또
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] board; // 인덱스를 행으로 사용, 값을 열로 사용
    static int N, cnt;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new int[N];

        for (int i=0; i<N; i++) {
            board[0] = i; // 보드판 0번째 row, i번째 col 에 퀸 놓기
            nqueen(1); // 다음 row 으로 진입
        }

        System.out.println(cnt);
    }

    static void nqueen(int row) { // x는 row
        if (row == N) {
            cnt++;
            return;
        }
        // row 행 y 열에 퀸을 놓을 것이라고 가정
        for (int y=0; y<N; y++) {
            boolean isOk = true; // y번째 column에 퀸을 놓을 수 있는지
            // 이전 행들을 모두 체크 -> 한번이라도 false인 행이 있으면 안된다!
            for (int x=0; x<row; x++) { // x는 row행 이전 행들 (0행 ~ (row-1)행)
                if (board[x] == y) isOk = false; // 같은 열이 있으면 안된다
                if ((x-row == board[x]-y) || (x-row == y-board[x])) isOk = false; // 기울기가 1 or -1 이면 안된다
            }
            if (isOk) {
                board[row] = y;
                nqueen(row+1); // 다음 row로
            }
        }
    }
}
