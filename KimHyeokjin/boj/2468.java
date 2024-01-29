import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// IDEA. 최소한으로 물에 잠기는 높이를 기존에 입력받을 때 구해준다.
		// 높이를 1만큼 증가시켜 가면서 안전 구역을 탐방한다(bfs로 탐색, visited사용)
		// 현재 최대로 구한 안전 구역보다 남은 땅 수가 적어지면 그만!
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		// 지역의 높이를 저장할 변수 board
		int[][] board = new int[n][n];
		//높이중 최솟값을 기억해둠(이유 : 초기에 시작 높이를 정하기 위해)
		int minVal = 101;
		//현 시간의 안전 영역의 갯수
		int safeArea = 0;
		//각 시간 별 물에 잠기지 않은 땅의 갯수
		int areaCnt = n*n;
		//최대 안전 영역 갯수
		int maxVal = 1;

		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				minVal = Math.min(minVal, board[i][j]);
			}
		}
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		boolean[][] visited = new boolean[n][n];
		//bfs를 진행할 것이니 queue선언
		Queue<Point> queue = new LinkedList<>();
		
		
		//물에 잠기지 않은 땅의 갯수보다 안전영역이 많다면 break!
		while(safeArea<areaCnt) {
			//현시간의 안전 구역과, 물에 잠기지 않은 땅은 초기화
			areaCnt=0;
			safeArea = 0;
			
			//방문 여부 초기화
			for(int vis=0; vis<n; vis++) {
				Arrays.fill(visited[vis], false);
			}
			
			//board를 모두 돌면서
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					//해당 board의 index값이 잠기는 높이보다 높고 방문하지 않았다면
					if(board[i][j] > minVal && visited[i][j] == false) {
						//queue에 추가해주고
						queue.add(new Point(j, i));
						//안전구역을 1만큼 더해줌(안전구역은 이어져있는 땅 당 한개씩이니 while문에서는 안더해줌)
						safeArea++;
						//queue가 빌때까지(주위가 없을 때까지)
						while(!queue.isEmpty()) {
							Point point = queue.poll();
							int newX = point.x;
							int newY = point.y;
							visited[newY][newX] = true;
							//현재 잠기지 않은 땅 +1
							areaCnt++;
							for(int k=0; k<4; k++) {
								int curX = newX + dx[k];
								int curY = newY + dy[k];
								//4방향을 확인해주면서 물에 안잠겼고 방문하지 않은 곳을 queue에 추가해줌!
								if(0<=curX && curX<n && 0<=curY && curY<n && 
										board[curY][curX]>minVal && visited[curY][curX]==false) {
									queue.add(new Point(curX, curY));
									visited[curY][curX] = true;
								}
							}
						}
					}
				}
			}
			//물에 잠길 안전지역의 숫자를 1만큼 올려줌
			minVal++;
			maxVal = Math.max(safeArea, maxVal);
		}
		System.out.println(maxVal);
	}

}
