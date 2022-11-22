package com.example.malibupharmacy;

public class User {
    String AdditionalInfo, Allergies, DeliveryInfo, InsuranceInfo,Tests,UnderlyingConditions,UserInformation;

    public User(String additionalInfo, String allergies, String deliveryInfo, String insuranceInfo, String tests, String underlyingConditions, String userInformation) {
        AdditionalInfo = additionalInfo;
        Allergies = allergies;
        DeliveryInfo = deliveryInfo;
        InsuranceInfo = insuranceInfo;
        Tests = tests;
        UnderlyingConditions = underlyingConditions;
        UserInformation = userInformation;
    }

    public String getAdditionalInfo() {
        return AdditionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        AdditionalInfo = additionalInfo;
    }

    public String getAllergies() {
        return Allergies;
    }

    public void setAllergies(String allergies) {
        Allergies = allergies;
    }

    public String getDeliveryInfo() {
        return DeliveryInfo;
    }

    public void setDeliveryInfo(String deliveryInfo) {
        DeliveryInfo = deliveryInfo;
    }

    public String getInsuranceInfo() {
        return InsuranceInfo;
    }

    public void setInsuranceInfo(String insuranceInfo) {
        InsuranceInfo = insuranceInfo;
    }

    public String getTests() {
        return Tests;
    }

    public void setTests(String tests) {
        Tests = tests;
    }

    public String getUnderlyingConditions() {
        return UnderlyingConditions;
    }

    public void setUnderlyingConditions(String underlyingConditions) {
        UnderlyingConditions = underlyingConditions;
    }

    public String getUserInformation() {
        return UserInformation;
    }

    public void setUserInformation(String userInformation) {
        UserInformation = userInformation;
    }
}
