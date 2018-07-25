package com.treebricks.dokuter.models;

import com.google.gson.annotations.SerializedName;

public class Patient {

    @SerializedName("patientUUID")
    private String patientUUID;

    @SerializedName("gender")
    private String gender;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("dob")
    private String dob;

    @SerializedName("patient_name")
    private String patientName;

    @SerializedName("blood_group")
    private String bloodGroup;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    public void setPatientUUID(String patientUUID) {
        this.patientUUID = patientUUID;
    }

    public String getPatientUUID() {
        return patientUUID;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "Patient{" +
                        "patientUUID = '" + patientUUID + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",dob = '" + dob + '\'' +
                        ",patient_name = '" + patientName + '\'' +
                        ",blood_group = '" + bloodGroup + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}