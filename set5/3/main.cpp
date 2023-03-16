#include<iostream>
#include<vector>
#include<deque>

using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    vector<int> arr(N); 
    for(int i = 0; i < N; i++) cin >> arr[i];
    vector<pair<int, int>> answer(N);
    deque<pair<int, int>> leftToRight, rightToleft;
    for(int i = 0; i < N; i++) {
        while(!leftToRight.empty() && leftToRight.back().first <= arr[i]) leftToRight.pop_back();
        if(!leftToRight.empty()) {
            answer[i].first += leftToRight.size();
            answer[i].second = leftToRight.back().second + 1;
        }
        leftToRight.emplace_back(arr[i], i);
    }
    for(int i = N - 1; i >= 0; i--) {
        while(!rightToleft.empty() && rightToleft.back().first <= arr[i]) rightToleft.pop_back();
        if(!rightToleft.empty()) {
            answer[i].first += rightToleft.size();
            if(answer[i].second) {
                int L = i - answer[i].second + 1;
                int R = rightToleft.back().second - i;
                if(L > R) answer[i].second = rightToleft.back().second + 1;
            }
            else answer[i].second = rightToleft.back().second + 1;
        }
        rightToleft.emplace_back(arr[i], i);
    }
    for(int i = 0; i < N; i++) {
        cout << answer[i].first;
        if(answer[i].first) cout << " " << answer[i].second;
        cout << '\n';
    }

    return 0;
}