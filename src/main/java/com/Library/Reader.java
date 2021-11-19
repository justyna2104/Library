package com.Library;

import com.sun.istack.Nullable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "peselNumber")
    private Long peselNumber;

    @Nullable
    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Nullable
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "reader")
    private Set<CheckOut> checkOuts;

    @Column(name = "birthday")
    private LocalDate birthday;

    public Reader(String firstname, String lastname, Long PESEL, Long phoneNumber, String address, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.peselNumber = PESEL;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
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


    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
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


    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Long getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(Long peselNumber) {
        this.peselNumber = peselNumber;
    }

    @Override
    public String toString() {
        return "Reader: " +
                  firstname +
                " " + lastname +
                ", phoneNumber: " + phoneNumber +
                ", address: " + address;
                //", date of birth (YYYY/MM/DD): " + birthday;
    }
}
