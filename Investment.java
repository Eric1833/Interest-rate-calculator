 

public class Investment {

    private double investmentAmount;
    private int numYears;
    private double annualRate;

    public Investment() {
        investmentAmount = 1000;
        numYears = 1;
        annualRate = .25;
    }

    public Investment(double investmentAmount, int numYears, double annualRate) {
        setInvestmentAmount(investmentAmount);
        setNumYears(numYears);
        setAnnualRate(annualRate);
    }

    public double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(double investmentAmount) {
        if (investmentAmount >= 1000 && investmentAmount <= 10000000) {
            this.investmentAmount = investmentAmount;
        } else {
            throw new IllegalArgumentException("Invalid Investment Amount");
        }
    }

    public int getNumYears() {
        return numYears;
    }

    public void setNumYears(int numYears) {
        if (numYears >= 1 && numYears <= 50) {
            this.numYears = numYears;
        } else {
            throw new IllegalArgumentException("Invalid Number of Years");
        }
    }

    public double getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(double annualRate) {
        if (annualRate >= .25 && annualRate <= 100) {
            this.annualRate = annualRate;
        } else {
            throw new IllegalArgumentException("Invalid Annual Rate");
        }
    }

    public double calcFutureValue() {
        return (this.investmentAmount * Math.pow((1 + this.annualRate / 100 / 12), 
                (this.numYears * 12)));
    }
}
