/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import comparator.ListComparator;
import db.DataHelper;
import entity.Author;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

/**
 *
 * @author User
 */
//@Named(value = "authorController")
@ManagedBean(eager = false)
@ApplicationScoped
public class AuthorController  implements Serializable,Converter {

    private List<SelectItem> selectItems = new ArrayList<>();
    private Map<Integer,Author> map;
    private List<Author> list;
    
    public AuthorController() {
        map = new HashMap<Integer,Author>();
        list= DataHelper.getInstance().getAllAuthors();
        Collections.sort(list,ListComparator.getInstance());
        
        for (Author author : list){
            map.put(author.getId(),author);
            selectItems.add(new SelectItem(author,author.getFio()));
        }
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    public Map<Integer, Author> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Author> map) {
        this.map = map;
    }

    public List<Author> getList() {
        return list;
    }

    public void setList(List<Author> list) {
        this.list = list;
    }

   /* @Override
    public String toString() {
        return "AuthorController{" + "selectItems=" + selectItems + ", map=" + map + ", list=" + list + '}';
        //return list.getFio();
    }
*/

    @Override
    public Object getAsObject(FacesContext context,UIComponent component,String value ){
        return map.get(Integer.valueOf(value));
    }
    
    @Override
    public String getAsString(FacesContext context,UIComponent component,Object value ){
        return ((Author)value).getId().toString();
    }

    
    
    
}
