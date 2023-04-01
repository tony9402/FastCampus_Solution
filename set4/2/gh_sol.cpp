#include <stdio.h>
#include <memory.h>
#include <set>
#include <algorithm>
using namespace std;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};
int temp[25];
int na[25];
int M = 202;
char str[105][105];
set <int> visit;
int mmx = 0;
void gogo(int sx, int sy, int T)
{
    int cx = sx;
    int cy = sy;
    int ans = 0;
    visit.clear(); visit.insert(cx * M + cy);    
    for(int i=0;i<T;i++)
    {
        cx = cx + dx[na[i]]; cy = cy + dy[na[i]];
        if(str[cx][cy] == '#')
            return;
        visit.insert(cx * M + cy);
    }
    for(set<int>::iterator iter = visit.begin(); iter != visit.end(); iter++)
    {
        int U = (*iter);
        int tx = U/M; int ty = U%M;
        ans = ans + (str[tx][ty] == 'S');
    }
    mmx = max(ans, mmx);
}
int main(void)
{
    int R, C, T; scanf("%d %d %d",&R, &C, &T);
    for(int i=0; i<=R+1; i++)
        memset(str[i], '#', sizeof(char)*(C+2));
    for(int i=1; i<=R; i++){
        scanf("\n%s",&(str[i][1]));
        str[i][C+1] = '#';
    }
    int sx = -1, sy = -1;
    for(int i=1; i<=R; i++)
    {
        for(int j=1; j<=C; j++)
        {
            if(str[i][j] == 'G')
            {
                sx = i; sy = j;
            }
        }
    }
    
    for(int i=0; i<(1<<(2*T)); i++)
    {
        for(int j=0; j<2*T; j++)
        {
            if(i&(1<<j))
                temp[j] = 1;
            else
                temp[j] = 0;
        }
        
        for(int j=0;j<T;j++)
            na[j] = 2*temp[2*j] + temp[2*j+1];
        //set..
        gogo(sx, sy, T);
    }
    printf("%d\n",mmx);
}