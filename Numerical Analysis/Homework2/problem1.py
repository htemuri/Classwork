import numpy as np
import timeit
from scipy import optimize

start = timeit.default_timer()

# combined function
def f(x):
    return (x ** 2 + (np.sqrt(3) * x) ** 2) ** 3 - 4 * x ** 2 * (np.sqrt(3) * x) ** 2


# roots are labeled from x_0 to x_2 from left to right

# root of x_0 using bisection method
x0_root = optimize.bisect(f, -1, -0.4)
x0_root = np.format_float_positional(x0_root, precision=4, unique=False, fractional=False, trim='k')
y = np.sqrt(3)*float(x0_root)
int0 = (x0_root,y)
print("Intersection 1 is {}".format(int0))

# root of x_1 using newton method
x1_root = optimize.newton(f, -0.1, tol=1.48e-06)
if (x1_root < 0.0001) | (x1_root > -0.0001):
    x1_root = 0
y = np.sqrt(3) * float(x1_root)
int1 = (x1_root, y)
print("Root of x_1 is {}".format(int1))

# root of x_2 using bisection method
x2_root = optimize.bisect(f, 0.4, 1)
x2_root = np.format_float_positional(x2_root, precision=4, unique=False, fractional=False, trim='k')
y = np.sqrt(3) * float(x2_root)
int2 = (x2_root, y)
print("Root of x_2 is {}".format(int2))

stop = timeit.default_timer()

print('Time: ', stop - start)
