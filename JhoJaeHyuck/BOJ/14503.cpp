#include <stdio.h>

using namespace std;

short dx[4] = {-1, 0, 1, 0};
short dy[4] = {0, 1, 0, -1};

short N, M, x, y, nx, ny, dir, i, j;
short cleaned;

int main()
{
    scanf("%hd %hd", &N, &M);

    short arr[N][M];
    scanf("%hd %hd %hd", &x, &y, &dir);

    for (i = 0; i < N; i++)
        for (j = 0; j < M; j++)
            scanf("%hd", &arr[i][j]);

    while (true) {
        if (!arr[x][y]) {
            arr[x][y] = 2;
            cleaned++;
        }

        if (arr[x-1][y] && arr[x+1][y] && arr[x][y-1] && arr[x][y+1]) {
            nx = x - dx[dir];
            ny = y - dy[dir];
            if (arr[nx][ny] == 1) break;

            x = nx;
            y = ny;
            continue;
        }

        dir = (dir + 3) % 4;
        nx = x + dx[dir];
        ny = y + dy[dir];
        if (!arr[nx][ny]) {
            x = nx;
            y = ny;
        }
    }

    printf("%hd", cleaned);

    return 0;
}
