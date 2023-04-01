#include <stdio.h>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;
struct OPER{
    /*
    op == 1 => ADD
    op == 2 => DEL
    */
    int op;
    int idx;
    char ch;
};
typedef struct OPER OPER;
vector <int> v;
vector <OPER> card[10];
vector <string> ans;
char str[5000];
//if order[a][b] == 1, must a->b
int order[10][10];
void pre(string &F, string &S, int card_num)
{
    OPER I;
    if(F.compare("DEL") == 0)
    {
        I.op = 2; I.idx = stoi(S);
    }
    else
    {
        I.op = 1; I.ch = S[0];
    }
    card[card_num].push_back(I);
}
int LO[10];
int order_prev[10];
int chk()
{
    LO[0] = -1;
    for(int i=0;i<(int)v.size();i++)
        LO[v[i]] = i;
    
    for(int i=0;i<(int)v.size();i++)
    {
        int cur = v[i];
        int prev = order_prev[cur];
        if(LO[prev] > LO[cur])
            return -1;
    }
    return 0;
}
int simulate(string &tar, int cn)
{
    for(int i=0;i<(int)card[cn].size();i++)
    {
        int op = card[cn][i].op;
        if(op == 1)
        {
            char ch = card[cn][i].ch;
            tar += ch;
        }
        else
        {
            int idx = card[cn][i].idx;
            if(idx >= tar.length())
            {
                tar = string("ERROR");
                return -1;
            }
            tar.erase(tar.begin() + idx);
        }
    }
    return 0;
}
int main(void)
{
    int n,c; scanf("%d %d",&n,&c);
    for(int i=0;i<n;i++)
    {
        int cn,r; scanf("\n%d ",&cn);
        int prev = 0;
        for(int j=0;j<cn;j++)
        {
            scanf("%d",&r); order_prev[r] = prev;
            prev = r;
        }
    }
    for(int i=1;i<=c;i++)
    {
        scanf("\n%[^\n]",str);
        string str_t = string(str);
        string token;
        
        while(str_t.find(",") != -1)
        {
            token = str_t.substr(0, str_t.find(","));
            string first = token.substr(0, token.find(" "));
            string second = token.substr(token.find(" ")+1);
            pre(first, second, i); 
            str_t = str_t.substr(str_t.find(",")+1);
        }
        token = str_t;
        string first = token.substr(0, token.find(" "));
        string second = token.substr(token.find(" ")+1);
        pre(first, second, i);
    }
    
    //pre processing complete.
    //so next_permutation!
    for(int i=1; i<=c; i++)
        v.push_back(i);
    
    do{
        if(chk() == -1)
            continue;
        string target = string("");
        
        //simulate
        for(int i=0;i<(int)v.size();i++)
        {
            int ret = simulate(target, v[i]);
            if(ret == -1)
                break;
        }
        if(target.compare("") == 0)
            target = string("EMPTY");
        ans.push_back(target);
    }while(next_permutation(v.begin(),v.end()));
    sort(ans.begin(), ans.end());
    ans.erase(unique(ans.begin(), ans.end()), ans.end());
    for(int i=0;i<(int)ans.size(); i++)
        printf("%s\n",ans[i].c_str());
}