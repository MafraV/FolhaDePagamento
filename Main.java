import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        DataCenter dataCenter = new DataCenter();

        int op = 1;

        System.out.print("\nWelcome!\n");
        dataCenter.print_date();

        while(op != 12)
        {
            System.out.print("\nWhat do you want to do?\n");
            System.out.print("1 - Add a Worker to the Company\n");
            System.out.print("2 - Remove a Worker from the Company\n");
            System.out.print("3 - Cast Point Card\n");
            System.out.print("4 - Cast a Sale\n");
            System.out.print("5 - Cast Service Fee\n");
            System.out.print("6 - Change Worker's Information\n");
            System.out.print("7 - Run Payroll\n");
            System.out.print("8 - Undo\n");
            System.out.print("9 - Change Payment Schedule\n");
            System.out.print("10 - Create a Payment Schedule\n");
            System.out.print("11 - Pass day\n");
            System.out.print("12 - Turn off the System (Stop Program)\n");
            op = Integer.parseInt(input.nextLine());

            if(op == 1) dataCenter.add_worker();

            if(op == 2) dataCenter.remove_worker();

            if(op == 3) dataCenter.castPC();

            if(op == 4) dataCenter.castSale();

            if(op == 5) dataCenter.castServiceFee();

            if(op == 6) dataCenter.changeInfo();

            if(op == 7) dataCenter.PayRoll();

            if(op == 8) dataCenter.Undo();

            if(op == 9) dataCenter.ChangeAgenda();

            if(op == 10) dataCenter.CreateAgenda();

            if(op == 11) dataCenter.pass_day();

        }

    }
}
