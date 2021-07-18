/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
@ToString
@Builder
public class History {
    private int accountId;
    private int bookId;
    private int chapterId;
    private Calendar date;
    public String getHistoryDate(){
        String month = date.get(Calendar.MONTH)+1+"";
        return date.get(Calendar.DAY_OF_MONTH)+"-"+month+"-"+date.get(Calendar.YEAR);
    }
}
