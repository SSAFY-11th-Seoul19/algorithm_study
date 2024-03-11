#include <vector>
#define MAX 1000000

using namespace std;

// 들어오는 간선의 개수를 a, 나가는 간선의 개수를 b라고 할때,
// 모든 노드는 a:b로 나타낼 수 있다
// 생성된 정점만 0:N (N은 2이상)이 될 수 있다

// 도넛: 모든 노드가 1:1
// 막대: 0:0 이거나, 1:0 이 시작지점
// 8자: 2:2 하나가 시작지점

// 막대와 8자는 시작지점으로 개수를 판단할 수 있기 때문에
// 총 생성된 그래프 - 위의 두 그래프 개수 = 도넛 그래프 개수

int indegree[MAX+1];
int outdegree[MAX+1];

vector<int> solution(vector<vector<int>> edges) {
    vector<int> answer(4, 0);
    
    int start = 0; // 시작 지점
    int count = 0; // 총 그래프의 개수
    for (const auto& v : edges) {
        outdegree[v[0]]++;
        indegree[v[1]]++;
    }
    
    for (int i = 1; i <= MAX; i++) {
        
        // 만약 생성된 노드를 찾았다면,
        // 1. 나가는 간선의 개수를 세준다
        // 2. 한칸짜리 막대 그래프 예외처리 해준다
        if (indegree[i] == 0 && outdegree[i] > 1) {
            
            // 정답에 생성 노드 추가
            answer[0] = i;
            
            // 나가는 간선 돌면서 카운팅이랑 예외처리
            for (const auto& v : edges) {
                if (v[0] == i) {
                    count++;
                    indegree[v[1]]--;
                    
                    // 만약 홀로 남겨진 노드라면, 막대 그래프
                    if (indegree[v[1]] == 0 && outdegree[v[1]] == 0) {
                        answer[2]++;
                        count--;
                    }
                }
            }
            
            break;
        }
    }
    
    // 모든 degree를 확인하면서 그래프 모양 판별하기
    for (int i = 1; i <= 1000000; i++) {
        
        // 1:1 노드 무시 (상관 없음)
        if (indegree[i] == 1 && outdegree[i] == 1) continue;
        // 삽입된 노드 무시 (이미 처리했음)
        if (indegree[i] == 0 && outdegree[i] > 1) continue;
        
        // 8자 그래프 (2:2)
        if (indegree[i] == 2 && outdegree[i] == 2) {
            answer[3]++;
            count--;
            continue;
        }
        // 막대 그래프 (0:1)
        if (indegree[i] == 0 && outdegree[i] == 1) {
            answer[2]++;
            count--;
        }
    }
    
    // 공식대로 도넛 그래프 계산
    answer[1] = count;
    
    return answer;
}
