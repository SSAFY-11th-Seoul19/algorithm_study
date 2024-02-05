import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int N;
    static int answer = 0;
    static int[] chess;
    static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        chess = new int[N];
        visited = new boolean[N];
        countPossible(0);
        bw.write(Integer.toString(answer));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void countPossible(int cnt){
        if(cnt == N){
            answer++;
            return;
        }

        for(int j=0;j<N;j++){ // 0~N번째 열에 체스 두기
            if(visited[j]){
                continue;
            }

            if(isPossible(cnt,j)){
                continue;
            }

            visited[j] = true;
            chess[cnt] = j;
            countPossible(cnt+1);
            visited[j] = false;
        }
    }

    public static boolean isPossible(int row, int col){
        for(int i=0;i<row;i++){
            if(row-i==col-chess[i] || row-i==chess[i]-col){ // 대각선 체크
                return false;
            }
        }
        return true;
    }
}
