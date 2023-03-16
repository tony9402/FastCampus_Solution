#include<iostream>
#include<string>

using namespace std;

string erase_simple_tag(string data) {
    string ret;
    bool space = true;
    bool bucket_open = false;
    for(int i = 0; i < (int)data.size(); i++) {
        if(data[i] == '>') {
            bucket_open = false;
            continue;
        }
        if(data[i] == '<') {
            bucket_open = true;
            continue;
        }
        if(bucket_open) continue;
        if(data[i] == ' ') {
            if(space) continue;
            space = true;
            ret += data[i];
        }
        else {
            ret += data[i];
            space = false;
        }
    }
    while(ret.back() == ' ') ret.pop_back();
    return ret;
}

int main(){
    ios::sync_with_stdio(false); cin.tie(0);

    string html; getline(cin, html);
    int L = 0, N = html.size();
    while(L < N) {
        auto idx = html.find("title=\"", L);
        if(idx == string::npos) break;
        auto end_title = html.find("\">", idx);
        auto end_tag = html.find("</div>", idx);
        string title  = html.substr(idx + 7, end_title - idx - 7);
        cout << "title : " << title << '\n';

        int cursor = L;
        while(true) {
            auto start_p_tag = html.find("<p>", cursor);
            if(start_p_tag == string::npos) break;
            if(start_p_tag  > end_tag) break;
            auto end_p_tag   = html.find("</p>", start_p_tag);
            string paragraph = html.substr(start_p_tag + 3, end_p_tag - start_p_tag - 3);
            cout << erase_simple_tag(paragraph) << '\n';
            cursor = end_p_tag;
        }
        L = end_tag;
    }
    return 0;
}