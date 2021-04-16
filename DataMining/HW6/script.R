install.packages("mlbench")
library(mlbench)

# import data
data("PimaIndiansDiabetes2")
df<- PimaIndiansDiabetes2

# remove NA data points
df <- na.omit(df)

# Problem 1.1
# Split data into 80% training and 20% testing

set.seed(123)

training <- df$diabetes %>%
  createDataPartition(p=0.8, list = FALSE)

trainData <- df[training, ]
testData <- df[-training, ]

# Problem 1.2
# Logistic Regression Fit

model <- glm(diabetes ~ ., data=trainData, family = binomial)
summary(model)

# Predictions
probabilities <- model %>% predict(testData, type="response")
predictedClasses <- ifelse(probabilities > 0.5, "pos", "neg")

# Prediction accuracy
mean(predictedClasses == testData$diabetes)
# Prediction error
mean(predictedClasses != testData$diabetes)

# Confusion matrix
cm <- confusionMatrix(factor(predictedClasses), testData$diabetes, positive = "pos")

# Plotting regression model

trainData %>%
  mutate(prob = ifelse(diabetes=="pos", 1, 0)) %>%
  ggplot(aes(glucose, prob)) +
  geom_point(alpha=0.2) + 
  geom_smooth(method="glm", method.args = list(family="binomial")) +
  labs(
    title="Logistic Regression Model",
    x = "Plasma Glucose Concentration",
    y = "Probability of being diabete-pos"
  )
