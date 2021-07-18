/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
public class Product {
    private int id;
    private String name;
    private int authorId;
    private int translatorId;
    private int totalChap;
    private int totalLike;
    private int totalView;
    private String status;
    private String description;
    private String imgName;
}
