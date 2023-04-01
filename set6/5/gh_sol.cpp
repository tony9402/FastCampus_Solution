#include <stdio.h>
#include <stdlib.h>
#define inf 0x3f3f3f3f
int special[280] = {0};
int nj[280];
struct moo
{
    int from, to, ti;
};
typedef struct moo moo;
moo arr[4000]; //(60*24*5)/5
void init()
{
    for(int i=0; i<280; i++)
        special[i] = 2;
    special[210] = 3;
    special[220] = special[221] = 4;
    special[222] = special[225] = special[238] = 3;
    special[245] = 4;
    special[247] = 5;
    special[249] = 4;
    special[250] = special[256] = special[266] = 3;
    for(int i=211; i<=272; i++){
        nj[i] = nj[i-1] + 60*special[i-1]+20;
    }
}
char str[20], st1[20], st2[20];
int main(void)
{
    int hh, mm, ss; scanf("%s",str); str[2] = str[5] = 0;
    hh = atoi(str); mm = atoi(str+3); ss = atoi(str+6);
    int cur_time = 60*60*hh + 60*mm + ss;
    init();
    int n; scanf("\n%d",&n);
    for(int i=0;i<n;i++)
    {
        int from, to, ti;
        scanf("\n%s %s %s",st1, st2, str);
        from = atoi(st1+1); to = atoi(st2+1);
        str[2] = 0;
        hh = atoi(str); mm = atoi(str+3);
        ti = 60*60*hh + 60*mm + 0;
        arr[i].from = from;
        arr[i].to = to;
        arr[i].ti = ti;
    }
    int start[5] = {225, 233, 246, 258, 272};
    
    //(K225 -> K233) : nj - 20sec
    
    //20sec
    //(K233 -> K246) : nj - 20sec
    
    //20sec
    //(K246 -> K258) : nj - 20sec
    
    //20sec
    //(K258 -> K272) : nj - 20sec
    for(int s=0; s<4; s++)
    {
        int mmn = inf;
        for(int i=0;i<n;i++)
        {
            //현재 내가 있는 위치는 start[s]임.
            //start[s] from ---> to 이거나
            //from --> to == start[s]인 경우 continue 처리.
            if(start[s] < arr[i].from || arr[i].to <= start[s])
                continue;
            int ff = arr[i].from; int tt = start[s];
            
            //arr[i].ti + nj[ff] - nj[tt] : 열차가 내가 있는 위치를 출발하는 시각.
            int leave_time = arr[i].ti + nj[tt] - nj[ff];
            if(leave_time <= cur_time)
                continue;
            
            if(leave_time < mmn)
                mmn = leave_time;
        }
        if(mmn == inf)
        {
            printf("-1\n");
            return 0;
        }
        //역 도착
        cur_time = mmn + nj[start[s+1]] - nj[start[s]] - 20;
    }
    if(cur_time >= 86400)
        printf("-1\n");
    else
        printf("%02d:%02d:%02d\n",cur_time/3600, (cur_time%3600)/60, (cur_time%3600)%60);
    return 0;
}