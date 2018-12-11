
public class PaymentSchedule {

    int days_until_payment;
    int day_of_week_be_payed;
    String name;


    public PaymentSchedule(String name, int days_until_payment, int day_of_week_be_payed)
    {
        this.name = name;
        this.day_of_week_be_payed = day_of_week_be_payed;
        this.days_until_payment = days_until_payment;
    }

    public int getPaymentDay()
    {
        return day_of_week_be_payed;
    }

    public int getUntilPayment()
    {
        return days_until_payment;
    }

    public String getName()
    {
        return name;
    }
}
