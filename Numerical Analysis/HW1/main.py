import numpy as np
from scipy import *
import scipy.optimize as opt
import scipy.integrate as integ
# Problem 1
# area of the entire bean curve
p1fun = lambda theta: 0.5 * (np.sin(theta) ** 3 + np.cos(theta) ** 3) ** 2
beanArea = integ.quad(p1fun, 0, np.pi)
# area of the green circle
greenCircleArea = (0.25 ** 2) * np.pi
# area of bean minus green circle
answer = beanArea.__getitem__(0) - greenCircleArea
print("The area of the black curve minus the green circle "
      "is {}".format(answer))

# Problem 2
# function in problem 2
def p2fun(x):
    return 2.021 ** (-x ** 3) - (x ** 3) * np.cos(x ** 4) - 1.984
# root of x_1 using bisection method
x1_root = opt.bisect(p2fun, -0.925, -0.725)
print("Root of x_1 is {}".format(x1_root))
# root of x_2 using Newtons method
x2_root = opt.newton(p2fun, 1.275)
print("Root of x_2 is {}".format(x2_root))

