
package classes;

public class test {


    public static void main(String[] args) {
        
         //TO DO
         
        //Codigo de Andres
        Player player = new Player();

        
        Ingrediente pan=new Ingrediente("Pan","image");
        Ingrediente carne=new Ingrediente("Carne","image");
        Ingrediente queso=new Ingrediente("Queso","image");
        Ingrediente lechuga=new Ingrediente("Lechuga","image");
        LinkedList<Ingrediente> listaSencilla=new LinkedList<>();
        listaSencilla.insert(pan);
        listaSencilla.insert(carne);
        
        LinkedList<Ingrediente> listaQueso=new LinkedList<>();
        listaQueso.insert(pan);
        listaQueso.insert(carne);
        listaQueso.insert(queso);
        
        LinkedList<Ingrediente> listaClasica=new LinkedList<>();
        listaClasica.insert(pan);
        listaClasica.insert(carne);
        listaClasica.insert(queso);
        listaClasica.insert(lechuga);

        Orden hamSencilla = new Orden(1, "image", listaSencilla);
        Orden hamQueso = new Orden(2, "image", listaQueso);
        Orden hamClasica = new Orden(3, "image", listaClasica);
        
        Queue<Orden> ordenes=new Queue<>();
        CircularList<Ingrediente> cinta = new CircularList<>();
        cinta.insert(carne);
        cinta.insert(pan);
        cinta.insert(queso);
        cinta.insert(lechuga);
        cinta.insert(pan);
        player.getPlatillo().insert(cinta.extract(4));
                           
        Runnable llenarCola = new Runnable() {
            @Override
            public void run() {
            // Esto se ejecuta en segundo plano una única vez
                while (true) {
                // Pero usamos un truco y hacemos un ciclo infinito
                    try {
                        // En él, hacemos que el hilo duerma
                        Thread.sleep(1000);
                        // Y después realizamos las operaciones
                        if(ordenes.size<3){
                            ordenes.add(hamClasica);
                        } else{
                            System.out.println("Cola llena");
                        }
                        // Así, se da la impresión de que se ejecuta cada cierto tiempo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
	};
	// Creamos un hilo y le pasamos el runnable
	Thread hilo = new Thread(llenarCola);
        hilo.start();

	// Y aquí podemos hacer cualquier cosa, en el hilo principal del programa
	System.out.println("Yo imprimo en el hilo principal");
                
        //Codigo de Andres
	}
        
            
}
