# Loading required packages
library(ggplot2)
library(dplyr)
library(readr)
library(tidyr)

# Creating df of text data
df <- mtcars

# Converting to categorical data type
df$am = factor(df$am)
df$cyl = factor(df$cyl)
df$vs = factor(df$vs)
df$gear = factor(df$gear)
df$carb = factor(df$carb)

# 1.1 Finding Linear fit for mpg ~ am
fit1 <- lm(mpg ~ am, data=df)
summary(fit1)

# 1.2 Making 1 as am baseline
df$am <- relevel(df$am, ref = "1")
fit1 <- lm(mpg ~ am, data=df)
summary(fit1)

# 1.3 Finding Linear fit for mpg ~ hp,wt,am,gear
fit2 <- lm(mpg ~ hp + wt + am + gear, data=df)
Anova(fit2)
summary(fit2)

# 1.5 Testing assumptions for homoscedasticity and normality
shapiro.test(residuals(fit2))
lmtest::bptest(fit2)

