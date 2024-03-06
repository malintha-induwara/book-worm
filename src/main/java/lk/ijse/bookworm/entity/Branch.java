package lk.ijse.bookworm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @Column(name = "branch_id",length = 30)
    private String branchID;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_location")
    private String branchLocation;

    @ManyToOne
    @JoinColumn(name = "username",referencedColumnName = "username")
    private Admin admin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER ,mappedBy = "branch")
    private List<Book> books = new ArrayList<>();


    public Branch() {
    }

    public Branch(String branchID, String branchName, String branchLocation, Admin admin, List<Book> books) {
        this.branchID = branchID;
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.admin = admin;
        this.books = books;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchID='" + branchID + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchLocation='" + branchLocation + '\'' +
                ", admin=" + admin +
                ", books=" + books +
                '}';
    }


}

