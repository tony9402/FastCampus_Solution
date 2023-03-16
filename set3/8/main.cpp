#include<iostream>
#include<vector>

using namespace std;

vector<int> go, idx, arr, tmp;
vector<int> index_arr, numbering;
vector<vector<int>> V;

void dfs(int cur, int root, int number) {
    go[cur] = number;
    index_arr[cur] = root;
    V[root].push_back(cur);
    if(go[idx[cur]] == -1) dfs(idx[cur], root, number + 1);
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; long long K; cin >> N >> K;
    go = vector<int>(N + 1, -1);
    index_arr = arr = tmp = idx = vector<int>(N + 1);
    V.resize(N + 1);
    for(int i = 1; i <= N; i++) cin >> arr[i];
    for(int i = 1; i <= N; i++) cin >> idx[i];
    
    for(int i = 1; i <= N; i++) {
        if(go[i] == -1) dfs(i, i, 1);
        int idx  = index_arr[i];
        int siz  = V[idx].size();
        int T    = K % siz;
        int Vidx = (go[i] + T - 1) % siz;
        int pre  = V[idx][Vidx];
        tmp[pre] = arr[i];
    }

    arr = tmp;
    for(int i = 1; i <= N; i++) cout << arr[i] << " ";

    return 0;
}