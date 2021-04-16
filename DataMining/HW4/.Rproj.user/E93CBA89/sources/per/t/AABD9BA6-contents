# Loading required packages
library(ggplot2)
library(dplyr)
library(readr)

# Creating df of text data
heart_data <- read_csv("heart_data.csv")

# 1.1 Finding Least squares regression line
lin_fit <- lm(heart.disease ~ biking, data=heart_data)
summary(lin_fit)

# 1.2 Plotting Linear Fit
ggplot(data = heart_data, aes(x=biking, y=heart.disease)) + geom_point() + stat_smooth(method = lm)

# 1.3 Testing linear relationship along with assumptions
# Normality Test of residuals
shapiro.test(lin_fit$residuals)
# Significance test (look at p-value of variable)
summary(lin_fit)

# 1.4 Correlation coefficient test
cor.test(heart_data$heart.disease, heart_data$biking)
# Normality Test of variables
shapiro.test(heart_data$heart.disease)
shapiro.test(heart_data$biking)
# Kendall Correlation Test
cor.test(heart_data$heart.disease, heart_data$biking, method = "kendall")

# 1.5 Coefficient of determination
cor(heart_data$heart.disease, heart_data$biking)^2
