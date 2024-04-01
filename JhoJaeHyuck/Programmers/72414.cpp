#include <string>
#include <vector>
#define MAX 360000

using namespace std;
typedef long long ll;

// convert given time stamp to int
int t2i(string str) {
    int h = stoi(str.substr(0, 2));
    int m = stoi(str.substr(3, 2));
    int s = stoi(str.substr(6, 2));
    int result = h * 3600 + m * 60 + s;
    
    return result;
}

string solution(string play_time, string adv_time, vector<string> logs) {
    int pt = t2i(play_time);
    int at = t2i(adv_time);
    ll arr[MAX] = {};

    // IMO method array
    for (const auto& str : logs) {
        int l = t2i(str.substr(0, 8));
        int r = t2i(str.substr(9, 8));
        arr[l]++;
        arr[r]--;
    }

    // fill in dp
    for (int i = 1; i < pt; i++) {
        arr[i] += arr[i-1];
    }

    // sliding window init
    ll sum = 0;
    for (int i = 0; i < at; i++) {
        sum += arr[i];
    }

    // main logic of sliding window using EMO
    ll m = sum;
    int ans = 0;
    int left = 0;
    int right = at-1;
    while (right < pt-1) {
        sum += arr[++right];
        sum -= arr[left++];
        if (sum > m) {
            m = sum;
            ans = left;
        }
    }

    // parse answer to string type
    string hh = to_string(ans / 3600);
    if (hh.length() == 1) hh = "0" + hh;
    string mm = to_string((ans % 3600) / 60);
    if (mm.length() == 1) mm = "0" + mm;
    string ss = to_string((ans % 3600) % 60);
    if (ss.length() == 1) ss = "0" + ss;
    
    string answer = hh + ":" + mm + ":" + ss;
    return answer;
}
