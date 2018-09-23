package Subject;

public interface IObjectService {
    ObjectModel create(String code, String name, String codeRegister, String teacherName);

    ObjectModel get(int id);

    boolean delete(int id);

    ObjectModel update(int id, String code, String name, String codeRegister, String teacherName);

    ObjectModel get(String userName);
//    JSONObject get(SearchLegalRelationshipModel searchLegalRelationshipModel);
}