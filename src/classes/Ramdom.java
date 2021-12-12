package classes;

import java.util.Scanner;

public class Ramdom {    
    int contador = 0;
    CircularList cl = new CircularList();
    Stack st = new Stack();
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
                st.push(pan);
                cl.remove(pan);
                contador--;
                break;
            }
            case 2: {
                st.push(carne);
                cl.remove(carne);
               contador--;
                break;
            }
            case 3: {
                st.push(queso);
                cl.remove(queso);
               contador--;
                break;
            }
            case 4: {
                st.push(tomate);
                cl.remove(tomate);
               contador--;
                break;
            }
            case 5: {
                st.push(lechuga);
                cl.remove(lechuga);
                contador--;
                break;
            }
            case 6: {
                System.out.println("La orden");
                st.print();
            }
        }

    }

}
