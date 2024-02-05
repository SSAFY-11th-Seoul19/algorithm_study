import java.io.*;

class Main {
    static int N;
    static int[] map;
    static boolean[] isVisited;
    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        // 각 행에 하나의 퀸이 들어가야함. -> idx:행, val:열
        map = new int[N];
        isVisited = new boolean[N];

        findQueen(0);
        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
    }

    public static void findQueen(int row) {
        if (row == N) {
            answer++;
            return;
        }

        for (int col = 0; col < N; col++) {
            // check column
            if (isVisited[col]) {
                continue;
            }

            // check diagonal
            if (checkDiagonal(row, col))
                continue;

            isVisited[col] = true;
            map[row] = col;
            findQueen(row+1);
            isVisited[col] =false;
        }
    }
    private static boolean checkDiagonal(int row, int col) {
        for (int j = 0; j< row; j++) {
            // j:비교 행, map[j]:비교 열, row:대상 행, col:대상 열
            if (Math.abs(j- row) == Math.abs(map[j]- col)) {
                return true;
            }
        }
        return false;
    }
}