#include <iostream>
#include <queue>
#include <tuple>

using namespace std;

int M, N, H, freshNum;
short tomatoes[100][100][100];
bool visited[100][100][100];
int dir[6][3] = {{1, 0 ,0 }, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}}; 
queue <tuple <short, short, short, int> > q;

int bfs(){
  int curM,curN,curH, nextM, nextN, nextH, tom, d;
  
  while(!q.empty()){
    tie(curM,curN,curH, d) = q.front();
    q.pop();
    tom = tomatoes[curM][curN][curH];
   
    for(int i = 0 ; i < 6; i++){
      nextM = curM + dir[i][0];
      nextN = curN + dir[i][1];
      nextH = curH + dir[i][2];
      tom = tomatoes[nextM][nextN][nextH];
      if(nextM < 0 || nextM >= M || nextN < 0 || nextN >= N || nextH < 0 || nextH >= H) 
        continue;
      if(tom == 0 && !visited[nextM][nextN][nextH]){
        visited[nextM][nextN][nextH] = true;
        tomatoes[nextM][nextN][nextH] = 1;
        if(--freshNum == 0) 
          return d+1;
        q.push({nextM, nextN, nextH, d+1});
      }
        
    }
  }
  if(freshNum == 0) 
    return 0;
  else  
    return -1;
} 

int main(void){
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> M >> N >> H;
    for(int h = 0; h < H; h++){
      for(int n = 0; n < N; n++){
        for(int m = 0; m < M; m++){
          cin >> tomatoes[m][n][h];
          if(tomatoes[m][n][h] == 0) freshNum++;
          if(tomatoes[m][n][h] == 1) q.push({m, n, h, 0});
        }
      }
    }

    cout << bfs() << "\n";

    return 0;
}
