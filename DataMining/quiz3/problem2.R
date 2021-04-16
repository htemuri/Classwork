# Loading required packages
library(tidyverse)
library(caret)
library(leaps)
library(MASS)

# subset variable selection
models <- regsubsets(mpg~., data=df, nvmax=5)
summary(models)

# 5-fold cross validation

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

get_cv_error <- function(model.formula){
  set.seed(1)
  train.control <- trainControl(method = "cv", number = 5)
  cv <- train(model.formula, data = df, method = "lm",
              trControl = train.control)
  cv$results$RMSE
}

model.ids <- 1:5

model_formula <- c()

for (model.id in 1:5) {
  model_formula <- c(model_formula,get_model_formula(model.id,models,'mpg'))
}

#cv.errors <- map(model.ids, get_model_formula, models, "mpg") %>%
#  map(get_cv_error, data = df) %>%
#  unlist()
cv.errors

res.lm <- lm(mpg ~., data= df)
step <- stepAIC(res.lm, direction = "both", trace = FALSE)
