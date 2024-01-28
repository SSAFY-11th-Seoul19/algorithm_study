package boj2636;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static int[][] map;
	static int[][] visited;
	public static boolean checkRange(int x, int y) { // 범위 체크
		if(x>=0 && x<N && y>=0 && y<M) return true;
		return false;
	}
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	public static void findArea(int x, int y) { 
		if(visited[x][y]==1) return;
		
		visited[x][y] = 1;
		
		if(map[x][y] == 1) return; 
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i]; // nextX
			int ny = y+dy[i]; // nextY
			if(checkRange(nx, ny)) {
				findArea(nx, ny);
				
			}
		}
	}
	

	public static void main(String[] args) throws IOException {
		//~input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		
		int cheese = 0;
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j*2) - '0';
				if(map[i][j] == 1) cheese++; // 치즈 개수 저장
			}
		}
		//~
		
		int hour = 0;
		while(cheese > 0) {
			hour++; 
			findArea(0, 0); // 외부 영역 찾기
			
			int removeCheese = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(visited[i][j] == 1 && map[i][j] == 1) {
						removeCheese++; // 지우는 치즈 찾기
						map[i][j] = 0;
					}
				}
			}
			
			if(cheese - removeCheese == 0) break; // 다 지우면 종료 
			cheese -= removeCheese;
			
			for(int i=0;i<N;i++) {
				Arrays.fill(visited[i], 0);
			}
		}
		
		System.out.println(hour);
		System.out.println(cheese);
		
	}

}
