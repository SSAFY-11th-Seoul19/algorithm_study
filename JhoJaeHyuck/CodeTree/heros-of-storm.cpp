/*
  Type: Simulation
  Idea: 2-step simulation which are dust propagtion and wind blowing
   - implement a temp array to accumulate propagated dust amount and add it to original array
   - make sure to divide the wind index bound into to section
*/

#include <iostream>
#include <cstring>

using namespace std;

static int N, M, T, wind;
static int wIdx = 0;
static int arr[50][50];
static int dx[4] = {-1, 0, 1, 0};
static int dy[4] = {0, 1, 0, -1};

void propagation();
void windblow();
int count();

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    // input
    cin >> N >> M >> T;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> arr[i][j];
            if (arr[i][j] < 0) wind = i;
        }
    }

    // repeat the 2-step simulation each tick
    for (int i = 0; i < T; i++) {
        propagation();
        windblow();
    }

    // output
    cout << count();

    return 0;
}

void propagation() {
    // fill the temp array with 0 to add dust
    int temp[N][M];
    memset(temp, 0, sizeof(temp));

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            int cnt = 0; // counting valid adjacent space
            int amount = arr[i][j] / 5; // amount of dust being propagated

            for (int dir = 0; dir < 4; dir++) {
                int x = i + dx[dir];
                int y = j + dy[dir];

                // check index bound
                if (x < 0 || y < 0 || x >= N || y >= M) continue;
                if (arr[x][y] < 0) continue;

                // add it to temp array
                cnt++;
                temp[x][y] += amount;
            }

            // take away the total amount of dust from the original array
            arr[i][j] -= amount * cnt;
        }
    }

    // bring all the dust of temp to original array
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            arr[i][j] += temp[i][j];
        }
    }
}

void windblow() {
    // tx is the middle-way index bound to seperate sections
    int tx = wind;

    // stating points & directions
    int wx = wind - 2;
    int wy = 0;
    int dir = 0;
    int nx, ny;

    // wind blowing in upper section
    while (true) {
        nx = wx + dx[dir];
        ny = wy + dy[dir];

        if (nx < 0 || ny < 0 || nx >= tx || ny >= M) {
            dir = (dir + 1) % 4;
            nx = wx + dx[dir];
            ny = wy + dy[dir];
        }

        // last one will be 0 (cleaned by the wind)
        if (arr[nx][ny] < 0) {
            arr[wx][wy] = 0;
            break;
        }

        arr[wx][wy] = arr[nx][ny];
        wx = nx;
        wy = ny;
    }

    // inititate again for the lower section
    wx = wind + 1;
    wy = 0;
    dir = 2;

    while (true) {
        nx = wx + dx[dir];
        ny = wy + dy[dir];

        if (nx < tx || ny < 0 || nx >= N || ny >= M) {
            dir = (dir + 3) % 4; // direction rotating in opposite way
            nx = wx + dx[dir];
            ny = wy + dy[dir];
        }

        if (arr[nx][ny] < 0) {
            arr[wx][wy] = 0;
            break;
        }

        arr[wx][wy] = arr[nx][ny];
        wx = nx;
        wy = ny;
    }
}

int count() {
    // total for counting the whole array
    int total = 0;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (arr[i][j] < 0) continue; // make sure not to add the wind (-1)
            total += arr[i][j];
        }
    }

    return total;
}
