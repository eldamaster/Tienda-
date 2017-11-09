
import Vistas.JFPrincipal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        JFPrincipal nuevoPrincipal = new JFPrincipal();
        nuevoPrincipal.setExtendedState(nuevoPrincipal.MAXIMIZED_BOTH);
        nuevoPrincipal.setVisible(true);
    }
    
}
