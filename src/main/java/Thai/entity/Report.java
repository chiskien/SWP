/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thai.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 *
 * @author thaip
 */
@Builder
@Getter
@Setter
@ToString
public class Report {
    private int reportId;
    private int accountId;
    private int bookId;
    private int commentId;
    private String content;
    private int status;
    private String type;

    public Report() {
    }

    public Report(int reportId, int accountId, int bookId, int commentId, String content, int status,String type) {
        this.reportId = reportId;
        this.accountId = accountId;
        this.bookId = bookId;
        this.commentId = commentId;
        this.content = content;
        this.status = status;
        this.type = type;
    }
    
}
