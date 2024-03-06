package lk.ijse.bookworm.embeddad;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class BookTransactionsPK implements Serializable {
    @Column(name = "book_id",length = 30)
    private String bookId;
    @Column(name = "user_id",length = 30)
    private String userId;
}

