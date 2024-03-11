#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    // 크레인 정보 입력 + 정렬
    int N; cin >> N;
    vector<int> cranes(N);
    for (int i = 0; i < N; i++) {
        cin >> cranes[i];
    }
    sort(cranes.begin(), cranes.end(), greater<>());

    // 화물 정보 입력 + 정렬
    int M; cin >> M;
    vector<int> boxes(M);
    for (int i = 0; i < M; i++) {
        cin >> boxes[i];
    }
    sort(boxes.begin(), boxes.end(), greater<>());

    // 가장 좋은 크레인이 가장 무거운 화물을 못들면 -1 출력
    if (cranes.front() < boxes.front()) {
        cout << -1;
        return 0;
    }

    // 모든 크레인과 화물을 1:1로 비교하면서 greedy하게 제거
    // erase 함수를 이용해서 boxes vector에서 제거
    int ans = 0;
    while (!boxes.empty()) {
        ans++;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < boxes.size(); j++) {
                if (cranes[i] >= boxes[j]) {
                    boxes.erase(boxes.begin() + j);
                    break;
                }
            }
        }
    }

    cout << ans;

    return 0;
}
