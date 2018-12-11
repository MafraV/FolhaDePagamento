import java.util.ArrayList;
import java.util.Scanner;

public class Hourly {

    private String name;
    private String Address;
    private double salary;
    private int system_ID;
    private String payment_method;
    private Boolean syndicate;
    private int sydicate_ID;
    private double union_fee;
    private ArrayList<ServiceFee> servicefees = new ArrayList<ServiceFee>();
    private ArrayList<TimeCard> timecards = new ArrayList<TimeCard>();
    private PaymentSchedule agenda;
    private int worked_days;

    private Scanner input = new Scanner(System.in);

    public Hourly(String name, String Address, double salary, int system_ID, int payment_method, int syndicate, PaymentSchedule agenda)
    {
        this.name = name;
        this.Address = Address;
        this.salary = salary;
        this.system_ID = system_ID;

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
        this.worked_days = 1;
    }

    public double getSalary()
    {
        return this.salary;
    }

    public void addTimecard(TimeCard x)
    {
        timecards.add(x);
    }

    public double Pay()
    {
        double how_much = 0;

        while(!timecards.isEmpty())
        {
            how_much = how_much + timecards.get(0).getHow_much_to_receive();
            timecards.remove(0);
        }

        worked_days = 0;

        if(syndicate) how_much = how_much - (how_much*union_fee/100);

        if(!servicefees.isEmpty()) how_much = how_much - servicefees.get(0).getValue();

        return how_much;
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

    public int get_last_TC_day()
    {
        if(timecards.isEmpty()) return -5;
        return timecards.get(timecards.size()-1).getDay();
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
}
