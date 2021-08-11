package client.model;


import javafx.scene.control.CheckBox;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Contract {

    private Integer number;
    private Calendar createdDate;
    private Calendar lastUpdatedDate;
    private CheckBox select;

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public Contract() {
        this.select = new CheckBox();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCreatedDate() {
        return format.format(createdDate.getTime());
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedDate() {
        return format.format(lastUpdatedDate.getTime());
    }

    public void setLastUpdatedDate(Calendar lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        updateSelect();
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect() {
        this.select = select;
    }

    private void updateSelect() {
        long period = 5184000000L;
        Calendar now = new GregorianCalendar();


        if (now.getTime().getTime() - lastUpdatedDate.getTime().getTime() < period) {
            select.setSelected(true);
        }
    }


}


