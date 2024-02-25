import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static char PLUS = '+';
    static char MINUS = '-';
    static char MULTIPLY = '*';

    static int N;
    static char[] expression;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        expression = br.readLine().toCharArray();

        findMax(1, expression[0]-'0');
        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findMax(int idx, int sum){
        if(idx>=N){
            max = Math.max(max, sum);
            return;
        }

        findMax(idx+2, calculate(expression[idx], sum, expression[idx+1]-'0'));

        if(idx+3 >= N){
            return;
        }

        int tmpSum = calculate(expression[idx+2],expression[idx+1]-'0', expression[idx+3]-'0');
        findMax(idx+4, calculate(expression[idx], sum, tmpSum));

    }

    public static int calculate(char operation, int a, int b){
        if(operation == PLUS){
            return a+b;
        }

        if(operation == MINUS){
            return a-b;
        }

        return a*b;
    }
}
