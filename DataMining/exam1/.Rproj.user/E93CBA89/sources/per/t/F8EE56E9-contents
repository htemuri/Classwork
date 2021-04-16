# Loading required packages
library(ggplot2)
library(dplyr)
library(readr)
library(tidyverse)
library(caret)
library(leaps)
library(MASS)

# Creating df of text data
diaDf <- diamond

# 2.1 Fit least squares regression price and carat
fit <- lm(price ~ carat, data = diaDf)
summary(fit)

# Pearson Correlation Test
cor.test(x=diaDf$carat,y=diaDf$price)

# Normality Test
shapiro.test(fit$residuals)

# Homoscedasticity Test
plot(fit)
lmtest::bptest(fit)


# 2.2 Linear model fit price and color
fit2 <- lm(price ~ color, data = diaDf)
summary(fit2)

# Normality Test
shapiro.test(diaDf$price[diaDf$color=="D"])
shapiro.test(diaDf$price[diaDf$color=="E"])
shapiro.test(diaDf$price[diaDf$color=="F"])
shapiro.test(diaDf$price[diaDf$color=="G"])
shapiro.test(diaDf$price[diaDf$color=="H"])
shapiro.test(diaDf$price[diaDf$color=="I"])
shapiro.test(diaDf$price[diaDf$color=="J"])

# variance test
bartlett.test(price ~ color, data=diaDf)

# 2.3 Test for best regressors
models <- regsubsets(price~., data = diaDf, nvmax = 9)
summary(models)

# 2.4 K-fold Cross validation

get_model_formula <- function(id, object, outcome){
  # get models data
  models <- summary(object$which[id,-1])
  # Get outcome variable
  form <- as.formula(object$call[[2]])
  outcome <- all.vars(form)[1]
  # Get model predictors
  predictors <- names(which(models == TRUE))
  predictors <- paste(predictors, collapse = "+")
  # Build model formula
  as.formula(paste(outcome, "~", predictors))
}

get_cv_error <- function(model.formula, data){
  set.seed(1)
  train.control <- trainControl(method = "cv", number = 5)
  cv <- train(model.formula, data = data, method = "lm",
              trControl = train.control)
  cv$results$RMSE
}

model.ids <- 1:9
cv.errors <- map(model.ids, get_model_formula, models, "price") %>%
  map(get_cv_error, data = diaDf) %>%
  unlist()
cv.errors



# 2.5 Step AIC
res.lm <- lm(price~., data = diaDf)
step <- stepAIC(res.lm,direction = "both", trace = FALSE)
step