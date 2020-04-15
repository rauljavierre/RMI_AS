import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CollectionImpl extends UnicastRemoteObject implements Collection {
    // Private member variables
    private int m_number_of_books;
    private String m_name_of_collection;

    //Constructor
    public CollectionImpl() throws RemoteException {
        super(); //Llama al constructor de UnicastRemoteObject
        m_number_of_books = 0;
        m_name_of_collection = "Coleccion sin nombre";
    }

    public int number_of_books() throws RemoteException {
        return m_number_of_books;
    }

    public String name_of_collection() throws RemoteException {
        return m_name_of_collection;
    }

    public void name_of_collection(String _new_value) throws RemoteException {
        m_name_of_collection = _new_value;
    }

    public static void main(String args[]){
        //Fijar el directorio donde se encuentra el java.policy
        //El segundo argumento es la ruta al java.policy
        System.setProperty("java.security.policy", "java.policy");

        //Crear administrador de seguridad
        System.setSecurityManager(new SecurityManager());

        //nombre o IP del host donde reside el objeto servidor. Por defecto RMI usa el puerto 1099.
        String hostName = "127.0.0.1:1099"; //se puede usar "IPhostremoto:puerto"

        try {
            // Crear objeto remoto
            CollectionImpl obj = new CollectionImpl();
            System.out.println("Creado!");

            //Registrar el objeto remoto
            Naming.rebind("//" + hostName + "/MyCollection", obj);
            System.out.println("Estoy registrado!");
        }
        catch (Exception ex) { System.out.println(ex); }
    }
}
