package Account;

/**
 * Created by Son on 4/28/2017.
 */
public class AccountModel {
    public int id;
    public String userName;
    public String password;
    public String cokie;

    public AccountModel(int id, String userName, String password, String cokie) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.cokie = cokie;
    }

    public AccountModel(AccountEntity AccountEntity) {
        this.id = AccountEntity.getId();
        this.userName = AccountEntity.getUserName();
        this.password = AccountEntity.getPassword();
        this.cokie = AccountEntity.getCokie();
    }

    public AccountEntity toEntity() {
        AccountEntity resultEntity = new AccountEntity();
        resultEntity.setId(id);
        resultEntity.setCokie(cokie);
        resultEntity.setPassword(password);
        resultEntity.setUserName(userName);
        return resultEntity;
    }
}
