import pandas as pd
import numpy as np
from sklearn.linear_model import LinearRegression, LogisticRegression
import os

module_dir = os.path.dirname(__file__)
data = pd.read_csv(os.path.join(module_dir, "processed.csv"))

x = data.iloc[:, 2:-1] 
y = data.iloc[:, -1]

x_train = data.iloc[:80, 2:-1]
y_train = data.iloc[:80, -1]

x_test = data.iloc[80:, 2:-1]
y_test = data.iloc[80:, -1]

model = LogisticRegression()
model.fit(x, y)

def getCreditRating(person):
    """
        person is a tuple ( facebook_twitter, postpaid, late_bill_days_avg
            Education, similar_locations, network_bytes, daily_sms,	LoanAmount
            usage_hours_per_week,	PAN,	Property_Area )
    """
    predictedProba = model.predict_proba(person)
    creditRating = int(predictedProba[0][1]*100)
    interestRate = 0
    riskCategory = "A"
    if creditRating < 25:
        interestRate = 22
        riskCategory = "D"
    elif creditRating < 50:
        interestRate = 17
        riskCategory = "C"
    elif creditRating < 75:
        interestRate = 14
        riskCategory = "B"
    else:
        interestRate = 10
        riskCategory = "A"
    return (interestRate, riskCategory)


if __name__ == '__main__':
    pass