#include<iostream>

using namespace std;

long long mx, A, B, C, M;

void go(int t, long long a, long long s) {
    if(t == 24) { 
        mx = max(mx, s);
        return;
    }
    if(a + A <= M) go(t + 1, a + A, s + B);
    go(t + 1, max(0LL, a - C), s);
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> A >> B >> C >> M;
    go(0, 0, 0);
    cout << mx;

    return 0;
}