# [FastCampus 모의고사](https://fastcampus.co.kr/dev_online_codingtest) 공식 솔루션

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Ftony9402%2FFastCampus_Solution&count_bg=%236BC923&title_bg=%2310214E&icon=github.svg&icon_color=%23E7E7E7&title=Hits&edge_flat=false)](https://hits.seeyoufarm.com)
[![Issue](https://img.shields.io/github/issues/tony9402/FastCampus_Solution/wrong%20or%20typo)](https://github.com/tony9402/FastCampus_Solution/issues?q=is%3Aopen+is%3Aissue+label%3A%22wrong+or+typo%22)
![C++](https://img.shields.io/badge/C++-010101?style=flat-square&logo=c%2B%2B&logoColor=white)
![Python](https://img.shields.io/badge/Python-010101?style=flat-square&logo=python&logoColor=white)
![Java](https://img.shields.io/badge/Java-010101?style=flat-square&logo=java&logoColor=white)

## 문제 풀이

| 세트  |   링크   | 출제자 |
| :---: | :------: | :----: |
| 1 세트| [바로 가기](./set1) | [tony9402](https://www.acmicpc.net/user/tony9402) |
| 2 세트| [바로 가기](./set2) | [tony9402](https://www.acmicpc.net/user/tony9402) |
| 3 세트| [바로 가기](./set3) | [tony9402](https://www.acmicpc.net/user/tony9402) |
| 4 세트| [바로 가기](https://github.com/cdog-gh/gh_coding_test/tree/main/1) | [chogahui05](https://www.acmicpc.net/user/chogahui05) |
| 5 세트| [바로 가기](./set5) | [tony9402](https://www.acmicpc.net/user/tony9402) |
| 6 세트| [바로 가기](https://github.com/cdog-gh/gh_coding_test/tree/main/2) | [chogahui05](https://www.acmicpc.net/user/chogahui05) |
| 7 세트| [바로 가기](./set7) | [tony9402](https://www.acmicpc.net/user/tony9402) |

## 입력 형식

[A+B](https://www.acmicpc.net/problem/1000) 예시

1. C++

<details>
    <summary>소스코드 보기</summary>

```cpp
#include<iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    
    int a, b; cin >> a >> b;
    cout << a + b;

    return 0;
}
```
</details>
<br>
  
2. Java

<details>
    <summary>소스코드 보기</summary>

```Java
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int a = rd.nextInt();
        int b = rd.nextInt();
        System.out.println(a + b);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
```
</details>
<br>
  

3. python

<details>
    <summary>소스코드 보기</summary>

```python
import sys

def input():
    return sys.stdin.readline().rstrip()

a, b = map(int, input().split())
print(a + b)
```
</details>

## 오타 및 오류 제보

[Issue](https://github.com/tony9402/FastCampus_Solution/issues)로 틀린 부분을 알려주시면 감사하겠습니다.
