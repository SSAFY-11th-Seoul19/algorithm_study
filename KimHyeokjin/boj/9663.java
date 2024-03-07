import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int ans=0;
	static int n;
	static int[] board;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//n을 입력으로 받고 1차원 배열로 board 선언
		n =Integer.parseInt(br.readLine());
		//board는 [1, 2, 3, 4]라고 하면 0번째 row에 1번째 칸에 queen이 들어가있다는 얘기임!!
		board= new int[n];
		
		//n번반복할것이며 0부터 시작해서 n번까지 반복
		backTracking(0);
		System.out.println(ans);
	}
	public static void backTracking(int count) {
		//만약, n번 반복했다면 정답 +1후 종료
		if(count==n) {
			ans+=1;
			return;
		}
		
		//모든 행을 반복하면서
		for(int i=0; i<n; i++) {
			//flag를 true라고 선언해두고
			boolean flag = true;
			//현재까지 열에 채워진 부분까지 확인하며
			for(int j=0; j<count; j++) {
				//만약, 가로 혹은 세로에 값이 들어가 있거나 대각선에 값이 들어가 있으면
				if(board[j]==i || count-j == Math.abs(i-board[j])) {
					//못해요
					flag = false;
					//멈춰요
					break;
				}
			}
			//할 수 있다면
			if(flag) {
				//현잿번 열에 행값을 넣어주고
				board[count] = i;
				//다음 열을 진행(재귀)
				backTracking(count+1);
			}
		}
	}
}
