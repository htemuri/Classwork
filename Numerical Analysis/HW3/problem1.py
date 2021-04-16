import timeit
import matplotlib.pyplot as plt
import numpy as np

np.seterr(divide='ignore', invalid='ignore')

start = timeit.default_timer()

a = 100
w = 33
v0 = 99


def dydx(x, y):
    return (y / x) - (w / v0) * np.sqrt(1 + (y / x) ** 2)


def f(x):
    return x * np.sinh((1 / 3) * (np.log(100) - np.log(x)))


def rungeKutta(f, x0, y0, h, xi):
    n = abs(int((xi - x0) / h))
    x = [x0]
    y = [y0]

    pairs = [x, y]

    for i in range(0, n):
        k1 = f(x[i], y[i])
        if xi > x0:
            k2 = f(x[i] + h / 2, y[i] + (h * k1) / 2)
            k3 = f(x[i] + h / 2, y[i] + (h * k2) / 2)
            k4 = f(x[i] + h, y[i] + h * k3)

            x.append(x[i] + h)
            y.append(y[i] + h * ((k1 / 6) + (k2 / 3) + (k3 / 3) + (k4 / 6)))
        else:
            k2 = f(x[i] - h / 2, y[i] - (h * k1) / 2)
            k3 = f(x[i] - h / 2, y[i] - (h * k2) / 2)
            k4 = f(x[i] - h, y[i] - h * k3)

            x.append(x[i] - h)
            y.append(y[i] - h * ((k1 / 6) + (k2 / 3) + (k3 / 3) + (k4 / 6)))
    return pairs


sol = rungeKutta(f=dydx, x0=a, y0=0, h=0.01, xi=0.00000001)
# print(rungeKutta(f=dydx, x0=a, y0=0, h=0.01, xi=0.001)[0])

x = np.linspace(0, 100, 10000)
plt.figure()
plt.subplot(211)
plt.plot(x, f(x), label="Actual")
plt.legend(loc='best')
plt.grid()

plt.subplot(212)
plt.plot(sol[0], sol[1], 'r-', label="RK4")
plt.legend(loc='best')
plt.xlabel('x')
plt.grid()

plt.show()

stop = timeit.default_timer()

print('Time: ', stop - start)
