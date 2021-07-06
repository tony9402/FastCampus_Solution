// #include<bits/stdc++.h>
#include<iostream>
#include<vector>

using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    vector<int> v(N); 
    for(int i = 0; i < N; i++) cin >> v[i];

    int ans = 0;
    for(int i=0,j;i<N;){
        bool found = false;
        for(j=i+1;j<N;j+=2){
            int l = i, r = j;
            bool f = true;
            while(f && l < r){
                if(v[l] != v[r]) f = false;
                l ++; r--;
            }
            if(f) {
                ans ++;
                found = true;
                break;
            }
        }
        if(found == false) {
            ans = -1;
            break;
        }
        i = j + 1;
    }
    cout << ans;

    return 0;
}
