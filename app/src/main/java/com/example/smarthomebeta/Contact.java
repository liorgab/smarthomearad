package com.example.smarthomebeta;

import android.util.Log;

import static com.example.smarthomebeta.Methods.dateAndTimeTostring;

public class Contact extends Address {
    private String contactFamily;
    private String contactName;
    private String contactEmail;
    private String contactCellularNum;
    private String contactPhoneNum;
    private String contactCity;
    private String contactStreet;
    private String contactStreetNum;
    private String contactApartmentNum;
    private String contactFloor;
    private String contactLastUpdated;
    private boolean contactIsActive;

    public Contact(){}
    public Contact(String contactFamily, String contactName, String contactEmail, String contactCellularNum, String contactPhoneNum,
                   String contactCity, String contactStreet, String contactStreetNum, String contactApartmentNum, String contactFloor){
        contactIsActive = true;
        this.contactFamily = contactFamily;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactCellularNum = contactCellularNum;
        this.contactPhoneNum = contactPhoneNum;
        this.contactCity = contactCity;
        this.contactStreet = contactStreet;
        this.contactStreetNum = contactStreetNum;
        this.contactApartmentNum = contactApartmentNum;
        this.contactFloor = contactFloor;

    }

    public String getContactFamily() {
        return contactFamily;
    }

    public void setContactFamily(String contactFamily) {
        this.contactFamily = contactFamily;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactCellularNum() {
        return contactCellularNum;
    }

    public void setContactCellularNum(String contactCellularNum) {
        this.contactCellularNum = contactCellularNum;
    }

    public String getContactCity() {
        return contactCity;
    }

    public void setContactCity(String contactCity) {
        this.contactCity = contactCity;
    }

    public String getContactStreet() {
        return contactStreet;
    }

    public void setContactStreet(String contactStreet) {
        this.contactStreet = contactStreet;
    }

    public String getContactStreetNum() {
        return contactStreetNum;
    }

    public void setContactStreetNum(String contactStreetNum) {
        this.contactStreetNum = contactStreetNum;
    }

    public String getContactApartmentNumber() {
        return contactApartmentNum;
    }

    public void setContactApartmentNumber(String contactApartmentNumber) {
        this.contactApartmentNum = contactApartmentNumber;
    }

    public String getContactFloor() {
        return contactFloor;
    }

    public void setContactFloor(String contactFloor) {
        this.contactFloor = contactFloor;
    }

    public String getContactLastUpdated() {
        return contactLastUpdated;
    }

    public void setContactLastUpdated(String contactLastUpdated) {
        this.contactLastUpdated = contactLastUpdated;
    }

    public boolean isContactIsActive() {
        return contactIsActive;
    }

    public void setContactIsActive(boolean contactIsActive) {
        this.contactIsActive = contactIsActive;
    }

    public String getContactPhoneNum() {
        return contactPhoneNum;
    }

    public void setContactPhoneNum(String contactPhoneNum) {
        this.contactPhoneNum = contactPhoneNum;
    }

    public String getContactApartmentNum() {
        return contactApartmentNum;
    }

    public void setContactApartmentNum(String contactApartmentNum) {
        this.contactApartmentNum = contactApartmentNum;
    }
}


