package com.example.cyclestationsemal.Admin.cycle;

public class Cycle {
    String cid;
    String station;
    String status;
    int cycleRegNo;
    String imageUrl;

    Cycle() {

    }

    public Cycle(String cid, String station, String status, int cycleRegNo, String imageUrl) {
        this.cid = cid;
        this.station = station;
        this.status = status;
        this.cycleRegNo = cycleRegNo;
        this.imageUrl = imageUrl;
    }

    public String toString() { return "STATION : "+this.station + ", CYCLE REG NO : " +this.cycleRegNo ;}

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCycleRegNo() {
        return cycleRegNo;
    }

    public void setCycleRegNo(int cycleRegNo) {
        this.cycleRegNo = cycleRegNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
