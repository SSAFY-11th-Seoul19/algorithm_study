import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int M,N,H;
    static int box[][][];
    static boolean visited[][][];
    static Deque<Location> dq;
    static int remainCnt = 0;
    static int[] dh = {-1,1,0,0,0,0};
    static int[] dx = {0,0,0,0,-1,1};
    static int[] dy = {0,0,-1,1,0,0};

    static class Location{
        int h;
        int x;
        int y;

        public Location(int h, int x, int y){
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        dq = new ArrayDeque<>();
        visited = new boolean[H][N][M];
        for(int h=0;h<H;h++){
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++){
                    int value = Integer.parseInt(st.nextToken());
                    box[h][i][j] = value;

                    if(value == 0){
                        remainCnt++;
                    }
                    else if(value == 1){
                        dq.add(new Location(h,i,j));
                        visited[h][i][j] = true;
                    }
                }
            }
        }

        int time = 0;
        while(!dq.isEmpty() && remainCnt!=0){
            changeTomatoes();
            time++;
        }

        if(remainCnt > 0){
            time = -1;
        }

        bw.write(Integer.toString(time));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void changeTomatoes(){
        int dqSize = dq.size();

        for(int t=0;t<dqSize;t++){
            Location now = dq.poll();

            for(int i=0;i<6;i++){
                int nh = now.h + dh[i];
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nh<0 || nh>=H || nx<0 || nx>=N || ny<0 || ny>=M){
                    continue;
                }

                if(visited[nh][nx][ny] || box[nh][nx][ny] != 0){
                    continue;
                }

                visited[nh][nx][ny] = true;
                box[nh][nx][ny] = 1;
                dq.add(new Location(nh,nx,ny));
                remainCnt--;
            }
        }
    }
}
