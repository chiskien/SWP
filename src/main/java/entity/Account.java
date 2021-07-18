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
public class Account {
    private int accountId;
    private String email;
    private String password;
    private int status;
    private int role;
    private String activeCode;
    private String name;

    public Account() {
        
    }

    public Account(int accountId, String email, String password, int status, int role, String activeCode, String name) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
        this.activeCode = activeCode;
        this.name = name;
    }
    
}
