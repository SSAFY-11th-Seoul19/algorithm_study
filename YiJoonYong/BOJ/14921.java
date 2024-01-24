import java.io.*;
class Main {
    static final int MAX_VALUE = 200_000_001;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] liquidArr = new int[N];

        String[] split = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            liquidArr[i] = Integer.parseInt(split[i]);
        }

        int lc = 0;
        int rc = N-1;

        int result = MAX_VALUE;
        int resultAbs = MAX_VALUE;
        while (lc < rc) {
            int candidate = liquidArr[lc] + liquidArr[rc];
            int candiAbs = Math.abs(candidate);

            if (candiAbs < resultAbs) {
                resultAbs = candiAbs;
                result = candidate;
            }

            if (candidate < 0) {
                lc++;
                continue;
            }
            if (candidate > 0) {
                rc--;
                continue;
            }

            // candidate == 0
            break;
        }

        System.out.println(result);
    }
}
