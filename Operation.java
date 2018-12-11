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
    private ArrayList<ServiceFee> servicefees_casted = new ArrayList<ServiceFee>();
    private ArrayList<Hourly> hourlys_unchanged = new ArrayList<Hourly>();
    private ArrayList<Commissioned> commissioneds_unchanged = new ArrayList<Commissioned>();
    private ArrayList<Salaried> salarieds_unchanged = new ArrayList<Salaried>();
    private ArrayList<Hourly> payed_hourlys = new ArrayList<Hourly>();
    private ArrayList<Commissioned> payed_commissioneds = new ArrayList<Commissioned>();
    private ArrayList<Salaried> payed_salarieds = new ArrayList<Salaried>();
    private ArrayList<String> operations = new ArrayList<String>();

    public void addHourly(Hourly x)
    {
        hourlys_added.add(x);
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
            operations.remove(size_op-1);
        }

    }

}
