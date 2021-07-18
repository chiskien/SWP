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
public class Translator {
    private int translatorId;
    private String name;
    private String linkFanpage;
    private String donationAccount;
    private String imgName;
    private String imageName;
    private boolean status;

    public Translator() {
    }

    public Translator(int translatorId, String name, String linkFanpage, String donationAccount, String imgName, String imageName, boolean status) {
        this.translatorId = translatorId;
        this.name = name;
        this.linkFanpage = linkFanpage;
        this.donationAccount = donationAccount;
        this.imgName = imgName;
        this.imageName = imageName;
        this.status = status;
    }
    
}
