import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n,center,crossRotateCnt,squareRotateCnt;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<Group> groups;
    static int groupCnt;
    static int[] combination;
    static int harmony = 0;

    static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Group{
        int num;
        int size = 0;
        ArrayList<Location> locs = new ArrayList<>();

        public Group(int num){
            this.num = num;
        }

        public void addLocation(Location loc){
            locs.add(loc);
            size++;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        center = n/2;
        crossRotateCnt = center;
        squareRotateCnt = n/4;

        map = new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while(true){
            visited = new boolean[n][n];
            groups = new ArrayList<>();
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(visited[i][j]){
                        continue;
                    }
                    findGroup(i,j);
                }
            }
            groupCnt = groups.size();

            combination = new int[2];
            calculateHarmony(0,0);
            if(++cnt == 4){
                break;
            }
            rotateCross();
            rotate();
        }

        bw.write(Integer.toString(harmony));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findGroup(int x, int y){
        Deque<Location> dq = new ArrayDeque<>();
        dq.add(new Location(x,y));
        visited[x][y] = true;

        int num = map[x][y];
        Group group = new Group(num);
        group.addLocation(new Location(x,y));
        while(!dq.isEmpty()){
            Location now = dq.poll();

            for(int i=0;i<4;i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=n){
                    continue;
                }

                if(map[nx][ny] != num){
                    continue;
                }

                if(visited[nx][ny]){
                    continue;
                }

                visited[nx][ny] = true;
                group.addLocation(new Location(nx,ny));
                dq.add(new Location(nx,ny));
            }
        }

        groups.add(group);
    }

    public static void calculateHarmony(int cnt, int idx){
        if(cnt == 2){
            findHarmony();
            return;
        }

        if(idx==groupCnt){
            return;
        }

        for(int i=idx;i<groupCnt;i++){
            combination[cnt] = i;
            calculateHarmony(cnt+1,i+1);
        }
    }

    public static void findHarmony(){
        Group groupA = groups.get(combination[0]);
        Group groupB = groups.get(combination[1]);

        ArrayList<Location> aLocs = groupA.locs;
        ArrayList<Location> bLocs = groupB.locs;
        for(Location aLoc:aLocs){
            for(Location bLoc:bLocs){
                if(Math.abs(aLoc.x-bLoc.x)+Math.abs(aLoc.y-bLoc.y)!=1){
                    continue;
                }

                harmony += (groupA.size+groupB.size)*groupA.num*groupB.num;
            }
        }
    }

    public static void rotateCross(){
        for(int i=0;i<crossRotateCnt;i++){
            int tmp = map[i][center];

            map[i][center] = map[center][n-1-i];
            map[center][n-1-i] = map[n-1-i][center];
            map[n-1-i][center] = map[center][i];
            map[center][i] = tmp;
        }
    }

    public static void rotate(){
        rotateSquare(0,0);
        rotateSquare(0,center+1);
        rotateSquare(center+1,0);
        rotateSquare(center+1,center+1);
    }

    public static void rotateSquare(int x, int y){
        for(int i=0;i<squareRotateCnt;i++){
            int moveCnt = center-1-i*2;
            while(moveCnt-->0){
                int tmp = map[x+i][y+center-1-i];

                for(int j=center-1-i;j>i;j--){
                    map[x+i][y+j] = map[x+i][y+j-1];
                }
                for(int j=i;j<center-1-i;j++){
                    map[x+j][y+i] = map[x+j+1][y+i];
                }
                for(int j=i;j<center-1-i;j++){
                    map[x+center-1-i][y+j] = map[x+center-1-i][y+j+1];
                }
                for(int j=center-1-i;j>i;j--){
                    map[x+j][y+center-1-i] = map[x+j-1][y+center-1-i];
                }
                map[x+i+1][y+center-1-i] = tmp;
            }
        }
    }
}
