# Loading required packages
library(ggplot2)
library(dplyr)
library(readr)

# Creating df of text data
Diet <- read_csv("~/Documents/AMS380/quiz2/Diet.csv", 
                 + col_types = cols(Diet = col_character()))
# 1.1 Box Plots of Diet effectiveness
ggplot(data = Diet, aes(x = Diet, y = (weight6weeks-pre.weight))) + geom_boxplot()

# 1.2 Test for diets being equally effective
res_aov <- aov((weight6weeks-pre.weight) ~ Diet, data = Diet)
summary(res_aov)

# Testing Assumptions
# Normality Test of residuals
shapiro.test(res_aov$residuals)
# Homogeneity of Variance test
bartlett.test((weight6weeks-pre.weight) ~ factor(Diet), data = Diet)

# 1.4 Tukey Test
TukeyHSD(res_aov)

# 1.5 Comparing Diets 2 and 3
t.test((Diet$weight6weeks-Diet$pre.weight)[Diet$Diet == 2], (Diet$weight6weeks-Diet$pre.weight)[Diet$Diet == 3], var.equal = T)
# Testing Assumptions
# Normality of Groups
shapiro.test((Diet$weight6weeks-Diet$pre.weight)[Diet$Diet == 2])
shapiro.test((Diet$weight6weeks-Diet$pre.weight)[Diet$Diet == 3])
# Var test
var.test((Diet$weight6weeks-Diet$pre.weight)[Diet$Diet == 2], (Diet$weight6weeks-Diet$pre.weight)[Diet$Diet == 3])

