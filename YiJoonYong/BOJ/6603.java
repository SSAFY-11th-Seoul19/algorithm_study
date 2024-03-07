import java.io.*;

class Main {
    static StringBuilder sb;
    static String[] split;
    static int k;
    static int[] numArr, result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            sb = new StringBuilder();

            split = br.readLine().split(" ");
            if (split[0].equals("0")) {
                break;
            }

            k = Integer.parseInt(split[0]);

            numArr = new int[k];
            result = new int[6];
            for (int i = 1; i <= k; i++) {
                numArr[i-1] = Integer.parseInt(split[i]);
            }

            findNum(0, 0);

            bw.write(sb.toString());
            bw.write('\n');
        }
        bw.flush();
        br.close();
        bw.close();

    }

    public static void findNum(int cnt, int idx) {
        if (cnt == 6) {
            for (int num : result) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = idx; i < k; i++) {
            if (6 - cnt > k - i) {
                continue;
            }
            result[cnt] = numArr[i];
            findNum(cnt+1, i+1);
        }
    }
}