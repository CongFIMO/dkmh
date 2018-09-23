package CloneHTML;

import java.sql.Timestamp;

/**
 * Created by Son on 4/28/2017.
 */
public class CloneHTMLModel {
    public int id;
    public Timestamp time;
    public String data;

    public CloneHTMLModel(int id, Timestamp time, String data) {
        this.id = id;
        this.time = time;
        this.data = data;
    }

    public CloneHTMLModel(CloneHTMLEntity CloneHTMLEntity) {
        this.id = CloneHTMLEntity.getId();
        this.time = CloneHTMLEntity.getTime();
        this.data = CloneHTMLEntity.getData();
    }

    public CloneHTMLEntity toEntity() {
        CloneHTMLEntity resultEntity = new CloneHTMLEntity();
        resultEntity.setId(id);
        resultEntity.setData(data);
        resultEntity.setTime(time);
        return resultEntity;
    }
}
