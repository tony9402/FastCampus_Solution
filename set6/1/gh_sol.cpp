#include <stdio.h>
#include <algorithm>
#include <vector>
#include <string>
#include <string.h>
using namespace std;
struct moo
{
    string name;
    string ext;
    int flag;
};
typedef struct moo moo;
moo arr[200000];
char str[30];
vector <string> ext;
bool cmp(moo a,moo b)
{
    if(a.name.compare(b.name) != 0)
        return a.name.compare(b.name) < 0;
    if(a.flag != b.flag)
        return a.flag > b.flag;
    return a.ext.compare(b.ext) < 0;
}
int main(void)
{
    int n,m; moo I; scanf("%d %d\n",&n,&m);
    for(int i=0;i<n;i++)
    {
        scanf("%s\n",str);
        int lo = strchr(str, '.') - str; str[lo] = 0;
        arr[i].name = string(str);
        arr[i].ext = string(str + lo + 1);
        //ext.push_back(arr[i].ext);
    }
    for(int i=0;i<m;i++)
    {
        scanf("%s\n",str);
        ext.push_back(string(str));
    }
    sort(ext.begin(), ext.end());
    ext.erase(unique(ext.begin(), ext.end()), ext.end());
    
    for(int i=0;i<n;i++)
    {
        int lo = lower_bound(ext.begin(), ext.end(), arr[i].ext) - ext.begin();
        if(lo == (int)ext.size())
            continue;
        if(arr[i].ext.compare(ext[lo]) != 0)
            continue;
        arr[i].flag = 1;
    }
    sort(arr, arr+n, cmp);
    for(int i=0; i<n; i++)
        printf("%s.%s\n",arr[i].name.c_str(), arr[i].ext.c_str());
    return 0;
}