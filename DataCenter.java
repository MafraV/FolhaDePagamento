import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.GregorianCalendar;

public class DataCenter {

    private ArrayList<Hourly> Hourlys = new ArrayList<Hourly>();
    private ArrayList<String> Hourly_names = new ArrayList<String>();
    private ArrayList<Salaried> Salarieds = new ArrayList<Salaried>();
    private ArrayList<String> Salaried_names = new ArrayList<String>();
    private ArrayList<Commissioned> Commissioneds = new ArrayList<Commissioned>();
    private ArrayList<String> Commissioned_names = new ArrayList<String>();
    private ArrayList<String> Workers_names = new ArrayList<String>();
    private ArrayList<PaymentSchedule> Agendas = new ArrayList<PaymentSchedule>();
    private int Current_ID = 1000;
    private Scanner input = new Scanner(System.in);
    private GregorianCalendar calendar = new GregorianCalendar(2018, 0, 1);
    private double fixed_weekly_salary = 954.00/4;
    private Operation operations = new Operation();

    public DataCenter()
    {
        PaymentSchedule Agenda1 = new PaymentSchedule("mensal $", 20, 6);
        PaymentSchedule Agenda2 = new PaymentSchedule("semanal 1 sexta", 5, 6);
        PaymentSchedule Agenda3 = new PaymentSchedule("semanal 2 sexta", 10, 6);
        Agendas.add(Agenda1);
        Agendas.add(Agenda2);
        Agendas.add(Agenda3);
    }

    public void add_worker()
    {
        int readd = 1;

        while(readd == 1)
        {
            System.out.print("What's the new Worker's type?\n");
            System.out.print("1 - Hourly\n");
            System.out.print("2 - Salaried\n");
            System.out.print("3 - Commissioned\n");
            int type = Integer.parseInt(input.nextLine());

            System.out.print("\nPlease, enter the new Worker's full name: ");
            String name = input.nextLine();
            int reop = 1;

            while(Workers_names.contains(name) && reop != 2)
            {
                System.out.print("\nWorker already registered!\n");
                reop = this.tryAgain();

                if(reop == 1)
                {
                    System.out.print("Please, enter the new Worker's name: ");
                    name = input.nextLine();
                }
            }

            if(reop == 2) break;

            System.out.print("Please, enter the new Worker's Address: ");
            String address = input.nextLine();

            System.out.print("Please, enter the new Worker's Payment Method:\n");
            System.out.print("1 - Paycheck by post office\n");
            System.out.print("2 - Paycheck in hands\n");
            System.out.print("3 - Bank account deposit\n");
            int payment_method = Integer.parseInt(input.nextLine());

            System.out.print("Is the new Worker a union member?\n");
            System.out.print("Press 1 to Yes or 2 to No\n");
            int syndicate = Integer.parseInt(input.nextLine());

            if(type == 1)
            {
                System.out.print("Please, enter the new Worker's Salary (in Real): ");
                double salary = Double.parseDouble(input.nextLine());

                Hourly newWorker = new Hourly(name, address, salary, Current_ID, payment_method, syndicate, Agendas.get(1));
                Hourlys.add(newWorker);
                Hourly_names.add(name);
                operations.addHourly(newWorker);
                operations.addOperation("Add_Hourly");
            }

            else if(type == 2)
            {
                System.out.print("Please, enter the new Worker's Salary (in Real): ");
                double salary = Double.parseDouble(input.nextLine());

                Salaried newWorker = new Salaried(name, address, salary, Current_ID, payment_method, syndicate, Agendas.get(0));
                Salarieds.add(newWorker);
                Salaried_names.add(name);
            }

            else if(type == 3)
            {
                System.out.print("Please, enter the new Worker's Commission (in Percentage, only numbers): ");
                double commission = Double.parseDouble(input.nextLine());

                Commissioned newWorker = new Commissioned(name, address, fixed_weekly_salary, commission, Current_ID, syndicate, payment_method, Agendas.get(2));
                Commissioneds.add(newWorker);
                Commissioned_names.add(name);
            }

            Workers_names.add(name);
            Current_ID++;
            System.out.print("\nWorker Added!\n");
            System.out.print("\nWant to add other Worker?\n");
            System.out.print("Press 1 to Yes or 2 to No\n");
            readd = Integer.parseInt(input.nextLine());
        }

        System.out.print("\nReturning to the Main Menu!\n");
    }

