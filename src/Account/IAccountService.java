package Account;
public interface IAccountService {
    AccountModel create(String userName, String password, String cokie);

    AccountModel get(int id);

    boolean delete(int id);

    AccountModel update(int id, String userName, String password, String cokie);

    AccountModel get(String userName);
//    JSONObject get(SearchLegalRelationshipModel searchLegalRelationshipModel);
}