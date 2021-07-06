from urllib import request
import ssl
import sys
import json

class Table:
    HEADER = [ "번호", "문제 이름", "출제자 난이도", "솔브드 난이도", "솔루션" ]
    def __init__(self, path = ""):
        self.ssl_context = ssl._create_unverified_context()
        self.config_path = f"{path}/config.json"
        self.readme_path = f"{path}/README.md"
        self.problems    = None
        self.__load_config()
        self.data = ""
        self.latest_data = dict()

    def __make_level_str(self, level):
        a = [ 'B', 'S', 'G', 'P', 'D', 'R' ]
        l = level[0].upper()
        r = int(level[1])
        ret = a.index(l) * 5 + 6 - r
        return self.__make_level_int(ret)
    
    def __make_level_to_str(self, level):
        a = [ 'B', 'S', 'G', 'P', 'D', 'R' ]
        level -= 1
        return f"{a[level // 5]}{5 - level % 5}"
    
    def __make_title(self):
        self.data += f"{self.problems['title']}\n\n"

    def __make_level_int(self, level):
        link = f"https://static.solved.ac/tier_small/{level}.svg"
        img_url = f"<img height=\"25px\" width=\"25px\" src=\"{link}\"/>"
        return img_url

    def __make_header(self) -> str:
        self.data += f"| {' | '.join(self.HEADER)} |\n"
        tmp  = "|"
        for i in range(len(self.HEADER)):
            tmp += " :--: |"
        tmp += "\n"
        self.data = f"{self.data}{tmp}"

    def __load_config(self):
        with open(self.config_path, 'r') as f:
            self.problems = json.load(f)
            f.close()

    def __make_problem(self):
        problems = self.problems['problemIds']
        for i in range(len(problems)):
            problemId   = problems[i]
            problemInfo = self.problems[problemId]
            creator     = problemInfo['creator']
            official    = problemInfo['official']
            problemName = problemInfo['name']
            link        = f"[{problemName}](https://www.acmicpc.net/problem/{problemId})"
            tmp = f"| {i:02d} | {link} | {self.__make_level_str(creator)} | {self.__make_level_str(official)} | [바로 가기](./{i:02d}) |\n"
            self.data += tmp

    def __get_official_level(self):
        problemIds = list(map(str, self.problems['problemIds']))
        problems = ','.join(problemIds)
        URL      = f"https://solved.ac/api/v3/problem/lookup?problemIds={problems}"
        req      = request.Request(URL, headers = {'User-Agent': 'Mozilla/5.0'})
        response = request.urlopen(req, context=self.ssl_context)
        try:
            JSON = json.loads(response.read().decode('UTF-8'))
        except:
            assert False, "[*** API Error] Failed"

        for info in JSON:
            problemId    = str(info['problemId'])
            problemLevel = self.__make_level_to_str(info['level'])
            problemName  = info['titleKo']
            self.latest_data[problemId] = {
                'id': problemId,
                'level': problemLevel,
                'name': problemName
            }

    def __update(self):
        self.__get_official_level()
        for key, value in self.latest_data.items():
            self.problems[key]['official'] = value['level']
            self.problems[key]['name']     = value['name']

        with open(self.config_path, 'w') as f:
            f.write(json.dumps(self.problems, ensure_ascii=False, indent=4))
            f.close()

    def __save_readme(self):
        with open(self.readme_path, 'w') as f:
            f.write(self.data)
            f.close()

    def run(self):
        self.__update()
        self.__make_title()
        self.__make_header()
        self.__make_problem()

        self.__save_readme()

if __name__=="__main__":
    path = sys.argv[1]
    _ = Table(path)
    _.run()
