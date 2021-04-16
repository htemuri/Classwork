# Loading required packages
library(ggplot2)
library(dplyr)
library(datarium)

# Creating df of text data
df <- iris

# 1.1 Creating a box plot of the data
ggplot(df, aes(x=Species, y=Sepal.Width)) + geom_boxplot()

# 1.2 Testing Normality and equal variance assumptions
# Normality Test
# for setosa
shapiro.test(df$Sepal.Width[df$Species=="setosa"])
# for versicolor
shapiro.test(df$Sepal.Width[df$Species=="versicolor"])
# for virginica
shapiro.test(df$Sepal.Width[df$Species=="virginica"])

# Equal variance assumptions test
bartlett.test(Sepal.Width ~ Species, data=df)

# 1.2 2 sample t-test to test for equal means
summary(aov(Sepal.Width ~ Species, data=df))

# 1.3 Tukey Test
TukeyHSD(aov(Sepal.Width ~ Species, data = df))

# 1.4 Comparing veriginica to versicolor
virgDf <- df$Sepal.Width[df$Species=="virginica"]
versDf <- df$Sepal.Width[df$Species=="versicolor"]
# Equal variance test
var.test(virgDf, versDf)
# T-test
t.test(virgDf,versDf,var.equal = T)

