

public class Sale {

    private Commissioned seller;
    private double price;
    private int day;
    private int month;
    private int year;
    double how_much_to_receive;

    public Sale(Commissioned worker, double price, int day, int month, int year)
    {
        this.seller = worker;
        this.price = price;
        this.day = day;
        this.month = month;
        this.year = year;

        this.how_much_to_receive = price*(seller.getComission()/100);
    }

    public double getHow_much_to_receive()
    {
        return how_much_to_receive;
    }

    public Commissioned getWorker()
    {
        return this.seller;
    }
}
