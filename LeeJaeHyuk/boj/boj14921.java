import java.io.*;
import java.util.*;

public class Study1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] list = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);

        int left = 0;
        int right = N-1;

        int answer = 0; // 두 용액의 합
        int gap = Integer.MAX_VALUE; // 두 용액의 합이 0과 떨어진 거리

        while(left < right) {
            int lvalue = list[left];
            int rvalue = list[right];

            // 두 용액의 합이 기존 값보다 0과 더 가깝다면 해당 값으로 갱신
            if(gap > Math.abs(lvalue + rvalue)) {
                gap = Math.abs(lvalue + rvalue);
                answer = lvalue + rvalue;
            }

            // 투포인터 움직이기
            if(Math.abs(lvalue) > Math.abs(rvalue))
                left += 1;
            else
                right -= 1;
        }

        System.out.println(answer);
    }
}