# Loading required packages
library(ggplot2)
library(dplyr)
library(readr)

# Creating df of text data
crop_data <- read_csv("crop_data.csv")

# 1.1 Box Plots of fertilizer yield
ggplot(data = crop_data, aes(x = fertilizer, y = yield)) + geom_boxplot()

# 1.2 Test for fertilizer being equally effective
res_aov <- aov(yield ~ fertilizer, data = crop_data)
summary(res_aov)

# Testing Assumptions
# Normality Test of residuals
shapiro.test(res_aov$residuals)
# Homogeneity of Variance test
bartlett.test(yield ~ factor(fertilizer), data = crop_data)

# 1.4 Tukey Test
TukeyHSD(res_aov)

# 1.5 Comparing fertilizers 2 and 3
t.test(crop_data$yield[crop_data$fertilizer == 2], crop_data$yield[crop_data$fertilizer == 3], var.equal = T)
# Testing Assumptions
# Normality of Groups
shapiro.test(crop_data$yield[crop_data$fertilizer == 2])
shapiro.test(crop_data$yield[crop_data$fertilizer == 3])
# Var test
var.test(crop_data$yield[crop_data$fertilizer == 2], crop_data$yield[crop_data$fertilizer == 3])

