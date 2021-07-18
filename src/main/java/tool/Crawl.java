/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import dao.BookDao;
import dao.CategoryDao;
import dao.ChapterDao;
import dao.FrameDao;
import entity.Book;
import entity.Chapter;
import entity.Frame;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.StringJoiner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author hoang
 */
public class Crawl {

    public static void main(String[] args) throws IOException {
        ArrayList<String> arr = getBookUrl("https://truyentranhlh.net/");
        for (String s : arr) {
            crawlImg(s);
        }
    }

    public static ArrayList<String> getBookUrl(String pageUrl) throws IOException {
        ArrayList<String> lsb = new BookDao().getAllName();
        ArrayList<String> bookUrl = new ArrayList();
        Document doc = Jsoup.connect(pageUrl).get();
        System.out.println("ok");
        Elements ls = doc.select(".col-md-8 .series-title a");
        int bound = 0;
        for (Element e : ls) {
            if (bound++ >= 1) {
                break;
            }
            Document doc2 = Jsoup.connect(e.attr("href")).get();
            Element xname = doc2.selectFirst(".series-name a");
            String[] name = xname.html().split("[\\/:*?\"<>|]");
            StringJoiner joiner = new StringJoiner("");
            for (String s : name) {
                joiner.add(s);
            }
            String hoang = joiner.toString();
            System.out.println(hoang);
            boolean check = false;
            for (String s : lsb) {
                if (s.equals(hoang)) {

                    check = true;
                    break;
                }
            }
            if (!check) {
                bookUrl.add(e.attr("href"));
            }
            System.out.println("check: " + check);
        }
        return bookUrl;
    }

    public static boolean crawlImg(String bookUrl) throws IOException {
        String info[] = getBookInfo(bookUrl);
        String book = info[0];
        deleteExistFolder(book);

        createBookImg(bookUrl);
        addBookIntoDB(bookUrl);

        ArrayList<Chapters> chapterFolders = getChaptersFolders(bookUrl);
        createChapterFolders(chapterFolders, book);
        int i = 0;
        for (Chapters chap : chapterFolders) {
            System.out.println(chap.getChapterTitle());
            if (i >= 0) {
                break;
            }
            createImg(chap, bookUrl, book);
            i++;
        }
        return true;
    }

    public static String[] getBookInfo(String bookUrl) throws IOException {
        String[] info = new String[10];
        Document doc = Jsoup.connect(bookUrl).get();
        Element xname = doc.selectFirst(".series-name a");
        String[] name = xname.html().split("[\\/:*?\"<>|]");
        StringJoiner joiner = new StringJoiner("");
        for (String s : name) {
            joiner.add(s);
        }
        info[0] = joiner.toString();

        Elements xstatus = doc.select(".info-item");
        String status = "";
        for (Element e : xstatus) {
            Elements a = e.select(".info-name");
            if (a.get(0).html().equals("Tình trạng:")) {
                status = e.select(".info-value a").html();
                if (status.equals("Đang tiến hành")) {
                    info[1] = "on going";
                }
                break;
            }

        }

        Element xdescription = doc.selectFirst(".summary-content p");
        String xdes[] = xdescription.html().split("<br>");
        joiner = new StringJoiner("\n");
        for (String x : xdes) {
            joiner.add(x);
        }
        info[2] = joiner.toString();

        Element xImg = doc.selectFirst(".series-cover .content");
        String img = xImg.attr("style").split("[']")[1];
        info[3] = img;

        Elements xCategory = doc.select(".info-item");
        int cateCount = 4;
        for (Element e : xCategory) {

            Elements a = e.select(".info-name");
            if (a.get(0).html().equals("Thể loại:")) {
                Elements category = e.select(".info-value a span");
                for (Element c : category) {
                    if (cateCount < 10) {
                        info[cateCount++] = c.html();
                    } else {
                        break;
                    }
                }

            }

        }
        return info;
    }

