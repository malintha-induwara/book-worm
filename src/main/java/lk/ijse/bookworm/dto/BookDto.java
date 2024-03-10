package lk.ijse.bookworm.dto;

public class BookDto {
    private  String bookId;
    private String author;
    private String genre;
    private String title;
    private String branchId;


    public BookDto() {
    }

    public BookDto(String bookId, String author, String genre, String title, String branchId) {
        this.bookId = bookId;
        this.author = author;
        this.genre = genre;
        this.title = title;
        this.branchId = branchId;
    }


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}

