package Subject;

/**
 * Created by Son on 4/28/2017.
 */
public class ObjectModel {
    public int id;
    public String code;
    public String name;
    public String codeRegister;
    public String teacherName;

public ObjectModel(){

}
    public ObjectModel(int id, String code, String name, String codeRegister, String teacherName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.codeRegister = codeRegister;
        this.teacherName = teacherName;
    }

    public ObjectModel(ObjectEntity ObjectEntity) {
        this.id = ObjectEntity.getId();
        this.code = ObjectEntity.getCode();
        this.name = ObjectEntity.getName();
        this.codeRegister = ObjectEntity.getCodeRegister();
        this.teacherName = ObjectEntity.getTeacherName();
    }

    public ObjectEntity toEntity() {
        ObjectEntity resultEntity = new ObjectEntity();
        resultEntity.setId(id);
        resultEntity.setCode(code);
        resultEntity.setCodeRegister(codeRegister);
        resultEntity.setName(name);
        resultEntity.setTeacherName(teacherName);
        return resultEntity;
    }
}
