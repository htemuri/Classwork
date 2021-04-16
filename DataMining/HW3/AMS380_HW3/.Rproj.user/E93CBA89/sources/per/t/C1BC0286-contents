# Loading required packages
library(ggplot2)
library(dplyr)
library(PairedData)

# Creating df of text data
data_2 <- read.csv("~/Documents/AMS380/HW3/AMS380_HW3/data_2.txt", sep="")

# 1.1 Creating a box plot and profile plot of the data
# Box Plot
ggplot(data = data_2, aes(x = group, y = weight)) + geom_boxplot()
# Profile Plot
before <- subset(data_2, group=="before", weight, drop = TRUE)
after <- subset(data_2, group=="after", weight, drop = TRUE)
pd <- paired(before, after)
plot(pd, type="profile")

# 1.2 Testing Normality and equal variance assumptions
# Normality Test
shapiro.test(data_2$weight[data_2$group=="after"] - data_2$weight[data_2$group=="before"])

# Paired t-test to test for equal means
t.test(before, after, paired=TRUE)
st