    public void remove_worker()
    {
        int reRemove = 1;

        while(reRemove == 1)
        {
            System.out.print("\nPlease, enter the Worker's name: ");
            String name = input.nextLine();
            int reop = 1;

            while(!Workers_names.contains(name) && reop == 1)
            {
                System.out.print("\nWorker not found!\n");
                reop = this.tryAgain();

                if(reop == 1)
                {
                    System.out.print("\nPlease, enter the Worker's name: ");
                    name = input.nextLine();
                }
            }

            if(reop == 2) break;

            if(Hourly_names.contains(name))
            {
                int worker_index = Hourly_names.indexOf(name);
                Hourlys.remove(worker_index);
                Hourly_names.remove(name);
            }

            else if(Commissioned_names.contains(name))
            {
                int worker_index = Commissioned_names.indexOf(name);
                Commissioneds.remove(worker_index);
                Commissioned_names.remove(name);
            }

            else if(Salaried_names.contains(name))
            {
                int worker_index = Salaried_names.indexOf(name);
                Salarieds.remove(worker_index);
                Salaried_names.remove(name);
            }

            Workers_names.remove(name);

            System.out.print("\nWorker Removed!\n");
            System.out.print("\nWant to remove other Worker?\n");
            System.out.print("Press 1 to Yes or 2 to No\n");
            reRemove = Integer.parseInt(input.nextLine());
        }

        System.out.print("\nReturning to the Main Menu!\n");
    }

    public void castPC()
    {
        System.out.print("Please, enter the Hourly Worker's name: ");
        String name = input.nextLine();
        int reop = 1;

        while(!Hourly_names.contains(name) && reop == 1)
        {
            System.out.print("\nHourly Worker not found!\n");
            reop = this.tryAgain();

            if(reop == 1)
            {
                System.out.print("Please, enter the Hourly Worker's name: ");
                name = input.nextLine();
            }
        }

        if(reop == 2)
        {
            System.out.print("\nReturning to the Main Menu!\n");
            return;
        }

        int hourly_index = Hourly_names.indexOf(name);
        Hourly worker = Hourlys.get(hourly_index);

        if(worker.get_last_TC_day() == calendar.get(GregorianCalendar.DAY_OF_MONTH))
        {
            System.out.print("\nHourly already worked today!\n");
            System.out.print("\nReturning to the Main Menu!\n");
            return;
        }

        System.out.print("Please, enter the time of entry (hours and minutes, numbers and space only): ");
        int e_h = input.nextInt();
        int e_m = input.nextInt();
        input.nextLine();

        while((e_h>23 || e_h<0 || e_m>59 || e_m<0) && reop == 1)
        {
            System.out.print("\nInvalid Entry Time!\n");
            reop = this.tryAgain();

            if(reop == 1)
            {
                System.out.print("Please, enter the time of entry (hours and minutes, numbers and space only): ");
                e_h = input.nextInt();
                e_m = input.nextInt();
                input.nextLine();
            }
        }

        if(reop == 2)
        {
            System.out.print("\nReturning to the Main Menu!\n");
            return;
        }

        System.out.print("Please, enter the exit time (hours and minutes, numbers and space only): ");
        int l_h = input.nextInt();
        int l_m = input.nextInt();
        input.nextLine();

        while((l_h>23 || l_h<0 || l_m>59 || l_m<0 || l_h<e_h || (l_h==e_h && l_m<e_m)) && reop == 1)
        {
            System.out.print("\nInvalid Exit Time!\n");
            reop = this.tryAgain();

            if(reop == 1)
            {
                System.out.print("Please, enter the exit time (hours and minutes, numbers and space only): ");
                l_h = input.nextInt();
                l_m = input.nextInt();
                input.nextLine();
            }
        }

        if(reop == 2)
        {
            System.out.print("\nReturning to the Main Menu!\n");
            return;
        }

        TimeCard pointCard = new TimeCard(worker, e_h, e_m, l_h, l_m, calendar.get(GregorianCalendar.DAY_OF_WEEK), calendar.get(GregorianCalendar.DAY_OF_MONTH), calendar.get(GregorianCalendar.MONTH) + 1, calendar.get(GregorianCalendar.YEAR));
        worker.addTimecard(pointCard);

        System.out.print("\nDone!\n");
        System.out.print("\nReturning to the Main Menu!\n");
    }

