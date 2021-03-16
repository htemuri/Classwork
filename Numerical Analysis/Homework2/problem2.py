import numpy as np
import timeit

start = timeit.default_timer()

degree = np.arctan([np.sqrt(3), 1])
r = np.sin(2 * (degree[0]))

root1 = [r * np.cos(degree[0]), r * np.sin(degree[0])]
for i in range(len(root1)):
    root1[i] = np.format_float_positional(root1[i], precision=4, unique=False, fractional=False, trim='k')

root2 = [r * np.cos(degree[0] + np.pi), r * np.sin(degree[0] + np.pi)]
for i in range(len(root2)):
    root2[i] = np.format_float_positional(root2[i], precision=4, unique=False, fractional=False, trim='k')

print("Root 1 is {}".format(root1))
print("Root 2 is {}".format(root2))


stop = timeit.default_timer()

print('Time: ', stop - start)
