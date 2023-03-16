#include<iostream>
#include<set>
#include<unordered_map>

using namespace std;

set<pair<int, int>> problemList[105];
set<pair<int, int>> problemAllList;
unordered_map<int, pair<int, int>> problemInfo;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    for (int i = 0; i < N; ++i) {
        int problem, level, group; cin >> problem >> level >> group;
        problemList[group].insert(pair<int, int>(level, problem));
        problemAllList.insert(pair<int, int>(level, problem));
        problemInfo[problem] = pair<int, int>(level, group);
    }
    int M; cin >> M;
    for (int i = 0; i < M; ++i) {
        string cmd; cin >> cmd;
        if(cmd == "add") {
            int problem, level, group; cin >> problem >> level >> group;
            problemList[group].insert(pair<int, int>(level, problem));
            problemAllList.insert(pair<int, int>(level, problem));
            problemInfo[problem] = pair<int, int>(level, group);
        }
        else if(cmd == "recommend") {
            int group, x; cin >> group >> x;
            if(x == 1) {
                cout << problemList[group].rbegin()->second << '\n';
            }
            else if(x == -1) {
                cout << problemList[group].begin()->second << '\n';
            }
        }
        else if(cmd == "recommend2") {
            int x; cin >> x;
            if(x == -1){
                cout << problemAllList.begin()->second << '\n';
            }
            else {
                cout << problemAllList.rbegin()->second << '\n';
            }
        }
        else if(cmd == "recommend3") {
            int x, L; cin >> x >> L;
            auto it = problemAllList.lower_bound(pair<int, int>(L, -1));
            if(x == 1) {
                if(it == problemAllList.end()) cout << -1 << '\n';
                else cout << it->second << '\n';
            }
            else {
                if(it == problemAllList.begin()) cout << -1 << '\n';
                else {
                    it--;
                    cout << it->second << '\n';
                }
            }
        }
        else if(cmd == "solved") {
            int problem; cin >> problem;
            auto [level, group] = problemInfo[problem];
            problemList[group].erase(pair<int, int>(level, problem));
            problemAllList.erase(pair<int, int>(level, problem));
            problemInfo.erase(problem);
        }
    }

    return 0;
}