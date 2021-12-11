
package classes;

public class test {


    public static void main(String[] args) {
        
         //TO DO
         
        //Codigo de Andres
        Player player = new Player();

        
        Ingrediente pan=new Ingrediente("Pan","/resources/pan.png");
        Ingrediente carne=new Ingrediente("Carne","/resources/carne.png");
        Ingrediente queso=new Ingrediente("Queso","/resources/queso.png");
        Ingrediente lechuga=new Ingrediente("Lechuga","/resources/lechuga.png");
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

        Orden hamSencilla = new Orden(1, "/resources/hamconcarne.png", listaSencilla);
        Orden hamQueso = new Orden(2, "/resources/hamconqueso.png", listaQueso);
        Orden hamClasica = new Orden(3, "/resources/hamclasica.png", listaClasica);
        
        Queue<Orden> ordenes=new Queue<>();
        CircularList<Ingrediente> cinta = new CircularList<>();
        cinta.insert(carne);
        cinta.insert(pan);
        cinta.insert(queso);
        cinta.insert(lechuga);
        cinta.insert(pan);
        player.getPlatillo().insert(cinta.extract(4)); //Agregar elemento de la cinta al platillo del jugador
         

//Thread de llenar el queue de ordenes                  
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
                        if (ordenes.size < 3) {
                            int num;
                            num = (int) (Math.random() * (3) + 1);
                            if (num == 4) {
                                num--;
                            }
                            switch (num) {
                                case 1: {
                                    ordenes.add(hamSencilla);
                                    break;
                                }
                                case 2: {
                                    ordenes.add(hamQueso);
                                    break;
                                }
                                case 3: {
                                    ordenes.add(hamClasica);
                                    break;
                                }
                            }
                        }
                        
                        else{
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
