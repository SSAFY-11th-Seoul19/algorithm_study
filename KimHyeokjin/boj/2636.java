import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void bfs(int N, int M, int[][] board) {
		//공기에 접촉한 칸(치즈 있는 곳 제외)을 저장할 queue
		Queue<Point> queue = new LinkedList<>();
		//공기와 접촉한 치즈를 저장할 queue
		Queue<Point> cheezeQueue = null;
		//중복 접근을 방지할 방문 확인 2차원 배열
		Boolean[][] isVisited = new Boolean[N][M];
		for(int i=0; i<N; i++) {
			Arrays.fill(isVisited[i], false);
		}
		
		//time-> 몇시간이 지났는지, prevCnt -> 지난번까지의 치즈 개수를 저장할 변수
		int time = 0;
		int prevCnt = 0;
		//bfs탐색을 위해 가로세로 방향으로 확인할 때 사용할 배열
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		//초기에 0,0은 무조건 공기이므로 0,0으로 초기화 해줌
		queue.add(new Point(0, 0));
		while(true) {
			//만약, 초기시간이 아니라면 이전 시간에서 공기와 접촉한 치즈가 사라지고 해당 공간이 새로운 공기가 될것이므로 queue를 할당
			if(time!=0) {
				queue = cheezeQueue;
			}
			//공기와 접촉한 치즈 공간은 새로 초기화
			cheezeQueue = new LinkedList<>();
			
			//공기부분을 모두 탐색하면서
			while(!queue.isEmpty()) {
				Point point = queue.poll();
				int y = point.x;
				int x = point.y;
				
				for(int i=0; i<4; i++) {
					int xPos = x+dx[i];
					int yPos = y+dy[i];
					if((xPos>=0 && xPos<M) && (yPos>=0 && yPos<N)){
						//만약, 해당 위치가 방문하지 않은 공간이고 공기로 차있다면
						//확인해야될 공기층으로 생각하고 queue에 append, 방문처리
						if(!isVisited[yPos][xPos] && board[yPos][xPos]==0) {
							isVisited[yPos][xPos] = true;
							queue.add(new Point(yPos, xPos));
							continue;
						}
						//만약, 해당 위치가 방문하지 않은 공간이고 치즈가 있다면
						//다음번 회차때 치즈가 없어지면서 공기층이 될것이므로 또다른 queue(cheezeQueue)에서 관리
						if(!isVisited[yPos][xPos] && board[yPos][xPos]==1) {
							isVisited[yPos][xPos] = true;
							cheezeQueue.add(new Point(yPos, xPos));
							board[yPos][xPos] = 0;
							continue;
						}
						
					}
				}
			}
			//1시간 지났음을 의미
			time+=1;
			
			//만약, 치즈queue가 비었다면(화면이 모두 비어있다면)
			//화면이 모두 비었다는거는 이전번 회차에서 치즈 queue가 빈게 완료되었다는 뜻이므로 time-1에서 다 없어진 상태임.
			//그때의 cheezeQueue에 저장되어있는 갯수를 출력
			if(cheezeQueue.isEmpty()) {
				System.out.println(time-1);
				System.out.println(prevCnt);
				break;
			}
			prevCnt = cheezeQueue.size();
		}
	}
	public static void main(String[] args) throws IOException {
		//IDEA1. 입력 받은 후 전체 탐색하며 공기와 접촉하는 치즈를 확인. (0,0)에서 접촉면 확인-> BFS사용
		//IDEA2. 갯수를 세어주면서 가장자리를 지워주고 이를 반복.
		//IDEA3. 접촉면을 확인할때 BFS로 가로세로 확인.
		//시간 복잡도 : O(N*M*4*MAX(N,M)) => O(N^3). N이 100까지이므로 시간초과 X
		//queue를 두개씀으로 O(N^2)까지 줄임
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [][] board = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(N, M, board);
	}
}
