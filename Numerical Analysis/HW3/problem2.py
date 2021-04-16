import numpy as np
import timeit
import matplotlib.pyplot as plt

start = timeit.default_timer()

d = [0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.5, 2, 3]
p1 = []
p2 = []
p3 = []
w = 1
n = 1444444
for i in range(0, len(d)):
    cross1 = 0
    cross2 = 0
    cross3 = 0
    for j in range(0, n):
        point = np.random.uniform(0, 1)
        top = point + (d[i] / 2)
        bot = point - (d[i] / 2)
        if (top >= 1) | (bot <= 0):
            cross1 += 1
        if (top >= 1) & (bot <= 0):
            cross2 += 1
        if ((top >= 1) & (bot <= 0)) & ((top >= 2) | (bot <= -1)):
            cross3 += 1
    p1.append(cross1 / n)
    p2.append(cross2 / n)
    p3.append(cross3 / n)
print(d)
print(p1)
print(p2)
plt.scatter(d, p1, marker=",", s=60, label="P(1 line)")
plt.scatter(d, p2, marker=">", label="P(2 line)")
plt.scatter(d, p3, marker="<", label="P(3 line)")
plt.legend()
plt.grid()
plt.show()

stop = timeit.default_timer()

print('Time: ', stop - start)
