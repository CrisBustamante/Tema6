package FunkoAdministratorSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Funko {
    private String cod;
    private String name;
    private String company;
    private String price;
    private Date date;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public String getPrice() {
        return price;
    }
    public String getCod() {
        return cod;
    }
    public String getName() {
        return name;
    }
    public String getCompany() {
        return company;
    }
    public String getDate() {
        return format.format(date);
    }

    public Funko(String cod, String name, String company, String price, String date) {
        this.cod = cod;
        this.name = name;
        this.company = company;
        this.price = price;
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            System.out.println("Error al parsear la fecha: " + e.getMessage());
        }
    }



    @Override
    public String toString() {
        return cod + "," + name + "," + company + "," + price + "," + format.format(date);
    }
}