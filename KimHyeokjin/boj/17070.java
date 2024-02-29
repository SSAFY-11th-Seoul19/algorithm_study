import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 파이프옮기기_17070 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//dp로 할 것임
		int[][][] dp = new int[N][N][3];
		//0 -> 가로, 1-> 세로, 2-> 대각선
		dp[0][0][0] = 1;
		dp[0][1][0] = 1;
		
		for(int i=0; i<N; i++) {
			//0, 1은 제일 위에 가로가 놔질테니 절대 안됨
			for(int j=2; j<N; j++) {
				if(board[i][j]==1) continue;
				//가로가 될때
				if(dp[i][j-1][0]!=0 || dp[i][j-1][2]!=0 ) {
					dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2]; 
				}
				//세로가 될때
				if(i>=2 && (dp[i-1][j][1]!=0 || dp[i-1][j][2]!=0)) {
					dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
				}
				//대각선이 될때
				if(i>=1 && board[i-1][j]==0 && board[i][j-1]==0 && (dp[i-1][j-1][0]!=0 || dp[i-1][j-1][1]!=0 || dp[i-1][j-1][2]!=0)) {
					dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}

}
