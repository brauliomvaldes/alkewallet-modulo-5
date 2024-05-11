package coneccionDB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase para conexion a la BBDD, implementa patrón singleton
 */
public class Coneccion {
    // declarada static para ser invovada estáticamente
    public static Coneccion instancia;
    // atributo a retornar con la instancia de la conección
    private Connection connection;
    // parametriza e instancia el driver de conección a mysql
    // registra el driver de mysql, se carga dinámicamente el conjtrolador de base de datos

    private Coneccion() {
        // parámetros para realizar la conexión
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/walletmod5";
        String usuario = "root";
        String pass = "150919";
        try {
            // invoca el driver de conexión
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usuario, pass);
            System.out.println("Conección a la DB existosa!!");
        } catch (Exception ex) {
            System.out.println("Ocurrio un error al intentar conectar a la DB !!"+ex.getMessage());
        }
    }

    // patrón singleton para la instancia de conección a la DB
    public static Coneccion getInstance() {
        // si ya existe no la vuelve a instanciar
        if (instancia == null) {
            // crea instancia de la coenxión
            instancia = new Coneccion();
        }
        System.out.println("recupera instancia de la conección!!");
        return instancia;
    }

    // retorna conexion
    public Connection getConeccion() {
        return connection;
    }

    // cierra conexion
    public static void cerrar() {
        instancia = null;
        System.out.println("Desconección de la DB existosa!!");
    }
}
