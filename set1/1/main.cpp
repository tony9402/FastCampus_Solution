#include<iostream>
#include<vector>

using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;
    vector<int> state(N + 1);
    for(int i = 1; i <= N; i++) cin >> state[i];

    for (int i = 0; i < M; i++) {
        int command, a, b; cin >> command >> a >> b;
        if(command == 1){
            state[a] = b;
        }
        else if(command == 2) {
            for(int i = a; i <= b; i++) state[i] ^= 1; // state[i] = 1 - state[i]
        }
        else if(command == 3){
            for(int i = a; i <= b; i++) state[i] &= 0;
        }
        else if(command == 4){
            for(int i = a; i <= b; i++) state[i] |= 1;
        }
    }

    for(int i = 1; i <= N; i++) cout << state[i] << " ";

    return 0;
}