package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;

public class BOJ_1697 {
	static Queue<Point> queue = new LinkedList<Point>();
	static int K;
	static boolean[] checkList = new boolean[1000000];
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		int ans = 0;
		
		
		if (N>=K) {
			ans = N-K;
		}else {
			queue.add(new Point(N,0));
			while (queue.size()>0 && ans==0) {
				Point p0 = queue.poll();
				ans = move(p0);
			}
		}
		
		System.out.println(ans);
		
	} //main end
	
	
	public static int move(Point startP) {
		Point p1 = new Point(startP.x -1, startP.y+1);
		Point p2 = new Point(startP.x +1, startP.y+1);
		Point p3 = new Point(2*startP.x, startP.y+1);
		ArrayList<Point> pointArray = new ArrayList<>();
		pointArray.add(p1);
		pointArray.add(p2);
		pointArray.add(p3);
		
		
		if(p1.x==K || p2.x==K || p3.x==K) {
			return p1.y;
		}
		for(Point p : pointArray) {
			if (p.x >0 && p.x <= 100000 && checkList[p.x-1]==false){
				queue.add(p);
				checkList[p.x -1] = true;
			}
		}
		
		return 0;
		}
		
}
