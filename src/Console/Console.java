package Console;

import Crawler.CrawlerService;
import Subject.ObjectModel;
import Subject.ParseObjectService;
import com.google.common.collect.Lists;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Console {

    public static void main(String[] args) {
        while(true) {
            try {
                List<Integer> n = Lists.newArrayList(1, 2, 3, 4, 5, 6);
                n.parallelStream().forEach(integer -> {
//                    if (integer == 2) {
//                        CrawlerService crawlerService = new CrawlerService("15022886", "01010001");
//                        while (true) {
//                            if (!crawlerService.checkBusy()) break;
//                        }
//
//                        assign("15021976", "adn9x14121997", Lists.newArrayList("ELT2028 1"));
//                    }
                    if (integer == 1) {
                        CrawlerService crawlerService = new CrawlerService("15022886", "01010001");
                        while (true) {
                            if (!crawlerService.checkBusy()) break;
                        }

                        assign("15021377", "calan1vn", Lists.newArrayList( "INT3216 1", "INT3301 2"));
                    }
//                    if (integer == 2) {
//                        CrawlerService crawlerService = new CrawlerService("15022886", "01010001");
//                        while (true) {
//                            if (!crawlerService.checkBusy()) break;
//                        }
//
//                        assign("15020941", "2711997", Lists.newArrayList("INT3301 1"));
//                    }

                });
            }
            catch (Exception e){

            }
        }
    }

    public static boolean assign(String mssv, String passWord, List<String> subject) {
        System.setProperty("webdriver.chrome.driver", "G:\\Dangkimonhoc\\src\\chromedriver.exe");
        CrawlerService crawlerService = new CrawlerService(mssv, passWord);
        while (crawlerService.login() && subject.size() > 0) {
            List<ObjectModel> objectModelList = null;
            try {
                objectModelList = ParseObjectService.Parse(crawlerService.getSubject());
                System.out.println("checking....." + objectModelList.size());
                System.out.println("regist for: " + subject);
                for (ObjectModel objectModel : objectModelList) {
                    subject = subject.parallelStream().filter(s -> {
                        if (objectModel.code.contains(s)) {
                            try {
                                crawlerService.getData();
                                crawlerService.register(objectModel);
                                if (!crawlerService.commit()) return true;
                                crawlerService.logout();
                                return false;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return true;
                    }).collect(Collectors.toList());
                }
                //Thread.sleep(300    );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void openBrowser(String mssv, String passWord) {
        CrawlerService crawlerService = new CrawlerService(mssv, passWord);
        try {
            crawlerService.login();
            WebDriver webDriver2 = new ChromeDriver();
            Date date = new Date();
            date.setTime(date.getTime() + Long.valueOf("86400000"));
            webDriver2.get("http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1");
            webDriver2.manage().addCookie(new Cookie("ASP.NET_SessionId", crawlerService.asp.substring(18, crawlerService.asp.length()), "/", date));
            webDriver2.manage().addCookie(new Cookie("__RequestVerificationToken", crawlerService.request.substring("__RequestVerificationToken".length() + 1, crawlerService.request.length()), "vnu.edu.vn", "/", date));
            webDriver2.get("http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1");

        } catch (Exception e) {

        }
    }
}
