package Database;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by Son on 4/28/2017.
 */
public class DatabaseService implements IDatabaseService {

    public boolean create(String url, String name, String password, String dbName, int type) {
        DatabaseEntity x = new DatabaseEntity(new DatabaseModel(0, type, url, dbName, name, password));
        x.insert();
        return true;
    }

    public boolean update(int id, String url, String name, String password, String dbName, int type) {
        DatabaseEntity x = new DatabaseEntity();
        DatabaseModel databaseModel = ((List<DatabaseModel>) (List<?>) x.select(id)).get(0);
        databaseModel.databaseName = dbName;
        databaseModel.userName = name;
        databaseModel.passWord = password;
        databaseModel.typeDB = type;
        return true;
    }

    public boolean delete(int id) {
        DatabaseEntity x = new DatabaseEntity(new DatabaseModel(id, 0, "", "", "", ""));
        x.delete();
        return true;
    }

    public JSONObject get() {
        JSONObject result = new JSONObject();
        DatabaseEntity x = new DatabaseEntity();
        JSONArray data = new JSONArray();
        List<DatabaseModel> t = (List<DatabaseModel>) (List<?>) x.select();
        for (DatabaseModel databaseModel : t) {
            data.add(databaseModel.toJsonObject());
        }
        result.put("status", 200);
        result.put("data", data);
        return result;
    }

    public DatabaseModel get(int id) {
        DatabaseEntity x = new DatabaseEntity();
        List<DatabaseModel> databaseModelList = (List<DatabaseModel>) (List<?>) x.select(id);
        if (databaseModelList != null && !databaseModelList.isEmpty()) {
            return databaseModelList.get(0);
        }
        return null;
    }
}
