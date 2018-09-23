package CloneHTML;

import Database.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Son on 6/15/2017.
 */
public class CloneHTMLService implements ICloneHTMLService {
    private static SessionFactory factory;
    private static int currentActive;

    public CloneHTMLService(SessionFactory factory) {
        this.factory = factory;
    }

    public CloneHTMLService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        CloneHTMLService.factory = factory;
    }

    public CloneHTMLModel create(Timestamp time, String data) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CloneHTMLModel CloneHTMLModel = new CloneHTMLModel(0,time,data);
            int id = Integer.valueOf(String.valueOf(session.save(CloneHTMLModel.toEntity())));
            tx.commit();
            CloneHTML.CloneHTMLModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public CloneHTMLModel create(List<String> data) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CloneHTMLModel CloneHTMLModel = new CloneHTMLModel(0,Timestamp.valueOf(LocalDateTime.now()),String.join("\n",data));
            int id = Integer.valueOf(String.valueOf(session.save(CloneHTMLModel.toEntity())));
            tx.commit();
            CloneHTML.CloneHTMLModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public CloneHTMLModel get(int Id) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CloneHTMLEntity> criteria = builder.createQuery(CloneHTMLEntity.class);
        Root<CloneHTMLEntity> ResultEntities = criteria.from(CloneHTMLEntity.class);
        criteria.where(builder.equal(ResultEntities.get("id"), Id));
        List<CloneHTMLEntity> resultEntities = session.createQuery(criteria).getResultList();
        if (!resultEntities.isEmpty()) {
            return new CloneHTMLModel(resultEntities.get(0));
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
            CloneHTMLEntity CloneHTMLEntity = new CloneHTMLEntity();
            CloneHTMLEntity.setId(id);
            session.delete(CloneHTMLEntity);
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

    public CloneHTMLModel update(int id, Timestamp time, String data) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CloneHTMLModel CloneHTMLModel = new CloneHTMLModel(id, time,data);
            session.update(CloneHTMLModel.toEntity());
            tx.commit();
            CloneHTML.CloneHTMLModel result = get(id);
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
