/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Calendar;
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
@Builder
@ToString
public class Comment implements Comparable<Comment> {

    private int commentId;
    private int bookId;
    private int chapterId;
    private Account account;
    private String content;
    private Date date;

    @Override
    public int compareTo(Comment t) {
        if (this.getPostTime().equals("New!") && t.getPostTime().equals("New!")) {
            if(this.commentId>t.getCommentId())return -1;
            return 1;
        }

        return -this.date.compareTo(t.getDate());
    }

    public String getPostTime() {
        Calendar latestChapterDate = Calendar.getInstance();
        latestChapterDate.setTime(date);
        Calendar cur = Calendar.getInstance();
        long dif = (long) ((cur.getTimeInMillis() - latestChapterDate.getTimeInMillis()) / 999.999);

        int year = (int) (dif / 60 / 60 / 24 / 365);
        if (year > 0) {
            if (year > 1) {
                return year + " years";
            } else {
                return "1 year";
            }
        }
        int month = (int) (dif / 60 / 60 / 24 / 30);
        if (month > 0) {
            if (month > 1) {
                return month + " months";
            } else {
                return "1 month";
            }
        }
        int day = (int) (dif / 60 / 60 / 24);
        if (day > 0) {
            if (day > 1) {
                return day + " days";
            } else {
                return "1 day";
            }
        }
//        int hour = (int) (dif/60/60);
//        if(hour > 0){
//            if(hour>1){
//                return hour + " hours";
//            }
//            else{
//                return "1 hour";
//            }
//        }
//        int minute = (int) (dif/60);
//        if(minute > 0){
//            if(minute>1){
//                return minute+" minutes";
//            }
//            else{
//                return "1 minutes";
//            }
//        }
        return "New!";
    }
}
