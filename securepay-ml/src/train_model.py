import pandas as pd
import joblib

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, confusion_matrix
from sklearn.preprocessing import StandardScaler

data = pd.read_csv("../dataset/transactions.csv")
#features
X = data[["amount", "hour", "accountAge", "velocity"]]
#labels
y = data["fraud"]

# Splitting the dataset into the Training set and Test set
X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.2,
    random_state=42
)

scaler = StandardScaler()

X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# using logistic regression for classification
model = LogisticRegression(max_iter=1000)

#training the model on the training set
model.fit(X_train, y_train)

#predicting the test set results
predictions = model.predict(X_test)

print("Accuracy :", accuracy_score(y_test, predictions))
# evaluation metrics
# precision is for how many of the predicted values are correct
print("Precision:", precision_score(y_test, predictions))
# recall is for how many of the actual values correct
print("Recall   :", recall_score(y_test, predictions))
# f1 score is the harmonic mean of precision and recall
print("F1 Score :", f1_score(y_test, predictions))

print("\nConfusion Matrix:")
print(confusion_matrix(y_test, predictions))

# saving the model and scaler
joblib.dump(model, "../models/fraud_model.pkl")
joblib.dump(scaler, "../models/scaler.pkl")

print("\nModel trained successfully!")
print("Model saved as fraud_model.pkl")
print("Scaler saved as scaler.pkl")

