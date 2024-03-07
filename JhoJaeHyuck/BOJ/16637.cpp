#include <iostream>
#include <vector>
#include <stack>

using namespace std;

static int N, ans;
static string str;
static bool bracketed[19];
static vector<int> arr;

void func(int);
int cal();

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> str;

    // 편의상 연산자를 int로 변환해서 사용했다
    // + -> -1
    // - -> -2
    // * -> -3
    for (char c : str) {
        switch (c) {
            case '+':
                arr.push_back(-1);
                break;
            case '-':
                arr.push_back(-2);
                break;
            case '*':
                arr.push_back(-3);
                break;
            default:
                arr.push_back(c - 48);
                break;
        }
    }

    // 최솟값 설정하고
    ans = -2147483648;

    // 로직상 N이 1인 경우는 처리하기 힘들기에 예외처리
    if (N == 1) {
        cout << arr.front();
        return 0;
    }

    // 첫번째 연산자가 괄호를 먹는지 아닌지에 따른 재귀 시작
    bracketed[1] = true;
    func(3);
    bracketed[1] = false;
    func(3);

    cout << ans;

    return 0;
}

void func(int idx) {
    // 만약 마지막 까지 왔다면, 계산 결과 정답과 비교해서 갱신
    if (idx == N) {
        ans = max(ans, cal());
        return;
    }

    // 만약 바로 전 연산자가 괄호를 먹었다면, 바로 뒤 연산자는 못먹는다
    // 즉, 전 연산자가 괄호를 먹지 않은 경우만 현재 연산자가 괄호 먹은 재귀를 한다
    if (!bracketed[idx-2]) {
        bracketed[idx] = true;
        func(idx+2);
    }
    bracketed[idx] = false;
    func(idx + 2);
}

int cal() {
    // 덱을 이용해서 빠르게 연산했다
    deque<int> dq;
    for (int i = 0; i < N; i ++) {
        // 숫자인 경우는 그냥 넣는다
        if (arr[i] >= 0) {
            dq.push_back(arr[i]);
            continue;
        }

        // 괄호가 없는 경우도 그냥 넣는다
        if (!bracketed[i]) {
            dq.push_back(arr[i]);
            continue;
        }

        // 여기부터는 괄호를 먹은 연산자를 만난 경우
        // 맨 뒤의 숫자를 빼오고, 이제 올 숫자도 미리 땡겨온다 (++i)
        // 그다음 강제로 연산해서 덱에 넣어준다
        int a = dq.back();
        dq.pop_back();
        int op = arr[i];
        int b = arr[++i];
        switch (op) {
            case -1:
                dq.push_back(a + b);
                break;
            case -2:
                dq.push_back(a - b);
                break;
            case -3:
                dq.push_back(a * b);
                break;
            default:
                break;
        }
    }

    // 여기부터는 모든 괄호가 사라졌으므로 평범한 연산
    int sum = dq.front();
    dq.pop_front();

    while (!dq.empty()) {
        int op = dq.front();
        dq.pop_front();
        int num = dq.front();
        dq.pop_front();

        switch (op) {
            case -1:
                sum += num;
                break;
            case -2:
                sum -= num;
                break;
            case -3:
                sum *= num;
                break;
            default:
                break;
        }
    }

    return sum;
}
