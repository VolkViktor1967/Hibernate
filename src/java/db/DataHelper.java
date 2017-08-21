/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Pager;
import entity.Author;
import entity.Book;
import entity.Genre;
import entity.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author User
 */
public class DataHelper {
    
    private SessionFactory sessionFactory = null;
    private static DataHelper dataHelper;
    private Pager currentPager;
    private DetachedCriteria bookListCriteria;
    private DetachedCriteria booksCountCriteria;
    private ProjectionList bookProjection;
    
    

    private DataHelper() {
        sessionFactory =  HibernateUtil.getSessionFactory();
        
        bookProjection = Projections.projectionList();
        bookProjection.add(Projections.property("id"),"id");
        bookProjection.add(Projections.property("name"),"name");
        bookProjection.add(Projections.property("image"),"image");
        bookProjection.add(Projections.property("genre"),"genre");
        bookProjection.add(Projections.property("pageCount"),"pageCount");
        bookProjection.add(Projections.property("isbn"),"isbn");
        bookProjection.add(Projections.property("publisher"),"publisher");
        bookProjection.add(Projections.property("author"),"author");
        bookProjection.add(Projections.property("publishYear"),"publishYear");
        bookProjection.add(Projections.property("descr"),"descr");
        
        
        
    }
    
    public static DataHelper getInstance(){
        
        if (dataHelper == null ){
            dataHelper = new DataHelper();
        }
        return dataHelper; //== null ? new DataHelper() : dataHelper;
    }
    
    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    
    public void getAllBooks(Pager pager){
        currentPager = pager;
        
        createBooksCountCreteria();
        runCountCreteria();
        
        createBookListCreteria();
        runBookListCreteria();
        
        /*
        Criteria criteria =getSession().createCriteria(Book.class);
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(total);
        //sessionFactory.getCurrentSession().getTransaction().commit();
        
        currentCriteria=DetachedCriteria.forClass(Book.class);
        addAliases();
        runCurrentCriteria();
        */
    }
    
    public void getBookByGenre(Integer ganreId,Pager pager){
         currentPager = pager;
         
         Criterion criterion = Restrictions.eq("genre.id", ganreId);
         
        createBooksCountCreteria(criterion);
        runCountCreteria();
        
        createBookListCreteria(criterion);
        runBookListCreteria();
        
        
         /*
        Criterion criterion = Restrictions.eq("genre.id", ganreId);
        
        Criteria criteria =getSession().createCriteria(Book.class);
        Long total = (Long) criteria.add(criterion).setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(total);
        
        
        currentCriteria=DetachedCriteria.forClass(Book.class);
        
        addAliases();
        currentCriteria.add(criterion);
        runCurrentCriteria();
        */
    }
    
    public void getBookByLetter(String letter,Pager pager){
         currentPager = pager;
        
        Criterion criterion = Restrictions.ilike("name",letter.toString(),MatchMode.START);
        createBooksCountCreteria(criterion);
        runCountCreteria();
        
        createBookListCreteria(criterion);
        runBookListCreteria();
        
       /* Criteria criteria =getSession().createCriteria(Book.class);
        Long total = (Long) criteria.add(criterion).setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(total);
        
        currentCriteria=DetachedCriteria.forClass(Book.class);
        
        addAliases();
        currentCriteria.add(criterion);
        runCurrentCriteria();
        */
    }
    
