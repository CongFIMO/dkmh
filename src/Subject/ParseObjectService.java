package Subject;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ParseObjectService {
    public ParseObjectService(){

    }
    public static List<ObjectModel> Parse(List<String> input) throws UnsupportedEncodingException {
        String key = "data-rowindex=";
        List<ObjectModel> objectModelList = new ArrayList<>();
        int nKey = key.length();
        String[] result = new String[100];
        int kq = 0;
        int length = 0;
        String rowIndex = "";
        for (int i = 0; i < input.size();i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) != key.charAt(0)) continue;
                int check = 1;
                for (int k = 1; k < nKey; k++) {
                    if (input.get(i).charAt(j + k) != key.charAt(k)) {
                        check = 0;
                        break;
                    }
                }
                if (check == 0) continue;

                ObjectModel objectModel = new ObjectModel();
                j += key.length() + 1 ;
                while (input.get(i).charAt(j) != '"') {
                    rowIndex += input.get(i).charAt(j);
                    j++;
                }
                objectModel.codeRegister = rowIndex;
                rowIndex="";
                while (input.get(i).contains("</td>")) i++;
                i+=3;
                objectModel.name = StringEscapeUtils.unescapeHtml4(input.get(i)).trim();
                while (!input.get(i).contains("/td")) i++;
                i++;
                while (!input.get(i).contains("/td")) i++;
                i++;
                while (!input.get(i).contains("/td")) i++;
                i+=2;
                objectModel.code = StringEscapeUtils.unescapeHtml4(input.get(i)).trim();
                while (!input.get(i).contains("/td")) i++;
                i++;
                while (!input.get(i).contains("/td")) i++;
                i++;
                while (!input.get(i).contains("/td")) i++;
                i++;
                String temp = StringEscapeUtils.unescapeHtml4(input.get(i)).trim();
                objectModel.teacherName=temp.substring(4,temp.length()-5);
                i+=9;
                objectModelList.add(objectModel);
            }
        }
        return objectModelList;
    }
}
