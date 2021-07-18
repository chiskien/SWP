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
@Setter
@Getter
@ToString
@Builder
public class Chapter implements Comparable<Chapter>{
    private int bookId;
    private int chapterId;
    private String chapterName;
    private Calendar dateOfPublic;
    private int status;
    private int view;
    public String getDate(){
        String a = dateOfPublic.get(Calendar.MONTH)+1+"";
        return dateOfPublic.get(Calendar.DAY_OF_MONTH)+"-"+a+"-"+dateOfPublic.get(Calendar.YEAR);
    }

    
    @Override
    public int compareTo(Chapter t) {
        if(this.chapterId>t.chapterId)return 1;
        else if(this.chapterId<t.chapterId) return -1;
        else return 0;
    }
}
