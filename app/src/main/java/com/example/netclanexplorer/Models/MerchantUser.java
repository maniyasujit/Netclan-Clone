package com.example.netclanexplorer.Models;

public class MerchantUser {
    private String name, contact, about, location, distanceRange, profileProgress;
    private int profilePicture;

    public MerchantUser(String name, String contact, String about, String location, String distanceRange, String profileProgress, int profilePicture) {
        this.name = name;
        this.contact = contact;
        this.about = about;
        this.location = location;
        this.distanceRange = distanceRange;
        this.profileProgress = profileProgress;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistanceRange() {
        return distanceRange;
    }

    public void setDistanceRange(String distanceRange) {
        this.distanceRange = distanceRange;
    }

    public String getProfileProgress() {
        return profileProgress;
    }

    public void setProfileProgress(String profileProgress) {
        this.profileProgress = profileProgress;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }
}
