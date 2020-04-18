package com.example.cyclestationsemal.Admin.station;

public class Station {

    String sid;
    public String stationName;
    public double latitude;
    public double longitude;
    String description;
    String openingTime;
    String closingTime;
    String conductedBy;
    int noOfCycle;
    int availableCycle;

    Station(){

    }

    public String toString(){ return "STATION NAME : "+this.stationName + ", DESCRIPTION : "+this.description; }

    public Station(String sid, String stationName, double latitude, double longitude, String description, String openingTime, String closingTime, String conductedBy, int noOfCycle, int availableCycle) {
        this.sid = sid;
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.conductedBy = conductedBy;
        this.noOfCycle = noOfCycle;
        this.availableCycle = availableCycle;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getConductedBy() {
        return conductedBy;
    }

    public void setConductedBy(String conductedB) {
        this.conductedBy = conductedB;
    }

    public int getNoOfCycle() {
        return noOfCycle;
    }

    public void setNoOfCycle(int noOfCycle) {
        this.noOfCycle = noOfCycle;
    }

    public int getAvailableCycle() {
        return availableCycle;
    }

    public void setAvailableCycle(int availableCycle) {
        this.availableCycle = availableCycle;
    }
}
