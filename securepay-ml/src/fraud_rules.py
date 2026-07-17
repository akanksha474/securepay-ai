import random

def calculate_fraud_label(amount, hour, account_age, velocity):

    fraud_score = 0

    # Large Amount Rule
    if amount > 50000:
        fraud_score += 40

    # Night Transaction Rule
    if 0 <= hour <= 5:
        fraud_score += 20

    # New Account Rule
    if account_age < 7:
        fraud_score += 20

    # High Velocity Rule
    if velocity >= 4:
        fraud_score += 40

    # Convert score to probability
    if fraud_score >= 80:
        probability = 0.95
    elif fraud_score >= 40:
        probability = 0.50
    else:
        probability = 0.02

    return 1 if random.random() < probability else 0