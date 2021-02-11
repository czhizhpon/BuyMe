/**
 * @(#)Main.java	1.0, 2019-02-05
 * 
 * Universidad Politécnica Salesiana
 * Carrera de Computación
 * Cuenca - Ecuador	
 */
package ec.edu.ups.controller;

import ec.edu.ups.model.Product;

/**
 * Clase que gestiona a la clase Product
 *
 * @version		1.0, 2019-02-05
 * @author		Roberto Serpa, Eduardo Zhizhpon
 *
 */
public class ProductTreeController implements java.io.Serializable{
    private Product root;

    public ProductTreeController() {
    }

    public Product getRoot() {
        return root;
    }

    public void setRoot(Product root) {
        this.root = root;
    }
    /**
     * crea un producto
     * @param product tipo Product
     * @return boolean
     */
    public boolean create(Product product) {
        
        if (root==null) {
            root=product;
            return true;
        }else{
            
            insert(root, product);
            return true;
        }
        
    }
    /**
     * metodo auxiliar de create
     * @param products tipo Product
     * @param product tipo Product
     */
    public void insert(Product products, Product product) {
        
        if (product.getId().compareTo(products.getId()) > 0) {
            if (products.getRight() == null) {
                products.setRight(product);
            }else{
                insert(products.getRight(), product);
            }
        }else if (product.getId().compareTo(products.getId()) < 0) {
            if (products.getLeft() == null) {
                products.setLeft(product);
            }else{
                insert(products.getLeft(), product);
            }
        }else{
            throw new NullPointerException();
        }
    }
    /**
     * edita datos de un producto
     * @param id tipo String
     * @param name tipo String
     * @param description tipo String
     * @return boolean
     */
    public boolean update(String id, String name, String description) {
        if (root!=null) {
            
            if(root.getId().equals(id)){
                root.setId(id);
                root.setName(name);
                root.setDescription(description);
            }else{
                Product product=search(id);
                
                product.setId(id);
                product.setName(name);
                product.setDescription(description);
            }
        }
        return true;
    }
    /**
     * elimina un producto
     * @param id tipo String
     * @return boolean
     */
    public boolean delete(String id){
        if (search(id) == null) {
            return false;
        }
        root = deleteProduct(root, id);
        return true;
    }
    /**
     * metodo auxiliar de delete
     * @param product tipo Product
     * @param id tipo String
     * @return Product
     */
    public Product deleteProduct(Product product, String id){
        
        if(product==null)
            return product;
        if(product.getId().compareTo(id)<0)
            product.setLeft(deleteProduct(product.getLeft(), id));
        
        else if(product.getId().compareTo(id)>0)
            product.setRight(deleteProduct(product.getRight(), id));
        
        else{
            if(product.getLeft()==null)
                return product.getRight();
            else if(product.getRight()==null)
                return product.getLeft();
            
            product.setId(minor(product.getRight()).getId());
            product.setName(minor(product.getRight()).getName());
            product.setDescription(minor(product.getRight()).getDescription());
            
            product.setRight(deleteProduct(product.getRight(), id));
        }
        return product;
    }
    /**
     * metodo auxiliar de deleteProduct
     * @param node tipo Product
     * @return Product
     */
    public Product minor(Product node){
        if (node.getLeft()!=null) {
            node=minor(node.getLeft());
        }
        return node;
    }
    /**
     * busca un producto
     * @param id tipo String
     * @return Product
     */
    public Product search(String id) {
        
        Product product = searchProduct(root, id);
        
        return product;
    }
    /**
     * metodo auxiliar de search
     * @param products tipo Product
     * @param id tipo String
     * @return Product
     */
    public Product searchProduct(Product products, String id){
        System.out.println("id " + id);
        if (root!=null) {
            if(root.getId().equals(id)){
                return root;
            }else if (products!=null) {
                
                if (id.equals(products.getId())) {
                    return products;
                }
                if (id.compareTo(products.getId())>0) {
                    products = searchProduct(products.getRight(), id);
                    
                }else if (id.compareTo(products.getId())<0) {
                    products = searchProduct(products.getLeft(), id);
                }
            }
        }
        return products;
    }
    
}
