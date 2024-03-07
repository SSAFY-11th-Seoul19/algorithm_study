import java.util.*;
import java.io.*;

public class Main {
	
	public static void melt(int[][] arr, int x, int y,  int N, int M, int tryCount, int[] ansList) {
		int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1, 0}};
		
		if(x < 0 || x > N || y < 0 || y > M) return;

		if(arr[x][y] == tryCount) return;
		
		if(arr[x][y] == 1) {
			ansList[-tryCount]++;
			arr[x][y] = tryCount;
			return;
		}
			
		arr[x][y] = tryCount;
		
		
		for(int[] d : dir) {
			melt(arr, x+d[0], y+d[1], N, M, tryCount, ansList);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		int N = Integer.parseInt(s.split(" ")[0]);
		int M = Integer.parseInt(s.split(" ")[1]);	
		int[][] cheese = new int[102][102];
		int[] ansList = new int[100];
		
		for(int i = 0; i < N; i++) {
			String[] arr = bf.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(arr[j]);
			}
		}
		
		int tryCount = -1;
		while(true) {
			for(int i = 0; i < N; i++) {
				melt(cheese, i, 0, N, M, tryCount, ansList);
				melt(cheese, i, M, N, M, tryCount, ansList);
			}
			for(int i = 0; i < M; i++) {
				melt(cheese, 0, i, N, M, tryCount, ansList);
				melt(cheese, N, i, N, M, tryCount, ansList);
			}
			if(ansList[-tryCount] == 0) 
				break;
			tryCount--;
		}
		System.out.println(-(tryCount + 1));
		System.out.println(ansList[-(tryCount + 1)]);
			
	}

}
