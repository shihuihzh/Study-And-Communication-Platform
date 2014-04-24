package com.study.platform.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.study.platform.dto.GroupVo;
import com.study.platform.pojo.User;
import com.study.platform.util.PageController;

/**
 * Generic Manager that talks to GenericDao to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) managers
 * for your domain objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Updated by jgarcia: added full text search + reindexing
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericService<T, PK extends Serializable> {

    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if
     * nothing is found.
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    T get(PK id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * @param id the identifier (primary key) of the object to get
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     * @param object the object to save
     * @return the updated object
     */
    T save(T object);

    /**
     * Generic method to delete an object
     * @param object the object to remove
     */
    void remove(T object);

    /**
     * Generic method to delete an object based on class and id
     * @param id the identifier (primary key) of the object to remove
     */
    void remove(PK id);

    /**
     * Generic method to search for an object.
     * @param searchTerm the search term
     * @param clazz type of class to search for.
     * @return a list of matched objects
     */
    List<T> search(String searchTerm, Class clazz);
    /**
     * Generic method to regenerate full text index of the persistent class T
     */
    void reindex();

    /**
     * Generic method to regenerate full text index of all indexed classes
     *
     * @param async
     *            true to perform the reindexing asynchronously
     */
    void reindexAll(boolean async);
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * 
     * 描述：条件查找
     * 
     * @Title: findByProp 
     * @param pc
     * @param term
     * @return
     */
	List<T> findByProp(PageController pc, Map term, String orderBy);
	
	/**
	 * 
	 * 描述：条件查找数量
	 * 
	 * @Title: getCountByProp 
	 * @param term
	 * @return
	 */
	Number getCountByProp(Map term);

	
}
