/*
 * Clase que ejecuta las consultas a la base de datos y establece las conexiones con esta
 */
package modelo;

/**
 *
 * @author Danny Orozco
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import modelo.dao.UsuarioDAO;

public class PostgreSQLDBHelper {
    
      private final static Logger LOGGER = Logger.getLogger("Conexion.PostgresqlDBHerlper");
     
    public Connection connectDatabase(String host, String port, String database,
            String user, String password) {
        String url = "";
        Connection connection = null;
        try {

            // Registramos el driver de PostgresSQL
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            //Connection connection = null;
            url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    url,
                    user, password);           
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) { 
            System.out.println("Error al conectar con la base de datos de PostgreSQL (" + url + "): " + sqle);
            LOGGER.log(Level.WARNING, "Error al conectar con la base de datos de PostgreSQL: " + sqle);
        }
        return connection;
    }
    
    public void insertarUsuario(Connection con, int ced , String nom, String primerAp, String segundoAp , String corr, String tip,  String contr ){
        //Abrimos la conexión y la iniciamos
        try{
            CallableStatement cst = con.prepareCall("{call \"InventarioBD\".\"insertarUsuario\"(?,?,?,?,?,?,?)}");
            
            cst.setInt(1, ced);
            cst.setString(2,nom);
            cst.setString(3,primerAp);
            cst.setString(4,segundoAp);
            cst.setString(5,corr);
            cst.setString(6,tip);
            cst.setString(7,contr);
            
            cst.execute();
            cst.close();
            con.close();
        }
        catch( Exception e ){
        //Por si ocurre un error
        System.out.println(e.getMessage());
        e.printStackTrace();
        LOGGER.log(Level.SEVERE, "Error al insertar usaurio: " + e);
        }

    }
    
    public UsuarioDAO mostrarUsuario(Connection con, int cedula){

        ResultSet rs = null;

         UsuarioDAO usuarioObtenido = new UsuarioDAO();

        try{      
            //Abrimos la conexión y la iniciamos
            Statement stmt = con.createStatement();
            //Un ResultSet es como en .NET un DataSet, un arreglo temporal donde se
            //almacenará el resultado de la consulta SQL
           
            //Una variable String para almacenar la sentencia SQL
            System.out.println("Antes de ejecutar select obtener usuario");
            String query = "SELECT " + DBContract.DBInfo.cedUsuario + "," + DBContract.DBInfo.nombreUsuario + ","+ DBContract.DBInfo.primerApellidoUsuario + 
                    "," + DBContract.DBInfo.segundoApellidoUsuario + "," + DBContract.DBInfo.correoUsuario + "," + DBContract.DBInfo.tipUsuario +
                    " FROM \""  + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabUsuario  + "\" WHERE \"" + DBContract.DBInfo.cedUsuario + 
                    "\" = " + cedula;
            //En el ResultSet guardamos el resultado de ejecutar la consulta
            rs = stmt.executeQuery(query);
            //En un ciclo while recorremos cada fila del resultado de nuestro Select
            while ( rs.next()){
                
                usuarioObtenido.setCedula(rs.getInt("cedula"));
                usuarioObtenido.setNombre(rs.getString("nombre"));
                usuarioObtenido.setPrimerApellido(rs.getString("primerapellido"));
                usuarioObtenido.setSegundoApellido(rs.getString("segundoapellido"));
                usuarioObtenido.setCorreo(rs.getString("correo"));
                usuarioObtenido.setTipo(rs.getString("tipo"));     
            }
            //Cerramos la conexión
            stmt.execute("END");
            stmt.close();
            con.close();
        }
        catch( Exception e ){
            //Por si ocurre un error
             System.out.println(e.getMessage());
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Error al obtener usaurio: " + e);
        }
       return usuarioObtenido;
    }
    
    
    public List listarUsuario(Connection con){
        ResultSet rs = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
         List<UsuarioDAO> listaValores = new ArrayList<UsuarioDAO>();
        try{
             
            
            //Abrimos la conexión y la iniciamos
            Statement stmt = con.createStatement();
            /*Un ResultSet es como en .NET un DataSet, un arreglo temporal donde se
            almacenará el resultado de la consulta SQL*/
            
            //Una variable String para almacenar la sentencia SQL
             String query = "SELECT " + DBContract.DBInfo.cedUsuario + "," + DBContract.DBInfo.nombreUsuario + ","+ DBContract.DBInfo.primerApellidoUsuario + 
                    "," + DBContract.DBInfo.segundoApellidoUsuario + "," + DBContract.DBInfo.correoUsuario + "," + DBContract.DBInfo.tipUsuario +
                    " FROM \""  + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabUsuario + "\"";
            //En el ResultSet guardamos el resultado de ejecutar la consulta
            System.out.println("Antes de ejecutar el query");
            rs = stmt.executeQuery(query);
            System.out.println("Despues de ejecutar el query");
            //En un ciclo while recorremos cada fila del resultado de nuestro Select
                      
            while ( rs.next()){

                usuarioDAO.setCedula(rs.getInt("cedula"));
                usuarioDAO.setNombre(rs.getString("nombre"));
                usuarioDAO.setPrimerApellido(rs.getString("primerapellido"));
                usuarioDAO.setSegundoApellido(rs.getString("segundoapellido"));
                usuarioDAO.setCorreo(rs.getString("correo"));
                usuarioDAO.setTipo(rs.getString("tipo"));
                listaValores.add(usuarioDAO);
                System.out.println(rs.getInt("cedula") + " " + rs.getString("Nombre") + " " +
                rs.getString("primerApellido") + " " + rs.getString("segundoApellido") + " " + rs.getString("correo") + " " + rs.getString("tipo"));
            }
            
            //Cerramos la conexión
            stmt.execute("END");
            stmt.close();
            con.close();
            
        }
        catch( Exception e ){
            //Por si ocurre un error
             System.out.println(e.getMessage());
             LOGGER.log(Level.SEVERE, "Error al obtener lista de usaurios: " + e);
            e.printStackTrace();
        }
        return listaValores;
    }
    
    public void eliminarUsuario(Connection con, int ced){
        try{
            CallableStatement cst = con.prepareCall("{call \"InventarioBD\".\"eliminarUsuario\"(?)}");
            cst.setInt(1, ced);
            
            cst.execute();
            cst.close();

            con.close();
        }
        catch( Exception e ){
        //Por si ocurre un error
        System.out.println(e.getMessage());
        e.printStackTrace();
        }
    }
    
    public void modificarUsuario(Connection con, int cedVieja,int ced, String nom, String pA, String sA, String corr , String tip){
         try{
            CallableStatement cst = con.prepareCall("{call \"InventarioBD\".\"actualizarUsuario\"(?,?,?,?,?,?,?)}");
            cst.setInt(1, cedVieja);
            cst.setInt(2,ced);
            cst.setString(3, nom);
            cst.setString(4, pA);
            cst.setString(5, sA);
            cst.setString(6, corr);
            cst.setString(7, tip);
            
            cst.execute();
            cst.close();
             /*
             Statement stmt = con.createStatement();

            //Una variable String para almacenar la sentencia SQL
            String query = "UPDATE \"" + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabUsuario + 
                    "\" SET \"" + DBContract.DBInfo.cedUsuario + "\" = '" + ced + "',\"" + DBContract.DBInfo.nombreUsuario + "\" = '" + nom + 
                    "',\"" + DBContract.DBInfo.primerApellidoUsuario + "\" = '" + pA + 
                    "',\"" + DBContract.DBInfo.segundoApellidoUsuario + "\" = '" + sA +
                    "',\"" + DBContract.DBInfo.cedUsuario + "\" = " + ced + 
                    ",\"" + DBContract.DBInfo.tipUsuario + "\" = " + tip + " WHERE \"" + DBContract.DBInfo.cedUsuario + "\" =" + cedVieja;

            stmt.executeUpdate(query);

            //Cerramos la conexión
            stmt.execute("END");
            stmt.close();
            */
            con.close();
        }
        catch( Exception e ){
        //Por si ocurre un error
        System.out.println(e.getMessage());
        e.printStackTrace();
        }
    }
    
    public void insertarArticulo(Connection con, String nom, String desc, int cant, String cA, String fE, float cost, String cate, String oC ){
         //Abrimos la conexión y la iniciamos
        try{
           

            Statement stmt = con.createStatement();
           

            //Una variable String para almacenar la sentencia SQL
            String query = "INSERT INTO \"" + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabProducto + "\" (\"" + DBContract.DBInfo.nombreProducto +  "\",\"" + 
                    DBContract.DBInfo.descripcionProducto + "\",\"" + DBContract.DBInfo.cantidadProducto + "\",\"" + DBContract.DBInfo.codigoActivo + "\",\"" + DBContract.DBInfo.fechaEntrada + 
                    "\",\"" + DBContract.DBInfo.costoProducto + "\",\"" + DBContract.DBInfo.categoriaProducto +  
                    "\",\"" + DBContract.DBInfo.ordenCompra +
                    "\") VALUES('" + nom + "','"+ desc + "'," + cant + ",'" + cA + "',to_date('" + fE + "','YYYYMMDD')," + cost + ",'" + cate + "','" + oC + "');";

            stmt.executeUpdate(query);

            //Cerramos la conexión
            stmt.execute("END");
            LOGGER.log(Level.INFO, "Articulo insertado correctamente");
            stmt.close();
            con.close();
        }
        catch( Exception e ){
        //Por si ocurre un error
        System.out.println(e.getMessage());
        e.printStackTrace();
        LOGGER.log(Level.SEVERE, "Error al insertar usaurio: " + e);
        }
        
    }
    
    /*
    public void crearDB(Connection con){
        
        try{
            Statement stmt = con.createStatement();

            //Una variable String para almacenar la sentencia SQL
            String query = "CREATE SCHEMA If NOT EXISTS \"" + DBContract.DBInfo.schemaNombre + "\" AUTHORIZATION postgres;";
            
            stmt.executeUpdate(query);
            System.out.println("Base de datos creada o abierta");
            
            //Cerramos la conexión
            stmt.execute("END");
            stmt.close();
            con.close();
        }
        catch( Exception e ){
        //Por si ocurre un error
        System.out.println(e.getMessage());
        e.printStackTrace();
        }
    }
    
    
    public void crearTablaUsuario(Connection con){
        try{
            Statement stmt = con.createStatement();
             String query = "CREATE TABLE IF NOT EXISTS \"" + DBContract.DBInfo.schemaNombre + "\"." + DBContract.DBInfo.tabUsuario +
                    "(" +
                DBContract.DBInfo.idUsuario + "integer NOT NULL DEFAULT nextval('\"" + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabUsuario + 
                    "_id_seq'::regclass)," +
                DBContract.DBInfo.nombreUsuario + " character varying(20) COLLATE pg_catalog.\"default\" NOT NULL," +
                DBContract.DBInfo.primerApellidoUsuario +  " character varying(20) COLLATE pg_catalog.\"default\" NOT NULL," +
                DBContract.DBInfo.segundoApellidoUsuario +  " character varying(20) COLLATE pg_catalog.\"default\" NOT NULL," +
                DBContract.DBInfo.cedUsuario + " integer NOT NULL," + 
                DBContract.DBInfo.tipUsuario + " integer NOT NULL," + 
                " CONSTRAINT " + DBContract.DBInfo.tabUsuario + "_pkey PRIMARY KEY (" + DBContract.DBInfo.idUsuario + ")" +
                ") " + 
                "WITH (" +
                   " OIDS = FALSE " +
                ")" +
                "TABLESPACE pg_default;" +

                "ALTER TABLE \"" + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabUsuario + "\" " +
                "OWNER to postgres;";
                    
            stmt.executeUpdate(query);
            System.out.println("Tabla Usuario creada o abierta");
            //Cerramos la conexión
            stmt.execute("END");
            stmt.close();
            con.close();
        }
        catch( Exception e ){
        //Por si ocurre un error
        System.out.println(e.getMessage());
        e.printStackTrace();
        }
    }
    
    public void crearTablaArticulo(Connection con){
        try{
            Statement stmt = con.createStatement();
             String query = "CREATE TABLE IF NOT EXISTS \"" + DBContract.DBInfo.schemaNombre + "\"." + DBContract.DBInfo.tabArticulo +
                    "(" +
                DBContract.DBInfo.idArticulo + "integer NOT NULL DEFAULT nextval('\"" + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabArticulo + 
                    "_id_seq'::regclass)," +
                DBContract.DBInfo.nombreArticulo + " character varying(30) COLLATE pg_catalog.\"default\" NOT NULL," +
                DBContract.DBInfo.marcaArticulo +  " character varying(20) COLLATE pg_catalog.\"default\" NOT NULL," +
                DBContract.DBInfo.estadoArticulo +  " character varying(20) COLLATE pg_catalog.\"default\" NOT NULL," +
                DBContract.DBInfo.cantidadArticulo + " integer NOT NULL," + 
                " CONSTRAINT " + DBContract.DBInfo.tabArticulo + "_pkey PRIMARY KEY (" + DBContract.DBInfo.idArticulo + ")" +
                ") " + 
                "WITH (" +
                   " OIDS = FALSE " +
                ")" +
                "TABLESPACE pg_default;" +

                "ALTER TABLE \"" + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabArticulo + "\" " +
                "OWNER to postgres;";
                    
            stmt.executeUpdate(query);
            System.out.println("Tabla Articulo creada o abierta");
            //Cerramos la conexión
            stmt.execute("END");
            stmt.close();
            con.close();
        }
        catch( Exception e ){
        //Por si ocurre un error
        System.out.println(e.getMessage());
        e.printStackTrace();
        }
    }
    
    public void crearTablaGrupo(Connection con){
          try{
            Statement stmt = con.createStatement();
             String query = "CREATE TABLE IF NOT EXISTS \"" + DBContract.DBInfo.schemaNombre + "\"." + DBContract.DBInfo.tabGrupo +
                    "(" +
                DBContract.DBInfo.idGrupo + "integer NOT NULL DEFAULT nextval('\"" + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabGrupo + 
                    "_id_seq'::regclass)," +
                DBContract.DBInfo.insertArticulo + " boolean NOT NULL," +
                DBContract.DBInfo.deleteArticulo + " boolean NOT NULL," +
                DBContract.DBInfo.selectArticulo + " boolean NOT NULL," +
                DBContract.DBInfo.updateArticulo + " boolean NOT NULL," +
                DBContract.DBInfo.adminUsuario + " boolean NOT NULL," +
                DBContract.DBInfo.deleteUsuario + " boolean NOT NULL," +
                " CONSTRAINT " + DBContract.DBInfo.tabGrupo + "_pkey PRIMARY KEY (" + DBContract.DBInfo.idGrupo + ")" +
                ") " + 
                "WITH (" +
                   " OIDS = FALSE " +
                ")" +
                "TABLESPACE pg_default;" +

                "ALTER TABLE \"" + DBContract.DBInfo.schemaNombre + "\".\"" + DBContract.DBInfo.tabGrupo + "\" " +
                "OWNER to postgres;";
                    
            stmt.executeUpdate(query);
            System.out.println("Tabla Grupo creada o abierta");
            //Cerramos la conexión
            stmt.execute("END");
            stmt.close();
            con.close();
        }
        catch( Exception e ){
        //Por si ocurre un error
        System.out.println(e.getMessage());
        e.printStackTrace();
        }
    }
    */
}
