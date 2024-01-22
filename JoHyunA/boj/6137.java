import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static final int MAX_LENGTH = 80;
    public static int N;
    public static char[] S;
    public static StringBuilder T;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        S = new char[N];
        for(int i=0;i<N;i++){
            S[i] = br.readLine().charAt(0);
        }

        findString();
        for(int i=0;i<N;i++){
            bw.write(T.charAt(i));
            if((i+1)%MAX_LENGTH == 0){
                bw.write("\n");
            }
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findString(){
        T = new StringBuilder();
        int start = 0;
        int end = N-1;

        while(start<=end) {
            char startChar = S[start];
            char endChar = S[end];

            if (startChar < endChar) {
                T.append(startChar);
                start++;
                continue;
            } else if (startChar > endChar) {
                T.append(endChar);
                end--;
                continue;
            }

            int tmpStart = start + 1;
            int tmpEnd = end - 1;
            boolean plusChar = false;
            while (tmpStart <= tmpEnd) {
                char tmpStartChar = S[tmpStart];
                char tmpEndChar = S[tmpEnd];

                if (tmpStartChar < tmpEndChar) {
                    T.append(startChar);
                    start++;
                    plusChar = true;
                    break;
                } else if (tmpStartChar > tmpEndChar) {
                    T.append(endChar);
                    end--;
                    plusChar = true;
                    break;
                }

                tmpStart++;
                tmpEnd--;
            }

            if (!plusChar) {
                T.append(startChar);
                start++;
            }
        }
    }
}