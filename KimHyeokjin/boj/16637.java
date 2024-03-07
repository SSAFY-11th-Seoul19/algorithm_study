import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 괄호추가하기_16637 {
	//IDEA. 가짓수가 많지 않으므로 완탐하면 될 것임.
	//괄호는 최대 (N+1)/4까지 만들 수 있으며 이는 백트래킹으로 뽑아줄 것임.
	//즉, 괄호갯수 1개부터 최대 갯수까지 가능한 모든 가짓수를 만들어 줄것임.
	//단, 괄호 위치를 기억하기 위해 연산자가 묶이는 지점을 확인함.
	//ex) 괄호 0번을 묶을것이다 -> 수0, 연0, 수1이 묶일 것임을 의미
	//괄호를 묶은 수식을 만든 후 해당 수식을 계산해줄것임
	//전역 변수 선언
	//N : 첫 수식 길이
	public static int N;
	//입력받을 수식
	public static String[] calStr;
	//괄호의 위치
	public static int[] calLoc;
	//cnt : 해당 차수에 괄호를 몇개 묶을 것인지
	public static int cnt;
	//정답을 기억할 변수
	public static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		calStr = br.readLine().split("");
		//개가튼 음수;;
		//ans초기화 할때 음수로 해야됨..
		ans = -Integer.MAX_VALUE;
		//만약, 수식의 길이가 1이라면 해당 수가 답임
		if(N==1) {
			ans = Integer.parseInt(calStr[0]);
		}
		//그외의 경우엔 가능한 괄호의 갯수만큼 괄호수를 늘려주면서
		for(int i=1; i<=(N+1)/4; i++) {
			//cnt를 괄호의 갯수로 초기화 해주며(backtracking 확인할때 사용)
			cnt = i;
			//괄호의 위치를 기억할 배열은 i갯수로 초기화 해주고
			calLoc = new int[i];
			//백트래킹 시작
			backTracking(0, 0);
		}
		System.out.println(ans);
	}
	//괄호 위치가 들어왔을때 계산해주는 함수
	public static int cal(int level) {
		//괄호를 반영한 수식을 출력할것인데 길이는 N-(level*2)
		String[] newStr = new String[N-(level*2)];
		//count => 반영한 괄호의 갯수
		//strCount => 새로 저장할 수식의 현 위치
		int count = 0;
		int strCount = 0;
		//N번 만큼 반복하면서
		for(int i=0; i<N; i++) {
			//만약, 괄호를 다 넣었다면
			if(count>=level) {
				//그이후로는 그냥 넣어줌
				newStr[strCount++]= calStr[i];
				continue;
			}
			
			//만약, 현재 넣어야될 괄호의 왼쪽 수를 발견했다면
			if(calLoc[count]*2 ==i) {
				//왼쪽 수와 오른쪽 수를 연산한 값을 변환하여 newStr에 저장
				newStr[strCount++] = convert(calStr[i], calStr[i+1], calStr[i+2]);
				//괄호하나 추가
				count++;
				//위치를 바꿔주고
				i = i+2;
				//실행
				continue;
			}
			//그외의 경우엔 그냥 추가해줌
			newStr[strCount++] = calStr[i];
		}
		
		//left는 연산할 때 왼쪽에 오는 값
		int left = Integer.parseInt(newStr[0]);
		//연산자를 발견하면
		for(int i=1; i<N-(level*2); i+=2) {
			//연산자의 기호를 확인하여
			switch(newStr[i]) {
			//오른쪽 수와 계산해준 후
			case "+":
				left = left + Integer.parseInt(newStr[i+1]);
				break;
			case "*":
				left = left * Integer.parseInt(newStr[i+1]);
				break;
			case "-":
				left = left - Integer.parseInt(newStr[i+1]);
				break;
			}
		}
		//마지막에 계산한 최종값을 return
		return left;
	}
	public static String convert(String a, String op, String b) {
		int val = 0;
		switch(op) {
		case "+":
			val = Integer.parseInt(a) + Integer.parseInt(b);
			break;
		case "*":
			val = Integer.parseInt(a)* Integer.parseInt(b);
			break;
		case "-":
			val = Integer.parseInt(a) - Integer.parseInt(b);
			break;
		}
		
		return Integer.toString(val);
	}
	//백트래킹 인자 level : 몇개의 괄호를 넣었는지, start : 탐색할 index의 시작이 어디인지
	public static void backTracking(int level, int start) {
		//cnt만큼 괄호를 넣었으면
		if(level==cnt) {
			//해당 괄호를 포함해 계산한 수식과 정답중 더 큰값을 가져옴
			ans = Math.max(ans, cal(level));
			return;
		}
		//괄호가 다 안채워져있다면..
		for(int i=start; i<N/2; i++) {
			//괄호를 추가해주고
			calLoc[level] = i;
			//자신이 괄호를 칠경우 바로 옆 연산자는 괄호가 쳐질 수 없으므로
			//i+2에서 새로운 괄호를 넣을 것임!
			backTracking(level+1, i+2);
			
		}
	}
}
