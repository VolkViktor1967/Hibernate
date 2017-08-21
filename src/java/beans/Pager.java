/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Pager<T> {
    private int selectedPageNumber =1;
    private int booksCountOnPage =2;
    private long totalBooksCount; 
    private List<T> list;
    private int pageCount;
    private ArrayList<Integer> pageNambers = new ArrayList<>();

    
    public int getFrom() {
         return selectedPageNumber*booksCountOnPage - booksCountOnPage;
    }
    public int getTo(){
        return booksCountOnPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getSelectedPageNumber() {
        return selectedPageNumber;
    }

    public void setSelectedPageNumber(int selectedPageNumber) {
        this.selectedPageNumber = selectedPageNumber;
    }

    public int getBooksCountOnPage() {
        return booksCountOnPage;
    }

    public void setBooksCountOnPage(int booksCountOnPage) {
        this.booksCountOnPage = booksCountOnPage;
        fillPageNumbers(totalBooksCount,booksCountOnPage);
    }

    public long getTotalBooksCount() {
        return totalBooksCount;
    }

    public void setTotalBooksCount(long totalBooksCount) {
        this.totalBooksCount = totalBooksCount;
        fillPageNumbers(totalBooksCount,booksCountOnPage);
    }
    
        public ArrayList<Integer> getPageNambers() {
        return pageNambers;
    }

    public void setPageNambers(ArrayList<Integer> pageNambers) {
        this.pageNambers = pageNambers;
    }
   
    private void fillPageNumbers(long totalBookCount, int bookOnPage) {
        
     if (totalBookCount % bookOnPage ==0){
         pageCount = bookOnPage > 0 ? (int)(totalBookCount/bookOnPage):0;
     }
     else{
         pageCount = bookOnPage > 0 ? (int)(totalBookCount/bookOnPage)+1:0;
     } 
         
       /* int pageCount = totalBookCount>0 ? (int)(totalBookCount/bookOnPage):0;*/
        pageNambers.clear();
        //if ((totalBookCount%bookOnPage)>0)  pageCount++;

        for (int i=1; i<=pageCount;i++){
            pageNambers.add(i);
        }
        
    }    
    
}
