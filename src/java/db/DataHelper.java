/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Book;
import entity.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author User
 */
public class DataHelper {
    
    private SessionFactory sessionFactory = null;
    private static DataHelper dataHelper;

    private DataHelper() {
        sessionFactory =  HibernateUtil.getSessionFactory();
    }
    
    public static DataHelper getInstance(){
        return dataHelper == null ? new DataHelper() : dataHelper;
    }
    
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    
    public List<Book> getAllBooks(){
        //sessionFactory.getCurrentSession().beginTransaction();
        
        List<Book> lst;
        lst =getSession().createCriteria(Book.class).list();
        //sessionFactory.getCurrentSession().getTransaction().commit();
        return lst;
    }
    
    public List<Book> getBookByGenre(Integer ganreId){
        List<Book> lst;
        lst =getSession().createCriteria(Book.class).add(Restrictions.eq("genre.id",ganreId)).list();
        return lst;
    }

    public List<Book> getBookByName(String bookName){
        
        return getBookList("name",bookName,MatchMode.ANYWHERE);
    }
    
    public List<Book> getBookByAuthor(String authorName){
        
        return getBookList("author.fio",authorName,MatchMode.ANYWHERE);
    }
    
    public List<Book> getBookByLetter(String authorName){
        
        return getBookList("name",authorName,MatchMode.START);
    }
    
    private List<Book> getBookList(String field, String value,MatchMode matchmode){
        //List<Book> lst;
        //lst =getSession().createCriteria(Book.class).add(Restrictions.eq("genre.name",value)).list();
        //Integer ganreId=1;
        //lst =getSession().createCriteria(Book.class).add(Restrictions.eq("genre.name","Фантастика")).list();
        //return lst;
        return getSession().createCriteria(Book.class).add(Restrictions.ilike(field,value,matchmode)).list();
    }
    
            
   public byte[] getImage(Integer id){
       return (byte[]) getFieldValue("image",id);
   }
   public byte[] getContent(Integer id){
       return (byte[]) getFieldValue("content",id);
   }
   
   
   private Object getFieldValue(String field, Integer id ){
       
       
       //sessionFactory.getCurrentSession().beginTransaction();
       Object obj;
       //Integer id_=(Integer)id;
       obj =  getSession().createCriteria(Book.class).setProjection(Projections.property(field)).add(Restrictions.eq("id", id)).uniqueResult();
       //sessionFactory.getCurrentSession().getTransaction().commit();
       return obj;
       
   }
    
}
