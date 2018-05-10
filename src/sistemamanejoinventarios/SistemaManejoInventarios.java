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
        //LocalDate localDate = LocalDate.now();
        //implementacionDAOPostgres.insertarUsuario(100002222, "Monika", "Galindo", "Soto", "monikagalindo@gmail.com", "Usuario", "1234");
        //implementacionDAOPostgres.insertarArticulo("escoba","marca patito",2,"","20180507",100,"Limpieza","CDT-OC-1000");
        //implementacionDAOPostgres.mostrarUsuario(1111);
        //implementacionDAOPostgres.listarUsuario();
        //implementacionDAOPostgres.eliminarUsuario(100002222);
        implementacionDAOPostgres.modificarUsuario(203330445,203330555,"Jose","Perez","Molina","joseperez@gmail.com","Administrador");
        
    }
    
}
