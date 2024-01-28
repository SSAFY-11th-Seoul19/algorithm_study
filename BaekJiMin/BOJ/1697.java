package boj1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info {
	int x;
	int sec;
	
	public Info(int x, int sec) {
		this.x = x;
		this.sec = sec;
	}
}

public class Main {
	
	public static void main(String[] args) throws IOException {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int max = 100000;
		
		//탐색 관리
		Queue<Info> queue = new LinkedList<>();
		queue.add(new Info(N, 0));
		
		//방문 기록
		int[] visited = new int[max+1];
		
		//탐색
		while(!queue.isEmpty()) {
			Info info = queue.poll();
			if(info.x == K) {
				System.out.println(info.sec);
				return;
			}
			
			visited[info.x] = 1; // 방문
			
			// 범위 확인 및 방문 체크 후 탐색 
			if(info.x-1 >= 0 && visited[info.x-1] == 0) {
				queue.add(new Info(info.x-1,info.sec+1));
			}
			if(info.x+1 <= max && visited[info.x+1] == 0) {
				queue.add(new Info(info.x+1,info.sec+1));
			}
			if(info.x*2 <= max && visited[info.x*2] == 0) {
				queue.add(new Info(info.x*2,info.sec+1));
			}
		}
	}

}
