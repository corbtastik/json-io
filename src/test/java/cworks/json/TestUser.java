package cworks.json;

public class TestUser {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String ipAddress;

    public TestUser() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String toString() {
        return this.id + " " + this.firstName + " " + this.lastName;
    }
    
    public boolean equals(Object other) {
        if(other == this) {
            return true;
        }
        if(!(other instanceof TestUser)) {
            return false;
        }
        TestUser otherUser = (TestUser)other;
        return (otherUser.id.intValue() == this.id.intValue() &&
           otherUser.firstName.equals(this.firstName) &&
           otherUser.lastName.equals(this.lastName) &&
           otherUser.email.equals(this.email) &&
           otherUser.ipAddress.equals(this.ipAddress) &&
           otherUser.country.equals(this.country));
    }
}
