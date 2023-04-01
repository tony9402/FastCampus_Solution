#include <stdio.h>
#include <string>
#include <set>
#include <deque>
using namespace std;
struct moo
{
    int id;
    string op;
    string resource;
};
typedef struct moo moo;
set <string> ss;
deque <moo> dummy;
moo player[500002];
int turn[500002];
char str[20];
int main(void)
{
    int n, T, id; scanf("%d %d\n",&n, &T);
    for(int i=1;i<=T;i++)
        scanf("%lld",turn + i);
    for(int i=1;i<=n;i++)
        player[i].id = -1;
    for(int i=1;i<=T;i++)
    {
        moo I;
        scanf("\n%d %s",&id, str);
        string temp = string(str);
        I.id = id; I.op = temp;
        if(temp.compare("next") != 0)
        {
            scanf(" %s",str);
            I.resource = string(str);
        }
        dummy.push_back(I);
    }
    for(int i=1;i<=T;i++)
    {
        int pn = turn[i];
        
        if(player[pn].id == -1)
        {
            //deck에서 카드 하나 뽑는다.
            player[pn] = dummy.front(); dummy.pop_front();
        }
        printf("%d\n",player[pn].id);
        if(player[pn].op.compare("next") == 0)
        {
            player[pn].id = -1;
        }
        else if(player[pn].op.compare("acquire") == 0)
        {
            string rr = player[pn].resource;
            if(ss.find(rr) == ss.end())
            {
                ss.insert(rr);
                player[pn].id = -1;
            }
        }
        else
        {
            //resource 제거
            string rr = player[pn].resource;
            if(ss.find(rr) == ss.end()) while(1){}
            ss.erase(ss.find(rr));
            player[pn].id = -1;
        }    
    }
    return 0;
}