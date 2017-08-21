/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import comparator.ListComparator;
import db.DataHelper;
import entity.Genre;
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
 * @genre User
 */
//@Named(value = "genreController")
@ManagedBean(eager = false)
@ApplicationScoped
public class GenreController  implements Serializable,Converter {

    private List<SelectItem> selectItems = new ArrayList<>();
    private Map<Integer,Genre> map;
    private List<Genre> list;
    
    public GenreController() {
        map = new HashMap<Integer,Genre>();
        list= DataHelper.getInstance().getAllGenres();
        Collections.sort(list,ListComparator.getInstance());
        
        for (Genre genre : list){
            map.put(genre.getId(),genre);
            selectItems.add(new SelectItem(genre,genre.getName()));
        }
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    public Map<Integer, Genre> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Genre> map) {
        this.map = map;
    }

    public List<Genre> getList() {
        return list;
    }

    public void setList(List<Genre> list) {
        this.list = list;
    }

   /* @Override
    public String toString() {
        return "GenreController{" + "selectItems=" + selectItems + ", map=" + map + ", list=" + list + '}';
        //return list.getFio();
    }
*/

    @Override
    public Object getAsObject(FacesContext context,UIComponent component,String value ){
        return map.get(Integer.valueOf(value));
    }
    
    @Override
    public String getAsString(FacesContext context,UIComponent component,Object value ){
        return ((Genre)value).getId().toString();
    }
    
    
}

