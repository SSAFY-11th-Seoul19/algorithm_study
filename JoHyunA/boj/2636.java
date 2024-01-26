import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static int row, column;
    public static int[][] map;
    public static boolean[][] visited;
    public static int remainCnt;
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    
    static class Location{
    	int x;
    	int y;
    	
    	public Location(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());

        map = new int[row][column];
        for(int i=0;i<row;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<column;j++){
                int type = Integer.parseInt(st.nextToken());
                map[i][j] = type;

                if(type==1) {
                    remainCnt++;
                }
            }
        }

        int ans = 0;
        int time = 0;
        while(remainCnt>0){
        	ans = remainCnt;
        	
        	visited = new boolean[row][column];
        	meltCheese();
        	
            time++;
        }
        
        bw.write(time+"\n"+ans);
        bw.flush();
        
        br.close();
        bw.close();
    }
    
    public static void meltCheese() {
    	Deque<Location> dq = new ArrayDeque<>();
    	dq.add(new Location(0,0));
    	visited[0][0] = true;
    	
    	while(!dq.isEmpty()) {
    		Location now = dq.poll();
    		
    		for(int i=0;i<4;i++) {
    			int nx = now.x + dx[i];
    			int ny = now.y + dy[i];
    			
    			if(nx<0 || nx>=row || ny<0 || ny>=column) {
    				continue;
    			}
    			
    			if(map[nx][ny] == 1) {
    				visited[nx][ny] = true;
    				map[nx][ny] = 0;
    				remainCnt--;
    				continue;
    			}
    			
    			// map[nx][ny] == 0 인 경우
    			if(!visited[nx][ny]) {
    				dq.add(new Location(nx,ny));
    				visited[nx][ny] = true;
    			}
    		}
    	}
    }
}
