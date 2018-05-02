/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemamanejoinventarios;

/**
 *
 * @author Danny Orozco
 */
import bitacora.Bitacora;
import modelo.ImplementacionDAOPostgres;

public class SistemaManejoInventarios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Bitacora bitacora = new Bitacora();
        ImplementacionDAOPostgres implementacionDAOPostgres = new ImplementacionDAOPostgres();
        implementacionDAOPostgres.insertarUsuario();
        
    }
    
}
