#include <iostream>
#include <queue>

using namespace std;

typedef long long ll;

const ll INF = 0x7FFFFFFFFFFFFFFF;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    // 시야가 있는지 확인하는 sight 배열
    bool sight[N];
    for (int i = 0; i < N; i++) {
        int n; cin >> n;
        sight[i] = n != 0;
    }
    // 마지막 칸은 사실상 못보는것과 같다 
    sight[N-1] = false;

    // 최단 거리들을 갱신하는 dist 배열
    ll dist[N];
    for (int i = 1; i < N; i++) {
        dist[i] = INF;
    }
    // 첫 노드는 0의 무게를 갖는다
    dist[0] = 0;

    // 간선들의 정보를 담는 arr 배열
    vector<pair<int, int>> arr[N];
    for (int i = 0; i < M; i++) {
        int a, b, c;
        cin >> a >> b >> c;

        // 만약 시야가 있다면 애초에 넣을 필요가 없다
        if (sight[a] || sight[b]) continue;

        // 양방향 간선이기 때문에 2개씩 넣어줘야 한다
        arr[a].emplace_back(b, c);
        arr[b].emplace_back(a, c);
    }

    // 현재까지 가장 0과 가까운 node를 저장하기 위한 우선순위 큐
    // 속도 향상 겸, -1을 곱해서 넣으면 Java의 우선순위 큐와 같아진다
    priority_queue<pair<int, int>> pq;
    pq.emplace(0, 0);
  
    // 메인 로직
    while (!pq.empty()) {
        auto now = pq.top();
        pq.pop();

        int cost = -now.first;
        int node = now.second;

        // 현재 가장 작은 cost가 지금 node의 최단거리보다 크다면, 더이상 비교가 의미가 없다
        if (dist[node] < cost) continue;

        // 모든 간선 정보들을 돌면서 갱신할 거리가 있는지 확인
        for (auto p : arr[node]) {
            int nextNode = p.first;
            int nextCost = p.second;

            // 갱신하지 못하면 다음 간선 정보 확인
            if (dist[nextNode] <= dist[node] + nextCost) continue;

            // 최단거리 갱신 후에 우선순위 큐에 추가한다
            dist[nextNode] = dist[node] + nextCost;
            pq.emplace(-dist[nextNode], nextNode);
        }
    }

    // 정답 출력
    if (dist[N-1] == INF) cout << -1;
    else cout << dist[N-1];

    return 0;
}