    public static boolean addBookIntoDB(String bookUrl) throws IOException {
        int[] ranInt = {1, 3, 4};
        Random ran = new Random();

        String[] info = getBookInfo(bookUrl);
        Book temp = Book.builder()
                .name(info[0])
                .authorId(ran.nextInt(6) + 1)
                .translatorId(ranInt[ran.nextInt(3)])
                .status(info[1])
                .description(info[2])
                .imgName(info[0] + ".jpg")
                .build();
        new BookDao().add(temp);

        for (int i = 4; i < 10; i++) {
            if (info[i] == null) {
                break;
            }
            int categoryId = new CategoryDao().getIdWithName(info[i]);
            int bookId = new BookDao().getIdWithName(info[0]);
            new CategoryDao().addCate_Book(categoryId, bookId);
        }
        return true;
    }

    public static boolean createChapterFolders(ArrayList<Chapters> folders, String book) {
        int bookId = new BookDao().getIdWithName(book);
        for (Chapters s : folders) {
            File file = new File("web/asset/img/book/" + book + "/" + s.getChapterTitle());
            if (!file.exists()) {

                file.mkdirs();
            } else {

                file.delete();
                file.mkdirs();

            }
            addChapterIntoDB(bookId, s.getChapterTitle());
        }

        return true;
    }

    public static boolean createBookImg(String bookUrl) throws IOException {
        String info[] = getBookInfo(bookUrl);
        URL bookImgUrl = new URL(info[3]);
        File file = new File("web/asset/img/book/" + info[0] + ".jpg");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        InputStream in = bookImgUrl.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        int a;
        while ((a = in.read()) != -1) {
            out.write(a);
        }
        out.close();
        in.close();
        return true;
    }

    public static ArrayList<Chapters> getChaptersFolders(String bookUrl) throws IOException {
        Document doc = Jsoup.connect(bookUrl).get();
        Elements ls = doc.select("ul.list-chapters a");
        ArrayList<Chapters> arr = new ArrayList();
        for (Element e : ls) {
            String[] s = e.attr("href").split("/");
            Chapters temp = new Chapters();
            temp.setChapterName(s[s.length - 1]);
            temp.setChapterTitle(e.attr("title"));
            arr.add(temp);
        }
        Collections.reverse(arr);
        System.out.println(arr);
        return arr;
    }

    public static ArrayList<String> getImgUrl(String bookUrl, String chapter) throws IOException {
        String chapterUrl = bookUrl + "/" + chapter;

        Document doc = Jsoup.connect(chapterUrl).get();
        Elements img = doc.select("#chapter-content img");
        ArrayList<String> imgUrl = new ArrayList();
        int count = 0;
        for (Element e : img) {
            if (count++ <= 1) {
                imgUrl.add(e.attr("data-src"));
            } else {
                break;
            }
        }
        return imgUrl;
    }

    public static boolean createImg(Chapters chapter, String bookUrl, String book) throws IOException {

        ArrayList<String> imgUrl = getImgUrl(bookUrl, chapter.getChapterName());
        for (String s : imgUrl) {
            int x = imgUrl.indexOf(s) + 1;
            File file = new File("web/asset/img/book/" + book + "/" + chapter.getChapterTitle() + "/" + book + "_" + chapter + "_" + x + ".jpg");
            file.createNewFile();
            URL url = new URL(s);
            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            int a;
            while ((a = in.read()) != -1) {
                out.write(a);
            }
            out.close();
            in.close();
            int chapterId = new ChapterDao().getIdWithName(chapter.getChapterTitle(), new BookDao().getIdWithName(book));
            new FrameDao().add(Frame.builder()
                    .bookId(new BookDao().getIdWithName(book))
                    .chapterId(chapterId)
                    .imgName(chapter.getChapterTitle() + "/" + book + "_" + chapter + "_" + x + ".jpg")
                    .status(1)
                    .build());

        }

        return true;
    }

    public static boolean addChapterIntoDB(int bookId, String chapterName) {
        new ChapterDao().add(Chapter.builder()
                .bookId(bookId)
                .chapterName(chapterName)
                .status(1)
                .build());
        return true;
    }

    public static void deleteExistFolder(String book) {
        deleteFile(new File("web/asset/img/" + book + ".jpg"));
        deleteFile(new File("web/asset/img/book/" + book));
    }

    private static void deleteFile(File a) {
        if (a.isDirectory()) {
            System.out.println("Folder " + a.getAbsolutePath());
            File[] b = a.listFiles();
            for (File c : b) {
                deleteFile(c);
            }
            a.delete();
        } else {
            a.delete();
        }
    }

}
