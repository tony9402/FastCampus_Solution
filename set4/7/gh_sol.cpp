#include <stdio.h>
#include <memory.h>
#include <algorithm>
using namespace std;
int arr[100001];
int ps[1000001];
int pe[1000001];
int calc(int lo)
{
    int prev = arr[lo];
    for(int i=lo+1;;i++)
    {
        if(prev >= arr[i])
            return i;
        prev = arr[i];
    }
}
int main(void)
{
    int T, ti = 1; scanf("%d",&T);
    memset(ps, 0x3f, sizeof(int)*1000001);
    for(int i=0;i<T;i++)
        scanf("%d",&(arr[i]));
    arr[T] = -1; //dummy
    for(int i=0;i<T;)
    {
        int en = calc(i);
        for(int j=i; j<en; j++)
        {
            ps[arr[j]] = min(ps[arr[j]], ti);
            pe[arr[j]] = max(ps[arr[j]], ti);
        }
        i = en; ti++;
    }
    int pn = 0;
    for(int i=1; i<=1000000; i++)
    {
        if(pe[i] == 0)
            continue;
        pn++;
        //pid, time, process
        
    }
    printf("%d\n",pn);
    for(int i=1; i<=1000000; i++){
        if(pe[i] == 0)
            continue;
        printf("%d %d %d\n",i, pe[i] - ps[i] + 1, 1000000 - ps[i]); 
    }
    /*
    pid's execution range >= 2, impossible
    for example, 1 1 2 2 2 2 2 1
    [1] [1 2] [2] [2] [2] [2] [1]
    1's gugan is 1~2, 7~7. more than 1.     
    */
}