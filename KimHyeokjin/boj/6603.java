import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int k;
	static int[] lst;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//무한으로 돌면서
		while(true) {
			//입력을 받고
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			
			//0이 입력으로 들어오면 break
			if(k==0) {
				break;
			}
			//0이 아니라면 초기화 해주고
			lst = new int[k];
			//반복해서 lst에 값을 넣어줌
			for(int i=0; i<k; i++) {
				lst[i] = Integer.parseInt(st.nextToken());
			}
			//백트래킹 시작!
			backTracking(0, 0, new int[6]);
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	public static void backTracking(int level, int cur, int[] result) {
		//level => 현재 들어간 갯수
		//cur => lst에서 뽑아야 될 위치의 시작점.
		//result => 현재까지의 값들을 저장할 배열
		if(level==6) {
			//6개를 다 넣었다면 sb에 넣어주고 마무리
			for(int i=0; i<6; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		//lst에서 뽑아야 될 위치의 시작점으로 부터 k번째 까지 반복하며
		for(int i = cur; i<k; i++) {
			//정답을 저장할 배열에 위치 값을 넣어주고
			result[level] = lst[i];
			//level과 cur을 수정해서 인자로 넣어줌!
			backTracking(level+1, i+1, result);
		
		}
	}
}
