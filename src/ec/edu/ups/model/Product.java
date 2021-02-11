/**
 * @(#)Main.java	1.0, 2019-02-05
 * 
 * Universidad Politécnica Salesiana
 * Carrera de Computación
 * Cuenca - Ecuador	
 */
package ec.edu.ups.model;

/**
 * Clase que contiene informacion basica de Product
 *
 * @version		1.0, 2019-02-05
 * @author		Roberto Serpa, Eduardo Zhizhpon
 *
 */
public class Product implements java.io.Serializable{
    private String id;
    private String name;
    private String description;
    
    private Product left;
    private Product right;

    public Product(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getLeft() {
        return left;
    }

    public void setLeft(Product left) {
        this.left = left;
    }

    public Product getRight() {
        return right;
    }

    public void setRight(Product right) {
        this.right = right;
    }
    
}
