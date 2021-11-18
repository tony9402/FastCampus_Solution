# 문제 01 : 영상처리

| 언어 | 소스 코드 |
| :--: | :-------: |
| Python | 준비중 |
| C++    | [바로가기](./main.cpp) |
| Java | [바로가기](./Main.java) |

## 풀이

 - 자료구조
 - 시간복잡도 : <img src="https://render.githubusercontent.com/render/math?math=O((N %2B M) log N)">

해당 문제는 자료구조를 활용하면 매우 쉽게 풀 수 있습니다. (단, 파이썬에는 제가 생각했던 자료구조가 지원하지 않아서 없는거 같습니다... 그래도 다른 풀이로도 풀 수 있습니다.)

사용해야하는 언어별 자료구조 
- C++ : (`std::set` or `std::map`) 또는 `std::priority_queue`
- Java : (`TreeSet` or `TreeMap`) 또는 `PriorityQueue`
- Python : `heapq`

### `std::set`, `TreeSet` 등을 이용한 풀이

세 가지 쿼리가 존재합니다. 기본적으로 아래와 같이 정보를 관리합니다.  
`std::set` 또는 `TreeSet`에는 (난이도, 문제 번호) 쌍으로 넣어서 관리하고
`std::map` 또는 `TreeMap`에는 문제 번호에 대한 난이도 정보를 담아서 관리합니다.

1. recommand x  
`std::set`에서는 `begin()`, `rbegin()` method를 이용하면 최소,최대를 <img src="https://render.githubusercontent.com/render/math?math=O(1)">로 구할 수 있습니다.
`TreeSet`에서는 `first`, `last`을 이용하면 최소,최대를 <img src="https://render.githubusercontent.com/render/math?math=O(1)">만에 구할 수 있습니다.

2. add P L  
`std::set` 또는 `TreeSet`에 (난이도, 문제 번호) 순으로 넣습니다.  
`set::map` 또는 `TreeMap`에 문제 번호와 해당 난이도를 저장해놓습니다.  
이 쿼리를 수행하는데 시간복잡도 <img src="https://render.githubusercontent.com/render/math?math=O(logN)">으로 할 수 있습니다.

3. solved P
`set::map` 또는 `TreeMap`에서 문제번호에 대한 난이도를 추출하고 `set::set` 또는 `TreeSet`에 (난이도, 문제번호) 정보를 삭제합니다.  
그리고 `set::map` 또는 `TreeMap`에서도 해당 문제 번호를 삭제합니다.
이 쿼리를 수행하는데 시간복잡도 <img src="https://render.githubusercontent.com/render/math?math=O(logN)">으로 할 수 있습니다.

### `std::priority_queue`, `PriorityQueue`를 이용한 풀이

우선순위 큐를 이용하면 최대 힙, 최소 힙 두 가지를 가지고 있어야 합니다.  
우선순위 큐에는 (난이도, 문제 번호)

1. recommand x  
가장 어려운 문제를 뽑아야 한다면 최대 힙에서, 가장 쉬운 문제를 뽑아야 한다면 최소 힙에서 뽑으면 됩니다.
이 쿼리를 수행하는데 시간복잡도 <img src="https://render.githubusercontent.com/render/math?math=O(1)">으로 할 수 있습니다.

2. add P L  
(난이도, 문제번호) 정보를 최대 힙, 최소 힙에 넣습니다.
이 쿼리를 수행하는데 시간복잡도 <img src="https://render.githubusercontent.com/render/math?math=O(logN)">으로 할 수 있습니다.

3. solved P
삭제할 정보를 다른 우선순위 큐에 담아놓습니다.  
다른 쿼리를 실행할 때마다 우선순위 큐의 top이 지워야하는 원소이면 계속 지워줍니다.
이 쿼리를 수행하는데 시간복잡도 <img src="https://render.githubusercontent.com/render/math?math=O(logN)">으로 할 수 있습니다.
