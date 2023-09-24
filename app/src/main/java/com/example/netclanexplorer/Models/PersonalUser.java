package com.example.netclanexplorer.Models;

public class PersonalUser {
    private String name, age, gender, contact, about, location, education, work, occupation, distanceRange, otherDetails, profileProgress;
    private int profilePicture;
    private String[] hobbies, movies;

    public PersonalUser(String name, String age, String gender, String contact, String otherDetails, String profileProgress, int profilePicture, String about, String location, String education, String work, String occupation, String distanceRange, String[] hobbies, String[] movies) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.otherDetails = otherDetails;
        this.profileProgress = profileProgress;
        this.profilePicture = profilePicture;
        this.about = about;
        this.location = location;
        this.education = education;
        this.work = work;
        this.occupation = occupation;
        this.distanceRange = distanceRange;
        this.hobbies = hobbies;
        this.movies = movies;
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

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDistanceRange() {
        return distanceRange;
    }

    public void setDistanceRange(String distanceRange) {
        this.distanceRange = distanceRange;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public String[] getMovies() {
        return movies;
    }

    public void setMovies(String[] movies) {
        this.movies = movies;
    }
}
