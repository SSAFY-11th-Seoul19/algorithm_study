#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;
typedef pair<int, int> pii;

int N, M, T;
priority_queue<pii> pq[2];
vector<pii> arrival;
queue<pii> ship;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> M >> T >> N;
    for (int i = 0; i < N; i++) {
        int t; string s;
        cin >> t >> s;

        // side 0 stands for left side, and 1 for right
        // each side has its own priority_queue of people waiting
        if (s == "left") pq[0].emplace(-t, i);
        else pq[1].emplace(-t, i);
    }

    int time = -1;
    int people = 0; // current number of people on board
    int side = 0; // current side of the river
    while (true) {
        time++;
        if (pq[0].empty() && pq[1].empty()) break; // no more people to cross

        // ship all the available people on current side
        while (!pq[side].empty() && -pq[side].top().first <= time && people < M) {
            people++;
            ship.emplace(pq[side].top());
            pq[side].pop();
        }

        // if at least one people on board, or a person waiting at the opposite side, ship moves
        // if not, ship waits for passenger as time past
        int other = (side + 1) % 2;
        if (people || (!pq[other].empty() && -pq[other].top().first <= time)) {
            // unship people
            while (!ship.empty()) {
                arrival.emplace_back(ship.front().second, time + T);
                ship.pop();
            }

            // change side and pass the time
            side = (side + 1) % 2;
            time += T-1;
            people = 0;
        }
    }

    // make sure to print people's arrival time in input order
    sort(arrival.begin(), arrival.end());
    for (const auto& p : arrival) {
        cout << p.second << '\n';
    }

    return 0;
}
