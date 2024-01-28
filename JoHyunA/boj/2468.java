import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int height = Integer.parseInt(st.nextToken());
                map[i][j] = height;
                minHeight = Math.min(minHeight, height);
                maxHeight = Math.max(maxHeight, height);
            }
        }

        int ans = 0;
        for(int rain=minHeight-1;rain<maxHeight;rain++){
            visited = new boolean[N][N];
            int safeAreaCnt = 0;

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(visited[i][j] || map[i][j]<=rain){
                        continue;
                    }

                    checkSafeArea(i,j,rain);
                    safeAreaCnt++;
                }
            }

            ans = Math.max(ans, safeAreaCnt);
        }
        bw.write(Integer.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void checkSafeArea(int x, int y, int rain){
        Deque<Location> dq = new ArrayDeque<>();
        dq.add(new Location(x,y));
        visited[x][y] = true;

        while(!dq.isEmpty()){
            Location now = dq.poll();

            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=N){
                    continue;
                }

                if(visited[nx][ny] || map[nx][ny]<= rain){
                    continue;
                }

                visited[nx][ny] = true;
                dq.add(new Location(nx,ny));
            }
        }
    }
}
