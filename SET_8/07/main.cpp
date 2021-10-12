#include<bits/stdc++.h>

using namespace std;

pair<int, int> S, E;
char Map[4][505][505];
int dist[4][505][505], k, N;
const int dx[] = {-1,1,0,0};
const int dy[] = {0,0,-1,1};

void turnRight(int t, int y, int x) {
    for(int i = 0; i < 4; i++) {
        for(int j = 0; j < 4; j++) {
            Map[t][y + j][x + 3 - i] = Map[t - 1][y + i][x + j];
        }
    }
}

void rotate(int &y, int &x) {
    int by = y / 4 * 4, bx = x / 4 * 4;
    y %= 4; x %= 4;
    int ny = x + by;
    int nx = 3 - y + bx;
    y = ny; x = nx;
}

int getIdx(int y, int x) {
    y /= 4; x /= 4;
    return y * k + x;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> k;
    N = k << 2;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            char x; cin >> x;
            if(x == 'S') S = make_pair(i, j);
            else if(x == 'E') E = make_pair(i, j);
            Map[0][i][j] = x;
            for(int k = 0; k < 4; k++) dist[k][i][j] = -1;
        }
    }

    for(int i = 0; i < N; i += 4) {
        for(int j = 0; j < N; j += 4) {
            for(int k = 1; k < 4; k++) {
                turnRight(k, i, j);
            }
        }
    }

    queue<tuple<int, int, int>> Q; 
    Q.emplace(0, S.first, S.second);
    dist[0][S.first][S.second] = 0;
    while(!Q.empty()) {
        auto [d, y, x] = Q.front(); Q.pop();
        int curk = getIdx(y, x);

        for(int k = 0; k < 4; k++) {
            int qy = y + dy[k];
            int qx = x + dx[k];
            int nd = (d + 1) % 4;
            int nxtk = getIdx(qy, qx);
            if(0 > qy || qy >= N || 0 > qx || qx >= N) continue;
            if(curk != nxtk) {
                if(Map[0][qy][qx] == '#') continue;
                rotate(qy, qx);
                if(dist[1][qy][qx] != -1) continue;
                dist[1][qy][qx] = dist[d][y][x] + 1;
                Q.emplace(1, qy, qx);
            }
            else {
                if(Map[d][qy][qx] == '#') continue;
                rotate(qy, qx);
                if(dist[nd][qy][qx] != -1) continue;
                dist[nd][qy][qx] = dist[d][y][x] + 1;
                Q.emplace(nd, qy, qx);
            }
        }
        // STAY
        int qy = y, qx = x, nd = (d + 1) % 4;
        rotate(qy, qx);
        if(dist[nd][qy][qx] == -1) {
            dist[nd][qy][qx] = dist[d][y][x] + 1;
            Q.emplace(nd, qy, qx);
        }
    }

    int ans = -1;
    for(int i = 0; i < 4; i++) {
        int ret = dist[i][E.first][E.second];
        rotate(E.first, E.second);
        if(ret == -1) continue;
        if(ans == -1) ans = ret;
        else ans = min(ans, ret);
    }
    cout << ans;

    return 0;
}
