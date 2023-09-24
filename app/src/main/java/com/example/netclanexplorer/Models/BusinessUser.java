package com.example.netclanexplorer.Models;

public class BusinessUser {
    private String name, age, gender, contact, about, location, distanceRange, occupation, profileProgress, experience;
    private int profilePicture;

    public BusinessUser(String name, String age, String gender, String contact, String about, String location, String distanceRange, String occupation, String profileProgress, int profilePicture, String experience) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.about = about;
        this.location = location;
        this.distanceRange = distanceRange;
        this.occupation = occupation;
        this.profileProgress = profileProgress;
        this.profilePicture = profilePicture;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
