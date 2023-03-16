#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

vector<pair<int, int>> sch;

int parse(string s) {
    int HH, MM, SS, sss;
    sscanf(s.c_str(), "%d:%d:%d.%d", &HH, &MM, &SS, &sss);
    return HH * 60 * 60 * 1000 + MM * 60 * 1000 + SS * 1000 + sss;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    for(int i = 0; i < N; i++) {
        string a, b; cin >> a >> b;
        int L = parse(a), R = parse(b);
        sch.emplace_back(L, 1);
        sch.emplace_back(R, -1);
    }
    sort(sch.begin(), sch.end());
    int sum = 0, mx = 0;
    for(int i = 0; i < 2 * N; i++) {
        sum += sch[i].second;
        mx = max(sum, mx);
    }
    cout << mx;

    return 0;
}