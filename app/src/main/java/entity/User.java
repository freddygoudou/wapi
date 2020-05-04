package entity;

public class User {

    private String id;
    private String name;
    private String phoneNumber;
    private String langue;

    public User(String id, String name,String phoneNumber, String langue) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.langue = langue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", langue='" + langue + '\'' +
                '}';
    }
}
