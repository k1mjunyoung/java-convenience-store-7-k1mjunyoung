package store.model;

import java.time.LocalDate;

public class Promotion {
    //name,buy,get,start_date,end_date
    private String name;
    private Integer buy;
    private Integer get;
    private LocalDate startDate;
    private LocalDate endDate;

    public Promotion(String name, Integer buy, Integer get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Boolean isValidPromotion() {
        LocalDate today = LocalDate.now();

        if (today.isAfter(startDate) || today.isBefore(endDate)) {
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
                ", start_date=" + startDate +
                ", end_date=" + endDate +
                "}\n";
    }
}
