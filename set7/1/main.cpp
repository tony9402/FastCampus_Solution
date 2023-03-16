#include<iostream>
#include<vector>
#include<tuple>
#include<stack>
#include<algorithm>

using namespace std;

vector<tuple<int, int, int>> v;
bool points[2020202];

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    const int base = 1010000;
    for(int i = 1; i <= N; i++) {
        int x, r; cin >> x >> r;
        v.emplace_back(x - r, 1, i);
        v.emplace_back(x + r, -1, i);
        if(points[x - r + base]) return cout << "NO", 0;
        if(points[x + r + base]) return cout << "NO", 0;
        points[x - r + base] = points[x + r + base] = 1;
    }
    sort(v.begin(), v.end());
    stack<int> st;
    bool ans = true;
    for(auto [x, t, idx]: v) {
        if(t == 1) st.emplace(idx);
        else {
            if(st.empty()) {
                ans = false;
                break;
            }
            else if(st.top() == idx) st.pop();
            else {
                ans = false;
                break;
            }
        }
    }

    if(ans) cout << "YES";
    else cout << "NO";

    return 0;
}