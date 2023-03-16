import sys

def input():
    return sys.stdin.readline().rstrip()

def erase_simple_tag(data: str) -> str:
    ret = ""
    space = True
    bucket_open = False
    for ch in data:
        if ch == '>':
            bucket_open = False
            continue
        if ch == '<':
            bucket_open = True
            continue

        if bucket_open:
            continue

        if ch == ' ':
            if space: continue
            space = True
            ret = f"{ret}{ch}"
        else:
            ret = f"{ret}{ch}"
            space = False
    return ret.rstrip()

html = input()
L, N = 0, len(html)
while L < N:
    idx = html.find("title=\"", L)
    if idx == -1:
        break
    end_title = html.find("\">", idx)
    end_tag   = html.find("</div>", idx)
    title     = html[idx + 7:end_title]
    print(f"title : {title}")

    cursor = L
    while True:
        start_p_tag = html.find("<p>", cursor)
        if start_p_tag == -1 or start_p_tag > end_tag: break
        end_p_tag = html.find("</p>", start_p_tag)
        paragraph = html[start_p_tag + 3:end_p_tag]
        print(erase_simple_tag(paragraph))
        cursor = end_p_tag
    L = end_tag