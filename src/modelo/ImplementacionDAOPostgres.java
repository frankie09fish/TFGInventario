/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Danny Orozco
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.ProductoDAO;
import modelo.dao.UsuarioDAO;


public class ImplementacionDAOPostgres implements InterfaceDAO{

    //Metodo para insertar usuario en 
    @Override
    public void insertarUsuario(int ced, String nom, String pA, String sA, String corr, String tip, String pw) {
        LOGGER.log(Level.INFO, "Estableciendo conexión con Base de datos");
        Connection con = dbHelper.connectDatabase("localhost", "5432", "inventarioCentroDiurno","postgres", "postgres");
        LOGGER.log(Level.INFO, "Insertando datos de usuario");
        dbHelper.insertarUsuario(con, ced, nom, pA, sA, corr, tip, pw);
    }

    ProductoDAO productoDAO = new ProductoDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<ProductoDAO> listaProductos = new ArrayList<ProductoDAO>();
    List<UsuarioDAO> listaUsuarios = new ArrayList<UsuarioDAO>();

     private final static Logger LOGGER = Logger.getLogger("Conexion.ImplementacionDAOPostgres");
    
    PostgreSQLDBHelper dbHelper = new PostgreSQLDBHelper();

    @Override
    public List<ProductoDAO> getArticulosPorFechaEntrada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoDAO> getArticulosPorFechaSalida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoDAO> getArticulosPorNombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoDAO> getArticulosPorRequisicion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoDAO> getArticulosPorCategoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoDAO> getArticulosPorOrdenCompra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoDAO> getArticulosPorFechaVencimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoDAO> getArticulosPorNumeroActivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarArticulo(String nom, String desc, int cant, String cA, String fE, float cost, String cate, String oC) {
        LOGGER.log(Level.INFO, "Estableciendo conexión con Base de datos");
        Connection con = dbHelper.connectDatabase("localhost", "5432", "inventarioCentroDiurno","postgres", "postgres");
        LOGGER.log(Level.INFO, "Insertando datos de articulo");
        dbHelper.insertarArticulo(con, nom, desc, cant, cA, fE, cost, cate, oC);
    }

    @Override
    public void eliminarArticulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductoDAO mostrarArticulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateArticulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarRequisicion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductoDAO> getArticulosInforme() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateContrasena() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContrasena() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    

    
    
    public UsuarioDAO mostrarUsuario(int ced){
        UsuarioDAO usuarioObtenido = new UsuarioDAO();
        
        try{
        Connection con = dbHelper.connectDatabase("localhost", "5432", "inventarioCentroDiurno","postgres", "postgres");
        usuarioObtenido = dbHelper.mostrarUsuario(con, ced);
        }
        catch(Exception e){
            System.out.println("Error al obtener usuario: " + e);
        }
        System.out.println("Valores de usuario obtenido: ");
        System.out.println("Descargando lista de usuarios: ");
        System.out.println("Cedula: " + usuarioObtenido.getCedula());
        System.out.println("Nombre: " + usuarioObtenido.getNombre());
        System.out.println("Primer apellido: " + usuarioObtenido.getPrimerApellido());
        System.out.println("Segundo apellido: " + usuarioObtenido.getSegundoApellido());
        System.out.println("Correo: " + usuarioObtenido.getCorreo());
        System.out.println("Tipo: " + usuarioObtenido.getTipo());
      
        return usuarioDAO;
    }
    
    
    
    public List<UsuarioDAO> listarUsuario(){
        ;
         List<UsuarioDAO> listaValores = new ArrayList<UsuarioDAO>();
        
        try{
            LOGGER.log(Level.INFO, "Estableciendo conexión con Base de datos");
            Connection con = dbHelper.connectDatabase("localhost", "5432", "inventarioCentroDiurno","postgres", "postgres");
            LOGGER.log(Level.INFO, "Obteniendo lista de usuarios");
            listaValores = dbHelper.listarUsuario(con);
           for(UsuarioDAO usuarioObtenido: listaValores){
               System.out.println("Descargando lista de usuarios: ");
               System.out.println("Cedula: " + usuarioObtenido.getCedula());
               System.out.println("Nombre: " + usuarioObtenido.getNombre());
               System.out.println("Primer apellido: " + usuarioObtenido.getPrimerApellido());
               System.out.println("Segundo apellido: " + usuarioObtenido.getSegundoApellido());
               System.out.println("Correo: " + usuarioObtenido.getCorreo());
               System.out.println("Tipo: " + usuarioObtenido.getTipo());
           }
            
        }
        catch(Exception se){
            LOGGER.log(Level.SEVERE, "Error obteniendo lista de usuarios: " + se);
        }
        
         System.out.println("Valores del primero usuario: " + usuarioDAO.getCedula() );
        return listaUsuarios;
    }
    public void eliminarUsuario(int ced){
        Connection con = dbHelper.connectDatabase("localhost", "5432", "inventarioCentroDiurno","postgres", "postgres");
        LOGGER.log(Level.INFO, "Eliminando datos de usuario");
        dbHelper.eliminarUsuario(con,ced);
    }
    
    public void modificarUsuario(int cedVieja,int ced, String nom, String pA, String sA, String corr, String tip){
        Connection con = dbHelper.connectDatabase("localhost", "5432", "inventarioCentroDiurno","postgres", "postgres");
        dbHelper.modificarUsuario(con,cedVieja,ced,nom,pA,sA,corr ,tip);
    }
    
    public void crearSchema(){
        Connection con = dbHelper.connectDatabase("169.254.237.62", "5432", "postgres","postgres", "postgres");
        
    }
}
