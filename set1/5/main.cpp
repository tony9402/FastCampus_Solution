#include<iostream>
#include<queue>
#include<algorithm>

using namespace std;

const int dy[] = {-1,1,0,0};
const int dx[] = {0,0,-1,1};
int Map[2005][2005], ans;
int trace[2005][2005];
int N, M;

int changeDir(int value, int curDir){
    if(value == 1) {
        if(curDir == 0 || curDir == 1) return curDir;
        return 5 - curDir;
    }
    if(value == 2) {
        if(curDir == 0 || curDir == 1) return 1 - curDir;
        return curDir;
    }
    if(value == 3) return 3 - curDir;
    if(value == 4) {
        if(curDir == 1 || curDir == 3)return 4 - curDir;
        return 2 - curDir;
    }
    return curDir;
}

bool range(int y, int x) {
    return 0 <= y && y < N && 0 <= x && x < M;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> M;
    queue<pair<int, int>> q;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> Map[i][j];
            if(Map[i][j] == 9)q.emplace(i, j);
        }
    }

    while(!q.empty()){
        auto [y, x] = q.front(); q.pop();
        if(~trace[y][x] & 16) {
            trace[y][x] |= 16;
            ans++;
        }
        for (int i = 0; i < 4; i++) {
            int dir = i;
            int qy = y, qx = x;
            if(trace[qy][qx] & (1 << dir))continue;
            trace[qy][qx] |= (1 << dir);
            while(true){
                qy += dy[dir]; qx += dx[dir];
                if(!range(qy, qx) || trace[qy][qx] & (1 << dir)) break;
                trace[qy][qx] |= (1 << dir);
                if(~trace[qy][qx] & 16){
                    trace[qy][qx] |= 16;
                    ans++;
                }
                dir = changeDir(Map[qy][qx], dir);
            }
        }
    }
    cout << ans;

    return 0;
}