# Loading required packages
library(ggplot2)
library(dplyr)
library(readr)

# Creating df of text data
income_data <- read_csv("income.data.csv")

# 1.1 Finding Least squares regression line
lin_fit <- lm(happiness ~ income, data=income_data)
summary(lin_fit)

# 1.2 Plotting Linear Fit
ggplot(data = income_data, aes(x=income, y=happiness)) + geom_point() + stat_smooth(method = lm)

# 1.3 Testing linear relationship along with assumptions
# Normality Test of residuals
shapiro.test(lin_fit$residuals)
# Significance test (look at p-value of variable)
summary(lin_fit)

# 1.4 Correlation coefficient test
cor.test(income_data$happiness, income_data$income)

# 1.5 Coefficient of determination
cor(income_data$happiness, income_data$income)^2