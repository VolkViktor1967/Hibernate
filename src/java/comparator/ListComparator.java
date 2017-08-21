/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator;

import java.util.*;
/**
 *
 * @author User
 */
public class ListComparator {
    private static ObjectComparator listComtarator;

   public static Comparator getInstance(){
       if (listComtarator==null){
           listComtarator = new ObjectComparator();
       }
       return listComtarator;
   }
    
    private static class ObjectComparator implements Comparator{

        public ObjectComparator() {
        }
        public int compare(Object a1, Object a2){
            return a1.toString().compareTo(a2.toString());
        }
        
    }
    
}
