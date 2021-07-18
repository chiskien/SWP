/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.CategoryDao;
import java.util.List;
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
public class Book {
    private int id;
    private String name;
    private int authorId;
    private int translatorId;
    private int totalChap;
    private int appear;
    private int totalView;
    private int totalLike;
    private int totalFollow;
    private int totalComment;
    private String status;
    private String description;
    private String imgName;
    private String latestUpdate;
    private String bookmarkedChapter;
    private int bookmarkedChapterId;
    
    public Book() {
    }

    public Book(int id, String name, int authorId, int translatorId, int totalChap, int appear, int totalView, int totalLike, int totalFollow, int totalComment, String status, String description, String imgName, String latestUpdate) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.translatorId = translatorId;
        this.totalChap = totalChap;
        this.appear = appear;
        this.totalView = totalView;
        this.totalLike = totalLike;
        this.totalFollow = totalFollow;
        this.totalComment = totalComment;
        this.status = status;
        this.description = description;
        this.imgName = imgName;
        this.latestUpdate = latestUpdate;
    }
    public Book(int id, String name, int authorId, int translatorId, int totalChap, int appear, int totalView, int totalLike, int totalFollow, int totalComment, String status, String description, String imgName, String latestUpdate,String bookmarkedChapter, int bookmarkedChapterId){
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.translatorId = translatorId;
        this.totalChap = totalChap;
        this.appear = appear;
        this.totalView = totalView;
        this.totalLike = totalLike;
        this.totalFollow = totalFollow;
        this.totalComment = totalComment;
        this.status = status;
        this.description = description;
        this.imgName = imgName;
        this.latestUpdate = latestUpdate;
        this.bookmarkedChapter = bookmarkedChapter;
        this.bookmarkedChapterId = bookmarkedChapterId;
    }
    
    public List<Category> getAllCategory(){
        List<Category> ls = new CategoryDao().getAllWithBookId(id);
        return ls;
    }
    public String getBookMarkChapter(){
        return null;
    }
}
