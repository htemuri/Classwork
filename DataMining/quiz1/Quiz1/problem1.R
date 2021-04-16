# Loading required packages
library(ggplot2)
library(dplyr)

# Creating df of text data
dosage <- c("0mg","0mg","0mg","0mg","0mg","0mg","0mg","0mg","0mg","0mg",
            "100mg","100mg","100mg","100mg","100mg","100mg","100mg","100mg","100mg","100mg")
fingertaps <- c(242,245, 244, 248, 247, 248, 242, 244, 246, 242, 252, 250, 249, 252, 252, 254, 251, 250, 247, 248)
data_1 <- data.frame(dosage, fingertaps)

# 1.1 Creating a box plot of the data
ggplot(data_1, aes(x=dosage, y=fingertaps)) + geom_boxplot()

# 1.2 Testing Normality and equal variance assumptions
# Normality Test
# for 0mg
shapiro.test(data_1$fingertaps[data_1$dosage=="0mg"])
# for 100mg
shapiro.test(data_1$fingertaps[data_1$dosage=="100mg"])

# Equal variance assumptions test
var.test(fingertaps ~ dosage, data=data_1)

# 1.3 2 sample t-test to test for equal means
t.test(data_1$fingertaps[data_1$dosage=="0mg"], data_1$fingertaps[data_1$dosage=="100mg"], var.equal = TRUE)
