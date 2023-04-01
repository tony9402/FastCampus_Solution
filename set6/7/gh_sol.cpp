#include <stdio.h>
#include <queue>
#include <vector>
#include <algorithm>
#include <memory.h>
using namespace std;
struct moo
{
    int x, y;
};
typedef struct moo moo;
struct Box
{
    int x1, x2, y1, y2;
};
typedef struct Box Box;
char str[1000][1001];
vector <moo> Jang;
moo Home;
moo Top;
Box bb; //bounding box
int R, C;
void init()
{
    moo I;
    bb.x1 = bb.y1 = 2002; bb.x2 = bb.y2 = -1;
    for(int i=R-1;i>=0;i--){
        for(int j=C-1;j>=0;j--){
            if(str[i][j] == 'T'){
                Top.x = i; Top.y = j;
                bb.x1 = min(bb.x1, i);
                bb.y1 = min(bb.y1, j);
                bb.x2 = max(bb.x2, i);
                bb.y2 = max(bb.y2, j);
            }
            else if(str[i][j] == '#'){
                I.x = i; I.y = j; Jang.push_back(I);
            }
            else if(str[i][j] == 'H'){
                Home.x = i; Home.y = j;
            }
        }
    }
}
//물리학 상대속도와 절대속도 참고.
int chk(moo &tar, int x, int y, moo &gijun)
{
    //거북이가 통째로 dx, dy만큼 이동했다면.
    //거북이가 가만히 있고, 타겟은 -dx, -dy만큼 이동한 것과 같다.
    
    //타겟 좌표 = tar.x, tar.y
    int dx = x - gijun.x;
    int dy = y - gijun.y;
    
    //dx, dy만큼이 변화량.
    //그러면 타겟은 -dx, -dy만큼 변해야 함. 그런가?
    int tar_x = tar.x - dx;
    int tar_y = tar.y - dy;
    
    //이제 [tar_x][tar_y]가 거북이와 만나는지 확인하자.
    if(tar_x < 0 || tar_y < 0 || tar_x >= R || tar_x >= C)
        return 0;
    
    return (str[tar_x][tar_y] == 'T');
}
char str2[1001][1001];
moo wif[1001][1001];
char visit[1001][1001];
queue <moo> Q;
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};
int main(void)
{
    scanf("%d%d\n",&R,&C);
    for(int i=0;i<R;i++)
        scanf("%s\n",str[i]);
    
    //(1) 맨 위, 좌측에 있는 'T'를 기준으로 돌리자.
    init(); //이 때 top_of_T는 거북이의 기준점이 됨.
    
    //'H' location
    for(int i=0;i<R;i++){
        for(int j=0;j<C;j++){
            if(chk(Home, i, j, Top))
                str2[i][j] = 'H';
            else
                str2[i][j] = '.';
        }
    }
    
    //'#' location
    for(int i=0;i<R;i++)
    {
        for(int j=0;j<C;j++)
        {
            for(int jj=0;jj<(int)Jang.size();jj++)
            {
                if(chk(Jang[jj], i, j, Top))
                    str2[i][j] = '#';
            }
        }
    }
    
    //거북이 Bounding Box 처리
    //bb -> x1 ~ x2, y1 ~ y2
    //bb.x1 ~ bb.x2까지? 현재 내 기준 좌표는 Top.x, Top.y임.
    //Top.x - bb.x1 이상 (R-1-bb.x2) + Top.x 이하여야 함.
    int a = Top.x - bb.x1; int c = (R-1-bb.x2) + Top.x;
    //Top.y - bb.y1이상 (C-1-bb.y2) + Top.y 이하여야 함.
    int b = Top.y - bb.y1; int d = (C-1-bb.y2) + Top.y;
    for(int i=0;i<R;i++)
        for(int j=0;j<C;j++)
            if( i<a || i>c || j<b || j>d )
                str2[i][j] = '#';
    //장애물 전처리까지 끝났으므로 BFS를 돌린다.
    //Top이 기준점이 됨.
    memset(wif, -1, sizeof(moo)*1001*1001);
    visit[Top.x][Top.y] = 1; moo I; I.x = Top.x; I.y = Top.y; Q.push(I);
    while(!Q.empty())
    {
        moo Ci = Q.front(); Q.pop();
        vector <moo> vv;
        if(str2[Ci.x][Ci.y] == 'H')
        {
            moo item; 
            int cx = Ci.x; int cy = Ci.y; vv.push_back(Ci);
            while(cx != Top.x || cy != Top.y)
            {
                //conversion..
                int tx = wif[cx][cy].x;
                int ty = wif[cx][cy].y;
                
                cx = tx; cy = ty;
                Ci.x = cx; Ci.y = cy; vv.push_back(Ci);
            }
            for(int i=(int)vv.size()-2; i>=0; i--)
            {
                int dx = vv[i].x - vv[i+1].x;
                int dy = vv[i].y - vv[i+1].y;
                if(dx == -1 && dy == 0)
                    printf("U");
                else if(dx == 0 && dy == -1)
                    printf("L");
                else if(dx == 0 && dy == 1)
                    printf("R");
                else
                    printf("D");
            }
            return 0;
        }
        for(int i=0;i<4;i++)
        {
            int tx = Ci.x + dx[i];
            int ty = Ci.y + dy[i];
            if(tx < 0 || ty < 0 || tx >= R || ty >= C || visit[tx][ty])
                continue;
            if(str2[tx][ty] == '#')
                continue;
            I.x = tx; I.y = ty; Q.push(I); visit[I.x][I.y] = 1;
            
            wif[I.x][I.y].x = Ci.x;
            wif[I.x][I.y].y = Ci.y;
        }
    }
    
    printf("-1\n");
    return 0;
}
/*
10 10
..........
...TTT....
.....T....
....TT....
...HT.....
..........
..........
..........
..........
..........
: L

10 10
..........
...TTT....
.....T....
....TT....
..H#T.....
..........
..........
..........
..........
..........
: ULLD

10 10
..........
...TTT....
.....T....
...#TT....
..H#T.....
..........
..........
..........
..........
..........
DRDDDLLLLU

10 10
..........
...TTTT...
.....T....
...#TT....
..H#T.....
..........
..........
..........
..........
..........
-1
*/