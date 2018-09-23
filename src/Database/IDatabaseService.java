package Database;

import org.json.simple.JSONObject;

/**
 * Created by Son on 4/28/2017.
 */
public interface IDatabaseService {
    boolean create(String url, String name, String password, String dbName, int type);

    JSONObject get();

    DatabaseModel get(int id);

    boolean delete(int id);

    boolean update(int id, String url, String name, String password, String dbName, int type);

}
