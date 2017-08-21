/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import beans.Book;
import entity.Book;
import beans.BookList;
import beans.Pager;
import db.DataHelper;
import db.Database;
import enums.SearchType;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
import org.apache.jasper.tagplugins.jstl.ForEach;


/**
 *
 * @author User
 */


        
@ManagedBean(eager = true)
@SessionScoped

/*@Named(value = "searchController")
@SessionScoped*/

public class SearchController implements Serializable {
    
    private boolean requestFromPage=false;
    private int bookOnPage =2;
    //private int pageCount;
    
    private int selectedGenreId;
    private char selectedLetter;
    //private long selectedPageNumber=1;
    private long totalBookCount;
    //private ArrayList<Integer> pageNambers = new ArrayList<>();
    
    
    private SearchType searchType;
    //private List<Book> currentBookList;
    private Character[] russianLetters;
    private String searchString;
    private String currentSql;
    
    private boolean editMode;
    
    private Pager<Book>  pager = new Pager<Book>();
    
    private transient int row = -1;

    public Pager<Book> getPager() {
        return pager;
    }

    public void setPager(Pager<Book> pager) {
        this.pager = pager;
    }
    
    
    
    public int getRow(){
        row+=1;
        return row;
    }

    public boolean isEditMode() {
           return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
    public void switchEditMode() {
        this.editMode = !this.editMode;
        
    }
    
    public SearchController() {
        
         getAllBook();
         /*
         ResourceBundle bandle = ResourceBundle.getBundle("lns.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
         searchList.put(bandle.getString("author_name"), SearchType.AUTHOR);
         searchList.put(bandle.getString("book_name"), SearchType.TITLE);
         */
    }
    

    public void setBookOnPage(int bookOnPage) {
        this.bookOnPage = bookOnPage;
    }

    public void setSelectedGenreId(int selectedGenreId) {
        this.selectedGenreId = selectedGenreId;
    }

    public void setSelectedLetter(char selectedLetter) {
        this.selectedLetter = selectedLetter;
    }
/*
    public void setSelectedPageNumber(long selectedPageNumber) {
        this.selectedPageNumber = selectedPageNumber;
    }
*/
    public void setTotalBookCount(long totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

  

    public int getBookOnPage() {
        return bookOnPage;
    }

    public int getSelectedGenreId() {
        return selectedGenreId;
    }

    public char getSelectedLetter() {
        return selectedLetter;
    }
/*
    public long getSelectedPageNumber() {
        return selectedPageNumber;
    }
*/
    public long getTotalBookCount() {
        return totalBookCount;
    }

   
    public void setRussianLetters(Character[] russianLetters) {
        this.russianLetters = russianLetters;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }
    /*
    public byte[] GetImage(int index){
        
        Book book = currentBookList.get(index);
        return book.getImage();
}*/
/*
    public void setCurrentBookList(List<Book> currentBookList) {
        this.currentBookList = currentBookList;
    }

     
    public List<Book> getCurrentBookList() {
         return currentBookList;
    }
    */
    
    
    
   /* public void setSearchList(Map<String, SearchType> searchList) {
        SearchController.searchList = searchList;
    }

    public Map<String, SearchType> getSearchList() {
        return searchList;
    }*/
       

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }
    public SearchType getSearchType() {
        return searchType;
    }
        
    
        
    private void FillBookBySql(String sqlstr){
        /*
        StringBuilder sqlBuilder = new StringBuilder(sqlstr);
        currentSql = sqlstr;
                

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
       
        
        try {
            conn = Database.getConnection();
            stmt = conn.createStatement();
            
            if (!requestFromPage) {
                rs = stmt.executeQuery(sqlBuilder.toString());
                rs.last();
                totalBookCount = rs.getRow();
                fillPageNumbers(totalBookCount, bookOnPage);

            }
            
            if (totalBookCount>bookOnPage){ 
                sqlBuilder.append(" limit ").append((selectedPageNumber)*bookOnPage-bookOnPage).append(",").append(bookOnPage);
            }
            
            rs = stmt.executeQuery(sqlBuilder.toString());
            
            currentBookList = new ArrayList<Book>();
            
            while (rs.next()){
                Book book = new Book();
                
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setPageCount(rs.getInt("page_count"));
                book.setIsbn(rs.getString("isbn"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
                book.setPublishYear(rs.getLong("publish_year"));
                book.setPublisher(rs.getString("publisher"));
                book.setImage(rs.getBytes("image"));
                book.setDescr(rs.getString("descr"));
                
                
                currentBookList.add(book);
            }
                                        
        } catch (SQLException ex) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            try {
                if (stmt!=null) stmt.close();
                if (rs!=null) rs.close();
                if (conn!=null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(currentBookList.size());
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        session.setAttribute("currentBookList", currentBookList);
        
        ArrayList<Book> list = (ArrayList<Book>)session.getAttribute("currentBookList");
        
        */
        return ;
    }
    /*
    public  void getBookList(){
        if (!currentBookList.isEmpty()){
            //return currentBookList;
        }
        else{
            FillBookBySql("select * from library.book");
        }
            
    }*/
    
    
    public  void getAllBook(){
        
        //currentBookList=DataHelper.getInstance().getAllBooks();
        DataHelper.getInstance().getAllBooks(pager);
        
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        //session.setAttribute("currentBookList", currentBookList);
   
    }
    
    public  void getBookByGenre(){
        FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getRequestParameterMap().get("genre_id");
        selectedGenreId = Integer.valueOf(id);
        
        DataHelper.getInstance().getBookByGenre(selectedGenreId,pager);
    }
    
    
    
     public  void FillBookBySearch(){
        
        
        //selectedPageNumber=1;
        requestFromPage=false;
        
        if (searchString.trim().length()==0){
            getAllBook();
            return;
        }
        
        if (searchType.equals(SearchType.AUTHOR)) {
            DataHelper.getInstance().getBookByAuthor(searchString,pager);
        }
        else {
            DataHelper.getInstance().getBookByName(searchString,pager);
        }
        
        selectedGenreId =-1;
        
        
    }
    
     public  void FillBookByLetters(){

        FacesContext context = FacesContext.getCurrentInstance();
        String letter = context.getExternalContext().getRequestParameterMap().get("letter");       
        
        //selectedPageNumber=1;
        requestFromPage=false;
              
        DataHelper.getInstance().getBookByLetter(letter.toLowerCase(),pager);
        
        selectedGenreId =-1;
    }
     
     public Character[] getRussianLetters(){
        
         //можно написать так 
        // Character[] letters =  new Character[]{'А','Б',........};
        
        Character[] letters =  new Character[33];
        letters[0]='А';
        letters[1]='Б';
        letters[2]='В';
        letters[3]='Г';
        letters[4]='Д';
        letters[5]='Е';
        letters[6]='Е';
        letters[7]='Ж';
        letters[8]='З';
        letters[9]='И';
        letters[10]='Й';
        letters[11]='К';
        letters[12]='Л';
        letters[13]='М';
        letters[14]='Н';
        letters[15]='О';
        letters[16]='П';
        letters[17]='Р';
        letters[18]='С';
        letters[19]='Т';
        letters[20]='У';
        letters[21]='Ф';
        letters[22]='Х';
        letters[23]='Ц';
        letters[24]='Ч';
        letters[25]='Ш';
        letters[26]='Щ';
        letters[27]='Ъ';
        letters[28]='Ы';
        letters[29]='Ь';
        letters[30]='Э';
        letters[31]='Ю';
        letters[32]='Я';
        
        return letters;
    }
     
     
     

    public void searchStringChanged(ValueChangeEvent event){
        searchString= event.getNewValue().toString();
    }
    
    /*
    private void fillPageNumbers(long totalBookCount, int bookOnPage) {
        
        
     if (totalBookCount % bookOnPage ==0){
         pageCount = bookOnPage > 0 ? (int)(totalBookCount/bookOnPage):0;
     }
     else{
         pageCount = bookOnPage > 0 ? (int)(totalBookCount/bookOnPage)+1:0;
     } 
         
      
        pageNambers.clear();
        //if ((totalBookCount%bookOnPage)>0)  pageCount++;

        for (int i=1; i<=pageCount;i++){
            pageNambers.add(i);
        }
        
    }
    */
    public void updateBooks() {
        row=-1;
        DataHelper.getInstance().update();
       switchEditMode();
         for(Book book:pager.getList()){
            book.setEdit(false);
        }
         
        DataHelper.getInstance().refreshList(); 
    }
    public void cansel() {
        //DataHelper.getInstance().cancel();
        switchEditMode();
        for(Book book:pager.getList()){
            book.setEdit(false);
        }
        DataHelper.getInstance().refreshList(); 
    }   
        
    
   /* public void updateBooks() {
        
        PreparedStatement prepStmt;
        Connection conn=null;
        Statement stmt=null;
        
                
        try {
            conn = Database.getConnection();
            prepStmt = conn.prepareStatement("update library.book set name=?,isbn=?,page_count=?,publish_year=?,descr=? where id=? ");
            
            for (Book book: currentBookList){
                if (!book.isEdit()) continue;
                prepStmt.setString(1,book.getName());
                prepStmt.setString(2,book.getIsbn());
                prepStmt.setInt(3,book.getPageCount());
                prepStmt.setLong(4,book.getPublishYear());
                prepStmt.setString(5,book.getDescr());
                
                prepStmt.setLong(6,book.getId());
                
               
                
                prepStmt.addBatch();
            }

             prepStmt.executeBatch();
                                        
        } catch (SQLException ex) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            try {
                if (stmt!=null) stmt.close();
                if (conn!=null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        switchEditMode();        
         for(Book book:currentBookList){
            book.setEdit(false);
        }
        //return "books";
    }
    */
    /*
    public void cansel() {
        switchEditMode();
        for(Book book:currentBookList){
            book.setEdit(false);
        }
    }
    */
    public void booksOnPageChanged(ValueChangeEvent e){
        row=-1;
        //if (isEditMode()) cansel();
        requestFromPage=false;
        pager.setBooksCountOnPage(Integer.valueOf(e.getNewValue().toString()));
        pager.setSelectedPageNumber(1);
        //DataHelper.getInstance().runCurrentCriteria();
        DataHelper.getInstance().refreshList();
     
    }
    public void selectPage(){
        row=-1;
        FacesContext context = FacesContext.getCurrentInstance();
        int _selectedPageNumber = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("page_namber"));       
        pager.setSelectedPageNumber(_selectedPageNumber); 
        //if (isEditMode()) cansel();
        requestFromPage=true;
        //DataHelper.getInstance().runCurrentCriteria();
        DataHelper.getInstance().refreshList();
        
     }


}














///////////////////////////////////


   
















