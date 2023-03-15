#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    vector<int> arr(N); 
    for(int i = 0; i < N; ++i) cin >> arr[i];

    int ans = 0;
    for(int i = 0, j; i < N; ) {
        bool flag = false;
        for(j = i + 1; j < N; j += 2) {
            int l = i, r = j;
            bool is_palindrome = true;
            while(is_palindrome && l < r) {
                if(arr[l] != arr[r]) is_palindrome = false;
                ++ l; -- r;
            }
            if(is_palindrome) {
                ++ans;
                flag = true;
                break;
            }
        }
        if(flag == false) {
            ans = -1;
            break;
        }
        i = j + 1;
    }
    cout << ans;

    return 0;
}
