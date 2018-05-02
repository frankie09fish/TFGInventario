/*
 Clase Bitacora se encarga de escribir las entradas al archivo log
 */
package bitacora;

/**
 *
 * @author Danny Orozco
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Bitacora {
       // Preparamos el log para cada paquete del proyecto, esto con el fin de capturar cada log
// que se genere e irlo pasando al nivel superior hasta que encuentren un handler que los
    // maneje
    private final static Logger LOG_RAIZ = Logger.getLogger("Bitacora");
    private final static Logger LOG_DB = Logger.getLogger("Conexion");
    
    // El log para ESTA clase en particular
    private final static Logger LOGGER = Logger.getLogger("Bitacora.Bitacora");
    
     public Bitacora() {
        try {
            // Los handler (manejadores) indican a donde mandar la salida ya sea consola o archivo
// En este caso ConsoleHandler envia los logs a la consola
            Handler consoleHandler = new ConsoleHandler();
            // Con el manejador de archivo, indicamos el archivo donde se mandaran los logs
            // El segundo argumento controla si se sobre escribe el archivo o se agregan los logs al final
            // Para sobre escribir pase un true para agregar al final, false para sobre escribir
            // todo el archivo
            Handler fileHandler = new FileHandler("./Log/bitacora.log", true);

            // El formateador indica como presentar los datos, en este caso usaremos el formaro sencillo
            // el cual es mas facil de leer, si no usamos esto el log estara en formato xml por defecto
            SimpleFormatter simpleFormatter = new SimpleFormatter();

            // Se especifica que formateador usara el manejador (handler) de archivo
            fileHandler.setFormatter(simpleFormatter);

            // Asignamos los handles previamente declarados al log *raiz* esto es muy importante ya que
// permitira que los logs de todas y cada una de las clases del programa que esten en ese paquete
            // o sus subpaquetes se almacenen en el archivo y aparescan en consola
            LOG_RAIZ.addHandler(consoleHandler);
            LOG_RAIZ.addHandler(fileHandler);
            LOG_DB.addHandler(fileHandler);

            // Indicamos a partir de que nivel deseamos mostrar los logs, podemos especificar un nivel en especifico
// para ignorar informacion que no necesitemos
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

            LOGGER.log(Level.INFO, "Bitacora inicializada");

            
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, Bitacora.getStackTrace(ex) ); 

          }
    }
     
     public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }
}
