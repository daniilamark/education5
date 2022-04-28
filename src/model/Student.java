package model;

public class Student {

    private String family;
    private String name;
    private String lastName;
    private String birthday;

    public Student(String family, String name, String lastName, String birthday) {
        this.family = family;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }
}
