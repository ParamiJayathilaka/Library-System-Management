package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bookTitle;
    private String nameOfUser;
    private String time;
    private String startDate;
    private String returnDate;
    @ManyToOne
    @JoinColumn
    private User userList;
    @ManyToOne
    @JoinColumn
    private Books bookList;

    public Transaction(long id, String bookTitle, String nameOfUser, String time, String startDate, String returnDate) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.nameOfUser = nameOfUser;
        this.time = time;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }
}
