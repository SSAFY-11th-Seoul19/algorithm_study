import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int HORIZONTAL = 0; // 가로
    static final int VERTICAL = 1; // 세로
    static final int DIAGONAL = 2; // 대각선
    static final int BLANK = 0;
    static final int WALL = 1;

    static int N;
    static int[][] map;
    static int ans;


    static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        movePipe(new Location(1,1), new Location(1,2));
        System.out.println(ans);
    }

    public static void movePipe(Location left, Location right){
        if(right.x==N && right.y==N){
            ans++;
            return;
        }

        int type = getType(left, right);
        int nx = right.x+1;
        int ny = right.y+1;

        if(type == HORIZONTAL){
            if(ny>N){
                return;
            }

            left.y += 1;

            if(map[right.x][ny]==BLANK){
                movePipe(new Location(left.x,left.y),new Location(right.x,ny));
            }

            if(nx<=N && map[nx][ny]==BLANK && map[right.x][ny]==BLANK && map[nx][right.y]==BLANK){
                movePipe(new Location(left.x,left.y),new Location(nx,ny));
            }

            return;
        }

        if(type == VERTICAL){
            if(nx>N){
                return;
            }

            left.x += 1;

            if(map[nx][right.y]==BLANK){
                movePipe(new Location(left.x,left.y),new Location(nx,right.y));
            }

            if(ny<=N && map[nx][ny]==BLANK && map[right.x][ny]==BLANK && map[nx][right.y]==BLANK){
                movePipe(new Location(left.x,left.y),new Location(nx,ny));
            }

            return;
        }

        if(type == DIAGONAL){
            left.x += 1;
            left.y += 1;

            if(ny<=N && map[right.x][ny]==BLANK){
                movePipe(new Location(left.x,left.y),new Location(right.x,ny));
            }

            if(nx<=N && map[nx][right.y]==BLANK){
                movePipe(new Location(left.x,left.y),new Location(nx,right.y));
            }

            if(nx<=N && ny<=N && map[nx][ny]==BLANK && map[right.x][ny]==BLANK && map[nx][right.y]==BLANK){
                movePipe(new Location(left.x,left.y),new Location(nx,ny));
            }
        }
    }

    public static int getType(Location left, Location right){
        int xDiff = Math.abs(left.x - right.x);
        int yDiff = Math.abs(left.y - right.y);

        if(xDiff==yDiff){ // 대각선
            return DIAGONAL;
        }

        if(xDiff==1){ // 세로
            return VERTICAL;
        }

        return HORIZONTAL; // 가로
    }
}
