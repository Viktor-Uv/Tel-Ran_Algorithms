package lesson7.homework4;

public class Persons {
    private String name;
    private Address address;

    public Persons(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person: {name='" + name + '\'' +
                ", " + address +
                '}';
    }
}
