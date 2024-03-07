#include <iostream>
#include <algorithm>
#include <vector>
#define INF 0x7FFFFFFF

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, S;
    cin >> N >> S;

    vector<int> v(N+1);
    vector<int> dp(N+1);
    v[0] = 0;
    dp[0] = 0;

    for (int i = 1; i <= N; i++) {
        cin >> v[i];
        dp[i] = dp[i-1] + v[i];
    }

    int start = 0;
    int end = 0;
    int minL = INF;
    while (end <= N) {
        if (start == end) {
            if (v[start] >= S) {
                minL = 1;
                break;
            }
            end++;
            continue;
        }
        if (dp[end] - dp[start-1] >= S) {
            minL = min(minL, end - start + 1);
            start++;
            continue;
        }
        end++;
    }

    cout << (minL == INF ? 0 : minL);

    return 0;
}
