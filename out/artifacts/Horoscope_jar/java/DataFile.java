import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        try {
            createDataFile();
            FileWriter fw = new FileWriter(fullName);
            for (Sign s : signs) {
                //Document doc = Jsoup.connect("https://dni.ru/horoscope/" + s.toString().toLowerCase()).get();

                fw.write(s + "|" + "doc.body().getElementsByClass(\"article__text\").toString()" + "\n");

                System.out.println("https://dni.ru/horoscope/" + s.toString().toLowerCase());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static String getFileData(String sign) {
        try{
            FileReader fr = new FileReader(fullName);
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNextLine()){
                if (scanner.next().contains(sign)) {
                    return scanner.next();
                }
            }
            fr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
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