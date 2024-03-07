#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#define INF 987654321

using namespace std;
typedef pair<int, int> pii;

vector<pii> adj[50001];
vector<int> peeks;
vector<int> starts;
int N;

vector<int> solution(int n, vector<vector<int>> paths, vector<int> gates, vector<int> summits) {
    // move parameters to global variables
    N = n;
    for (auto arr : paths) {
        adj[arr[0]].emplace_back(arr[1], arr[2]);
        adj[arr[1]].emplace_back(arr[0], arr[2]);
    }
    peeks = std::move(summits);
    starts = std::move(gates);

    // init for dijkstra
    vector<int> ans = {INF, INF};
    int dist[N+1];
    for (int i = 1; i <= N; i++) dist[i] = INF;
    priority_queue<pii, vector<pii>, greater<>> pq;
    for (const int& s : starts) {
        dist[s] = 0;
        pq.emplace(0, s);
    }

    // dijkstra
    while (!pq.empty()) {
        pair p = pq.top();
        pq.pop();
        int now = p.second;
        int weight = p.first;

        for (const auto& ad : adj[now]) {
            int next = ad.first;
            int cost = ad.second;

            int maxLength = max(weight, cost);
            if (dist[next] <= maxLength) continue;

            dist[next] = maxLength;
            if (count(peeks.begin(), peeks.end(), next)) continue;

            pq.emplace(dist[next], next);
        }
    }

    // find answer
    for (const int& peek : peeks) {
        if (dist[peek] < ans[1]) {
            ans = {peek, dist[peek]};
            continue;
        }
        if (dist[peek] == ans[1] && ans[0] > peek) {
            ans = {peek, dist[peek]};
        }
    }

    return ans;
}
