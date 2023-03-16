#include<iostream>
#include<vector>

using namespace std;


int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    string S; cin >> S;
    int siz = (int)S.size();
    vector<vector<pair<int, int>>> V(siz + 1);
    vector<int> DP(siz + 1);
    int N; cin >> N;
    for(int i = 0; i < N; ++i) {
        string X; int score; cin >> X >> score;
        if((int)X.size() >= score) continue;
        int idx = 0;
        while(true){
            auto cur = S.find(X, idx);
            if(cur == string::npos) break;
            V[cur].emplace_back((int)X.size(), score);
            idx = cur + 1;
        }
    }

    for (int i = 0; i < siz; i++) {
        for(auto &[len, s]: V[i]) {
            DP[i + len] = max(DP[i + len], DP[i] + s);
        }
        DP[i + 1] = max(DP[i + 1], DP[i] + 1);
    }
    cout << DP[siz];

    return 0;
}