    public void castSale()
    {
        System.out.print("Please, enter the Commissioned Worker's name: ");
        String name = input.nextLine();
        int reop = 1;

        while(!Commissioned_names.contains(name) && reop == 1)
        {
            System.out.print("\nCommissioned Worker not found!\n");
            reop = this.tryAgain();

            if(reop == 1)
            {
                System.out.print("Please, enter the Commissioned Worker's name: ");
                name = input.nextLine();
            }
        }

        if(reop == 2)
        {
            System.out.print("\nReturning to the Main Menu!\n");
            return;
        }

        int c_index = Commissioned_names.indexOf(name);
        Commissioned worker = Commissioneds.get(c_index);

        System.out.print("Please, enter the Sale Price (in Real): ");
        double price = Double.parseDouble(input.nextLine());

        Sale sale = new Sale(worker, price, calendar.get(GregorianCalendar.DAY_OF_MONTH), calendar.get(GregorianCalendar.MONTH), calendar.get(GregorianCalendar.YEAR));
        worker.addSale(sale);

        System.out.print("\nDone!\n");
        System.out.print("\nReturning to the Main Menu!\n");
    }

    public void castServiceFee()
    {
        System.out.print("Please, enter the Worker's name: ");
        String name = input.nextLine();
        int reop = 1;

        while(!Workers_names.contains(name) && reop == 1)
        {
            System.out.print("\nWorker not found!\n");
            reop = this.tryAgain();

            if(reop == 1)
            {
                System.out.print("Please, enter the Worker's name: ");
                name = input.nextLine();
            }
        }

        if(reop == 2)
        {
            System.out.print("\nReturning to the Main Menu!\n");
            return;
        }

        int w_index;

        if(Hourly_names.contains(name))
        {
            w_index = Hourly_names.indexOf(name);
            Hourly worker = Hourlys.get(w_index);
            if(!worker.getSyndicate())
            {
                System.out.print("\nWorker isn't a union member!\n");
                System.out.print("\nReturning to the Main Menu!\n");
                return;
            }

            else if(!worker.ServiceFee_Check())
            {
                System.out.print("\nWorker already has a Service Fee this week!\n");
                System.out.print("\nReturning to the Main Menu!\n");
                return;
            }

            else
            {
                System.out.print("Please, enter the Service Fee value (in Real): ");
                double value = Double.parseDouble(input.nextLine());

                ServiceFee servicefee = new ServiceFee(value, worker.getSyndicateID(), calendar.get(GregorianCalendar.DAY_OF_MONTH), calendar.get(GregorianCalendar.MONTH), calendar.get(GregorianCalendar.YEAR));
                worker.addServiceFee(servicefee);
            }
        }

        else if(Salaried_names.contains(name))
        {
            w_index = Salaried_names.indexOf(name);
            Salaried worker = Salarieds.get(w_index);
            if(!worker.getSyndicate())
            {
                System.out.print("\nWorker isn't a union member!\n");
                System.out.print("\nReturning to the Main Menu!\n");
                return;
            }

            else if(!worker.ServiceFee_Check())
            {
                System.out.print("\nWorker already has a Service Fee this week!\n");
                System.out.print("\nReturning to the Main Menu!\n");
                return;
            }

            else
            {
                System.out.print("Please, enter the Service Fee value (in Real): ");
                double value = Double.parseDouble(input.nextLine());

                ServiceFee servicefee = new ServiceFee(value, worker.getSyndicateID(), calendar.get(GregorianCalendar.DAY_OF_MONTH), calendar.get(GregorianCalendar.MONTH), calendar.get(GregorianCalendar.YEAR));
                worker.addServiceFee(servicefee);
            }
        }

        else if(Commissioned_names.contains(name))
        {
            w_index = Commissioned_names.indexOf(name);
            Commissioned worker = Commissioneds.get(w_index);
            if(!worker.getSyndicate())
            {
                System.out.print("\nWorker isn't a union member!\n");
                System.out.print("\nReturning to the Main Menu!\n");
                return;
            }

            else if(!worker.ServiceFee_Check())
            {
                System.out.print("\nWorker already has a Service Fee this week!\n");
                System.out.print("\nReturning to the Main Menu!\n");
                return;
            }

            else
            {
                System.out.print("Please, enter the Service Fee value (in Real): ");
                double value = Double.parseDouble(input.nextLine());

                ServiceFee servicefee = new ServiceFee(value, worker.getSyndicateID(), calendar.get(GregorianCalendar.DAY_OF_MONTH), calendar.get(GregorianCalendar.MONTH), calendar.get(GregorianCalendar.YEAR));
                worker.addServiceFee(servicefee);
            }
        }

        System.out.print("\nDone!\n");
        System.out.print("\nReturning to the Main Menu!\n");
    }

