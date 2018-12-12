import java.util.ArrayList;
import java.util.Scanner;

public class Commissioned {

    private String name;
    private String Address;
    private double salary;
    private double commission;
    private int system_ID;
    private String payment_method;
    private Boolean syndicate;
    private int sydicate_ID;
    private double union_fee;
    private ArrayList<ServiceFee> servicefees = new ArrayList<ServiceFee>();
    private PaymentSchedule agenda;
    private int worked_days;
    private ArrayList<Sale> Sales = new ArrayList<Sale>();

    private Scanner input = new Scanner(System.in);

    public Commissioned()
    {

    }

    public Commissioned(String name, String Address, double salary, double commission, int system_ID, boolean syndicate, String payment_method, PaymentSchedule agenda, int sydicate_ID, double union_fee)
    {
        this.name = name;
        this.Address = Address;
        this.salary = salary;
        this.system_ID = system_ID;
        this.commission = commission;
        this.payment_method = payment_method;
        this.syndicate = syndicate;
        this.sydicate_ID = sydicate_ID;
        this.union_fee = union_fee;
        this.agenda = agenda;
        worked_days = 0;
    }

    public Commissioned(String name, String Address, double salary, double commission, int system_ID, int syndicate, int payment_method, PaymentSchedule agenda)
    {
        this.name = name;
        this.Address = Address;
        this.salary = salary;
        this.system_ID = system_ID;
        this.commission = commission;

        if(payment_method == 1) this.payment_method = "Cheque pelos correios";
        else if(payment_method == 2) this.payment_method = "Cheque em mãos";
        else if(payment_method == 3) this.payment_method = "Deposito em conta bancaria";

        if(syndicate == 1) this.syndicate = true;
        else if(syndicate == 2) this.syndicate = false;

        if(this.syndicate)
        {
            System.out.print("Please, enter the Worker syndicate ID: ");
            this.sydicate_ID = Integer.parseInt(input.nextLine());

            System.out.print("Please, enter the Worker union fee (in Percentage, only numbers): ");
            this.union_fee = Double.parseDouble(input.nextLine());
        }

        this.agenda = agenda;
        worked_days = 1;
    }

    public double getComission()
    {
        return commission;
    }

    public void addSale(Sale sale)
    {
        this.Sales.add(sale);
    }

    public int getSyndicateID()
    {
        return sydicate_ID;
    }

    public boolean getSyndicate()
    {
        return syndicate;
    }

    public void addServiceFee(ServiceFee x)
    {
        servicefees.add(x);
    }

    public String getName()
    {
        return this.name;
    }

    public void work_a_day()
    {
        this.worked_days++;
    }

    public boolean ServiceFee_Check()
    {
        return servicefees.isEmpty();
    }

    public PaymentSchedule getAgenda()
    {
        return this.agenda;
    }

    public int getWorked_days()
    {
        return worked_days;
    }

    public String getMethod()
    {
        return payment_method;
    }

    public double Pay()
    {
        double how_much = (worked_days/5)*salary;

        while(!Sales.isEmpty())
        {
            how_much = how_much + Sales.get(0).getHow_much_to_receive();
            Sales.remove(0);
        }

        worked_days = 0;

        if(syndicate) how_much = how_much - (how_much*union_fee/100);

        if(!servicefees.isEmpty()) how_much = how_much - servicefees.get(0).getValue();

        return how_much;
    }

    public int getWorkerDays()
    {
        return worked_days;
    }

    public void setAgenda(PaymentSchedule x)
    {
        this.agenda = x;
    }

    public void setName(String x)
    {
        this.name = x;
    }

    public void setAddress(String x)
    {
        this.Address = x;
    }

    public void setMethod(int payment_method)
    {
        if(payment_method == 1) this.payment_method = "Cheque pelos correios";
        else if(payment_method == 2) this.payment_method = "Cheque em mãos";
        else if(payment_method == 3) this.payment_method = "Deposito em conta bancaria";
    }

    public void setSyndicate(boolean x)
    {
        this.syndicate = x;
    }

    public void setUnion_fee(double x)
    {
        this.union_fee = x;
    }

    public void setSydicate_ID(int x)
    {
        this.sydicate_ID = x;
    }

    public String getAddress()
    {
        return this.Address;
    }

    public int getSystemID()
    {
        return this.system_ID;
    }

    public double getUnion_fee()
    {
        return this.union_fee;
    }

    public void removeSale(Sale sale)
    {
        Sales.remove(sale);
    }
}
