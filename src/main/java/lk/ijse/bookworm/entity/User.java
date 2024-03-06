package lk.ijse.bookworm.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "email",length = 30)
    private String email;


    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;


    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<BookTransactions> bookTransactions = new ArrayList<>();


    public User() {
    }

    public User(String email, String name, String address, String password) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BookTransactions> getBookTransactions() {
        return bookTransactions;
    }

    public void setBookTransactions(List<BookTransactions> bookTransactions) {
        this.bookTransactions = bookTransactions;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", bookTransactions=" + bookTransactions +
                '}';
    }
}

