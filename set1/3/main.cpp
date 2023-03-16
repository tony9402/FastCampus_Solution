#include<iostream>
#include<vector>
#include<iomanip>

using namespace std;

long long gcd(long long a, long long b) {
    if(b == 0) return a;
    return gcd(b, a % b);
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    vector<int> arr(N);
    for(int i = 0; i < N; ++i) cin >> arr[i];
    int X; cin >> X;

    long long sum = 0, cnt = 0;
    for(int i = 0; i < N; ++i) {
        if(gcd(arr[i], X) == 1) {
            sum += arr[i];
            ++ cnt;
        }
    }

    cout << setprecision(6) << fixed << (double)sum / cnt;

    return 0;
}