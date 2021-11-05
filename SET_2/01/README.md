# 문제 01 : 영상처리

| 언어 | 소스 코드 |
| :--: | :-------: |
| Python | 준비중 |
| C++    | 준비중 |
| Java | [바로가기](./Main.java) |

## 풀이

 - 그래프 탐색, DFS, BFS
 - 시간복잡도 : <img src="https://render.githubusercontent.com/render/math?math=O(NM)">

해당 문제는 RGB 3개의 채널로 구성되어 있는 image를 gray scale로 변경하여 이진화를 시키는 알고리즘입니다.  
gray scale로 변경하는 식은 간단하게 평균으로 구하도록 하였습니다.  

풀이 순서는 아래와 같습니다.

1. 각 픽셀에서 RGB 정보를 평균을 내어 gray 값으로 변경합니다.
2. T(Threshold) 값을 기준으로 T이상인 픽셀들은 1, T미만인 픽셀들은 0으로 값을 변경한다.
3. [단지번호붙이기](https://www.acmicpc.net/problem/2667)처럼 픽셀들이 상하좌우로 붙어 있는 경우(그룹)을 센다.

