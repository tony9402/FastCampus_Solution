#include<bits/stdc++.h>

using namespace std;
vector<tuple<int, int, int>> circle;
vector<int> L, R;
bool visited[200005];

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N; cin >> N;
    for(int i = 0; i < N; i++) {
        int k, x, r; cin >> k >> x >> r;
        circle.emplace_back(r, x, k);
    }
    sort(circle.begin(), circle.end());
    circle.emplace_back(1000000000, 0, 0);
    int A, B; cin >> A >> B;
    int idxA = 0, idxB = 0;
    while(idxA <= N && get<2>(circle[idxA]) != A) idxA++;
    while(idxB <= N && get<2>(circle[idxB]) != B) idxB++;
    L.push_back(A);
    visited[A] = true;
    int ax = get<1>(circle[idxA]), ar = get<0>(circle[idxA]);
    int bx = get<1>(circle[idxB]), br = get<0>(circle[idxB]);
    for(int i = idxA + 1; i <= N; i++) {
        auto [cr, cx, ck] = circle[i];
        if(cr == ax) continue;
        if(abs(ax - cx) > abs(ar - cr)) continue;
        ax = cx; ar = cr;
        if(ck == B) break;
        L.push_back(ck);
        visited[ck] = true;
    }
    R.push_back(B);
    for(int i = idxB + 1; i <= N; i++) {
        auto [cr, cx, ck] = circle[i];
        if(cr == br) continue;
        if(abs(bx - cx) > abs(br - cr)) continue; 
        bx = cx; br = cr;
        if(visited[ck]) {
            while(L.back() != ck) L.pop_back();
            break;
        }
        R.push_back(ck);
    }
    cout << L.size() + R.size() << '\n';
    for(int i : L) cout << i << " ";
    reverse(R.begin(), R.end());
    for(int i : R) cout << i << " ";

    return 0;
}
