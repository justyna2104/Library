package com.Library;

import com.sun.istack.Nullable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "reader")
public class Reader {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Nullable
    @Column(name = "phoneNumber")
    private Integer phoneNumber;

    @Nullable
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "reader")
    private Set<CheckOut> checkOuts;

    @Column(name = "birthday")
    private Date birthday;

    public Reader(String firstname, String lastname, Boolean isAbleToCheckOut, int phoneNumber, String address, Set<CheckOut> checkOuts) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.checkOuts = checkOuts;
    }

    public Reader(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<CheckOut> getCheckOuts() {
        return checkOuts;
    }

    public void setCheckOuts(Set<CheckOut> checkOuts) {
        this.checkOuts = checkOuts;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Reader: " +
                "firstname: " + firstname +
                ", lastname: " + lastname +
                ", phoneNumber: " + phoneNumber +
                ", address: " + address +
                ", date of birth (YYYY/MM/DD): " + birthday;
    }
}
