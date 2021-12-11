package classes;

import java.util.Scanner;

public class Ramdom {

    
    int cont = 0;
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
        System.out.println("1. para escoger  2.Para borrar 3.salir ");
        String opt = "";
        opt = teclado.next();
        while (!opt.equals("salir")) {
            if (opt.equals("1")) {
                agregar();
            } else if (opt.equals("2")) {
                borrar();
            }
            if (cont < 3) {
                rellenar();
            }
        }
    }

    public void llenar() {
        for (int x = 0; x < 5; ++x) {
            int num;
            num = (int) (Math.random() * (3));     
            switch (num) {
                case 1: {
                    cl.insert(pan);
                    cont++;
                    break;
                }
                case 2: {
                    cl.insert(carne);
                    cont++;
                    break;
                }
                case 3: {
                    cl.insert(queso);
                    cont++;
                    break;
                }
                default: {
                    cl.insert(lechuga);
                    cont++;
                    break;
                }
            }
        }
    }

    public void rellenar() {
        for (int x = 0; x < 2; ++x) {
            int num;
            num = (int) (Math.random() * (5));
            switch (num) {
                case 1: {
                    cl.insert(pan);
                    break;
                }
                case 2: {
                    cl.insert(carne);
                    break;
                }
                case 3: {
                    cl.insert(queso);
                    break;
                }
                case 4: {
                    cl.insert(tomate);
                    break;
                }
                case 5: {
                    cl.insert(lechuga);
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
                cont--;
                cl.remove(pan);

                break;
            }
            case 2: {
                cont--;
                cl.remove(carne);

                break;
            }
            case 3: {
                cont--;
                cl.remove(queso);

                break;
            }
            case 4: {
                cont--;
                cl.remove(tomate);

                break;
            }
            case 5: {
                cont--;
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
                cont--;
                break;
            }
            case 2: {
                st.push(carne);
                cl.remove(carne);
               cont--;
                break;
            }
            case 3: {
                st.push(queso);
                cl.remove(queso);
               cont--;
                break;
            }
            case 4: {
                st.push(tomate);
                cl.remove(tomate);
               cont--;
                break;
            }
            case 5: {
                st.push(lechuga);
                cl.remove(lechuga);
                cont--;
                break;
            }
            case 6: {
                System.out.println("La orden");
                st.print();
            }
        }

    }

}
