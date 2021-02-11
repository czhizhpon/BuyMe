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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal de gestionamiento de datos
 *
 * @version		1.0, 2019-02-05
 * @author		Roberto Serpa, Eduardo Zhizhpon
 *
 */
public class TreeController implements java.io.Serializable {
    private PeopleTreeController people;
    private ProductTreeController products;

    /**
     * constructor
     */
    public TreeController() {
        this.people = new PeopleTreeController();
        this.products = new ProductTreeController();
    }
    
    /**
     * Getters y Setters
     * @return 
     */
    public PeopleTreeController getPersons() {
        return people;
    }

    public void setPeople(PeopleTreeController people) {
        this.people = people;
    }

    public ProductTreeController getProducts() {
        return products;
    }

    public void setProducts(ProductTreeController products) {
        this.products = products;
    }
    /**
     * Obttiene el Hash MD5 del codigo
     * @param key tipo String
     * @return Sring
     */
    public String getMd5(String key) {
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(key.getBytes()); 
  
            // Convert byte array into signum representation               
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    /**
     * valida la cedula
     * @param cedula tipo String
     * @return boolean
     */
    public boolean validId(String cedula) {
        try{
            //controlInteger(cedula);
            int[] coeficientes = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
            int suma = 0;
            int digVerificador = 0;

            if (cedula.length() == 10){
                int digProvincia = Integer.parseInt(cedula.substring(0, 2));
                int digTipo = Integer.parseInt(cedula.substring(2, 3));
                int verificadorCed = Integer.parseInt(cedula.substring(9,10));

                if ((digTipo < 6) && ((digProvincia) <= 24)){
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digVerificador = Integer.parseInt(cedula.substring(i, i + 1))* coeficientes[i];
                        suma += ((digVerificador % 10) + (digVerificador / 10));
                    }
                    if ((suma % 10 == 0) && (suma % 10 == verificadorCed)) {
                        return true;
                    } else if ((10 - (suma % 10)) == verificadorCed) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
            
        }catch(NumberFormatException ex){
            throw ex;
        }
    }
    /**
     * valida la creacion de las contrasenas
     * @param password tipo String
     * @param confirmPassword tipo String
     * @return (boolean)
     */
    public boolean validCreatePaswword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) 
            return true;
        return false;
    }
    /**
     * valida la contrasena para avilitar el login
     * @param password tipo String
     * @param person tipo Person
     * @return boolean
     */
    public boolean validPasword(String password, Person person) {
        if (person.getKey().equals(getMd5(password))) 
            return true;
        return false;
    }
    /**
     * valida el username de la base de datos
     * @param username tipo String
     * @return boolean
     */
    public boolean validUsername(String username) {
        if (people.search(username) != null) {
            return true;
        }
        return false;
    }
    
    /**
     * valida el name del producto de la base de datos
     * @param id tipo String
     * @return boolean
     */
    public boolean validProduct(String id) {
        if (products.search(id) != null) {
            return true;
        }
        return false;
    }
    
    /**
     * almacena una lista de personas
     * @return List
     */
    public List<Person> listPerson() {
        
        List<Person> listPeople = new ArrayList<>();
        if (people.getRoot() == null) {
            return null;
        }else{
            listPerson(people.getRoot(), listPeople);
        }
        return listPeople;
    }
    /**
     * metodo auxiliar de listPerson() que sirve para listar de manera recursiva
     * InOrden
     * @param person tipo Person
     * @param listPeople tipo Person
     */
    public void listPerson(Person person, List<Person> listPeople) {
        
        if (person.getLeft() != null) {
            listPerson(person.getLeft(), listPeople);
        }
        
        listPeople.add(person);
        
        if (person.getRight()!= null) {
            listPerson(person.getRight(), listPeople);
        }
    }
    /**
     * almacena una lista de productos
     * @return List
     */
    public List<Product> listProduct() {
        List<Product> listProducts = new ArrayList<>();
        
        if (products.getRoot() == null) {
            return null;
        }else{
            listProduct(products.getRoot(), listProducts);
        }
        
        return listProducts;
    }
    
    /**
     * Metodo rescursivo para listar en InOrden
     * @param product tipo Product
     * @param products  tipo Product
     */
    public void listProduct(Product product, List<Product> products) {
        
        if (product.getLeft() != null) {
            listProduct( product.getLeft(), products);
        }
        
        products.add(product);
        
        if (product.getRight() != null) {
            listProduct( product.getRight(), products);
        }
        
    }
    /**
     * verifica la lista de productos de una persona
     * @param person tipo Person
     * @return List
     */
    public List<Person> getPersonProducts(Person person) {
        
        return null;//ver
    }
    /**
     * envia datos al controlador de personas
     * @param password tipo String
     * @param username tipo String
     * @param id tipo String
     * @param name tipo String
     * @param lastname tipo String
     * @param age tipo int
     * @param products tipo Product
     */
    public void createPerson(String password, String username, String id, 
            String name, String lastname, int age, List<Product> products){

        Person person = new Person(getMd5(password), username,
                id, name, lastname, age, products);
        
        people.create(person);
    }
    /**
     * envia datos al controlador de productos
     * @param id tipo String
     * @param name tipo String
     * @param description tipo String
     */
    public void createProduct(String id, String name, String description){
        if (products==null) {
            products = new ProductTreeController();
        }
        
        Product product = new Product(id, name, description);

        products.create(product);
    }
    /**
     * actualiza datos de una persona
     * @param username tipo String
     * @param password tipo String
     * @param age tipo int
     * @param lastname tipo String
     * @param name tipo String
     * @param products  tipo Product
     */
    public void updatePerson(String username,String password, int age, String lastname, 
            String name, List<Product> products){
        
        people.update(username, getMd5(password), age, lastname, name, 
                products);
    }
    
    /**
     * guarda los datos en memoria en un objeto general
     * @throws IOException 
     */
    public void save() throws IOException {
        FileOutputStream file = null;
        ObjectOutputStream write = null;
         try {
            file = new FileOutputStream("data/people.obj");
            write = new ObjectOutputStream(file);
            
            write.writeObject(this.people);
            file.close();
            file = new FileOutputStream("data/products.obj");
            write = new ObjectOutputStream(file);
            
            write.writeObject(this.products);
            
        } catch (Exception e) {
            
        } finally {
            file.close();
        }
    }
    
    /**
     * restaura los datos de memoria
     * @throws IOException 
     */
    public void restore() throws IOException {
        FileInputStream file = null;
        ObjectInputStream read = null;
        
        try {
            file = new FileInputStream("data/people.obj");
            read = new ObjectInputStream(file);
            PeopleTreeController peopleData = (PeopleTreeController)read.
                    readObject();
            setPeople(peopleData);
            file.close();
            
            file = new FileInputStream("data/products.obj");
            read = new ObjectInputStream(file);
            ProductTreeController productsData = (ProductTreeController)read.
                    readObject();
            setProducts(productsData);
            
        } catch (Exception e) {
            System.out.println("error " 
                    + e );
        } finally {
            file.close();
        }
    }
}
