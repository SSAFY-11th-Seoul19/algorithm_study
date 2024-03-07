#include <iostream>
#include <stack>

using namespace std;
int N, M, r, c, d, ans, r2,c2,d2;
int room[50][50];
int dC[] = {0, 1, 0, -1};
int dR[] = {-1, 0, 1, 0};
int main(void){
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    cin >> r >> c >> d;
    for(int n = 0; n < N; n++){
      for(int m = 0; m < M; m++){
        cin >> room[n][m];
      }
    }

    while(true){
      if(room[r][c] == 0){
        ans++;
        room[r][c] = 2;
      }
      
      bool isAllClean = true; 
      for(int i = 0; i < 4; i++){
        d2 = (d + i) % 4;
        c2 = c + dC[d2];
        r2 = r + dR[d2];
        
        if(c2 < 0 || r2 < 0 || c2 >= M || r2 >= N)
          continue;
        if(room[r2][c2] == 0){
          isAllClean = false;
          break;
        }
      }

      if(isAllClean){
        c2 = c - dC[d];
        r2 = r - dR[d];
        if(room[r2][c2] == 1)
          break;
        c = c2;
        r = r2;
      }
      else{
        d = (d+3) % 4;
        c2 = c + dC[d];
        r2 = r + dR[d];
        if(room[r2][c2] == 0){
          r = r2;
          c = c2;
        }
      }
    }

    cout << ans << "\n";
    return 0;
}
