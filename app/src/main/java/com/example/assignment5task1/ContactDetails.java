package com.example.assignment5task1;

public class ContactDetails
{

    private int ContactID;
    private String ContactName;
    private String ContactNumber;
    private String ContactEmail;
    private String ContactCategory;


    public ContactDetails()
    {

    }


    public ContactDetails(int contactID, String contactName, String contactNumber, String contactEmail, String contactCategory) {
        ContactID = contactID;
        ContactName = contactName;
        ContactNumber = contactNumber;
        ContactEmail = contactEmail;
        ContactCategory = contactCategory;
    }


    public ContactDetails(String contactName, String contactNumber, String contactEmail,  String contactCategory)
    {
        ContactName = contactName;
        ContactNumber = contactNumber;
        ContactEmail = contactEmail;
        ContactCategory = contactCategory;
    }

    public String getContactCategory() {
        return ContactCategory;
    }

    public void setContactCategory(String contactCategory) {
        ContactCategory = contactCategory;
    }

    public int getContactID() {
        return ContactID;
    }

    public void setContactID(int contactID) {
        ContactID = contactID;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getContactEmail() {
        return ContactEmail;
    }

    public void setContactEmail(String contactEmail) {
        ContactEmail = contactEmail;
    }


}
