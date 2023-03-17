package org.example.api.pojo;

public class PartialUpdateBooking {

    private int totalprice;

    public PartialUpdateBooking(int totalprice) {
        this.totalprice = totalprice;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
