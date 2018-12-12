

public class TimeCard {

    Hourly worker;
    int enter_hour;
    int enter_minute;
    int leave_hour;
    int leave_minute;
    int day_of_week;
    int day;
    int month;
    int year;
    int worked_hours;
    double how_much_to_receive;

    public TimeCard(Hourly worker, int e_h, int e_m, int l_h, int l_m, int d_o_w, int day, int month, int year)
    {
        this.worker = worker;
        this.enter_hour = e_h;
        this.enter_minute = e_m;
        this.leave_hour = l_h;
        this.leave_minute = l_m;
        this.day_of_week = d_o_w;
        this.day = day;
        this.month = month;
        this.year = year;
        this.worked_hours = leave_hour - enter_hour;
        if(worked_hours > 8)
        {
            how_much_to_receive = 8*worker.getSalary() + (worked_hours - 8)*1.5*worker.getSalary();
        }
        else how_much_to_receive = worked_hours*worker.getSalary();
    }

    public double getHow_much_to_receive()
    {
        return how_much_to_receive;
    }

    public int getDay()
    {
        return day;
    }

    public Hourly getWorker()
    {
        return this.worker;
    }

}
