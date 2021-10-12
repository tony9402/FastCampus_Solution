from glob import glob
import os

set_folders = glob('SET_*')
stat = list()

for ps in set_folders:
    problems = glob(os.path.join(ps, '0*'))
    for problem in problems:
        number = int(problem.split('/')[-1]) + 1
        solutions = glob(os.path.join(problem, '*'))
        for sol in solutions:
            for lang in ['cpp', 'java', 'py']:
                if lang in sol:
                    stat.append(f"{ps}-{number}-{lang}")

print('\n'.join(stat))
