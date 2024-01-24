import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] S = new char[N];
        for (int i = 0; i < N; i++) {
            S[i] = br.readLine().charAt(0);
        }

        StringBuilder sb = new StringBuilder();
        int lc = 0;
        int rc = N-1;
        while (lc < rc) {
            if (S[lc] < S[rc]) {
                sb.append(S[lc++]);
                continue;
            }
            if (S[lc] > S[rc]) {
                sb.append(S[rc--]);
                continue;
            }

            // S[lc] == S[rc]
            int tlc = lc;
            int trc = rc;
            while (tlc < trc) {
                if (S[tlc] != S[trc]) break;
                tlc++;
                trc--;
            }

            if (S[tlc] < S[trc]) {
                sb.append(S[lc++]);
            } else { // S[tlc] > S[trc] or S[tlc] == S[trc]
                sb.append(S[rc--]);
            }
        }
        sb.append(S[lc]);   // lc == rc

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.append(sb.charAt(i));
            if ((i+1) % 80 == 0)
                bw.append('\n');
        }
        bw.flush();
        bw.close();
    }
}