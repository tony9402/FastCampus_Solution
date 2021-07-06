// #include<bits/stdc++.h>
#include<iostream>
#include<vector>

using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    
    int N, X; cin >> N >> X;
    vector<int> prefix(N + 1);
    for(int i = 0; i < N; i++) {
        cin >> prefix[i + 1];
        prefix[i + 1] += prefix[i];
    }

    int mx = 0, answer = 0;

    for(int L = 1, R = X ; R <= N; L++, R++) {
        int sum = prefix[R] - prefix[L - 1];
        if(mx == sum) answer ++;
        else if(mx < sum) {
            answer = 1;
            mx = sum;
        }
    }
    if(mx == 0) cout << "SAD";
    else cout << mx << '\n' << answer;

    return 0;
}
