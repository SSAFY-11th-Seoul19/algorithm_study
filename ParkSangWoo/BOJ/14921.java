import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr=new int[N];
		String[] inputArr=br.readLine().split(" ");
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(inputArr[i]);
		}
		System.out.println(mixLiquid(N, arr));
		}
	
	public static int mixLiquid(int N, int arr[]) {
		int left=0;
		int right=N-1;
		int B = Integer.MAX_VALUE;
		
		/////입력 값이 전부 양수일 때
		if (arr[left]>=0) {
			B = arr[left]+arr[left+1];
			return B;
		}
		//////입력 값이 전부 음수일 때
		else if (arr[right]<=0) {
			B = arr[right-1]+arr[right];
			return B;
		}
		
		while (left < right) {
			int sum = arr[left]+arr[right];
			if (Math.abs(sum)<Math.abs(B)) {
				B = sum;
			}
			if (sum ==0) {
				return 0;
			}
			else if (sum>0) {
				right --;
			}
			else if (sum<0) {
				left ++;
			}
		}
		return B;
	}
}
