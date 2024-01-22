import java.util.*;
public class Main {

	public static void main(String[] args) {
		int N, beg = 0, end;
		String S = "", T ="";
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		for(int i = 0; i < N; i++) {
			S += in.next().charAt(0);
		}
				
		end = N-1; 
		
		while(beg <= end) {
			char begVal = S.charAt(beg);
			char endVal = S.charAt(end);
			
			if(begVal > endVal) {
				T += endVal;
				end--;
			}
			else if (begVal < endVal) {
				T += begVal;
				beg++;
			}
			else if(beg + 1 <= end -1){
				char begNextVal = S.charAt(beg+1);
				char endNextVal = S.charAt(end-1);
				if(begNextVal < endNextVal) {
					T += begVal;
					beg++;
				}
				else if(begNextVal > endNextVal){
					T += endVal;
					end--;
				}
				else {
					T += begVal;
					beg++;
				}
			}
			else {
				T += begVal;
				beg++;
			}
			if(T.length() == 80) {
				System.out.println(T);
				T = "";
			}
		}
		
		System.out.println(T);
		
	}

}
