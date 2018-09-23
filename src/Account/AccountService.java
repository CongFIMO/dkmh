package Account;

import Database.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Son on 6/15/2017.
 */
public class AccountService implements IAccountService {
    private static SessionFactory factory;
    private static int currentActive;

    public AccountService(SessionFactory factory) {
        this.factory = factory;
    }

    public AccountService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        AccountService.factory = factory;
    }

    public AccountModel create(String userName, String password, String cokie) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            AccountModel AccountModel = new AccountModel(0,userName,password,cokie);
            int id = Integer.valueOf(String.valueOf(session.save(AccountModel.toEntity())));
            tx.commit();
            AccountModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public AccountModel get(int Id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<AccountEntity> criteria = builder.createQuery(AccountEntity.class);
        Root<AccountEntity> ResultEntities = criteria.from(AccountEntity.class);
        criteria.where(builder.equal(ResultEntities.get("id"), Id));
        List<AccountEntity> resultEntities = session.createQuery(criteria).getResultList();
        if (!resultEntities.isEmpty()) {
            return new AccountModel(resultEntities.get(0));
        }
        return null;
    }

    public AccountModel get(String userName) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<AccountEntity> criteria = builder.createQuery(AccountEntity.class);
        Root<AccountEntity> ResultEntities = criteria.from(AccountEntity.class);
        criteria.where(builder.like(ResultEntities.get("UserName"), userName));
        List<AccountEntity> resultEntities = session.createQuery(criteria).getResultList();
        if (!resultEntities.isEmpty()) {
            return new AccountModel(resultEntities.get(0));
        }
        return null;
    }

//    public JSONObject get(SearchLegalRelationshipModel searchLegalRelationshipModel) {
//        Session session = factory.openSession();
//        Criteria criteria = session.createCriteria(SearchLegalRelationshipModel.class, "legalrelationship");
//        criteria = searchLegalRelationshipModel.apply(criteria);
//        List<LegalrelationshipEntity> legalrelationshipEntities = criteria.list();
//        List<LegalRelationshipModel> legalRelationshipModels = new ArrayList<>();
//        legalrelationshipEntities.forEach(x -> {
//            legalRelationshipModels.add(new LegalRelationshipModel(x));
//        });
//        JSONObject obj = new JSONObject();
//        int statusCode = 200;
//        JSONArray data = new JSONArray();
//        for (LegalRelationshipModel x : legalRelationshipModels) {
//            data.add(x.toJsonObject());
//        }
//        obj.put("status", statusCode);
//        obj.put("data", data);
//        return obj;
//    }

    public boolean delete(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            AccountEntity AccountEntity = new AccountEntity();
            AccountEntity.setId(id);
            session.delete(AccountEntity);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public AccountModel update(int id,String userName, String password, String cokie) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            AccountModel AccountModel = new AccountModel(id, userName,password,cokie);
            session.update(AccountModel.toEntity());
            tx.commit();
            AccountModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
