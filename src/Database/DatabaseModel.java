package Database;

import org.json.simple.JSONObject;

/**
 * Created by Son on 4/28/2017.
 */
public class DatabaseModel {
    public int id;
    public int typeDB;
    public String url;
    public String databaseName;
    public String userName;
    public String passWord;

    public DatabaseModel(int id, int typeDB, String url, String databaseName, String userName, String passWord) {
        this.typeDB = typeDB;
        this.id = id;
        this.url = url;
        this.databaseName = databaseName;
        this.userName = userName;
        this.passWord = passWord;
    }

    public String toJSon() {
        return toJsonObject().toString();
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("url", url);
        obj.put("databaseName", databaseName);
        obj.put("userName", userName);
        obj.put("passWord", passWord);
        obj.put("typeDB", typeDB);
        return obj;
    }
}
