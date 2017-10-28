import pandas as pd

def main(filename):
    df = pd.read_csv(filename)
    df["facebook_twitter"] = [1 if x == "M" else 0 for x in df["facebook_twitter"]]
    df["postpaid"] = [1 if x == "Yes" else 0 for x in df["postpaid"]]	
    df["Education"] = [1 if x == "Graduate" else 0 for x in df["Education"]]
    df["similar_locations"] = [1 if x == "Yes" else 0 for x in df["similar_locations"]]
    df["late_bill_days_avg"] = [3 if x == "3+" else x for x in df["late_bill_days_avg"]]
    area = {"Urban" : 3, "Semiurban" : 2, "Rural" : 1}
    df["Property_Area"] = [area[x] for x in df["Property_Area"]]
    df["Loan_Status"] = [1 if x == "Y" else 0 for x in df["Loan_Status"]]
    df.to_csv("processed.csv")

if __name__ == '__main__':
    filename = "train_data.csv"
    main()