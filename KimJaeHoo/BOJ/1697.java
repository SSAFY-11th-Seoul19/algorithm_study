import java.util.*;
import java.io.*;

public class Main {
	
		public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		boolean checkLists[] = new boolean[100001];
		int N = Integer.parseInt(s.split(" ")[0]);
		int K = Integer.parseInt(s.split(" ")[1]);	
		Queue<int []> q = new LinkedList<>();
		q.add(new int[] {N, 0});
		
		int[] cur;
		while((cur = q.poll())[0] != K) {
			int nextv;

			
			nextv = cur[0] + 1;
			if(nextv >= 0 && nextv <= 100000 && !checkLists[nextv]) 
			{
				checkLists[nextv] = true;
				q.add(new int[] {nextv, cur[1] + 1});
			}
		
			nextv = cur[0] -1;
			if(nextv >= 0 && nextv <= 100000 && !checkLists[nextv])
			{
				checkLists[nextv] = true;
				q.add(new int[] {nextv, cur[1] + 1});
			}
			nextv = cur[0] *2;
			if(nextv >= 0 && nextv <= 100000 && !checkLists[nextv])
			{
				checkLists[nextv] = true;
				q.add(new int[] {nextv, cur[1] + 1});
			}
			
		}
		System.out.println(cur[1]);

	}
}
