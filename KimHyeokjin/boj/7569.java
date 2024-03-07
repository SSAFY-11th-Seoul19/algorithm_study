import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//현재 탐색하는 위치의 좌표를 저장할 Point 클래스 선언
//height : 높이, y : y좌표, x : x좌표

class Point {
	public int height;
	public int y;
	public int x;

	public Point(int height, int y, int x) {
		this.height = height;
		this.y = y;
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public int getHeight() {
		return height;
	}

	public int getY() {
		return y;
	}

}

public class Main {
	public static void main(String[] args) throws IOException {
		// Idea. 총 토마토의 갯수가 들어갈 수 있는 칸 수와 현재 익은 토마토의 갯수를 구해줌.
		// 익지않는 토마토는 익을때마다 토마토 갯수 +1
		// 가로세로높이.. 까지... 돌아야함..

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int[][][] tomato = new int[h][n][m];
		
		//tomato가 들어있는 갯수를 저장할 변수(-1일때를 제외한 것)
		int totalTomato = m * n * h;
		//익은 토마토 갯수를 저장할 변수 -> 추후 익은 토마토의 개수와 전체 토마토의 갯수를 세어 영영 익지 못하는 토마토를 찾기 위함
		int ripeTomato = 0;
		//시간을 기억할 변수
		int day = 0;
		// #1. 토마토 입력받기
		for (int height = 0; height < h; height++) {
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < m; x++) {
					int temp = Integer.parseInt(st.nextToken());
					tomato[height][y][x] = temp;
					//만약, 토마토가 없는 공간이라면 총 토마토 갯수 -1
					if (temp == -1) {
						totalTomato--;
						continue;
					}
					//만약, 토마토가 익어있다면 익은 토마토 +1
					if (temp == 1) {
						ripeTomato++;
						continue;
					}
				}
			}
		}

		// #2. 토마토 탐방..하기...
		int[] dh = { 1, -1, 0, 0, 0, 0 };
		int[] dy = { 0, 0, 1, 0, -1, 0 };
		int[] dx = { 0, 0, 0, 1, 0, -1 };
		
		//bfs를 진행하기 위해 그날(day)에 익은 토마토를 저장할 큐
		//#2-1. 먼저, 전체를 돌며 익은 토마토를 queue에 저장함(주위에 익힐 토마토를 확인해야 하니!)
		Queue<Point> q = new LinkedList<>();
		for (int height = 0; height < h; height++) {
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < m; x++) {
					if (tomato[height][y][x] == 1) {
						q.add(new Point(height, y, x));
					}
				}
			}
		}
		
		//익은 토마토가 익을 수 있는 전체토마토의 갯수가 될때까지 반복
		while (ripeTomato < totalTomato) {
			//하루 지남을 확인하기 위해 전날 익었던 토마토의 갯수를 cnt변수에 저장
			int cnt = q.size();
			//금일 방문한 전날 익은 토마토의 갯수
			int tmp = 0;
			//날짜를 1만큼 더함!
			day++;
			//전날 익은 토마토를 모두 방문 했거나, 큐에 아무 값이 없을 때까지 반복
			while (tmp<cnt && !q.isEmpty()) {
				Point point = q.poll();
				int curH = point.getHeight();
				int curY = point.getY();
				int curX = point.getX();
				tmp++;
				//여섯방향(위, 아래, 양옆)을 돌면서 익힐 수 있는 토마토를 확인
				for (int i = 0; i < 6; i++) {
					int newH = curH +dh[i];
					int newY = curY+dy[i];
					int newX = curX+dx[i];
					//익힐 수 있는 토마토를 발견했다면, 익힌 토마토 갯수를 +1만큼 해주고
					if(0<=newH && newH<h && 0<=newY && newY <n && 0<=newX && newX<m && tomato[newH][newY][newX]==0) {
						ripeTomato++;
						tomato[newH][newY][newX] = 1;
						//큐에 더해줌!
						q.add(new Point(newH, newY, newX));
					}
				}
			}
			//만약, 큐가 비었는데(오늘 익은 토마토가 없는데) 익혀지지 않은 토마토가 있다면 -> 평생 못익음
			if(q.isEmpty() && totalTomato-ripeTomato!=0) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		//정답 출력
		System.out.println(day);
	}

}