    public void changeInfo()
    {
        System.out.print("Please, enter the Worker's name: ");
        String name = input.nextLine();
        int reop = 1;

        while(!Workers_names.contains(name) && reop == 1)
        {
            System.out.print("\nWorker not found!\n");
            reop = this.tryAgain();

            if(reop == 1)
            {
                System.out.print("Please, enter the Worker's name: ");
                name = input.nextLine();
            }
        }

        if(reop == 2)
        {
            System.out.print("\nReturning to the Main Menu!\n");
            return;
        }

        

        int op = 0;

        System.out.print("\nWhat Information do you want to change?\n");
        System.out.print("1 - Name");
        System.out.print("2 - Address");
        System.out.print("3 - Type");
        System.out.print("4 - Payment Method");
        System.out.print("5 - If is member a Union Member");
        System.out.print("6 - Syndicate ID");
        System.out.print("7 - Union Fee");
        System.out.print("8 - None (Return to the Main Menu)");
        op = Integer.parseInt(input.nextLine());

        if(op == 1)
        {
            System.out.print("Please, enter the Worker's new name: ");
            String new_name = input.nextLine();


        }

    }

    public int tryAgain()
    {
        System.out.print("\nWant to try again?\n");
        System.out.print("Press 1 to Yes or 2 to No.\n");
        return Integer.parseInt(input.nextLine());
    }

    public void print_date()
    {
        int year = calendar.get(GregorianCalendar.YEAR);
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int day_week = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        String dia = "";

        if(day_week == 1) dia = "Sunday";
        else if(day_week == 2) dia = "Monday";
        else if(day_week == 3) dia = "Tuesday";
        else if(day_week == 4) dia = "Wednesday";
        else if(day_week == 5) dia = "Thursday";
        else if(day_week == 6) dia = "Friday";
        else if(day_week == 7) dia = "Saturday";

        System.out.print("\nToday is: " + dia + "\n");

        if(day < 10 && month<10) System.out.print("0" + day + "/" + 0+month + "/" + year +"\n");
        else if(day < 10) System.out.print("0" + day + "/" + month + "/" + year +"\n");
        else if(month<10) System.out.print(day + "/" + 0+month + "/" + year +"\n");
        else System.out.print(day + "/" + month + "/" + year +"\n");
    }

    public void pass_day()
    {
        int year = calendar.get(GregorianCalendar.YEAR);
        int month = calendar.get(GregorianCalendar.MONTH);
        int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);

        calendar.set(year, month, day+1);

        this.print_date();

        if(calendar.get(GregorianCalendar.DAY_OF_WEEK) == 1 || calendar.get(GregorianCalendar.DAY_OF_WEEK) == 7)
        {
            System.out.print("\nThe Company is Closed!\n");
            System.out.print("\nPassing the Day!\n");

            this.pass_day();
        }

