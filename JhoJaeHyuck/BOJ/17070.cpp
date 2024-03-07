#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

static int N, ans;
static int arr[17][17];

// state 오른쪽(0), 아래쪽(1), 대각선(2)
void func(int, int, int);

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> arr[i][j];
        }
    }

    ans = 0;
    func(1, 2, 0);

    cout << ans;

    return 0;
}

void func(int row, int col, int state) {
    if (row == N && col == N) {
        ans++;
        return;
    }

    for (int dir = 0; dir < 3; dir++) {
        // 직각 이동 안됨
        if (dir == 0 && state == 1) continue;
        if (dir == 1 && state == 0) continue;

        // 오른쪽
        if (dir == 0 && col < N && !arr[row][col+1]) {
            func(row, col+1, 0);
        }
        // 아래쪽
        if (dir == 1 && row < N && !arr[row+1][col]) {
            func(row+1, col, 1);
        }
        // 대각선
        if (dir == 2 && row < N && col < N && !arr[row+1][col+1]) {
            if (arr[row+1][col] || arr[row][col+1]) continue; // 벽에 긁힘
            func(row+1, col+1, 2);
        }
    }
}
