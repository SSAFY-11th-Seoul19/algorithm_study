import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine());
		char[] letter = new char[n];
		
		for(int i=0; i<n; i++) {
			letter[i] = br.readLine().charAt(0);
		}
		
		
		ArrayList<Character> newLetter = new ArrayList<>();
		int start = 0;
		int end = n-1;
		//현재까지 입력된 숫자의 개수를 저장할 변수
		int cnt= 0;
		//투포인터를 이용하여 추가해야되는 문자열의 위치를 확인함.
		//넣는 방법 : 왼/오른쪽 중에 더 작은걸 고른다. 단, 두개가 같다면 그 다음 차례에서 더 작은걸로 고른다.
		
		if(n==1) {
			System.out.println(letter[0]);
		}
		else {
			while(cnt<n) {
				if(letter[start]>letter[end]) {
					newLetter.add(letter[end--]);
				}
				else if(letter[start]<letter[end]) {
					newLetter.add(letter[start++]);
				}
				//같은데
				else {
					// 다음 확인하는 index들중 넘어버리는게 생긴다면..
					// 어짜피 똑같으니 아무거나 해줌
					int startTmp = start;
					int endTmp = end;
					Boolean flag = true;
					while(startTmp<endTmp) {
						if(startTmp<n-1) startTmp++;
						if(endTmp>0) endTmp--;
						
						if(letter[startTmp]>letter[endTmp]) {
							flag = false;
							break;
						}
						else if(letter[startTmp] <letter[endTmp]) {
							flag = true;
							break;
						}
					}
					if(flag==true) {
						newLetter.add(letter[start++]);
					}
					else {
						newLetter.add(letter[end--]);
					}
				}
				cnt++;
			}
			
			int chrCnt = 0;
			for(char cr:newLetter) {
				System.out.print(cr);
				chrCnt++;
				if(chrCnt%80==0) {
					System.out.println();
				}
			}
		}
	}
}
