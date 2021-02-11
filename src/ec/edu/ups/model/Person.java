/**
 * @(#)Main.java	1.0, 2019-02-05
 * 
 * Universidad Politécnica Salesiana
 * Carrera de Computación
 * Cuenca - Ecuador	
 */
package ec.edu.ups.model;

import java.util.List;

/**
 * Clase que contiene informacion basica de Person
 *
 * @version		1.0, 2019-02-05
 * @author		Roberto Serpa, Eduardo Zhizhpon
 *
 */
public class Person implements java.io.Serializable{
    private String key;
    private String username;
    private String id;
    private String name;
    private String lastname ;
    private int age;
    private List<Product> products;
    
    private Person left;
    private Person right;

    public Person(String key, String username, String id, String name, String lastname, int age, List<Product> products) {
        this.key = key;
        this.username = username;
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.products = products;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Person getLeft() {
        return left;
    }

    public void setLeft(Person left) {
        this.left = left;
    }

    public Person getRight() {
        return right;
    }

    public void setRight(Person right) {
        this.right = right;
    }
    
}
