# 문제 2 : 소수 최소 공배수

| 언어 | 소스 코드 |
| :--: | :-------: |
| Python | [바로가기](./main.py) |
| C++    | [바로가기](./main.cpp) |
| Java | [바로가기](./Main.java) |

## 풀이

 - 소수 판별, GCD, LCM, 유클리드 호제, 반복문
 - 시간복잡도 : <img src="https://render.githubusercontent.com/render/math?math=O(N\sqrt{X})"> 또는 <img src="https://render.githubusercontent.com/render/math?math=O(X\log\log X)">

주어진 수열 중에 소수를 찾고 찾은 소수들의 최소 공배수를 구해주면 됩니다.  

소수를 찾는 방법은 크게 두 가지가 있습니다. 

 1. <img src="https://render.githubusercontent.com/render/math?math=O(\sqrt{N})"> 소수 판별 이용
 2. <img src="https://render.githubusercontent.com/render/math?math=O(N\log\log N)"> 에라토스테네스의 체 사용

### 소수 판별 이용

수 <img src="https://render.githubusercontent.com/render/math?math=X">가 소수인지 확인하기 위해 소수 판별을 이용한다면 시간복잡도는 <img src="https://render.githubusercontent.com/render/math?math=O(\sqrt{X})">가 됩니다.  
아래 소스코드는 C++로 소수 판별을 구현한 코드입니다.  

**:bulb: 주의사항 :bulb:**
1. <img src="https://render.githubusercontent.com/render/math?math=X">가 `int`를 벗어나는지 확인해야합니다.
2. `i * i`가 `int`를 벗어나는지 확인해야합니다.

```cpp
bool isPrime(int X) {
    for(int i = 2; i * i <= X; i++) {
        if(X % i == 0) return false;
    }
    return true;
}
```

수열 N개에 대하여 소수 판별을 하기 때문에 수열 중에서 소수를 확인하는 시간복잡도는 <img src="https://render.githubusercontent.com/render/math?math=O(N\sqrt{X})">가 됩니다.

### 에러토스테네스의 체 이용

에라토스테네스의 체를 이용하여 총 <img src="https://render.githubusercontent.com/render/math?math=X">까지의 수가 소수인지 아닌지 체크를 합니다.  
이때, 문제에서 <img src="https://render.githubusercontent.com/render/math?math=X">의 범위는 `1,000,000`입니다.

그리고 배열에 체크한 것을 보고 각 수가 소수인지 확인하면 됩니다.

**:bulb: 주의사항 :bulb:**
1. `i * i`가 `int`를 벗어나는지 확인해야합니다. &#8594; `i * i <= X` 부분을 `i <= X`로 바꿔도 상관없습니다.

```cpp
// 에라토스테네스의 체
bool isNotPrime[1000001];

void eratosthenes_sieve() {
    long long X = 1000000;
    isNotPrime[0] = isNotPrime[1] = true;
    for(long long i = 2; i * i <= X; i++) {
        if(isNotPrime[i]) continue;
        for(long long j = i + i; j <= X; j += i) {
            isPrime[j] = true;
        }
    }
}

// 소수 판별
bool isPrime(int X) {
    return !isNotPrime[X];
}
```

에라토스테네스의 체 시간복잡도는 <img src="https://render.githubusercontent.com/render/math?math=O(X\log\log X)">가 되고, 각 수열에서 소수를 확인하는 과정은 각 수마다 <img src="https://render.githubusercontent.com/render/math?math=O(1)">가 걸리게 됩니다.

따라서, <img src="https://render.githubusercontent.com/render/math?math=O(X\log\logX%2BN)">  
<img src="https://render.githubusercontent.com/render/math?math=N">보다 <img src="https://render.githubusercontent.com/render/math?math=X">가 크기 때문에 <img src="https://render.githubusercontent.com/render/math?math=O(X\log\logX)">로 표현합니다.

### 최소공배수 구하기

수열에서 소수를 다 찾았다면 이를 최소공배수를 이용하여 계산을 해야합니다.

최소공배수(LCM)를 구할 때 유클리드 호제법으로 많이 사용됩니다.  
a, b의 최소공배수를 구하는 식은 `a * b / GCD(a, b)`입니다. 이때, GCD(a, b)는 a, b의 최대공약수입니다.

이를 구하는 소스코드는 아래와 같습니다.

```cpp
int GCD(int a, int b) {
    if(b == 0)return a;
    return GCD(b, a % b);
}

int LCM(int a, int b) {
    return a / gcd(a, b) * b;
}
```

두 수 a, b의 최소공배수를 구하는 시간복잡도는 <img src="https://render.githubusercontent.com/render/math?math=O(log(max(a, b)))"> 입니다.

 
### 시간복잡도

유클리드 호제법을 이용하여 최소공배수를 구하는 것보다 소수 판별을 하는 시간 복잡도가 큽니다.  
따라서 전체시간복잡도는 소수 판별을 어떤 것으로 이용한지에 크게 결정됩니다.

1. 소수 판별 + 유클리드 호제법 : <img src="https://render.githubusercontent.com/render/math?math=O(N\sqrt{X})">
2. 에라토스테네스의 체 + 유클리드 호제법 : <img src="https://render.githubusercontent.com/render/math?math=O(X\log\log X)">
