import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력
	static StringTokenizer st; // 문자열 자를 st
	static int N, S;
	static int[] arr;
	static int min = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int start = 0;
        int end = 0;
        int total = 0;
        
        while(start <= N && end <= N) {
            if(total >= S && min > end - start) {
            	min = end - start;
            }
            if(total < S) {
            	total += arr[end++];
            	continue;
            }
            total -= arr[start++];
        }
        
        if(min == Integer.MAX_VALUE) {
        	min = 0;
        }
        System.out.println(min);
    }
}    
