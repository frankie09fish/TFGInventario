/*
 * Clase que guarda los nombres de tablas, columnas y esquema para mantener más protección
 */
package modelo;

/**
 *
 * @author Danny Orozco
 */
public class DBContract {
    
     public static abstract class DBInfo{
        public static final String idUsuario = "id";
        public static final String cedUsuario = "cedula";
        public static final String nombreUsuario = "nombre";
        public static final String primerApellidoUsuario = "primerApellido";
        public static final String segundoApellidoUsuario = "segundoApellido";
        public static final String correoUsuario = "correo";
        public static final String tipUsuario = "tipo";
        public static final String contrasenaUsuario = "contrasena";
        public static final String tabUsuario = "usuario";
        
        public static final String idProducto = "id";
        public static final String nombreProducto = "nombre";
        public static final String descripcionProducto = "descripcion";
        public static final String cantidadProducto = "cantidad";
        public static final String codigoActivo = "codigoActivo";
        public static final String fechaEntrada = "fechaEntrada";
        public static final String fechaSalida = "fechaSaluda";
        public static final String costoProducto = "costo";
        public static final String categoriaProducto = "categoria";
        public static final String idRequisicionProducto = "idRequisicion";
        public static final String ordenCompra = "ordenCompra";
        public static final String tabProducto = "producto";
        
        public static final String idR = "id";
        public static final String idRequisicion = "idRequisicion";
        public static final String idUsuarioR = "idUsuario";
        public static final String fechaRequisicion = "fechaRequisicion";
        public static final String detalleRequisicion = "detalle";
        public static final String nombreSolicitante = "nombreSolicitante";
        public static final String apellidoSolicitante = "apellidoSolicitante";
        public static final String tabRequisicion = "reqisicion";
        
        public static final String idC = "id";
        public static final String idOrdenCompra = "idOrdenCompra";
        public static final String fechaCompra = "fechaCompra";
        public static final String tabCompra = "compra";
        
        public static final String idCat = "id";
        public static final String nombreCategoria = "nombreCategoria";
        public static final String tipoUso = "tipoUso";
        
        public static final String schemaNombre = "InventarioBD";
    }
}
