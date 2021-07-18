/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
@ToString
@Builder
public class Category {

    private int categoryId;
    private String categoryName;

    public boolean isInList(List<Category> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getCategoryId() == this.categoryId) {
                return true;
            }
        }
        return false;
    }
}
