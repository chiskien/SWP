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
@Builder
@ToString
public class Notification {
    private int notificationId;
    private int receiverId;
    private String content;
    private int status;
    private String receiverType;
}
