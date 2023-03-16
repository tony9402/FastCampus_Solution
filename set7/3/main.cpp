#include<iostream>
#include<vector>
#include<climits>

using namespace std;

pair<int, int> Start, End;
vector<pair<int, int>> point;
int used[15], ans = INT_MAX;
int N, H, D; 

void dfs(int y, int x, int h, int d, int t) {
    int to_end = abs(y - End.first) + abs(x - End.second);
    if(h + d - to_end >= 0) {
        ans = min(ans, t + to_end);
        return;
    }

    for(int i = 0; i < (int)point.size(); i++) {
        if(used[i]) continue;
        auto [ny, nx] = point[i];
        int dis = abs(ny - y) + abs(nx - x);
        if(h + d - dis < 0) continue;
        used[i] = 1;
        int nxtH = h - max(0, dis - d - 1);
        dfs(ny, nx, nxtH, D - 1, t + dis);
        used[i] = 0;
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> H >> D;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            char ch; cin >> ch;
            if(ch == 'S') Start = make_pair(i, j);
            else if(ch == 'E') End = make_pair(i, j);
            else if(ch == 'U') point.emplace_back(i, j);
        }
    }
    
    dfs(Start.first, Start.second, H, 0, 0);

    if(ans == INT_MAX) ans = -1;
    cout << ans;

    return 0;
}
