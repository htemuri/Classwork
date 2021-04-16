# Loading required packages
library(dplyr)

# 1.1 Testing null hypothesis of whether the 
# proportion of skiers are equal between group a and b 
prop.test(x = c(17,31), n = c(159,140))
