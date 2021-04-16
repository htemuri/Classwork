# Loading required packages
library(ggplot2)
library(dplyr)

# Creating df of text data
data_1 <- read.table("~/Documents/AMS380/HW3/AMS380_HW3/data_1.txt", header=TRUE, quote="\"")

# 1.1 Creating a box plot of the data
ggplot(data = data_1, aes(x = group, y = weight)) + geom_boxplot()

# 1.2 Testing Normality and equal variance assumptions
# Normality Test
# for male
shapiro.test(data_1$weight[data_1$group=="Man"])
# for female
shapiro.test(data_1$weight[data_1$group=="Woman"])

# Equal variance assumptions test
var.test(weight ~ group, data = data_1)

# 1.3 2 sample t-test to test for equal means
t.test(data_1$weight[data_1$group=="Woman"], data_1$weight[data_1$group=="Man"], var.equal = TRUE)

