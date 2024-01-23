import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//IDEA. 시작 지점으로부터 bfs를 통해 동생이 있는 지점까지 찾아간다.
		//queue에 값을 넣을 것임(값, 초)
		//수빈이가 갈 수 있는 위치는 최대 max(n, k)*2 임.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int maxVal = Math.max(n, k)*2;
		
		//중복 방문을 방지하기 위해 isVisited로 방문 처리, 초기 false
		Boolean[] isVisited = new Boolean[maxVal+1];
		Arrays.fill(isVisited, false);
		
		//(위치, 시간)으로 queue 선언하기 위해 Point사용
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(n, 0));
		int location=0; int time =0;
		
		//queue가 빌때까지 반복
		while(!q.isEmpty()) {
			Point cur = q.poll();
			location = cur.x;
			time = cur.y;
			if(location == k){
				System.out.println(time);
				break;
			}
			//동생의 위치보다 왼쪽 위치에 있다면 +1, -1, *2다해줘야함.
			//동생의 위치보다 오른쪽 위치에 있으면 -1만 해주면 됨.
			//따라서, 동생의  위치보다 왼쪽에 있다면 +1, *2. 모든 경우의 수에 대해 -1
			else if(location < k) {
				if(!isVisited[location+1]) {
					q.add(new Point(location+1, time+1));
					isVisited[location+1] = true;
				}
				if(!isVisited[location*2] && (location*2 <=maxVal)) {
					q.add(new Point(location*2, time+1));
					isVisited[location*2] = true;
				}
			}
			
			//순서가 바뀌거나 &&->&으로 바꾸면 오류남
			if(location-1>=0 && !isVisited[location-1]) {
				q.add(new Point(location-1, time+1));
				isVisited[location-1] = true;
			}
		}
	}
}
