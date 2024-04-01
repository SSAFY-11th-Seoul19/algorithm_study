#include <iostream>
#include <vector>
#include <unordered_map>
#include <set>
#include <string>
#include <sstream>

using namespace std;
typedef pair<int, string> File;

unordered_map<string, vector<File>> m;
set<string> types;
int files;

vector<string> split(const string& s, const char& c) {
    vector<string> buffer;
    istringstream ss(s);
    string temp;

    while (getline(ss, temp, c)) {
        buffer.push_back(temp);
    }

    return buffer;
}

void countAns(const string& dir) {
    for (const auto& a: m[dir]) {
        if (!a.first) {
            files++;
            types.insert(a.second);
            continue;
        }
        countAns(a.second);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;
    N += M;
    while (N--) {
        string parent, name;
        int c;
        cin >> parent >> name >> c;
        m[parent].emplace_back(c, name);
    }

    int K;
    cin >> K;
    while (K--) {
        files = 0;
        types.clear();

        string str;
        cin >> str;
        auto buffer = split(str, '/');

        string name = buffer[buffer.size() - 1];

        countAns(name);
        cout << types.size() << ' ' << files << '\n';
    }

    return 0;
}
