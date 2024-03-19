#include <string>
#include <vector>

using namespace std;

// 각 이모티콘에 대한 할인율을 저장할 배열 discounts와 이모티콘 정가 배열 arr
vector<int> discounts, arr;
// 고객정보 배열을 전역변수로 사용하기 위해 복사용 배열
vector<vector<int>> consumers;
// 플러스를 구매하는 가장 많은 사용자 수 ansCount와 그 때의 판매비용 ansPrice
int ansCount = 0, ansPrice = 0;
// 재귀의 끝을 확인하기 위해 이모티콘 배열의 정보 미리 N에다 저장
int N;

void func(int idx)
{
    // 만약 마지막에 도달하게 되면
    if (idx == N) {
        int cnt = 0; // 플러스 사는 사람 수
        int total = 0; // 나머지 사람에게의 판매액

        for (auto consumer : consumers) {
            int price = 0; // 한 사람의 소비
            int purchase = 0; // 한 사람의 구매 개수

            // 희망 할인율 넘는 애들만 계산해서 결과 저장
            for (int i = 0; i < N; i++) {
                if (discounts[i] * 10 < consumer[0]) continue;

                price += (arr[i] - ((arr[i] / 10) * discounts[i]));
                purchase++;
            }

            // 가격 넘으면 플러스에 추가, 아니라면 판매액에 추가
            if (price >= consumer[1]) cnt++;
            else total += price;
        }

        // 최댓값 갱신 및 판매액 갱신
        if (cnt > ansCount) {
            ansCount = cnt;
            ansPrice = total;
        }
        if (cnt == ansCount && total > ansPrice) {
            ansPrice = total;
        }

        return;
    }

    // 4가지 할인에 대해 넣고 빼고를 재귀적으로 반복
    for (int rate = 1; rate <= 4; rate++) {
        discounts.push_back(rate);
        func(idx + 1);
        discounts.pop_back();
    }
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
    // 전역 변수가 아니라 재귀를 쓸 수 없기에 배열 복사 진행함
    for (auto user : users) consumers.push_back(user);
    for (auto emoticon : emoticons) arr.push_back(emoticon);

    N = arr.size();

    func(0);

    vector<int> answer;
    answer.push_back(ansCount);
    answer.push_back(ansPrice);

    return answer;
}
