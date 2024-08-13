package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTM {
    private long id;
    private String bookTitle;
    private String nameOfUSer;
    private String time;
    private String startDate;
    private String returnDate;
}
