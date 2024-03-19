#include <vector>
#include <cstring>
#define MAX 17

using namespace std;

int ans, N;
bool v[1<<MAX];
vector<int> val;
vector<int> adj[MAX+1];

void func(int state) {
    
    // set visited
    if (v[state]) return;
    v[state] = true;
    
    // count animals
    int wolf = 0, sheep = 0;
    for (int i = 0; i < N; i++) {
        if (!(state & (1 << i))) continue;
        val[i] ? wolf++ : sheep++;
    }
    
    // if impossible way, return
    if (wolf >= sheep) return;
    // if not, update answer and continue
    ans = std::max(ans, sheep);
    
    // find next node and recurse
    for (int i = 0; i < N; i++) {
        if (!(state & (1 << i))) continue;
        for (const auto& next : adj[i]) {
            func(state | (1 << next));
        }
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    
    // move parametes
    for (auto i : info) val.push_back(i);
    for (auto vi : edges) adj[vi[0]].push_back(vi[1]);
    
    // bitmask + bfs
    N = val.size();
    func(1);
    
    return ans;
}
