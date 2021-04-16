library(readr)
library(mlbench)
library(caret)
library(tidyverse)
library(MASS)
library(leaps) 
library(bestglm)

# import data
banknote <- read_csv("banknote.csv")
df<- banknote

# remove NA data points
df <- na.omit(df)

# Problem 1.1
# Split data into 80% training and 20% testing

set.seed(123)

training <- df$class %>%
  createDataPartition(p=0.8, list = FALSE)

trainData <- df[training, ]
testData <- df[-training, ]

# Problem 1.2
# Logistic Regression Fit

df$class <- as.factor(df$class)
model <- glm(class ~ ., data=trainData, family = binomial)
summary(model)

# Predictions
probabilities <- model %>% predict(testData, type="response")
predictedClasses <- ifelse(probabilities > 0.5, "1", "0")

# Prediction accuracy
mean(predictedClasses == testData$class)
# Prediction error
mean(predictedClasses != testData$class)

# Confusion matrix
cm <- confusionMatrix(factor(predictedClasses), factor(testData$class), positive = "1")
cm


# Problem 2

step <- stepAIC(model, trace="0")
BIC<-stepAIC(model,k=log(nrow(df)))

# Problem 3

bestSubset <- bestglm::bestglm(df, IC="BIC", family=binomial)
