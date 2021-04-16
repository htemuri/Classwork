import numpy as np
import matplotlib.pyplot as plt
from scipy import interpolate


# Python program to implement Runge Kutta method
# A sample differential equation "dy / dx = (x - y)/2"
def dydx(x, y):
    return x + y + x * y


# Finds value of y for a given x using step size h
# and initial value y0 at x0.
def rungeKutta(x0, y0, x, h):
    # Count number of iterations using step size or
    # step height h
    n = (int)((x - x0) / h)
    # Iterate for number of iterations
    y = y0
    for i in range(1, n + 1):
        "Apply Runge Kutta Formulas to find next value of y"
        k1 = h * dydx(x0, y)
        k2 = h * dydx(x0 + 0.5 * h, y + 0.5 * k1)
        k3 = h * dydx(x0 + 0.5 * h, y + 0.5 * k2)
        k4 = h * dydx(x0 + h, y + k3)

        # Update next value of y
        y = y + (1.0 / 6.0) * (k1 + 2 * k2 + 2 * k3 + k4)

        # Update next value of x
        x0 = x0 + h
    return y


x = np.arange(0, .51, 0.01)
y = []
for i in range(len(x)):
    y.append(rungeKutta(0, 1, x[i], 0.01))

f = interpolate.interp1d(x,y)
f2 = interpolate.interp1d(x,y,kind='cubic')

xnew = np.arange(0.1,0.6,0.1)

plt.plot(xnew,f(xnew), '-', xnew, f2(xnew),'--')
plt.legend(['data','linear','cubic'], loc='best')
plt.show()
print(f(xnew))