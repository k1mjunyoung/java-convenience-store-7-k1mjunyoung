package store.model;

import java.time.LocalDate;
import java.util.Date;

public class Promotion {
    //name,buy,get,start_date,end_date
    private String name;
    private Integer buy;
    private Integer get;
    private LocalDate start_date;
    private LocalDate end_date;

    public Promotion(String name, Integer buy, Integer get, LocalDate start_date, LocalDate end_date) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getName() {
        return name;
    }

    public Integer getBuy() {
        return buy;
    }

    public Integer getGet() {
        return get;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public Boolean isValidPromotion() {
        LocalDate today = LocalDate.now();

        if (today.isAfter(start_date) || today.isBefore(end_date)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "name='" + name + '\'' +
                ", buy=" + buy +
                ", get=" + get +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                "}\n";
    }
}
