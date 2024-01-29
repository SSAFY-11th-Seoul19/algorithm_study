#include <iostream>
#include <queue>
#include <tuple>
#include <cstring>

using namespace std;

int N, minLevel = 100, maxLevel, level, idx, ans, curVal;
short arr[100][100];
bool visited[100][100];
queue<pair <short, short>> q;
int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0 , -1}};

void bfs()
{
    pair <short, short> cur;
    int x, y, nX, nY;
    while(!q.empty())
    {
        tie(x,y) = q.front();
        q.pop();
        for(int i = 0 ; i < 4; i ++){
            nX = x + dir[i][0];
            nY = y + dir[i][1];
            if(nX < 0 || nX >= N || nY < 0 || nY >= N)
                continue;
            if(!visited[nX][nY] && arr[nX][nY] > level){
                visited[nX][nY] = true;
                q.push({nX, nY});
            }
        }        
    }
          
}

int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    
    cin >> N;
    for(int i = 0 ; i < N; i++)
    {
        for(int j = 0 ; j < N; j++)
        {
            cin >> arr[i][j];
            if(arr[i][j] > maxLevel) maxLevel = arr[i][j];
            if(arr[i][j] < minLevel) minLevel = arr[i][j];
        }
    }
    
    for(idx = 0, level = minLevel-1; level <= maxLevel; level++, idx++)
    {
        curVal = 0;
        for(int i = 0;  i < N; i++)
        {
            for(int j = 0 ; j < N; j++)
            {
                if(!visited[i][j] && arr[i][j] > level)
                {
                    visited[i][j] = true;
                    q.push({i, j});
                    curVal++;
                    bfs();    
                }
                
            }
        }
        if(curVal > ans) 
            ans = curVal;
        memset(visited, 0 , sizeof visited);
    }
    
    cout << ans << "\n";
  
    return 0;
}
