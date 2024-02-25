import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 최대 9개의 연산자. 그런데 첫번째 괄호는 스킵. 따라서 8개의 연산자. 부분집합 2^8 256개.
 우선 괄호연산을 선택을 다하면 거기서 그것을 바탕으로 연산 시작.

 연산자 idx 테이블 만들기 우선 연산인지 체크하는 불리언 테이블

 재귀(opIdx)
    if(opIdx >= op개수-1)
        int result = 재귀로 연산 계산(opIdx);
        if (result > max) max = result
        return;

        현재 인덱스에 괄호가 없는경우
        재귀(opIdx + 1);

        현재 인덱스에 괄호를 치는 경우 다다음 연산자로.
        check[i] = true;
        재귀(opIdx + 2);

 재귀연산(opIdx)
    if(opIdx <= 0)
        return arr[0];

    괄호가 없는 경우 재귀연산(opIdx-1) op  str[(opIdx+1) * 2]
    괄호가 있는경우는 플래그를 전달해서 다음 연산자에서 한꺼번에 처리하도록 한다. 

 */

public class Main {
    static int N, OP;
    static String str;
    static int ans = Integer.MIN_VALUE;
    static boolean[] checks;

    static int calc(int opIdx, boolean flag){
        if(opIdx < 0){
            return str.charAt(0) - '0';
        }

        int result = 0;
        if(checks[opIdx])
            result = calc(opIdx-1, true);
        else{
            result = calc(opIdx -1, false);

            int right = 0;
            if(flag){
                int _left = str.charAt((opIdx + 2) * 2 - 2) - '0';
                char prevOp = str.charAt((opIdx + 2) * 2 -1);
                int _right = str.charAt((opIdx + 2) * 2) - '0';
                switch(prevOp){
                    case '+':
                        right = _left + _right;
                        break;
                    case '-':
                        right = _left - _right;
                        break;
                    case '*':
                        right = _left * _right;
                        break;
                }
            }
            else{
                right = str.charAt((opIdx+1) * 2) - '0';
            }

            char op = str.charAt((opIdx+1) * 2  - 1);
            switch(op){
                case '+':
                    result +=  right;
                    break;
                case '-':
                    result -=  right;
                    break;
                case '*':
                    result *=  right;
                    break;
            }
        }

        return result;
    }

    static void insert(int opIdx){
        if(opIdx >= OP){
            int temp = calc(OP-1, false);
            if(temp > ans) ans = temp;
            return;
        }

        insert(opIdx +1);

        checks[opIdx] = true;
        insert(opIdx+2);
        checks[opIdx] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        OP = (N-1) / 2;
        str = br.readLine();

        checks = new boolean[OP];

        insert(1);

        System.out.println(ans);
    }
}
