import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;
import test.ColorBomb.BombBundle;
import test.ColorBomb.Location;

public class Main {
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};
    static final int ROCK = -1;
    static final int RED = 0;
    static final int EMPTY = -2;
    static int totalBombCnt = 0;
    static int n, m, score;
    static int[][] map;
    static boolean[][] visited;
    static test.ColorBomb.BombBundle maxBombBundle;

    static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class BombBundle implements Comparable<test.ColorBomb.BombBundle>{
        int redCnt = 0;
        ArrayList<test.ColorBomb.Location> bombs = new ArrayList<>();
        ArrayList<test.ColorBomb.Location> redBombs = new ArrayList<>();
        test.ColorBomb.Location center;

        public BombBundle(test.ColorBomb.Location bomb){
            center = bomb;
            bombs.add(bomb);
        }

        public void add(test.ColorBomb.Location bomb){
            if(map[bomb.x][bomb.y] == RED){
                redCnt++;
                redBombs.add(bomb);
                return;
            }

            setCenter(bomb);
            bombs.add(bomb);
        }

        public void setCenter(test.ColorBomb.Location bomb){
            if(center.x < bomb.x){
                center = bomb;
                return;
            }

            if(center.x==bomb.x && center.y>bomb.y){
                center = bomb;
            }
        }

        public void removeAll(){
            for(test.ColorBomb.Location bomb:bombs){
                map[bomb.x][bomb.y] = EMPTY;
                totalBombCnt--;
            }

            for(test.ColorBomb.Location redBomb:redBombs){
                map[redBomb.x][redBomb.y] = EMPTY;
                totalBombCnt--;
            }
        }

        public int getSize(){
            return bombs.size()+redBombs.size();
        }

        public void resetVisited(){
            for(test.ColorBomb.Location redBomb:redBombs){
                visited[redBomb.x][redBomb.y] = false;
            }
        }

        @Override
        public int compareTo(test.ColorBomb.BombBundle o) {
            if(this.getSize() != o.getSize()){
                return o.getSize() - this.getSize() ;
            }

            if(this.redCnt != o.redCnt){
                return this.redCnt - o.redCnt;
            }

            if(this.center.x != o.center.x){
                return o.center.x - this.center.x;
            }

            return this.center.y - o.center.y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 격자 크기
        m = Integer.parseInt(st.nextToken()); // 서로 다른 폭탄의 종류 m

        map = new int[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]>=0){
                    totalBombCnt++;
                }
            }
        }

        while(totalBombCnt>0){
            visited = new boolean[n][n];
            maxBombBundle = new test.ColorBomb.BombBundle(new test.ColorBomb.Location(0,0));
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(visited[i][j]){
                        continue;
                    }

                    if(map[i][j]==RED || map[i][j]==EMPTY || map[i][j] == ROCK){
                        continue;
                    }

                    visited[i][j] = true;
                    findBundle(new test.ColorBomb.Location(i,j));
                }
            }

            if(maxBombBundle.getSize()<2){
                break;
            }

            int maxBombCnt = maxBombBundle.getSize();
            score += maxBombCnt*maxBombCnt;
            maxBombBundle.removeAll();

            applyGravity();
            rotateLeft();
        }
        bw.write(Integer.toString(score));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findBundle(test.ColorBomb.Location bomb){
        test.ColorBomb.BombBundle bombBundle = new test.ColorBomb.BombBundle(bomb);
        int color = map[bomb.x][bomb.y];

        Deque<test.ColorBomb.Location> dq = new ArrayDeque<>();
        dq.add(bomb);

        while(!dq.isEmpty()){
            test.ColorBomb.Location now = dq.poll();
            for(int i=0;i<4;i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=n){
                    continue;
                }

                if(visited[nx][ny]){
                    continue;
                }

                if(map[nx][ny] != color && map[nx][ny] != RED){
                    continue;
                }

                visited[nx][ny] = true;
                bombBundle.add(new test.ColorBomb.Location(nx,ny));
                dq.add(new test.ColorBomb.Location(nx, ny));
            }
        }

        if(maxBombBundle.compareTo(bombBundle)>0){
            maxBombBundle = bombBundle;
        }

        bombBundle.resetVisited();
    }

    public static void applyGravity(){
        for(int j=0;j<n;j++){
            for(int i=n-1;i>=1;i--){
                if(map[i][j] != EMPTY){
                    continue;
                }

                for(int droppedRow=i-1;droppedRow>=0;droppedRow--){
                    if(map[droppedRow][j] == ROCK){
                        break;
                    }

                    if(map[droppedRow][j] != EMPTY){
                        map[i][j] = map[droppedRow][j];
                        map[droppedRow][j] = EMPTY;
                        break;
                    }
                }
            }
        }
    }

    public static void rotateLeft(){
        int[][] rotatedMap = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                rotatedMap[n-1-j][i] = map[i][j];
            }
        }

        map = rotatedMap;
        applyGravity();
    }
}