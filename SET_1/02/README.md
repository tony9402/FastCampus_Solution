# 문제 02 : 서로소 평균

| 언어 | 소스 코드 |
| :--: | :-------: |
| Python | [바로가기](./main.py) |
| C++    | [바로가기](./main.cpp) |
| Java | [바로가기](./Main.java) |

## 풀이

 - GCD, 유클리드 호제법
 - 시간복잡도 : <img src="https://render.githubusercontent.com/render/math?math=O(NlogX)">

수열에 있는 원소와 X의 GCD 값을 구한 후 1인 것들을 뽑아 평균을 구하면 됩니다.
GCD 코드는 [이 링크](./../01#최소공배수-구하기)를 확인하시면 됩니다.

### 시간복잡도

총 시간 복잡도는 <img src="https://render.githubusercontent.com/render/math?math=O(NlogX)">가 됩니다.
