# Loading required packages
library(dplyr)

# 1.1 Testing null hypothesis of whether the 
# proportion of smokers are equal between group a and b 
prop.test(x = c(490,400), n = c(500,500))
