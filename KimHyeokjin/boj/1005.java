import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	//IDEA. 건설 순서의 규칙이 주어지니, topology sort를 이용해서 자신의 이전에 나오는 값들에 대해서 판단해줌.
	//Priority Queue를 이용하여 건물당 걸린 시간이 최소인 값을 뺴주는 것을 반복한다.
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입출력 처리 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		//Test Case입력받음
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			//N, K입력 처리
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			//각 건물별 짓는 시간
			int[] buildTime = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}
			//indegree -> 자신 보다 먼저 지어야 되는 건물의 갯수
			int[] indegree = new int[N];
			//각 건물별 방문 여부
			Boolean[] visited = new Boolean[N];
			Arrays.fill(visited, false);
			
			//현재 방문할 수 있는 건물중 걸리는 시간이 최소인 값을 기준으로 pq선언
			PriorityQueue<Building> pq = new PriorityQueue<>((o1, o2) -> o1.time-o2.time);
			
			//각 노드별 이후에 방문해야될 값들을 저장해놓을 map
			Map<Integer, List<Integer>> map = new HashMap<>();
		
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int X=Integer.parseInt(st.nextToken())-1;
				int Y=Integer.parseInt(st.nextToken())-1;
				//Y의 indegree증가
				indegree[Y]++;
				//만약, 초기화 되지 않았따면
				if(!map.containsKey(X)) {
					//초기화하고 map에 넣어줌
					List<Integer> list = new ArrayList<>();
					map.put(X, list);
				}
				//그외엔 값 추가
				map.get(X).add(Y);
			}
			
			int W = Integer.parseInt(br.readLine())-1;
			
			//만약, 자신이 바로 지어질 수 있는 건물이라면
			for(int i=0; i<N; i++) {
				if(indegree[i]==0) {
					//pq에 자신이 지어질 수 있는 시간과 함께 더해줌
					pq.add(new Building(i, buildTime[i]));
				}
			}
			
			//pq가 빌때까지(모든 건물을 방문할 수 있다는 조건이 있으므로)
			while(!pq.isEmpty()) {
				//시간이 제일 작은 값을 빼내서
				Building tmpBuilding = pq.poll();
				//원하는 건물이라면
				if(tmpBuilding.num==W) {
					//정답에 추가
					sb.append(tmpBuilding.time).append("\n");
					break;
				}
				//원하는 건물이 아니라면 방문처리 해주고
				visited[tmpBuilding.num] = true;
				
				//만약, 해당 건물이 이어져있는 값이 없다면 pq계속해줌.
				if(map.get(tmpBuilding.num)==null) {
					continue;
				}
				//해당 건물이 이어져있는 값이 있다면
				for(int i=0; i<map.get(tmpBuilding.num).size();i++) {
					//각각의 값들의 indegree를 1만큼 빼줌
					int nextNum = map.get(tmpBuilding.num).get(i);
					indegree[nextNum]--;
					//만약, nextNum이 방문한 노드이거나, indegree가 0이 아니면 pass
					if(visited[nextNum] || indegree[nextNum]!=0)
						continue;
					//그것이 아니라면 pq에 추가해준다.
					pq.add(new Building(nextNum, tmpBuilding.time+buildTime[nextNum]));
				}
			}
			
		}
		//bw를 이용해서 출력
		bw.write(sb.toString());
		bw.flush();
	}
	
	static class Building{
		int num;
		int time;
		
		public Building(int num, int time) {
			this.num = num;
			this.time = time;
		}
		
		public String toString() {
			return "num : " + num + " time : " + time;
		}
	}
}
