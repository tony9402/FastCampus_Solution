#include<iostream>
#include<string>
#include<map>
#include<unordered_map>
#include<algorithm>

using namespace std;

const int days[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};

unordered_map<string, unordered_map<string, long long>> mp;
map<string, long long> fine;

long long getDay(string date) {
    long long month  = stoi(date.substr(5, 2));
    long long day    = stoi(date.substr(8, 2));

    long long cur = day - 1;
    for (int i = 0; i < month; i++) cur += days[i];
    return cur;
}

long long getTime(string date, string time) {
    long long hour   = stoi(time.substr(0, 2));
    long long minute = stoi(time.substr(3, 2));
    hour += getDay(date) * 24;
    return hour * 60 + minute;
}

long long getTime(string time) {
    long long hour   = stoi(time.substr(0, 2));
    long long minute = stoi(time.substr(3, 2));
    return hour * 60 + minute;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, money;
    string info;
    cin >> N >> info >> money;

    long long limit = stoi(info.substr(0, 3)) * 1440 + getTime(info.substr(4));

    for (int i = 0; i < N; ++i) {
        string date, time, part, name;
        cin >> date >> time >> part >> name;
        long long curtime = getTime(date, time);

        if(mp.count(name) == 0) mp[name] = unordered_map<string, long long>();
        if(mp[name].count(part)) {
            long long prev = mp[name][part];
            long long dist = max(curtime - prev - limit, 0LL);
            fine[name] += dist;
            mp[name].erase(part);
        }
        else {
            mp[name][part] = curtime;
        }
    }
    bool ans = false;
    for(auto &[a, b]: fine) {
        if(b == 0) continue;
        ans = true;
        cout << a << ' ' << b * money << '\n';
    }
    if(!ans) cout << -1;

    return 0;
}