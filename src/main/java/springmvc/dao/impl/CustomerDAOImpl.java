package springmvc.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springmvc.dao.CustomerDAO;
import javax.persistence.Query;
import springmvc.entity.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List < Customer > getCustomers() {
        Session session = sessionFactory.getCurrentSession();
//        tạo ra đối tượng chứa câu lệnh truy vấn
        CriteriaBuilder cb = session.getCriteriaBuilder();
//        khai báo đối tượng bạn muốn lấy ra sau khi thực hiện query
        CriteriaQuery< Customer > cq = cb.createQuery(Customer.class);
//        khai báo đối tượng bạn sẽ sử dụng trong query
        Root< Customer > root = cq.from(Customer.class);
//        lấy đối tượng CriteriaQuery đã khai báo
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer book = session.byId(Customer.class).load(id);
        session.delete(book);
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer theCustomer = currentSession.get(Customer.class, theId);
        return theCustomer;
    }
}
