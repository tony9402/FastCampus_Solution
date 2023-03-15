#include<iostream>

using namespace std;

bool check_prime(long long num) {
    if(num < 2) return false;
    for(long long i = 2; i * i <= num; ++i) {
        if(num % i == 0) return false;
    }
    return true;
}

long long gcd(long long a, long long b) {
    if(b == 0) return a;
    return gcd(b, a % b);
}

long long lcm(long long a, long long b) {
    return a / gcd(a, b) * b;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    long long ans = 1;
    for(int i = 0; i < N; ++i) {
        long long x; cin >> x;
        if(check_prime(x)) ans = lcm(ans, x);
    }
    if(ans == 1) cout << -1;
    else cout << ans;

    return 0;
}