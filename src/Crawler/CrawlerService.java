package Crawler;

import Subject.ObjectModel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrawlerService {
    public String mssv;
    public String password;
    public String asp = "";
    public String request = "";
//    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.ho.fpt.vn", 8080));

    public CrawlerService(String mssv, String password) {
        this.mssv = mssv;
        this.password = password;
    }

    public boolean login() {
        String requestVerificationToken = "";
        String request = "http://dangkyhoc.vnu.edu.vn/dang-nhap/";
        try {
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/58.3.138 Chrome/52.3.2743.138 Safari/537.36");
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            String key = "name=\"__RequestVerificationToken\"";
            int nKey = key.length();
            while ((inputLine = in.readLine()) != null) {
                for (int i = 0; i < inputLine.length(); i++) {
                    if (inputLine.charAt(i) == key.charAt(0)) {
                        int check = 1;
                        for (int j = 1; j < nKey; j++) {
                            if (inputLine.charAt(i + j) != key.charAt(j)) {
                                check = 0;
                                break;
                            }
                        }
                        if (check == 1) {
                            int plus = " type=\"hidden\" value=\"".length();
                            i += key.length() + plus;
                            while (inputLine.charAt(i) != '"') {
                                requestVerificationToken += inputLine.charAt(i);
                                i++;
                            }
                        }
                    }
                }
            }
            String request1 = "http://dangkyhoc.vnu.edu.vn/dang-nhap/";
            String urlParameters = "LoginName=" + mssv + "&Password=" + password + "&__RequestVerificationToken=" + requestVerificationToken;
            byte[] postData = urlParameters.getBytes();
            int postDataLength = postData.length;
            URL u = new URL(request1);
            HttpURLConnection conn1 = (HttpURLConnection) u.openConnection();
            conn1.setDoOutput(true);
            Map<String, List<String>> headerFields = conn.getHeaderFields();
            List<String> cookiesHeader = headerFields.get("Set-Cookie");
            if (cookiesHeader != null) {
                for (String cookie : cookiesHeader) {
                    conn1.setRequestProperty("Cookie", cookie);
                    this.request = cookie.split(";")[0];
                }
            }
            conn1.setDoOutput(true);
            conn1.setInstanceFollowRedirects(false);
            conn1.setRequestMethod("POST");
            conn1.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn1.setRequestProperty("charset", "utf-8");
            conn1.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn1.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(conn1.getOutputStream());
            wr.write(postData);
            headerFields = conn1.getHeaderFields();
            cookiesHeader = headerFields.get("Set-Cookie");
            if (cookiesHeader != null) {
                for (String cookie : cookiesHeader) {
                    this.asp = cookie.split(";")[0];
                }
                return (conn1.getResponseCode() == 302);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkBusy() {
        String requestVerificationToken = "";
        String request = "http://dangkyhoc.vnu.edu.vn/dang-nhap/";
        try {
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/58.3.138 Chrome/52.3.2743.138 Safari/537.36");
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            String key = "name=\"__RequestVerificationToken\"";
            int nKey = key.length();
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("Hệ thống đang bận xử")) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void register(ObjectModel objectModel) throws Exception {
        String request = "http://dangkyhoc.vnu.edu.vn/chon-mon-hoc/" + objectModel.codeRegister + "/1/2";
        System.out.println("Registing " + objectModel.name);
        URL url = new URL(request);
        String urlParameters = "";
        byte[] postData = urlParameters.getBytes();
        int postDataLength = postData.length;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie", asp + ";" + this.request);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Referer", "http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(postData);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String inputLine = "";
        System.out.println(request);
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
    }

    public void getData() throws Exception {
        String request = "http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie", asp + ";" + this.request);
        System.out.println(conn.getResponseCode());
//        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//        String inputLine = "";
//        while ((inputLine = in.readLine()) != null) {
//            System.out.println(inputLine);
//        }
//        Map<String, List<String>> headerFields = conn.getHeaderFields();
//        List<String> cookiesHeader = headerFields.get("Set-Cookie");
//        if (cookiesHeader != null) {
//            for (String cookie : cookiesHeader) {
//                System.out.println(cookie);
//                this.cookie += cookie.split(";")[0] + ";";
//            }
//        }
    }

    public String getRegisted() throws Exception {
        String request = "http://dangkyhoc.vnu.edu.vn/danh-sach-mon-hoc-da-dang-ky/1";
        URL url = new URL(request);
        String urlParameters = "";
        byte[] postData = urlParameters.getBytes();
        int postDataLength = postData.length;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie", asp + ";" + this.request);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Referer", "http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(postData);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String inputLine = "";
        String s = "";
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("<tr class=\"registered\"")) {
                inputLine = "<br/>" + inputLine;
            }
            s += inputLine + "\n";
        }
        return s;
    }

    public void logout() throws Exception {
        String request = "http://dangkyhoc.vnu.edu.vn/Account/Logout";
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie", asp + ";" + this.request);
        conn.setInstanceFollowRedirects(false);
        System.out.println(conn.getResponseCode());
    }

    public boolean commit() throws Exception {
        String request = "http://dangkyhoc.vnu.edu.vn/xac-nhan-dang-ky/1";
        URL url = new URL(request);
        String urlParameters = "";
        byte[] postData = urlParameters.getBytes();
        int postDataLength = postData.length;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie", asp + ";" + this.request);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Referer", "http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(postData);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String inputLine = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            if (inputLine.toLowerCase().contains("message")) {
                if (inputLine.toLowerCase().contains("Ngoài thời hạn đăng ký".toLowerCase())) {
                    System.out.println("Ngoài thời gian đăng ký!");
                    return false;
                } else {
                    System.out.println("Commit Success!");
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getSubject() throws Exception {
        List<String> result = new ArrayList<String>();
        String request = "http://dangkyhoc.vnu.edu.vn/danh-sach-mon-hoc/1/2";
        URL url = new URL(request);
        String urlParameters = "";
        byte[] postData = urlParameters.getBytes();
        int postDataLength = postData.length;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Cookie", asp + ";" + this.request);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Referer", "http://dangkyhoc.vnu.edu.vn/dang-ky-mon-hoc-nganh-1");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(postData);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String inputLine = "";
        String s = "";
        while ((inputLine = in.readLine()) != null) {
            result.add(inputLine);
        }
        return result;
    }
}
