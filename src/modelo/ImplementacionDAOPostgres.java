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

    ProductoDAO productoDAO = new ProductoDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    List<ProductoDAO> listaProductos = new ArrayList<ProductoDAO>();
    List<UsuarioDAO> listaUsuarios = new ArrayList<UsuarioDAO>();


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
    public void insertarArticulo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    private final static Logger LOGGER = Logger.getLogger("Conexion.ImplementacionDAOPostgres");
    
    PostgreSQLDBHelper dbHelper = new PostgreSQLDBHelper();
    public void insertarUsuario(){
        LOGGER.log(Level.INFO, "Estableciendo conexión con Base de datos");
        Connection con = dbHelper.connectDatabase("localhost", "5432", "inventarioCentroDiurno","postgres", "postgres");
        LOGGER.log(Level.INFO, "Insertando datos de usuario");
        dbHelper.insertarUsuario(con, 302360145, "Elmer", "Oriel", "Miranda", "elmeroriel@hotmail.com", "Administrador", "12345a");
    }
    public UsuarioDAO mostrarUsuario(){
        Connection con = dbHelper.connectDatabase("localhost", "5433", "test","postgres", "postgres");
        dbHelper.mostrarUsuario(con, 103780124);
        return usuarioDAO;
    }
    public List<UsuarioDAO> listarUsuario(){
        Connection con = dbHelper.connectDatabase("localhost", "5433", "test","postgres", "postgres");
        dbHelper.listarUsuario(con);
        return listaUsuarios;
    }
    public void eliminarUsuario(){
        Connection con = dbHelper.connectDatabase("localhost", "5433", "test","postgres", "postgres");
        dbHelper.eliminarUsuario(con,103780124);
    }
    
    public void modificarUsuario(){
        Connection con = dbHelper.connectDatabase("localhost", "5433", "test","postgres", "postgres");
        dbHelper.modificarUsuario(con,103570159,"Elsa","Solano","Aguilar", "elsa@yahoo.com" ,103570159,"Usuario", "1234b");
    }
    
    public void crearSchema(){
        Connection con = dbHelper.connectDatabase("169.254.237.62", "5432", "postgres","postgres", "postgres");
        
    }
}
