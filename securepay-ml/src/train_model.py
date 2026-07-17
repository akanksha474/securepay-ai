import pandas as pd
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
import sklearn as sk

# Read the CSV file
data = pd.read_csv("../dataset/transactions.csv")

print(data)

#features
x = data[["amount","hour","accountAge","velocity"]]
#label
y = data["fraud"]

#splitting the dataset
x_train,x_test,y_train,y_test =train_test_split(x,y,test_size=0.2,random_state=42)

#model
model = sk.linear_model.LogisticRegression()
#training the model
model.fit(x_train,y_train)

#predicting the model
predictions = model.predict(x_test)

#checking the accuracy
accuracy = accuracy_score(y_test,predictions)

print("Accuracy Score", accuracy)


