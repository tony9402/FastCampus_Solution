#include <stdio.h>
char str[101][101];
int main(void)
{
    int r,c,r1,c1,r2,c2,gh = 0, pa = 0;
    scanf("%d%d\n",&r,&c);
    scanf("%d%d%d%d\n",&r1,&c1,&r2,&c2);
    for(int i=0;i<r;i++)
    {
        scanf("%s\n",str[i]);
        for(int j=0;j<c;j++)
        {
            gh = gh + (str[i][j] == 'G');
            pa = pa + (str[i][j] == 'P');
        }
    }
    printf("%d\n",pa != (r2*c2));
    return 0;
}
