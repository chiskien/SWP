/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.io.File;

/**
 *
 * @author hoang
 */
public class NewClass {
    public static void main(String[] args) {
        File file = new File("web/asset/img/book/hoang");
        file.mkdir();
    }
}
