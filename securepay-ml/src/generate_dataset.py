import pandas as pd
import random

from fraud_rules import calculate_fraud_label

TRANSACTIONS = 10000

transactions = []

for _ in range(TRANSACTIONS):

    # Generate transaction details
    amount = random.randint(100, 100000)
    hour = random.randint(0, 23)
    account_age = random.randint(1, 1000)
    velocity = random.randint(1, 5)

    # Generate fraud label
    fraud = calculate_fraud_label(
        amount,
        hour,
        account_age,
        velocity
    )

    # Store transaction
    transactions.append({
        "amount": amount,
        "hour": hour,
        "accountAge": account_age,
        "velocity": velocity,
        "fraud": fraud
    })

# Convert to DataFrame
df = pd.DataFrame(transactions)

# Save CSV
df.to_csv("../dataset/transactions.csv", index=False)

print("Dataset Generated Successfully!")
print(df.head())