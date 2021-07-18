package entity;
import java.sql.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hoang
 */
@Getter
@Setter
@ToString
@Builder
public class Day_View {
    private Date date;
    private int views;
}
