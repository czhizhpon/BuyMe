/**
 * @(#)Main.java	1.0, 2019-02-05
 * 
 * Universidad Politécnica Salesiana
 * Carrera de Computación
 * Cuenca - Ecuador	
 */
package ec.edu.ups.controller;

import ec.edu.ups.model.Person;
import ec.edu.ups.model.Product;
import java.util.List;

/**
 * Clase que gestiona a la clase Person
 *
 * @version		1.0, 2019-02-05
 * @author		Roberto Serpa, Eduardo Zhizhpon
 *
 */
public class PeopleTreeController implements java.io.Serializable{
    private Person root;

    public PeopleTreeController() {
    }

    public Person getRoot() {
        return root;
    }

    public void setRoot(Person root) {
        this.root = root;
    }
    /**
     * crea persona
     * @param person tipo Person
     * @return boolean
     */
    public boolean create(Person person) {
        if (root==null) {
            root = person;
            System.out.println("root " + person.getId());
            return true;
        }else{
            insert(root, person);
            return true;
        }
    }
    /**
     * metodo auxiliar de create
     * @param people
     * @param person
     */
    public void insert(Person people, Person person) {
        
        if (person.getUsername().compareTo(people.getUsername()) > 0) {
            if (people.getRight() == null) {
                people.setRight(person);
            }else{
                insert(people.getRight(), person);
            }
        }else if (person.getUsername().compareTo(people.getUsername()) < 0) {
            if (people.getLeft() == null) {
                people.setLeft(person);
            }else{
                insert(people.getLeft(), person);
            }
        }else{
            throw new NullPointerException();
        }
        
    }
    /**
     * Edita persona
     * @param username tipo String
     * @param key tipo String
     * @param age tipo int
     * @param lastname tipo String
     * @param name tipo String
     * @param products tipo Product
     * @return boolean
     */
    public boolean update(String username, String key, int age, String lastname, 
            String name, List<Product> products) {
        
        if (root!=null) {
            
            if(root.getUsername().equals(username)){
                root.setAge(age);
                root.setKey(key);
                root.setLastname(lastname);
                root.setName(name);
                root.setProducts(products);
                
            }else{
                Person person=search(username);
                
                if (person == null) {
                    return false;
                }
                person.setAge(age);
                person.setKey(key);
                person.setLastname(lastname);
                person.setName(name);
                person.setProducts(products);
            }
        }
        return true;
    }
    /**
     * Elimina persona
     * @param username tiop Strin
     * @return boolean
     */
    public boolean delete(String username){
        if (search(username) == null) {
            return false;
        }
        root = deletePerson(root, username);
        return true;
    }
    /**
     * metodo auxiliar de delete
     * @param person tipo Person
     * @param username tiop boolean
     * @return Person
     */
    public Person deletePerson(Person person, String username){
        
        if(person==null)
            return person;
        person = search(username);
//        if(person.getUsername().compareTo(username)<0)
//            person.setLeft(deletePerson(person.getLeft(), username));
//        else if(person.getId().compareTo(username)>0)
//            person.setRight(deletePerson(person.getRight(), username));
//        else{
            if(person.getLeft()==null)
                return person.getRight();
            else if(person.getRight()==null)
                return person.getLeft();
            person.setAge(minor(person.getRight()).getAge());
            person.setId(minor(person.getRight()).getId());
            person.setKey(minor(person.getRight()).getKey());
            person.setLastname(minor(person.getRight()).getLastname());
            person.setName(minor(person.getRight()).getName());
            person.setUsername(minor(person.getRight()).getUsername());
            person.setProducts(minor(person.getRight()).getProducts());
            
            person.setRight(deletePerson(person.getRight(), username));
//        }
        return person;
    }
    /**
     * metodo auxiliar de deletePerson
     * @param node tipo Person
     * @return Person
     */
    public Person minor(Person node){
        if (node.getLeft()!=null) {
            node=minor(node.getLeft());
        }
        return node;
    }
    /**
     * busca a la persona
     * @param username  tipo String
     * @return Person
     */
    public Person search(String username) {
        
        Person person = searchPerson(root, username);
        
        return person;
    }
    /**
     * metodo auxiliar de search
     * @param person tipo Person
     * @param username tipo String
     * @return Person
     */
    public Person searchPerson(Person person, String username){
        
        if (root!=null) {
            if (root.getUsername().equals(username)) {
                return root;
            }else if (person!=null) {
                if (username.equals(person.getUsername())) {
                    return person;
                }
                if (username.compareTo(person.getUsername())>0) {
                    
                    person = searchPerson(person.getRight(), username);
                }else if (username.compareTo(person.getUsername())<0) {
                    person = searchPerson(person.getLeft(), username);
                }
            }
        }
        return person;
    }
    
}
