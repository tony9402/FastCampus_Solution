// #include<bits/stdc++.h>
#include<iostream>
#include<vector>

using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;
    vector<int> bulb(N + 1);
    for(int i=1;i<=N;i++) cin >> bulb[i];

    for(int i=0;i<M;i++){
        int command, a, b; cin >> command >> a >> b;
        if(command == 1){
            bulb[a] = b;
        }
        else if(command == 2) {
            for(int i=a;i<=b;i++) bulb[i] ^= 1;
        }
        else if(command == 3){
            for(int i=a;i<=b;i++) bulb[i] &= 0;
        }
        else if(command == 4){
            for(int i=a;i<=b;i++) bulb[i] |= 1;
        }
    }

    for(int i=1;i<=N;i++) cout << bulb[i] << " ";

    return 0;
}
