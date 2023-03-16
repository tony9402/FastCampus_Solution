#include<iostream>
#include<vector>

using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, X; cin >> N >> X;
    vector<int> arr(N);
    for(int i = 0; i < N; ++i) cin >> arr[i];

    int sum = 0;
    for(int i = 0; i < X; ++i) sum += arr[i];
    int mx = sum, cnt = 1;

    for(int i = X; i < N; ++i) {
        sum -= arr[i - X];
        sum += arr[i];
        if(mx < sum) {
            mx = sum;
            cnt = 1;
        }
        else if(mx == sum) ++cnt;
    }
    if(mx == 0) cout << "SAD";
    else cout << mx << '\n' << cnt;

    return 0;
}