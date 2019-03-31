import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.Scanner;


class DataFile {
    private static String fileName = "data.txt";
    private static String dir = System.getProperty("user.dir");
    private static String fullName = dir + "\\src\\main\\resources" + File.separator + fileName;
    private static File data = new File(fullName);

    private static void createDataFile () {
        if(!data.exists()) {
            try{
                if(data.createNewFile()) {
                    System.out.println("File created");
                    getOnlineData();
                } else {
                    System.out.println("Error");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }else {
            System.out.println("File already exist at " + fullName);
        }
    }

    static void getOnlineData() {
        Sign[] signs = Sign.values();
        String[] signTitle;
        String signName;
        String singDates;
        String singText;
        if (data.exists() && data.canWrite()) {
            try {
                FileReader fr = new FileReader(fullName);
                Scanner scanner = new Scanner(fr);

                if (!scanner.hasNextLine()) {
                    FileWriter fw = new FileWriter(fullName);
                    for (Sign s : signs) {
                        Document doc = Jsoup.connect("https://dni.ru/horoscope/" + s.toString().toLowerCase()).get();
                        System.out.println(doc.body().getElementsByClass("horoscopeslide_title").text());
                        System.out.println(doc.body().getElementsByClass("article__text").text());

                        signTitle = doc.body().getElementsByClass("horoscopeslide_title").text().split(" ");
                        signName = signTitle[0];
                        singDates = signTitle[1] + " " + signTitle[2] + " " + signTitle[3] + " " + signTitle[4] + " " + signTitle[5];
                        singText = doc.body().getElementsByClass("article__text").text();

                        fw.write(s + "; " + signName + "; " + singDates + "; " + singText + singText + "\n");
                    }
                    fw.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            createDataFile();
        }
    }

    static String getSingText(String sign) {
        String text;
        String[] textArr;
        if (data.exists()) {
            try {
                FileReader fr = new FileReader(fullName);
                BufferedReader bf = new BufferedReader(fr);
                while ((text = bf.readLine()) != null) {
                    if (text.contains(sign)){
                        textArr = text.split(";");
                        return  textArr[3];
                    }
                }
                fr.close();
                bf.close();
            } catch (IOException e) {
                return e.getMessage();
            }
        }
        return null;
    }

    static String getSignTitle(String sign) {
        if (data.exists()) {
                    try {
                        FileReader fr = new FileReader(fullName);
                        Scanner scanner = new Scanner(fr);
                while (scanner.hasNextLine()) {
                    if (scanner.next().contains(sign)) {
                        fr.close();
                        return scanner.nextLine();
                    }
                }
                fr.close();
            } catch (IOException e) {
                return e.getMessage();
            }
        }
        return "Capri";
    }

    enum Sign {
        Aries,
        Taurus,
        Gemini,
        Cancer,
        Leo,
        Virgo,
        Libra,
        Scorpio,
        Sagittarius,
        Capricorn,
        Aquarius,
        Pisces,
    }
}