    public void getBookByAuthor(String authorName,Pager pager){
         currentPager = pager;
        
        Criterion criterion = Restrictions.ilike("author.fio",authorName,MatchMode.ANYWHERE);
        createBooksCountCreteria(criterion);
        runCountCreteria();
        
        createBookListCreteria(criterion);
        runBookListCreteria();
        
        /*
        Criteria criteria =getSession().createCriteria(Book.class,"book").createAlias("book.author", "author");
        Long total = (Long) criteria.add(criterion).setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(total);
        
        currentCriteria=DetachedCriteria.forClass(Book.class,"book").createAlias("book.author", "author");
        
        addAliases();
        currentCriteria.add(criterion);
        runCurrentCriteria();
        */
    }
    
    
    public void getBookByName(String nameBook,Pager pager){
         currentPager = pager;
        
        Criterion criterion = Restrictions.ilike("name",nameBook,MatchMode.ANYWHERE);
        createBooksCountCreteria(criterion);
        runCountCreteria();
        
        createBookListCreteria(criterion);
        runBookListCreteria();
        
        /*
        Criteria criteria =getSession().createCriteria(Book.class);
        Long total = (Long) criteria.add(criterion).setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(total);
        
        currentCriteria=DetachedCriteria.forClass(Book.class);
        
        addAliases();
        currentCriteria.add(criterion);
        runCurrentCriteria();
        */
    }
    
    
/*
    public void runCurrentCriteria(){
        Criteria criteria = currentCriteria.addOrder(Order.asc("name")).getExecutableCriteria(getSession());
        
        criteria.setProjection(bookProjection).setResultTransformer(Transformers.aliasToBean(Book.class));
                
        List<Book> list = criteria.setFirstResult(currentPager.getFrom()).setMaxResults(currentPager.getTo()).list();
        currentPager.setList(list);
    }
  */  
  
    
    /*
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
       
        return getSession().createCriteria(Book.class).add(Restrictions.ilike(field,value,matchmode)).list();
    }
    */
            
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

    public List<Author> getAllAuthors() {
        return getSession().createCriteria(Author.class).list();
    }
    
    public List<Genre> getAllGenres() {
        return getSession().createCriteria(Genre.class).list();
    }
    

    public void update() {
      // Session session = sessionFactory.openSession();
      // Transaction transaction = session.getTransaction();
       //transaction.begin();
       
       for (Object object : currentPager.getList() ){
           Book book = (Book) object;
           if (book.isEdit()){
               getSession().update(book);
           }
       }
       
       
       //transaction.commit();
       
       //transaction.rollback();
       
       //session.flush();
       //session.close();
       
    }
   
     /*public void cancel() {
       
       for (Object object : currentPager.getList() ){
           Book book = (Book) object;
           //if (book.isEdit()){
             //  getSession().update(book);
           //}
       }
     
    }*/

    private void createBooksCountCreteria() {
        booksCountCriteria=DetachedCriteria.forClass(Book.class,"b");
    }
    
    private void createBooksCountCreteria(Criterion criterion) {
        booksCountCriteria=DetachedCriteria.forClass(Book.class,"b");
        booksCountCriteria.add(criterion);
        
        
        booksCountCriteria.createAlias("b.author", "author");
        booksCountCriteria.createAlias("b.genre", "genre");
        booksCountCriteria.createAlias("b.publisher", "publisher");
        
    }
    
    private void createBookListCreteria() {
        bookListCriteria=DetachedCriteria.forClass(Book.class,"b");
        createAliases();
    }

     private void createBookListCreteria(Criterion criterion) {
        bookListCriteria=DetachedCriteria.forClass(Book.class,"b");
        bookListCriteria.add(criterion);
        createAliases();
    }


    private void runCountCreteria() {
        
        Criteria criteria = booksCountCriteria.getExecutableCriteria(getSession());
        Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(total);
        
    }

     
    private void runBookListCreteria() {
        
        Criteria criteria = bookListCriteria.addOrder(Order.asc("b.name")).getExecutableCriteria(getSession());
        
        criteria.setProjection(bookProjection).setResultTransformer(Transformers.aliasToBean(Book.class));
                
        List<Book> list = criteria.setFirstResult(currentPager.getFrom()).setMaxResults(currentPager.getTo()).list();
        currentPager.setList(list);
        
    }
    
    private void createAliases(){
        //currentCriteria=DetachedCriteria.forClass(Book.class,"b");
        bookListCriteria.createAlias("b.author", "author");
        bookListCriteria.createAlias("b.genre", "genre");
        bookListCriteria.createAlias("b.publisher", "publisher");
        
        
        
    }
    
    public void refreshList(){
        runCountCreteria();
        runBookListCreteria();
    }  
    
    
    
   
    


    
}
