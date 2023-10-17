package app.abstractData;
import java.time.LocalDate;

public class Date {
    private int year;
    private int month;
    private int day;
    private LocalDate date;

    public Date(LocalDate date)
    {
        LocalDate someDate = date;
        if(date == null){someDate = LocalDate.now();}
        this.date = someDate;
        this.year = someDate.getYear();
        this.month = someDate.getMonthValue();
        this.day = someDate.getDayOfMonth();
    }

    public String getStringYear()
    {
        return convertSingleIntegerToDoubleString01(this.year);
    }

    public String getStringMonth()
    {
        return convertSingleIntegerToDoubleString01(this.month);
    }

    public String getStringDay()
    {
        return convertSingleIntegerToDoubleString01(this.day);
    }

    public int getIntValueOfDate()
    {
        String fullDate = this.getStringYear() + this.getStringMonth() + this.getStringDay();
        return Integer.parseInt(fullDate);
    }

    public String getDateInStringForm()
    {
        String fullDate = this.getStringYear() + "/" + this.getStringMonth() + "/" + this.getStringDay();
        return fullDate;
    }

    public Date lastMonthDate()
    {
        LocalDate newLocalDate = this.date.minusMonths(1);
        Date newDate = new Date(newLocalDate);
        return newDate;
    }
    
    private String convertSingleIntegerToDoubleString01(int integer)
    {
        String integeString = Integer.toString(integer);
        if(integer < 10 && integer > -10)
        {
            return "0" + integeString;
        }
        return integeString;
    }


}
