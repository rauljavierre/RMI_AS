import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {

    public static void main(String[] args) {
        //Fijar el directorio donde se encuentra el java.policy
        //El segundo argumento es la ruta al java.policy
        System.setProperty("java.security.policy", "java.policy");

        if (System.getSecurityManager() == null) {
            //Crear administrador de seguridad
            System.setSecurityManager(new SecurityManager());
        }
        try {
            //Paso 1 - Obtener una referencia al objeto servidor creado anteriormente
            //Nombre del host servidor o su IP. Es donde se buscara al objeto remoto
            String hostname = "127.0.0.1:1099"; //se puede usar "IP:puerto"
            Collection server = (Collection) Naming.lookup("//" + hostname + "/MyCollection");

            //Paso 2 - Invocar remotamente los metodos del objeto servidor:
            // Obtener el nombre de la coleccion y el numero de libros
            System.out.println("Nombre de la coleccion: " + server.name_of_collection());
            System.out.println("Numero de libros en la coleccion: " + server.number_of_books());
            // Cambiar el nombre de la coleccion y ver que ha funcionado
            server.name_of_collection("Coleccion cambiada");
            System.out.println("Nombre de la coleccion post-invocacion: " + server.name_of_collection());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (NotBoundException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}