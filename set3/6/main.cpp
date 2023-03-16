#include<iostream>
#include<vector>
#include<unordered_map>
#include<algorithm>

using namespace std;

unordered_map<string, int> folder_id;
unordered_map<string, int> file_id;
vector<pair<int, int>> ans;
vector<vector<int>> tree, di_tree, files;
int moved[1005];
int fo_id, fi_id;

int getId(string a, bool isFolder = true) {
    if(isFolder) {
        if(folder_id.count(a)) return folder_id[a];
        folder_id[a] = ++fo_id;
        return fo_id;
    }
    if(file_id.count(a)) return file_id[a];
    file_id[a] = ++fi_id;
    return fi_id;
}

string parsing(const string &s) {
    int cur = (int)s.size() - 1;
    string ret = "";
    while(cur >= 0 && s[cur] != '/') cur--;
    for(int i = cur + 1; i < (int)s.size(); i++) ret += s[i];
    return ret;
}

void dfs(int cur = 1, int prev = 0) {
    for(int nxt : tree[cur]) {
        if(nxt == prev) continue;
        di_tree[cur].push_back(nxt);
        dfs(nxt, cur);
    }
}

void dfs2(int cur = 1){ 
    ans[cur].second = (int)files[cur].size();
    for(int nxt : di_tree[cur]) {
        if(moved[nxt]) continue;
        dfs2(nxt);
        ans[cur].second += (int)ans[nxt].second;
        for(int file : files[nxt]) files[cur].push_back(file);
    }
    sort(files[cur].begin(), files[cur].end());
    files[cur].erase(unique(files[cur].begin(), files[cur].end()), files[cur].end());
    ans[cur].first = (int)files[cur].size();
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M; cin >> N >> M;
    getId("main");
    tree.resize(N + 2);
    di_tree.resize(N + 2);
    files.resize(N + 2);
    ans.resize(N + 2);
    for(int i = 0; i < N + M; i++) {
        string a, b;
        int isFolder;
        cin >> a >> b >> isFolder;
        int idA = getId(a, true);
        int idB = getId(b, isFolder);
        if(isFolder) {
            tree[idA].push_back(idB);
            tree[idB].push_back(idA);
        }
        else files[idA].push_back(idB);
    }
    dfs();

    int K; cin >> K;
    for(int i = 0; i < K; i++) {
        string a, b; cin >> a >> b;
        a = parsing(a); b = parsing(b);
        int idA = getId(a);
        int idB = getId(b);
        for(int nxt : di_tree[idA]) if(!moved[nxt]) di_tree[idB].push_back(nxt);
        for(int file : files[idA]) files[idB].push_back(file);
        moved[idA] = 1;
        files[idA].clear();
        di_tree[idA].clear();
    }

    for(int i = 1; i <= fo_id; i++) {
        if(moved[i]) continue;
        sort(files[i].begin(), files[i].end());
        files[i].erase(unique(files[i].begin(), files[i].end()), files[i].end());
    }
    dfs2();
    int Q; cin >> Q;
    while(Q--) {
        string path; cin >> path;
        path = parsing(path);
        int x = getId(path);
        cout << ans[x].first << " " << ans[x].second << '\n';
    }

    return 0;
}