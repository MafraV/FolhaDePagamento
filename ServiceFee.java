public class ServiceFee {

    double value;
    int syndicate_ID;
    int day;
    int month;
    int year;

    public ServiceFee(double value, int syndicate_ID, int day, int month, int year)
    {
        this.value = value;
        this.syndicate_ID = syndicate_ID;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public double getValue()
    {
        return value;
    }
}
