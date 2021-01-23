package com.example.smarthomebeta;

import android.util.Log;

import static com.example.smarthomebeta.Methods.dateAndTimeTostring;

public class Contact {
    private String contactFamily;
    private String contactName;
    private String contactEmail;
    private String contactCellularNum;
    private boolean contactIsActive;
    private String contactLastUpdated;
    private Address contactAddress;

    public Contact(){}
    public Contact(String contactFamily, String contactName, String contactEmail, String contactCellularNum,
                    Address address){
        contactIsActive = true;
        this.contactFamily = contactFamily;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactCellularNum = contactCellularNum;
        this.contactIsActive=true;
        this.contactLastUpdated = dateAndTimeTostring();
        this.contactAddress = address;
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

    public boolean isContactIsActive() {
        return contactIsActive;
    }

    public void setContactIsActive(boolean contactIsActive) {
        this.contactIsActive = contactIsActive;
    }

    public String getContactLastUpdated() {
        return contactLastUpdated;
    }

    public void setContactLastUpdated(String contactLastUpdated) {
        this.contactLastUpdated = contactLastUpdated;
    }

    public Address getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(Address contactAddress) {
        this.contactAddress = contactAddress;
    }
}


