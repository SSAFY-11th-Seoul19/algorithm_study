import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] liquid = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		
		//접근 : 투포인터를 선언하여 음수일때 start+1, 양수일때 end-1로 포인터를 움직여 검사.
		//시간복잡도 : O(n)
		int start = 0;
		int end = n-1;
		//용액의 최소 차이를 저장할 변수
		int minVal = Integer.MAX_VALUE;
		
		while(start<end) {
			int tmp = liquid[start]+liquid[end];
			if(Math.abs(minVal) > Math.abs(tmp)) {
				minVal = tmp;
			}
			
			if(tmp>0) {
				end--;
			}
			else if(tmp<0) {
				start++;
			}
			else {
				break;
			}
		}
		
		System.out.println(minVal);
	}
}
