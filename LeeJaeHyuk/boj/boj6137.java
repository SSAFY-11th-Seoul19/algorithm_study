import java.util.*;
import java.io.*;

public class Study2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[] str = new char[N];
        for(int i=0; i<N; i++) {
            str[i] = br.readLine().charAt(0);
        }

        // 정방향 문자열
        String pStr = String.valueOf(str);
        // 역방향 문자열
        String nStr = new StringBuilder(pStr).reverse().toString();

        ArrayList<Character> answer = new ArrayList<>();

        // 정방향 문자열 인덱스
        int pStrIdx = 0;
        // 역방향 문자열 인덱스
        int nStrIdx = 0;

        // 두 문자열 인덱스의 합이 전체 문자열 길이를 넘지 않을때까지 반복
        while(pStrIdx + nStrIdx < N) {
            char lvalue = pStr.charAt(pStrIdx);
            char rvalue = nStr.charAt(nStrIdx);

            if(lvalue < rvalue) {
                answer.add(lvalue);
                pStrIdx += 1;
            }
            else if(lvalue > rvalue) {
                answer.add(rvalue);
                nStrIdx += 1;
            }
            else {
                // 정방향 문자열 인덱스와 역방향 문자열 인덱스가 가리키는 문자가 같을 경우

                // 문자열 인덱스가 가리키는 곳을 시작점으로 해서 문자열을 자른다.
                String p = pStr.substring(pStrIdx);
                String n = nStr.substring(nStrIdx);

                // 자른 문자열을 비교해서 정방향 문자열이 더 작다(사전순으로 빠르다)고 판단되면 정방향 문자열에서 문자 추출
                if(p.compareTo(n) < 0) {
                    answer.add(lvalue);
                    pStrIdx += 1;
                }
                // 역방향 문자열이 더 작다(사전순으로 빠르다)고 판단되면 역방향 문자열에서 문자 추출
                else {
                    answer.add(rvalue);
                    nStrIdx += 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        for(char c : answer) {
            sb.append(c);
            cnt++;
            if(cnt >= 80) {
                sb.append("\n");
                cnt = 0;
            }
        }
        System.out.println(sb);
    }
}
