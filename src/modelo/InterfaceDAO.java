/*
 * Interfaz de donde se desprenden las clases que interactúan con las clases DAO y el DBHelper
 */
package modelo;

/**
 *
 * @author Danny Orozco
 */
import modelo.dao.UsuarioDAO;
import modelo.dao.ProductoDAO;
import java.util.*;

public interface InterfaceDAO {
    public void insertarUsuario(int ced, String nom, String pA, String sA, String corr, String tip, String pw);
    
    //public UsuarioDAO mostrarUsuario();
    
    public List<UsuarioDAO> listarUsuario();
    
    public void eliminarUsuario(int ced);
    
    public void modificarUsuario(int cedVieja,int ced, String nom, String pA, String sA, String corr, String tip);
    
    public List<ProductoDAO> getArticulosPorFechaEntrada();
    
    public List<ProductoDAO> getArticulosPorFechaSalida();
    
    public List<ProductoDAO> getArticulosPorNombre();
    
    public List<ProductoDAO> getArticulosPorRequisicion();
    
    public List<ProductoDAO> getArticulosPorCategoria();
    
    public List<ProductoDAO> getArticulosPorOrdenCompra();
    
    public List<ProductoDAO> getArticulosPorFechaVencimiento();
    
    public List<ProductoDAO> getArticulosPorNumeroActivo();
    
    public void insertarArticulo(String nom, String desc, int cant, String cA, String fE, float cost, String cate, String oC);
    
    public void eliminarArticulo();
    
    public ProductoDAO mostrarArticulo();
    
    public void updateArticulo();
    
    public void insertarRequisicion();
    
    public List<ProductoDAO> getArticulosInforme();
    
    public void updateContrasena();
    
    public String getContrasena();
    
    
    
    
}
