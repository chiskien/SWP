/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import dao.AccountDao;
import dao.BookDao;
import dao.CategoryDao;
import dao.ChapterDao;
import dao.CommentDao;
import dao.FrameDao;
import dao.HistoryDao;
import dao.NotificationDao;
import dao.ProductDao;
import dao.TranslatorDao;
import dao.hoang_dao.FollowDao;
import entity.Account;
import entity.Book;
import entity.Category;
import entity.Chapter;
import entity.Comment;
import entity.Frame;
import entity.History;
import entity.Product;
import entity.Translator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tool.SendEmail;

/**
 * @author hoang
 */
public class Main {

    public static void alo(File a) {
        if (a.isDirectory()) {
            System.out.println("Folder " + a.getAbsolutePath());
            File[] b = a.listFiles();
            for (File c : b) {
                alo(c);
            }
            System.out.println(a.delete());
        } else {
            System.out.println("File " + a.getAbsolutePath());
            System.out.println(a.delete());
        }
    }

    private final String MAIL = "hoangdthe153200@fpt.edu.vn";
    private final String PASSWORD = "hoanglotu123";

    public void sentEmail(String toEmail, String subject, String text) {

        // Config
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");

        // Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL, PASSWORD);
            }
        });

        // Mail info
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            System.out.println("Message sent successfully...");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {

//                List<Category> lsCategory = new CategoryDao().getAll();
//                for(Category c:lsCategory){
//                    System.out.println(c);
//                }
//         System.out.println(FollowDao.addFollow(16, 3, -1));
        List<Book> book = new BookDao().getAllWithTranslatorId(3);
        for (Book b : book) {
            System.out.println(b);
        }
//        System.out.println(new BookDao().update2(1, "Onepiece", 3, "Câu chuyện kể về một chuyến phiêu lưu giữa các vùng biển của các hải tặc Mũ rơm trẻ tuổi, mục tiêu là quần đảo cuối cùng có tên Laugh tale. Đây là một hành trình khám phá những miền đất vô tận.", "ji"));
//        List<Category> a = new ArrayList();
//        a.add(new CategoryDao().getOne(3));
//        a.add(new CategoryDao().getOne(9));
//        List<Book> b = new BookDao().getAllWithFilter(a,"Newest");
//        b.forEach(System.out::println);
//        System.out.println(new BookDao().getOne(3));
//        Book b = new BookDao().getOne(1);
//        System.out.println(b);
//        List<Chapter> pro = new ChapterDao().getAllWithBookId(1);
//        pro.forEach(System.out::println);
//        
//        Chapter a = new ChapterDao().getNext(1,5);
//        System.out.println(a);
//        System.out.println(new BookDao().getOne(2));
//        Book a = Book.builder()
//                .name("Kien ngu")
//                .authorId(2)
//                .translatorId(4)
//                .status("break")
//                .description("")
//                .imgName("hoang.jpg")
//                .build();
//        
//        System.out.println(new BookDao().add(a));
//        Book a = Book.builder()
//                .name("Kien ngu")
//                .authorId(2)
//                .translatorId(4)
//                .status("finished")
//                .description("")
//                .imgName("dinh.jpg")
//                .build();
//        System.out.println(new BookDao().update(3, a));
//        System.out.println(new BookDao().remove(2));
//        List<Translator> ls = new TranslatorDao().getAll();
//        ls.forEach(System.out::println);
//        System.out.println(new TranslatorDao().getOne(2));
//        Translator a = Translator.builder()
//                .name("Kaguya Shinomia")
//                .linkFanpage("https:\\djfs.fds.dfs")
//                .donationAccount("92394342")
//                .build();
//        System.out.println(new TranslatorDao().add(a));
//        System.out.println(new TranslatorDao().remove(2));
//            Translator a = Translator.builder()
//                .name("Kaguya Shinomia")
//                .linkFanpage("https:\\djfs.fds.dfs")
//                .donationAccount("000000")
//                .build();
//            System.out.println(new TranslatorDao().update(3, a));
    }
}
