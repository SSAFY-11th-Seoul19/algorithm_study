import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		//crane 무게 제한 입력
		List<Integer> crane = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			crane.add(Integer.parseInt(st.nextToken()));
		}
		//box 무게 입력
		int M = Integer.parseInt(br.readLine());
		List<Integer> box = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}
		//역순 정렬
		Collections.sort(crane, Comparator.reverseOrder());
		Collections.sort(box, Comparator.reverseOrder());
		//변수 선언
		//time -> 몇분안에 박스를 옮겼는지
		int time = 0;
		//cnt -> 현재까지 옮긴 박스의 갯수
		int cnt = 0;
		//ischecked -> 해당 박스가 옮겨졌는지 확인
		boolean[] isChecked = new boolean[M];
		//craneVisitIdx -> 해당 크레인이 이전에 옮긴 박스의 index
		int[] craneVisitIdx = new int[N];
		
		//만약, 가장 무거운 무게가 크래인이 들수 있는 무게보다 무겁다면 -1출력
		if(box.get(0) > crane.get(0)) {
			System.out.println(-1);
		}
		//그외의 경우
		else {
			//모든 박스를 옮길때까지 반복하면서
			while(cnt<M) {
				//시간을 +1만큼 해주고
				time++;
				//각 크래인별로 반복하면서
				for(int i=0; i<N; i++) {
					//만약, 크레인이 이전에 아무곳도 반복하지 못했다면.. 이후에도 반복하지 못할것이기 때문에 continue
					if(craneVisitIdx[i]==-1) {
						continue;
					}
					//크레인이 이전에 방문했던곳부터 방문하며(원래 +1 로직이 조금 더 맞긴한데 0일때 처리가 힘듬)
					for(int j=craneVisitIdx[i]; j<M; j++) {
						//만약, 현재 box가 방문되지 않았고 해당 크레인이 상자의 무게를 옮길수 있다면
						 if(!isChecked[j] && (crane.get(i) >= box.get(j))) {
							 //방문 여부 변경
							 isChecked[j] = true;
							 //갯수 +1
							 cnt++;
							 //crane이 방문한 index변경
							 craneVisitIdx[i] = j;
							 //자신의 해당 차례 종료
							 break;
						 }
						 //만약, 무게를 옮기지 못하고 마지막 상자까지 왔다면
						 if(j==M-1) {
							 //방문을 못했다는 flag를 -1로 선언
							 craneVisitIdx[i] = -1;
						 }
					}
					//만약, 다 옮겼다면 정지
					if(cnt==M) {
						break;
					}
				}
				
			}
			System.out.println(time);
		}
	}
}
