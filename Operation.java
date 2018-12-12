import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;

public class Operation {

    private ArrayList<Hourly> hourlys_added = new ArrayList<Hourly>();
    private ArrayList<Commissioned> commissioneds_added = new ArrayList<Commissioned>();
    private ArrayList<Salaried> salarieds_added = new ArrayList<Salaried>();
    private ArrayList<Hourly> hourlys_removed = new ArrayList<Hourly>();
    private ArrayList<Commissioned> commissioneds_removed = new ArrayList<Commissioned>();
    private ArrayList<Salaried> salarieds_removed = new ArrayList<Salaried>();
    private ArrayList<TimeCard> timecards_casted = new ArrayList<TimeCard>();
    private ArrayList<Sale> sales_casted = new ArrayList<Sale>();
    private ArrayList<String> operations = new ArrayList<String>();

    public void addHourly(Hourly x)
    {
        hourlys_added.add(x);
    }

    public void addCommissioned(Commissioned x)
    {
        commissioneds_added.add(x);
    }

    public void addSalaried(Salaried x)
    {
        salarieds_added.add(x);
    }

    public void removeHourly(Hourly x)
    {
        hourlys_removed.add(x);
    }

    public void removeCommissioned(Commissioned x)
    {
        commissioneds_removed.add(x);
    }

    public void removeSalaried(Salaried x)
    {
        salarieds_removed.add(x);
    }

    public void castTC(TimeCard x)
    {
        timecards_casted.add(x);
    }

    public void castSale(Sale x)
    {
        sales_casted.add(x);
    }

    public void addOperation(String x)
    {
        operations.add(x);
    }

    public void Undo(DataCenter dataCenter)
    {
        if(!operations.isEmpty())
        {
            int size_op = operations.size();
            if(Objects.equals(operations.get(size_op-1), "Add_Hourly"))
            {
                int size_h = hourlys_added.size();
                Hourly worker = hourlys_added.get(size_h-1);
                dataCenter.remove_hourly(worker);
                hourlys_added.remove(worker);
            }

            else if(Objects.equals(operations.get(size_op-1), "Add_Salaried"))
            {
                int size_s = salarieds_added.size();
                Salaried worker = salarieds_added.get(size_s-1);
                dataCenter.remove_salaried(worker);
                salarieds_added.remove(worker);
            }

            else if(Objects.equals(operations.get(size_op-1), "Add_Commissioned"))
            {
                int size_c = commissioneds_added.size();
                Commissioned worker = commissioneds_added.get(size_c-1);
                dataCenter.remove_commissioned(worker);
                commissioneds_added.remove(worker);
            }

            else if(Objects.equals(operations.get(size_op-1), "Remove_Hourly"))
            {
                int size_h = hourlys_removed.size();
                Hourly worker = hourlys_removed.get(size_h-1);
                dataCenter.add_hourly(worker);
                hourlys_removed.remove(worker);
            }

            else if(Objects.equals(operations.get(size_op-1), "Remove_Salaried"))
            {
                int size_s = salarieds_removed.size();
                Salaried worker = salarieds_removed.get(size_s-1);
                dataCenter.add_salaried(worker);
                salarieds_removed.remove(worker);
            }

            else if(Objects.equals(operations.get(size_op-1), "Remove_Commissioned"))
            {
                int size_c = commissioneds_removed.size();
                Commissioned worker = commissioneds_removed.get(size_c-1);
                dataCenter.add_commissioned(worker);
                commissioneds_removed.remove(worker);
            }

            else if(Objects.equals(operations.get(size_op-1), "Cast_Time_Card"))
            {
                int size_tc = timecards_casted.size();
                TimeCard tc = timecards_casted.get(size_tc-1);
                dataCenter.remove_tc(tc);
                timecards_casted.remove(tc);
            }

            else if(Objects.equals(operations.get(size_op-1), "Cast_Sale"))
            {
                int size_sale = sales_casted.size();
                Sale sale = sales_casted.get(size_sale-1);
                dataCenter.remove_sale(sale);
                sales_casted.remove(sale);
            }

            operations.remove(size_op-1);
            System.out.print("\nOperation Undone!\n");
        }

        else System.out.print("\nOperations Stack is Empty!\n");

    }

}
