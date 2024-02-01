#include <iostream>
#include <stack>

using namespace std;
int N, M, r, c, d, a, ans, curR, curC, curD, nextR, nextC, nextD;
bool room[50][50];
bool visited[50][50];
int dC[] = {0, 1, 0, -1};
int dR[] = {-1, 0, 1, 0};
stack <tuple<int,int,int>> s;

int main(void){
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    cin >> r >> c >> d;
    for(int n = 0; n < N; n++){
      for(int m = 0; m < M; m++){
        cin >> a;
        if(a == 1)
          room[n][m] = true;
      }
    }
    s.push({r,c,d});
    while(!s.empty()){
      tie(curR, curC, curD) = s.top();
      s.pop();
      
      if(visited[curR][curC])
        continue;

      visited[curR][curC] = true;
      ans++;

      cout << curR << curC << curD << endl;
      for(int i = 1; i <= 4; i++){
        nextD = (curD + i) % 4;
        nextC = curC + dC[nextD];
        nextR = curR + dR[nextD];
        
        if(nextC < 0 || nextR < 0 || nextC >= M || nextR >= N)
          continue;
        if(room[nextR][nextC])
          continue;
      
        s.push({nextC, nextR, nextD});
      }
    
    }
    cout << ans << "\n";
    return 0;
}
