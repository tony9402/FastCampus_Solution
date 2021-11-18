#include<bits/stdc++.h>

using namespace std;

set<pair<int, int>> problemList;
unordered_map<int, int> problemInfo;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    for(int i=0;i<N;i++){
        int problem, level; cin >> problem >> level;
        problemList.insert(pair<int, int>(level, problem));
        problemInfo[problem] = level;
    }

    int M; cin >> M;
    for(int i=0;i<M;i++){
        string cmd; cin >> cmd;
        if(cmd == "recommend") {
            int x; cin >> x;
            if(x > 0) {
                cout << problemList.rbegin()->second << '\n';
            }
            else {
                cout << problemList.begin()->second << '\n';
            }
        }
        else if(cmd == "solved") {
            int problem; cin >> problem;
            int level = problemInfo[problem];
            problemList.erase(pair<int, int>(level, problem));
            problemInfo.erase(problem);
        }
        else if(cmd == "add") {
            int problem, level; cin >> problem >> level;
            problemList.insert(pair<int, int>(level, problem));
            problemInfo[problem] = level;
        }
    }

    return 0;
}

