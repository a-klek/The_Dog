/**
 * time
 */
public class Time {
    public int day;
    public int hour;

    public Time()
    {
        day = 1;
        hour = 8;
    }

    public void add(int n)
    {
        int k = hour + n - 24;
        if (k>=0)
        {
            day+=1;
            hour = k;
        }
        else
            hour += n;
    }

    public void showTime()
    {
        String s = "day: "+Integer.toString(day);
        System.out.println(s);
        s="hour: "+Integer.toString(hour);
        System.out.println(s);
    }

    public void newDay()
    {
        hour = 8;
        day+=1;
        System.out.println("Начался новый день");
    }
}
