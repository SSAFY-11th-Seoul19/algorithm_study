#include <iostream>
#include <vector>
#include <queue>

using namespace std;

// 간선에 순서가 존재 하는 단방향 그래프? -> 위상정렬
// 단, 문제에서 최소 시간이라고 했지만, 사실 거리상으로는 최장 거리이다.
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    for (int tc = 1; tc <= T; tc++) {
        int N, K;
        cin >> N >> K;

        // 건설시간 정보 벡터
        vector<int> arr(N+1);
        for (int i = 1; i <= N; i++) cin >> arr[i];

        // 간선 정보 벡터
        vector<int> road[N+1];
        vector<int> degree(N+1, 0);
        for (int i = 1; i <= K; i++) {
            int a, b;
            cin >> a >> b;
            road[a].push_back(b);
            // 받으면서 degree확인까지
            degree[b]++;
        }

        int W;
        cin >> W;

        // 최장거리 벡터와 위상정렬을 위한 큐
        vector<int> dist(N+1, 0);
        queue<int> q;
        // 초기 세팅
        for (int i = 1; i <= N; i++) {
            if (degree[i]) continue;
            q.push(i);
            dist[i] = arr[i];
        }

        // 위상정렬 시작
        while (!q.empty()) {
            int now = q.front();
            q.pop();

            // 다음으로 가는 간선을 찾으면서 "최대"값을 넣는다
            for (const auto& next : road[now]) {
                dist[next] = max(dist[next], dist[now] + arr[next]);
                // 만약 다음 노드의 indegree가 없다면 건설 가능
                degree[next]--;
                if (!degree[next]) q.push(next);
            }
        }

        cout << dist[W] << '\n';
    }

    return 0;
}