        else
        {
            int i;
            for(i=0; i<Hourlys.size(); i++) Hourlys.get(i).work_a_day();
            for(i=0; i<Salarieds.size(); i++) Salarieds.get(i).work_a_day();
            for(i=0; i<Commissioneds.size(); i++) Commissioneds.get(i).work_a_day();
        }
    }

    public void Undo()
    {
        operations.Undo(this);
    }

    public void remove_hourly(Hourly x)
    {
        String name = x.getName();
        Hourlys.remove(x);
        Hourly_names.remove(name);
        Workers_names.remove(name);
    }

    public void PayRoll()
    {
        int i;

        System.out.print("\nHourlys:\n");

        for(i=0; i<Hourlys.size(); i++)
        {
            if((Hourlys.get(i).getAgenda().getPaymentDay() == calendar.get(GregorianCalendar.DAY_OF_WEEK)) && (Hourlys.get(i).getWorked_days() >= Hourlys.get(i).getAgenda().getUntilPayment()))
            {
                double Payment = Hourlys.get(i).Pay();

                int method1;
                String method = Hourlys.get(i).getMethod();
                if(Objects.equals(method, "Cheque pelos correios"))  method1 = 1;
                else if(Objects.equals(method, "Cheque em mãos"))  method1 = 2;
                else method1 = 3;

                String name = Hourlys.get(i).getName();

                if(method1 == 1) System.out.print("A Paycheck of R$ " + Payment + " must be sent to " + name + "'s home by post office\n");
                else if(method1 == 2) System.out.print("A Paycheck of R$ " + Payment + " must be handed in to " + name + "\n");
                else System.out.print("A Deposit of R$ " + Payment + " must be done to " + name + "'s bank account\n");
            }
        }

        System.out.print("\nCommissioneds:\n");

        for(i=0; i<Commissioneds.size(); i++)
        {
            if((Commissioneds.get(i).getAgenda().getPaymentDay() == calendar.get(GregorianCalendar.DAY_OF_WEEK)) && (Commissioneds.get(i).getWorked_days() >= Commissioneds.get(i).getAgenda().getUntilPayment()))
            {
                double Payment = Commissioneds.get(i).Pay();

                int method1;
                String method = Commissioneds.get(i).getMethod();
                if(Objects.equals(method, "Cheque pelos correios"))  method1 = 1;
                else if(Objects.equals(method, "Cheque em mãos"))  method1 = 2;
                else method1 = 3;

                String name = Commissioneds.get(i).getName();

                if(method1 == 1) System.out.print("A Paycheck of R$ " + Payment + " must be sent to " + name + "'s home by post office\n");
                else if(method1 == 2) System.out.print("A Paycheck of R$ " + Payment + " must be handed in to " + name + "\n");
                else System.out.print("A Deposit of R$ " + Payment + " must be done to " + name + "'s bank account\n");
            }

        }

        System.out.print("\nSalarieds:\n");

        for(i=0; i<Salarieds.size(); i++)
        {
            if((Salarieds.get(i).getAgenda().getPaymentDay() == calendar.get(GregorianCalendar.DAY_OF_WEEK)) && (Salarieds.get(i).getWorked_days() >= Salarieds.get(i).getAgenda().getUntilPayment()))
            {
                double Payment = Salarieds.get(i).Pay();

                int method1;
                String method = Salarieds.get(i).getMethod();
                if(Objects.equals(method, "Cheque pelos correios"))  method1 = 1;
                else if(Objects.equals(method, "Cheque em mãos"))  method1 = 2;
                else method1 = 3;

                String name = Salarieds.get(i).getName();

                if(method1 == 1) System.out.print("A Paycheck of R$ " + Payment + " must be sent to " + name + "'s home by post office\n");
                else if(method1 == 2) System.out.print("A Paycheck of R$ " + Payment + " must be handed in to " + name + "\n");
                else System.out.print("A Deposit of R$ " + Payment + " must be done to " + name + "'s bank account\n");
            }
        }
    }
}
