package CloneHTML;

import java.sql.Timestamp;

public interface ICloneHTMLService {
    CloneHTMLModel create(Timestamp time, String data);

    CloneHTMLModel get(int id);

    boolean delete(int id);

    CloneHTMLModel update(int id, Timestamp time, String data);

//    JSONObject get(SearchLegalRelationshipModel searchLegalRelationshipModel);
}