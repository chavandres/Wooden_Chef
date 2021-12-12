package classes;

import java.util.Scanner;

public class Ramdom {    
    int contador = 0;
    
    CircularList cl = new CircularList();
    Stack userOrden = new Stack();
    Ingrediente pan = new Ingrediente("Pan","");
    Ingrediente carne = new Ingrediente("Carne","");
    Ingrediente queso = new Ingrediente("Queso","");
    Ingrediente tomate = new Ingrediente("Tomate","");
    Ingrediente lechuga = new Ingrediente("Lechuga","");
    Scanner teclado = new Scanner(System.in);
   
  
  
  
    public void play() {
        llenar();
        cl.get();
        System.out.println("1. para escoger |  2.Para borrar   |  3.salir ");
        String opt = "";
        opt = teclado.next();
        while (!opt.equals("salir")) {
            if (opt.equals("1")) {
                agregar();
            } else if (opt.equals("2")) {
                borrar();
            }
            if (cl.size == 3) {
                rellenar();
            }
        }
    }

   public void llenar() {
        for (int i = 0; i < 5; i++) {
            int num;
            num = (int) (Math.random() * (4)+1);
            if (num == 5) {
                num--;
            }
            switch (num) {
                case 1: {
                    cl.insert(pan);
                    contador++;
                    break;
                }
                case 2: {
                    cl.insert(carne);
                    contador++;
                    break;
                }
                case 3: {
                    cl.insert(queso);
                    contador++;
                    break;
                }
                case 4: {
                    cl.insert(lechuga);
                    contador++;
                    break;
                }
            }
        }
    }

      public void rellenar() {
        for (int i = 0; i < 2; i++) {
            int num;
            num = (int) (Math.random() * (4)+1);
            if (num == 5) {
                num--;
            }
            switch (num) {
                case 1: {
                    cl.insert(pan);
                    contador++;
                    break;
                }
                case 2: {
                    cl.insert(carne);
                    contador++;
                    break;
                }
                case 3: {
                    cl.insert(queso);
                    contador++;
                    break;
                }
                case 4: {
                    cl.insert(lechuga);
                    contador++;
                    break;
                }
            }
        }
    }
      
    public void borrar() {
        System.out.println("Escoga el ingrediente a borrar"
                + "1. pan , 2.carne, 3.Queso , 4.tomate , 5.lechuga");
        cl.get();
        int ingre = teclado.nextInt();
        switch (ingre) {
            case 1: {
                contador--;
                cl.remove(pan);

                break;
            }
            case 2: {
                contador--;
                cl.remove(carne);

                break;
            }
            case 3: {
                contador--;
                cl.remove(queso);

                break;
            }
            case 4: {
                contador--;
                cl.remove(tomate);

                break;
            }
            case 5: {
                contador--;
                cl.remove(lechuga);

                break;
            }
        }
    }

    public void agregar() {
        System.out.println("Escoga el ingrediente a agregar a la orden"
                + "1. pan , 2.carne, 3.Queso , 4.tomate , 5.lechuga  6.ver orden");
        cl.get();
        int ingre = teclado.nextInt();
        switch (ingre) {
            case 1: {
                userOrden.push(pan);
                cl.remove(pan);
                contador--;
                break;
            }
            case 2: {
                userOrden.push(carne);
                cl.remove(carne);
               contador--;
                break;
            }
            case 3: {
                userOrden.push(queso);
                cl.remove(queso);
               contador--;
                break;
            }
            case 4: {
                userOrden.push(tomate);
                cl.remove(tomate);
               contador--;
                break;
            }
            case 5: {
                userOrden.push(lechuga);
                cl.remove(lechuga);
                contador--;
                break;
            }
            case 6: {
                System.out.println("La orden");
                userOrden.print();
            }
        }

    }

    /*    public void comprobar(int id) {
        int cont = 0;
        if (id == 1) {
            // System.out.println("Funciona");
            String ingre1 = "", ingre2 = "";
            ingre1 = userOrden.printPos(0);
            ingre2 = userOrden.printPos(1);
            // System.out.println(ingre1 +" - "+ingre2);

            if (ingre1.equals("Carne")) {
                cont++;

                if (ingre2.equals("Pan")) {
                    cont++;
                }
            }
            if (cont == 2) {
                jugador.setScore(5);
                System.out.println("Orden correcta");
                play();
                return;
            } else {
                System.out.println("Orden incorrecta");
                return;
            }
        }

        if (id == 2) {
            String ingre1 = "", ingre2 = "", ingre3 = "";
            ingre1 = userOrden.printPos(0);
            ingre2 = userOrden.printPos(1);
            ingre3 = userOrden.printPos(2);

            if (ingre1.equals("Carne")) {
                cont++;
                if (ingre2.equals("Queso")) {
                    cont++;
                    if (ingre3.equals("Pan")) {
                        cont++;
                    }
                }
            }
            if (cont == 3) {
                jugador.setScore(10);
                System.out.println("Orden correcta");
                play();
                return;
            } else {
                System.out.println("Orden incorrecta");
                return;
            }
        }

        if (id == 3) {
            String ingre1 = "", ingre2 = "", ingre3 = "", ingre4 = "";
            ingre1 = userOrden.printPos(0);
            ingre2 = userOrden.printPos(1);
            ingre3 = userOrden.printPos(2);
            ingre4 = userOrden.printPos(3);

            if (ingre1.equals("Carne")) {
                cont++;
                if (ingre2.equals("Queso")) {
                    cont++;
                    if (ingre3.equals("Lechuga")) {
                        cont++;
                        if (ingre4.equals("Pan")) {
                            cont++;
                        }
                    }
                }
            }

            if (cont == 4) {
                jugador.setScore(15);
                System.out.println("Orden correcta");
                play();
                return;
            } else {
                System.out.println("Orden incorrecta");
                return;
            }

        } else {
            System.out.println("Orden incorrecta");
        }
    }*/
    
}
