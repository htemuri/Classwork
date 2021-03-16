import numpy as np
import scipy as sc
import matplotlib.pyplot as pp
import random

mean = 0.002021
std = 0.001984
fee = 0.01111
account = 1000000.0
random.seed(10)
for i in range(260):
    x1 = random.random()
    x2 = random.random()
    g1 = np.sqrt(-2 * np.log(x1)) * np.cos(2 * np.pi * x2)
    g2 = np.sqrt(-2 * np.log(x1)) * np.sin(2 * np.pi * x2)
    z1 = mean + g1 * std
    z2 = mean + g2 * std
    print(z1)
    p = (1 / np.sqrt(2 * np.pi * std ** 2)) * \
        (np.exp((-(z1 - mean) ** 2) / (2 * std ** 2)))


# for i in range(260):
#     if ()
#     account = account + account*