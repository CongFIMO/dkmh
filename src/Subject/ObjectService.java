package Subject;

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
public class ObjectService implements IObjectService {
    private static SessionFactory factory;
    private static int currentActive;

    public ObjectService(SessionFactory factory) {
        this.factory = factory;
    }

    public ObjectService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        ObjectService.factory = factory;
    }

    public ObjectModel create(String code, String name, String codeRegister, String teacherName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ObjectModel ObjectModel = new ObjectModel(0, code, name, codeRegister, teacherName);
            int id = Integer.valueOf(String.valueOf(session.save(ObjectModel.toEntity())));
            tx.commit();
            ObjectModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public ObjectModel update(int id, String code, String name, String codeRegister, String teacherName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ObjectModel ObjectModel = new ObjectModel(id, code, name, codeRegister, teacherName);
            session.update(ObjectModel.toEntity());
            tx.commit();
            ObjectModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public ObjectModel get(int Id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ObjectEntity> criteria = builder.createQuery(ObjectEntity.class);
        Root<ObjectEntity> ResultEntities = criteria.from(ObjectEntity.class);
        criteria.where(builder.equal(ResultEntities.get("id"), Id));
        List<ObjectEntity> resultEntities = session.createQuery(criteria).getResultList();
        if (!resultEntities.isEmpty()) {
            return new ObjectModel(resultEntities.get(0));
        }
        return null;
    }

    public ObjectModel get(String userName) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ObjectEntity> criteria = builder.createQuery(ObjectEntity.class);
        Root<ObjectEntity> ResultEntities = criteria.from(ObjectEntity.class);
        criteria.where(builder.like(ResultEntities.get("UserName"), userName));
        List<ObjectEntity> resultEntities = session.createQuery(criteria).getResultList();
        if (!resultEntities.isEmpty()) {
            return new ObjectModel(resultEntities.get(0));
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
            ObjectEntity ObjectEntity = new ObjectEntity();
            ObjectEntity.setId(id);
            session.delete(ObjectEntity);
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


}
