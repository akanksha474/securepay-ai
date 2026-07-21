from pathlib import Path

import joblib
import numpy as np
from fastapi import FastAPI
from pydantic import BaseModel

print(Path.cwd())
app = FastAPI()

# extract and load the model
BASE_DIR = Path(__file__).resolve().parent.parent

model = joblib.load(BASE_DIR / "models" / "fraud_model.pkl")
scaler = joblib.load(BASE_DIR / "models" / "scaler.pkl")

class Transaction(BaseModel):
    amount:float
    accountAge:int
    velocity:int
    hour:int

@app.post("/predict")
def predict(request:Transaction):

    features = np.array([[
        request.amount,
        request.hour,
        request.accountAge,
        request.velocity
    ]])
    scaler_features = scaler.transform(features)
    prediction = model.predict(scaler_features)
    probability = model.predict_proba(scaler_features)[0][1]

    return {
        "fraud": bool(prediction[0]),
        "probability": round(float(probability), 4)
    }


