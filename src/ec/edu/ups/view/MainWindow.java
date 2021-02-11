/**
 * @(#)Main.java	1.0, 2019-02-05
 * 
 * Universidad Politécnica Salesiana
 * Carrera de Computación
 * Cuenca - Ecuador	
 */
package ec.edu.ups.view;

import ec.edu.ups.controller.TreeController;
import ec.edu.ups.model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import javax.swing.*;
/**
 * Clase Principal del programa
 *
 * @version		1.0, 2019-02-05
 * @author		Roberto Serpa, Eduardo Zhizhpon
 *
 */
public class MainWindow extends JFrame implements ActionListener{
    
    private List<JCheckBox> checkList;
    
    private JPanel userPanel;
    private JPanel productPanel;
    
    private final TreeController control;
    private final String username;
    /**
     * constructor
     * @param person tipo Person
     * @param products tipo Product
     * @param control tipo TreeController
     */
    public MainWindow(Person person, List<Product> products, TreeController control){
        this.control = control;
        this.username = person.getUsername();
        
        mainComponent(person, products);
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                updatePreferences();
                saveData();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }


        });
    }
    /**
     * componentes iniciales
     * @param person tipo Person
     * @param products tipo Person
     */
        public void mainComponent(Person person, List<Product> products){
         
        setSize(1000, 700);
        setTitle("Preferencias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.fill = GridBagConstraints.BOTH;
        //Panel para la bienvenida del usuario
        this.userPanel = new JPanel(new BorderLayout());
        this.userPanel.add(createUserPanel(person), BorderLayout.NORTH);
        
        JPanel back = new JPanel(new FlowLayout());
        JButton close = new JButton("Cerrar Sesión");
        close.addActionListener(this);
        close.setActionCommand("close");
        close.setForeground(Color.red);
        back.add(close);
        this.userPanel.add(back, BorderLayout.SOUTH);
        
            gbcMain.gridx = 0;
            gbcMain.gridy = 0;
            gbcMain.weightx = 0;
            gbcMain.weighty = 1;
            this.userPanel.setBackground(Color.white);
            mainPanel.add(this.userPanel, gbcMain);
        
        this.productPanel = new JPanel(new BorderLayout());
            gbcMain.gridx = 1;
            gbcMain.gridy = 0;
            gbcMain.weightx = 1;
            gbcMain.weighty = 1;
            gbcMain.anchor = GridBagConstraints.CENTER;
        JScrollPane src = new JScrollPane(createProductPanel(control.listProduct(), 
                person.getProducts()));
        src.getVerticalScrollBar().setUnitIncrement(16);
        this.productPanel.add(src, BorderLayout.CENTER);
        
        mainPanel.add(this.productPanel, gbcMain);
        
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        
        c.add(mainPanel, BorderLayout.CENTER);
        
        
    }
    /**
     * interfaz de creacion de un usuario
     * @param person tipo Person
     * @return JPanel
     */
    public JPanel createUserPanel(Person person){
        
        Font font;
        
        JPanel panel = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 5, 5, 15);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        JLabel welcome = new JLabel("Bienvenido");
        font = welcome.getFont();
        welcome.setFont(new Font(font.getFontName(), font.getStyle(), 20));
        
            gbc.gridx = 0;
            gbc.gridy = 0;
        panel.add(welcome, gbc);
        
        JLabel user = new JLabel(person.getName() + " " + person.getLastname());
        font = user.getFont();
        user.setFont(new Font("Serif", Font.PLAIN, 30));
        
            gbc.gridx = 0;
            gbc.gridy = 1;
        panel.add(user, gbc);
        
        JLabel username = new JLabel(
                "Nombre de Usuario: "
                + person.getUsername());
        font = username.getFont();
        user.setFont(new Font(font.getFontName(), font.getStyle(), 15));
            gbc.gridx = 0;
            gbc.gridy = 2;
        panel.add(username, gbc);
        
        JLabel id = new JLabel(
                "Cédula: "
                + person.getId());
        font = id.getFont();
        user.setFont(new Font(font.getFontName(), font.getStyle(), 15));
            gbc.gridx = 0;
            gbc.gridy = 3;
        panel.add(id, gbc);
        
        JLabel age = new JLabel(
                "Edad: "
                + person.getAge());
        font = age.getFont();
        user.setFont(new Font(font.getFontName(), font.getStyle(), 15));
            gbc.gridx = 0;
            gbc.gridy = 4;
        panel.add(age, gbc);
        
        JButton settings = new JButton("Gestión Personas");
        settings.addActionListener(this);
        settings.setActionCommand("gestion");
            gbc.gridx = 0;
            gbc.gridy = 5;
        panel.add(settings, gbc);
        
        panel.setBackground(Color.white);
        
        
        
        return panel;
    }
    /**
     * 
     * @param products
     * @param userProducts
     * @return 
     */
    public JPanel createProductPanel(List<Product> products, 
        List<Product> userProducts){
        
        JPanel mainPanel = new JPanel(new GridBagLayout()); 
        
        GridBagLayout gLayout = new GridBagLayout();
        JPanel internalPanel;
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbcInternal = new GridBagConstraints();
        gbcInternal.insets = new Insets(15, 5, 5, 15);
        
        checkList = new ArrayList<>();
        int nUserProducts;
        int nProducts;
        JCheckBox cbox;
        JTextArea txtProductDes;
        JScrollPane scrProduct;
        JLabel name;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        try {
            //Bucle para agregar los CheckBox con su respectiva verificacion
            nProducts = products.size();
            for (int i = 0; i < nProducts; i++) {
                cbox = new JCheckBox();
                if (userProducts != null) {
                    
                    nUserProducts = userProducts.size();
                    for (int j = 0; j < nUserProducts; j++) {
                        if (userProducts.get(j).getId().equals(products.get(i).
                                getId()))
                            cbox.setSelected(true);

                    }
                }
                cbox.setText(products.get(i).getId());
                checkList.add(cbox);

                //Agregar JCheckBox
                internalPanel  = new JPanel(gLayout);
                    gbcInternal.ipady = 0;
                    gbcInternal.gridx = 0;
                internalPanel.add(checkList.get(i), gbcInternal);
                name = new JLabel(products.get(i).getName());
                    gbcInternal.gridx = 1;
                internalPanel.add(name, gbcInternal);

                //Agregar TextArea para la descripcion del producto
                txtProductDes = new JTextArea("Descripción:\n");
                txtProductDes.setColumns(50);//Maximo de columnas
                txtProductDes.setLineWrap(true);//Salto Automatico
                txtProductDes.setEditable(false);
                scrProduct = new JScrollPane(txtProductDes);
                scrProduct.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                txtProductDes.append(products.get(i).getDescription());
                    gbcInternal.gridx = 2;
                    gbcInternal.ipady = 100;
                internalPanel.add(scrProduct, gbcInternal);

                internalPanel.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));
                
                gbc.gridy = i;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weightx = 1;
                gbc.anchor = GridBagConstraints.WEST;
                mainPanel.add(internalPanel, gbc);
            }
        } catch (Exception e) {
            
        } finally {
            return mainPanel;
            
        }
    }
    /**
     * Actualiza los productos del usuario
     */
    public void updatePreferences(){
        try{
            Person person = control.getPersons().search(username);

            control.getPersons().update(person.getUsername(), person.getKey(), 
                    person.getAge(), person.getLastname(), person.getName(),
                    getSelectProducts(control.listProduct()));
        }catch(Exception e){
            
        }
        
    }
    /**
     * seleciona gustos de productos
     * @param products tipo Product
     * @return 
     */
    public List<Product> getSelectProducts(List<Product> products){
        List<Product> personProducts = new ArrayList<>();
        int n = this.checkList.size();
        for (int i = 0; i < n; i++) {
            if (this.checkList.get(i).isSelected()) {
                //System.out.println("i " + i);
                personProducts.add(products.get(i));
            }
        }
        return personProducts;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "gestion":
                openSettingPersonWindow();
                break;
                
            case "close":
                LoginWindow w = new LoginWindow();
                w.setVisible(true);
                super.setVisible(false);
                updatePreferences();
                try{
                control.save();
                }catch (Exception ex) {
                    
                }
                break;
                
        }
    }
    /**
     * habilita la ventana de ajustes de persona
     */
    public void openSettingPersonWindow() {
        SettingPersonWindow v = new SettingPersonWindow(control);
        v.setVisible(true);
    }
    
    /**
     * guardar datos
     */
    private void saveData() {
        try {
            control.save();
        } catch (Exception e) {
        }
    }
}
