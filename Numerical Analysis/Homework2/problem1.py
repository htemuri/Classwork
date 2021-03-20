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
print("Root of x_0 is {}".format(x0_root))

# root of x_1 using newton method
x1_root = optimize.newton(f, -0.1, tol=1.48e-06)
if (x1_root < 0.0001) | (x1_root > -0.0001):
    x1_root = 0
print("Root of x_1 is {}".format(x1_root))

# root of x_2 using bisection method
x2_root = optimize.bisect(f, 0.4, 1)
x2_root = np.format_float_positional(x2_root, precision=4, unique=False, fractional=False, trim='k')
print("Root of x_2 is {}".format(x2_root))

stop = timeit.default_timer()

print('Time: ', stop - start)
