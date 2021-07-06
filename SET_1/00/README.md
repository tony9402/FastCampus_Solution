# 문제 1 : 전구

| 언어 | 소스 코드 |
| :--: | :-------: |
| Python | [바로가기](./main.py) |
| C++    | [바로가기](./main.cpp) |
| Java | [바로가기](./Main.java) |

## 풀이

 - 구현, 배열, 반복문, 비트 연산
 - 시간복잡도 : <img src="https://render.githubusercontent.com/render/math?math=O(NM)">

명령어가 들어오는 순서대로 반복문을 이용해서 전구의 상태를 변경하면 됩니다.  

전구의 상태를 변경할 때 여러가지 방법이 있습니다.  
여러가지 방법 중 편한 걸로 사용하시면 됩니다.

 - 전구를 킬 때
```cpp
blub[index] |= 1 // 비트 연산 이용
blub[index] = 1  // 단순 대입
```

 - 전구를 끌 때
```cpp
blub[index] &= 0 // 비트 연산 이용
blub[index] = 0  // 단순 대입
```

 - 켜진 전구를 끄거나 꺼진 전구를 키거나

```cpp
blub[index] ^= 1                  // 비트 연산 이용
blub[index] = 1 - blub[index]     // 수식 이용
blub[index] = blub[index] ? 0 : 1 // 조건문 이용
```
