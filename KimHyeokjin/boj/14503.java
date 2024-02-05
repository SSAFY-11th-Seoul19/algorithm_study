import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
			
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		
		int ans = 0;
		
		while(true) {
			if(board[y][x]==0) {
				ans++;
				board[y][x] = 2;
			}
			
			boolean flag = true;
			for(int i=0; i<4; i++) {
				if(board[y+dy[i]][x+dx[i]]==0){
					flag = false;
					break;
				}
			}
			
			if(flag) {
				if(board[y+dy[(d+2)%4]][x+dx[(d+2)%4]]==1) break;
				y = y+dy[(d+2)%4];
				x = x+dx[(d+2)%4];
				continue;
			}

			if(d==0) d=3;
			else d--;
				
			if(board[y+dy[d]][x+dx[d]]==0) {
				y = y+dy[d];
				x = x+dx[d];
				continue;
			}
			
		}
		System.out.println(ans);
	}	
}
