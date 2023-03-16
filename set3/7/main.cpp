#include<iostream>
#include<vector>

using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, K; cin >> N >> K;
    vector<int> arr(N); 
    for(int i = 0; i < N; ++i) cin >> arr[i];

    int erased = 0, answer = 0;;
    for(int i = 0, R = 0; i < N; i++) {
        while(R < N) {
            if(arr[R] % 2 == 0) R++;
            else {
                if(erased == K) break;
                erased++;
                R++;
            }
        } 
        answer = max(answer, R - i - erased);
        if(arr[i] % 2) erased--;
    }
    cout << answer;

    return 0;
}