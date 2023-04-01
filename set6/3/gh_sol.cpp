#include <stdio.h>
#include <memory.h>
#include <deque>
using namespace std;
struct moo
{
    int id;
    int need_time;
    int worked_time;
};
typedef struct moo moo;
deque <moo> wait_q;
moo event[200001];
int main(void)
{
    int n,t,m,p; moo I, C; scanf("%d%d%d",&n,&t,&p);
    memset(event, -1, sizeof(moo)*200001);
    for(int i=0;i<n;i++)
    {
        scanf("%d%d",&(I.id), &(I.need_time)); I.worked_time = 0;
        wait_q.push_back(I);
    }
    scanf("%d",&m);
    for(int i=0;i<m;i++)
    {
        int ev_time; scanf("%d%d%d",&(I.id), &(I.need_time), &ev_time);
        if(ev_time <= 200000)
            event[ev_time] = I;
    }
    //printf("p = %d\n",p);
    for(int i=0; i<p; i++)
    {
        //printf("ti = %d\n",wait_q.front().worked_time);
        //만약에 해당 시간에 유저가 들어 온다면 wait_q의 맨 뒤에 추가한다.
        if(event[i].id != -1)
            wait_q.push_back(event[i]);
        
        //만약에 맨 앞에 있었던 사람의 일 처리가 모두 끝났다면, 과감하게 wait_q에서 뺀다.
        if(wait_q.front().need_time == 0)
            wait_q.pop_front();
        
        //일 처리가 끝나지 않았는데, worked_time이 t인 경우에는
        else if(wait_q.front().worked_time == t)
        {
            //맨 앞에 있었던 고객은 대기 큐의 맨 뒤로 간다.
            moo I = wait_q.front(); wait_q.pop_front(); I.worked_time = 0; wait_q.push_back(I);
        }
        printf("%d\n",wait_q.front().id);
        
        //업데이트
        moo C = wait_q.front(); wait_q.pop_front();
        C.need_time -= 1;
        C.worked_time += 1;
        wait_q.push_front(C);
    }
}