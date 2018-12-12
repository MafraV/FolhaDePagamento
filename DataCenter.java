import javax.sound.midi.SysexMessage;
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
                operations.addSalaried(newWorker);
                operations.addOperation("Add_Salaried");
            }

            else if(type == 3)
            {
                System.out.print("Please, enter the new Worker's Commission (in Percentage, only numbers): ");
                double commission = Double.parseDouble(input.nextLine());

                Commissioned newWorker = new Commissioned(name, address, fixed_weekly_salary, commission, Current_ID, syndicate, payment_method, Agendas.get(2));
                Commissioneds.add(newWorker);
                Commissioned_names.add(name);
                operations.addCommissioned(newWorker);
                operations.addOperation("Add_Commissioned");
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
                Hourly worker = Hourlys.get(worker_index);
                Hourlys.remove(worker);
                Hourly_names.remove(name);
                operations.removeHourly(worker);
                operations.addOperation("Remove_Hourly");
            }

            else if(Commissioned_names.contains(name))
            {
                int worker_index = Commissioned_names.indexOf(name);
                Commissioned worker = Commissioneds.get(worker_index);
                Commissioneds.remove(worker);
                Commissioned_names.remove(name);
                operations.removeCommissioned(worker);
                operations.addOperation("Remove_Commissioned");
            }

            else if(Salaried_names.contains(name))
            {
                int worker_index = Salaried_names.indexOf(name);
                Salaried worker = Salarieds.get(worker_index);
                Salarieds.remove(worker);
                Salaried_names.remove(name);
                operations.removeSalaried(worker);
                operations.addOperation("Remove_Salaried");
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
        operations.castTC(pointCard);
        operations.addOperation("Cast_Time_Card");

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
        operations.castSale(sale);
        operations.addOperation("Cast_Sale");

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

        int index, test = 0;

        Hourly worker_h = new Hourly();
        Commissioned worker_c = new Commissioned();
        Salaried worker_s = new Salaried();

        if(Hourly_names.contains(name))
        {
            index = Hourly_names.indexOf(name);
            worker_h = Hourlys.get(index);
            test = 1;
        }

        else if(Salaried_names.contains(name))
        {
            index = Salaried_names.indexOf(name);
            worker_s = Salarieds.get(index);
            test = 2;
        }

        else
        {
            index = Commissioned_names.indexOf(name);
            worker_c = Commissioneds.get(index);
            test = 3;
        }

        int op = 0;
        reop = 1;

        System.out.print("\nWhat Information do you want to change?\n");
        System.out.print("1 - Name\n");
        System.out.print("2 - Address\n");
        System.out.print("3 - Type\n");
        System.out.print("4 - Payment Method\n");
        System.out.print("5 - If is member a Union Member\n");
        System.out.print("6 - Syndicate ID\n");
        System.out.print("7 - Union Fee\n");
        System.out.print("8 - None (Return to the Main Menu)\n");
        op = Integer.parseInt(input.nextLine());

        if(op == 1)
        {
            System.out.print("Please, enter the Worker's new name: ");
            String new_name = input.nextLine();

            if(test == 1)
            {
                while(Hourly_names.contains(new_name) && reop == 1)
                {
                    System.out.print("\nName already registered!\n");
                    reop = this.tryAgain();

                    if(reop == 1)
                    {
                        System.out.print("Please, enter the Worker's new name: ");
                        new_name = input.nextLine();
                    }
                }

                if(reop == 2)
                {
                    System.out.print("\nReturning to the Main Menu!\n");
                    return;
                }

                worker_h.setName(new_name);
            }

            if(test == 2)
            {
                while(Salaried_names.contains(new_name) && reop == 1)
                {
                    System.out.print("\nName already registered!\n");
                    reop = this.tryAgain();

                    if(reop == 1)
                    {
                        System.out.print("Please, enter the Worker's new name: ");
                        new_name = input.nextLine();
                    }
                }

                if(reop == 2)
                {
                    System.out.print("\nReturning to the Main Menu!\n");
                    return;
                }

                worker_s.setName(new_name);
            }

            if(test == 3)
            {
                while(Commissioned_names.contains(new_name) && reop == 1)
                {
                    System.out.print("\nName already registered!\n");
                    reop = this.tryAgain();

                    if(reop == 1)
                    {
                        System.out.print("Please, enter the Worker's new name: ");
                        new_name = input.nextLine();
                    }
                }

                if(reop == 2)
                {
                    System.out.print("\nReturning to the Main Menu!\n");
                    return;
                }

                worker_c.setName(new_name);
            }

            System.out.print("\nInformation Changed!\n");
        }

        if(op == 2)
        {
            System.out.print("Please, enter the Worker's new Address: ");
            String newAddress = input.nextLine();

            if(test == 1) worker_h.setAddress(newAddress);
            if(test == 2) worker_s.setAddress(newAddress);
            if(test == 3) worker_c.setAddress(newAddress);

            System.out.print("\nInformation Changed!\n");
        }

        if(op == 3)
        {
            if(test == 1)
            {
                if(worker_h.getWorked_days() != 0)
                {
                    System.out.print("\nThe Worker must be payed before change his type!\n");
                    System.out.print("\nReturning to the Main Menu!\n");
                    return;
                }

                System.out.print("\nWorker is a Hourly Worker, change to:\n");
                System.out.print("1 - Salaried\n");
                System.out.print("2 - Commissioned\n");
                int change = Integer.parseInt(input.nextLine());

                String name1 = worker_h.getName();
                String address = worker_h.getAddress();
                String method = worker_h.getMethod();
                int systemID = worker_h.getSystemID();
                boolean syndicate = worker_h.getSyndicate();
                int syndicateID = worker_h.getSyndicateID();
                double fee = worker_h.getUnion_fee();

                if(change == 1)
                {
                    System.out.print("Please, enter the Worker's new Salary: ");
                    double salary = Double.parseDouble(input.nextLine());

                    Salaried New = new Salaried(name1, address, salary, systemID, method, syndicate, Agendas.get(0), syndicateID, fee);
                    Salarieds.add(New);
                    Salaried_names.add(name1);
                    Hourly_names.remove(name1);
                    Hourlys.remove(worker_h);
                }

                if(change == 2)
                {
                    System.out.print("Please, enter the Worker's new Commission: ");
                    double commission = Double.parseDouble(input.nextLine());

                    Commissioned New = new Commissioned(name1, address, fixed_weekly_salary, commission, systemID, syndicate, method, Agendas.get(2), syndicateID, fee);
                    Commissioneds.add(New);
                    Commissioned_names.add(name1);
                    Hourly_names.remove(name1);
                    Hourlys.remove(worker_h);
                }
            }

            if(test == 2)
            {
                if(worker_s.getWorked_days() != 0)
                {
                    System.out.print("\nThe Worker must be payed before change his type!\n");
                    System.out.print("\nReturning to the Main Menu!\n");
                    return;
                }

                System.out.print("\nWorker is a Salaried Worker, change to:\n");
                System.out.print("1 - Hourly\n");
                System.out.print("2 - Commissioned\n");
                int change = Integer.parseInt(input.nextLine());

                String name1 = worker_s.getName();
                String address = worker_s.getAddress();
                String method = worker_s.getMethod();
                int systemID = worker_s.getSystemID();
                boolean syndicate = worker_s.getSyndicate();
                int syndicateID = worker_s.getSyndicateID();
                double fee = worker_s.getUnion_fee();

                if(change == 1)
                {
                    System.out.print("Please, enter the Worker's new Salary: ");
                    double salary = Double.parseDouble(input.nextLine());

                    Hourly New = new Hourly(name1, address, salary, systemID, method, syndicate, Agendas.get(1), syndicateID, fee);
                    Hourlys.add(New);
                    Hourly_names.add(name1);
                    Salaried_names.remove(name1);
                    Salarieds.remove(worker_s);
                }

                if(change == 2)
                {
                    System.out.print("Please, enter the Worker's new Commission: ");
                    double commission = Double.parseDouble(input.nextLine());

                    Commissioned New = new Commissioned(name1, address, fixed_weekly_salary, commission, systemID, syndicate, method, Agendas.get(2), syndicateID, fee);
                    Commissioneds.add(New);
                    Commissioned_names.add(name1);
                    Salaried_names.remove(name1);
                    Salarieds.remove(worker_s);
                }
            }

            if(test == 3)
            {
                if(worker_s.getWorked_days() != 0)
                {
                    System.out.print("\nThe Worker must be payed before change his type!\n");
                    System.out.print("\nReturning to the Main Menu!\n");
                    return;
                }

                System.out.print("\nWorker is a Commissioned Worker, change to:\n");
                System.out.print("1 - Hourly\n");
                System.out.print("2 - Salaried\n");
                int change = Integer.parseInt(input.nextLine());

                String name1 = worker_c.getName();
                String address = worker_c.getAddress();
                String method = worker_c.getMethod();
                int systemID = worker_c.getSystemID();
                boolean syndicate = worker_c.getSyndicate();
                int syndicateID = worker_c.getSyndicateID();
                double fee = worker_c.getUnion_fee();

                if(change == 1)
                {
                    System.out.print("Please, enter the Worker's new Salary: ");
                    double salary = Double.parseDouble(input.nextLine());

                    Hourly New = new Hourly(name1, address, salary, systemID, method, syndicate, Agendas.get(1), syndicateID, fee);
                    Hourlys.add(New);
                    Hourly_names.add(name1);
                    Commissioned_names.remove(name1);
                    Commissioneds.remove(worker_c);
                }

                if(change == 2)
                {
                    System.out.print("Please, enter the Worker's new Salary: ");
                    double salary = Double.parseDouble(input.nextLine());

                    Salaried New = new Salaried(name1, address, salary, systemID, method, syndicate, Agendas.get(0), syndicateID, fee);
                    Salarieds.add(New);
                    Salaried_names.add(name1);
                    Commissioned_names.remove(name1);
                    Commissioneds.remove(worker_c);
                }
            }

            System.out.print("\nInformation Changed!\n");
        }

        if(op == 4)
        {
            System.out.print("Please, enter the Worker's new Payment Method:\n");
            System.out.print("1 - Paycheck by post office\n");
            System.out.print("2 - Paycheck in hands\n");
            System.out.print("3 - Bank account deposit\n");
            int new_payment_method = Integer.parseInt(input.nextLine());

            if(test == 1) worker_h.setMethod(new_payment_method);
            if(test == 2) worker_s.setMethod(new_payment_method);
            if(test == 3) worker_c.setMethod(new_payment_method);

            System.out.print("\nInformation Changed!\n");
        }

        if(op == 5)
        {
            if(test == 1)
            {
                boolean x = worker_h.getSyndicate();
                if(x)
                {
                    System.out.print("\nWorker is a Union Member!\n");
                    System.out.print("Want to change it?\n");
                    System.out.print("Press 1 to Yes or 2 to No\n");
                    int change = Integer.parseInt(input.nextLine());

                    if(change == 1)
                    {
                        worker_h.setSyndicate(false);
                        worker_h.setSydicate_ID(0);
                        worker_h.setUnion_fee(0);

                        System.out.print("\nInformation Changed!\n");
                    }
                }

                else
                {
                    System.out.print("\nWorker isn't a Union Member!\n");
                    System.out.print("Want to change it?\n");
                    System.out.print("Press 1 to Yes or 2 to No\n");
                    int change = Integer.parseInt(input.nextLine());

                    if(change == 1)
                    {
                        worker_h.setSyndicate(true);
                        System.out.print("\nPlease, enter the Worker's Syndicate ID: ");
                        int ID = Integer.parseInt(input.nextLine());

                        System.out.print("Please, enter the Worker's Union Fee: ");
                        double fee = Double.parseDouble(input.nextLine());

                        worker_h.setSydicate_ID(ID);
                        worker_h.setUnion_fee(fee);

                        System.out.print("\nInformation Changed!\n");
                    }
                }
            }

            if(test == 2)
            {
                boolean x = worker_s.getSyndicate();
                if(x)
                {
                    System.out.print("\nWorker is a Union Member!\n");
                    System.out.print("Want to change it?\n");
                    System.out.print("Press 1 to Yes or 2 to No\n");
                    int change = Integer.parseInt(input.nextLine());

                    if(change == 1)
                    {
                        worker_s.setSyndicate(false);
                        worker_s.setSydicate_ID(0);
                        worker_s.setUnion_fee(0);

                        System.out.print("\nInformation Changed!\n");
                    }
                }

                else
                {
                    System.out.print("\nWorker isn't a Union Member!\n");
                    System.out.print("Want to change it?\n");
                    System.out.print("Press 1 to Yes or 2 to No\n");
                    int change = Integer.parseInt(input.nextLine());

                    if(change == 1)
                    {
                        worker_s.setSyndicate(true);
                        System.out.print("\nPlease, enter the Worker's Syndicate ID: ");
                        int ID = Integer.parseInt(input.nextLine());

                        System.out.print("Please, enter the Worker's Union Fee: ");
                        double fee = Double.parseDouble(input.nextLine());

                        worker_s.setSydicate_ID(ID);
                        worker_s.setUnion_fee(fee);

                        System.out.print("\nInformation Changed!\n");
                    }
                }
            }

            if(test == 3)
            {
                boolean x = worker_c.getSyndicate();
                if(x)
                {
                    System.out.print("\nWorker is a Union Member!\n");
                    System.out.print("Want to change it?\n");
                    System.out.print("Press 1 to Yes or 2 to No\n");
                    int change = Integer.parseInt(input.nextLine());

                    if(change == 1)
                    {
                        worker_c.setSyndicate(false);
                        worker_c.setSydicate_ID(0);
                        worker_c.setUnion_fee(0);

                        System.out.print("\nInformation Changed!\n");
                    }
                }

                else
                {
                    System.out.print("\nWorker isn't a Union Member!\n");
                    System.out.print("Want to change it?\n");
                    System.out.print("Press 1 to Yes or 2 to No\n");
                    int change = Integer.parseInt(input.nextLine());

                    if(change == 1)
                    {
                        worker_c.setSyndicate(true);
                        System.out.print("\nPlease, enter the Worker's Syndicate ID: ");
                        int ID = Integer.parseInt(input.nextLine());

                        System.out.print("Please, enter the Worker's Union Fee: ");
                        double fee = Double.parseDouble(input.nextLine());

                        worker_c.setSydicate_ID(ID);
                        worker_c.setUnion_fee(fee);

                        System.out.print("\nInformation Changed!\n");
                    }
                }
            }
        }

        if(op == 6)
        {
            System.out.print("\nPlease, enter the Worker's new Syndicate ID: ");
            int ID = Integer.parseInt(input.nextLine());

            if(test == 1) worker_h.setSydicate_ID(ID);
            if(test == 2) worker_s.setSydicate_ID(ID);
            if(test == 3) worker_c.setSydicate_ID(ID);

            System.out.print("\nInformation Changed!\n");
        }

        if(op == 7)
        {
            System.out.print("Please, enter the Worker's new Union Fee: ");
            double fee = Double.parseDouble(input.nextLine());

            if (test == 1) worker_h.setUnion_fee(fee);
            if (test == 2) worker_s.setUnion_fee(fee);
            if (test == 3) worker_c.setUnion_fee(fee);

            System.out.print("\nInformation Changed!\n");
        }

        System.out.print("\nReturning to the Main Menu!\n");
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

    public void ChangeAgenda()
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
            System.out.print("\nReturning to the main menu!\n");
            return;
        }

        int index;

        if(Hourly_names.contains(name))
        {
            index = Hourly_names.indexOf(name);
            Hourly worker = Hourlys.get(index);

            if(worker.getWorked_days() > 0)
            {
                System.out.print("\nThe Worker must be payed before change his Payment Schedule!\n");
                System.out.print("\nReturning to the main menu!\n");
                return;
            }

            else
            {
                int i, change = 0;

                System.out.print("\nWant to change to the:\n");

                for(i=0; i<Agendas.size(); i++)
                {
                    System.out.print( Agendas.get(i).getName() + " Payment Schedule?\n");
                    System.out.print("Press 1 to Yes or 2 to No\n");
                    change = Integer.parseInt(input.nextLine());

                    if(change == 1) break;
                }

                if(change == 2)
                {
                    System.out.print("\nReturning to the main menu!\n");
                    return;
                }

                PaymentSchedule newAgenda = Agendas.get(i);

                worker.setAgenda(newAgenda);

                System.out.print("\nPayment Schedule Changed!\n");
                System.out.print("\nReturning to the main menu!\n");
            }
        }

        if(Salaried_names.contains(name))
        {
            index = Salaried_names.indexOf(name);
            Salaried worker = Salarieds.get(index);

            if(worker.getWorked_days() > 0)
            {
                System.out.print("\nThe Worker must be payed before change his Payment Schedule!\n");
                System.out.print("\nReturning to the main menu!\n");
                return;
            }

            else
            {
                int i, change = 0;

                System.out.print("\nWant to change to the:\n");

                for(i=0; i<Agendas.size(); i++)
                {
                    System.out.print( Agendas.get(i).getName() + " Payment Schedule?\n");
                    System.out.print("Press 1 to Yes or 2 to No\n");
                    change = Integer.parseInt(input.nextLine());

                    if(change == 1) break;
                }

                if(change == 2)
                {
                    System.out.print("\nReturning to the main menu!\n");
                    return;
                }

                PaymentSchedule newAgenda = Agendas.get(i);
                worker.setAgenda(newAgenda);

                System.out.print("\nPayment Schedule Changed!\n");
                System.out.print("\nReturning to the main menu!\n");
            }
        }

        if(Commissioned_names.contains(name))
        {
            index = Commissioned_names.indexOf(name);
            Commissioned worker = Commissioneds.get(index);

            if(worker.getWorked_days() > 0)
            {
                System.out.print("\nThe Worker must be payed before change his Payment Schedule!\n");
                System.out.print("\nReturning to the main menu!\n");
                return;
            }

            else
            {
                int i, change = 0;

                System.out.print("\nWant to change to the:\n");

                for(i=0; i<Agendas.size(); i++)
                {
                    System.out.print( Agendas.get(i).getName() + " Payment Schedule?\n");
                    System.out.print("Press 1 to Yes or 2 to No\n");
                    change = Integer.parseInt(input.nextLine());

                    if(change == 1) break;
                }

                if(change == 2)
                {
                    System.out.print("\nReturning to the main menu!\n");
                    return;
                }

                PaymentSchedule newAgenda = Agendas.get(i);
                worker.setAgenda(newAgenda);

                System.out.print("\nPayment Schedule Changed!\n");
                System.out.print("\nReturning to the main menu!\n");
            }
        }
    }

    public void CreateAgenda()
    {
        System.out.print("\nPlease, enter the Payment Schedule title: ");
        String title = input.nextLine();

        System.out.print("Please, enter the amount of days that need to be worked before be payed: ");
        int until_be_payed = Integer.parseInt(input.nextLine());

        System.out.print("Please, enter the payday day of the week (from 2 to 6): ");
        int payday = Integer.parseInt(input.nextLine());

        PaymentSchedule newAgenda = new PaymentSchedule(title, until_be_payed, payday);

        Agendas.add(newAgenda);

        System.out.print("\nPayment Schedule Created!\n");
        System.out.print("\nReturning to the Main Menu!\n");
    }

    public void remove_salaried(Salaried x)
    {
        String name = x.getName();
        Salarieds.remove(x);
        Salaried_names.remove(name);
        Workers_names.remove(name);
    }

    public void remove_commissioned(Commissioned x)
    {
        String name = x.getName();
        Commissioneds.remove(x);
        Commissioned_names.remove(name);
        Workers_names.remove(name);
    }

    public void add_hourly(Hourly x)
    {
        String name = x.getName();
        Hourlys.add(x);
        Hourly_names.add(name);
        Workers_names.add(name);
    }

    public void add_salaried(Salaried x)
    {
        String name = x.getName();
        Salarieds.add(x);
        Salaried_names.add(name);
        Workers_names.add(name);
    }

    public void add_commissioned(Commissioned x)
    {
        String name = x.getName();
        Commissioneds.add(x);
        Commissioned_names.add(name);
        Workers_names.add(name);
    }

    public void remove_tc(TimeCard x)
    {
        Hourly worker = x.getWorker();
        worker.removeTC(x);
    }

    public void remove_sale(Sale sale)
    {
        Commissioned worker = sale.getWorker();
        worker.removeSale(sale);
    }

    public void setHourly(Hourly worker)
    {
        String name = worker.getName();
        int index = Hourly_names.indexOf(name);
        Hourlys.set(index, worker);
    }

    public void setSalaried(Salaried worker)
    {
        String name = worker.getName();
        int index = Salaried_names.indexOf(name);
        Salarieds.set(index, worker);
    }


    public void setCommissioned(Commissioned worker)
    {
        String name = worker.getName();
        int index = Commissioned_names.indexOf(name);
        Commissioneds.set(index, worker);
    }
}

