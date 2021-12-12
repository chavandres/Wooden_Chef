
package gui;

import classes.CircularList;
import classes.Ingrediente;
import classes.LinkedList;
import classes.Orden;
import classes.Player;
import classes.Queue;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class MainGame extends javax.swing.JFrame {

    private ImageIcon iconQ1;
    private ImageIcon iconQ2;
    private ImageIcon iconQ3;
    private ImageIcon iconI1;
    private ImageIcon iconI2;
    private ImageIcon iconI3;
    private ImageIcon iconI4;
    private ImageIcon iconI5;
    private ImageIcon iconP1;
    private ImageIcon iconP2;
    private ImageIcon iconP3;
    private ImageIcon iconP4;
    
    Player player = new Player();
    
    Ingrediente pan = new Ingrediente("Pan", "/resources/pan.png");
    Ingrediente carne = new Ingrediente("Carne", "/resources/carne.png");
    Ingrediente queso = new Ingrediente("Queso", "/resources/queso.png");
    Ingrediente lechuga = new Ingrediente("Lechuga", "/resources/lechuga.png");
    
    LinkedList<Ingrediente> listaSencilla=new LinkedList<>();
    LinkedList<Ingrediente> listaQueso=new LinkedList<>();
    LinkedList<Ingrediente> listaClasica=new LinkedList<>();
    
    Orden hamSencilla = new Orden(1, "/resources/hamconcarne.png", listaSencilla);
    Orden hamQueso = new Orden(2, "/resources/hamconqueso.png", listaQueso);
    Orden hamClasica = new Orden(3, "/resources/hamclasica.png", listaClasica);

    Queue<Orden> ordenes = new Queue<>();
    CircularList<Ingrediente> cinta = new CircularList<>();
    LinkedList<Orden> UIorders = new LinkedList<>();

    public MainGame() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        initStructures();
        
        Thread fillQueue = new Thread(llenarCola);
        fillQueue.start();
        
        Thread readIngIcons = new Thread(leerIconIng);
        readIngIcons.start();
        
        Thread readQIcons = new Thread(leerIconQ);
        readQIcons.start();
        
        Thread readPlateIcons = new Thread(leerIconPlat);
        readPlateIcons.start();
        
        Thread fillBand = new Thread(llenarCinta);
        fillBand.start();
    }

    private void initStructures(){

        listaSencilla.insert(pan);
        listaSencilla.insert(carne);

        listaQueso.insert(pan);
        listaQueso.insert(carne);
        listaQueso.insert(queso);

        listaClasica.insert(pan);
        listaClasica.insert(carne);
        listaClasica.insert(queso);
        listaClasica.insert(lechuga);
        score.setText(Integer.toString(player.getScore()));
        
        for (int i = 0; i < 5; i++) {
            int num;
            num = (int) (Math.random() * (4)+1);
            if (num == 5) {
                num--;
            }
            switch (num) {
                case 1: {
                    cinta.insert(pan);
                    break;
                }
                case 2: {
                    cinta.insert(carne);
                    break;
                }
                case 3: {
                    cinta.insert(queso);
                    break;
                }
                case 4: {
                    cinta.insert(lechuga);
                    break;
                }
            }
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int i = 300;

            public void run() {
                if (i >= 0) {
                    String time = String.format("%02d:%02d", i / 60, i % 60);
                    i--;
                    timeLeft.setText(time);
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "¡Se acabó el tiempo! Su puntaje final fue de: "+player.getScore());
                    System.exit(0);
                
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);

    }
    
    private void comprobarOrden(int id) {
        int cont = 0;
        if (id == 1) {
            String ingre1 = "", ingre2 = "";
            try {
                ingre1 = player.getPlatillo().extract(1).getNombre();
                ingre2 = player.getPlatillo().extract(0).getNombre();

                if (ingre1.equals("Carne")) {
                    cont++;

                    if (ingre2.equals("Pan")) {
                        cont++;
                    }
                }
                if (cont == 2) {
                    player.setScore(player.getScore() + 5);
                    score.setText(Integer.toString(player.getScore()));
                    System.out.println("Orden correcta");
                    return;
                } else {
                    System.out.println("Orden incorrecta");
                    return;
                }
            } catch (NullPointerException npe) {
                System.out.println("Error obteniendo del platillo. Valor null");
            }
        }

        if (id == 2) {
            String ingre1 = "", ingre2 = "", ingre3 = "";
            try {
                ingre1 = player.getPlatillo().extract(2).getNombre();
                ingre2 = player.getPlatillo().extract(1).getNombre();
                ingre3 = player.getPlatillo().extract(0).getNombre();

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
                    player.setScore(player.getScore() + 10);
                    score.setText(Integer.toString(player.getScore()));
                    System.out.println("Orden correcta");
                    return;
                } else {
                    System.out.println("Orden incorrecta");
                    return;
                }
            } catch (NullPointerException npe) {
                System.out.println("Error obteniendo del platillo. Valor null");
            }
            
        }

        if (id == 3) {
            String ingre1 = "", ingre2 = "", ingre3 = "", ingre4 = "";
            try {
                ingre1 = player.getPlatillo().extract(3).getNombre();
                ingre2 = player.getPlatillo().extract(2).getNombre();
                ingre3 = player.getPlatillo().extract(1).getNombre();
                ingre4 = player.getPlatillo().extract(0).getNombre();

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
                    player.setScore(player.getScore() + 15);
                    score.setText(Integer.toString(player.getScore()));
                    System.out.println("Orden correcta");
                    return;
                } else {
                    System.out.println("Orden incorrecta");
                    return;
                }
            } catch (NullPointerException npe) {
                System.out.println("Error obteniendo del platillo. Valor null");
            }
        }
        player.setPlatillo(new LinkedList<Ingrediente>());
    }

    //Thread de llenar el queue de ordenes                  
    Runnable llenarCola = new Runnable() {
        @Override
        public void run() {

            while (true) {
                try {
                    if (ordenes.getSize() < 3) {
                        int num;
                        num = (int) (Math.random() * (3) + 1);
                        if (num == 4) {
                            num--;
                        }
                        switch (num) {
                            case 1: {
                                ordenes.add(hamSencilla);
                                UIorders.insert(hamSencilla);
                                break;
                            }
                            case 2: {
                                ordenes.add(hamQueso);
                                UIorders.insert(hamQueso);
                                break;
                            }
                            case 3: {
                                ordenes.add(hamClasica);
                                UIorders.insert(hamClasica);
                                break;
                            }
                        }
                    } 
                    
                    Thread.sleep(20000);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    //Thread para leer los iconos de la cinta
    Runnable leerIconIng = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    iconI1 = new ImageIcon(getClass().getResource(cinta.get(4).getImagePath()));
                    Image imgI1 = iconI1.getImage().getScaledInstance(iconIng1.getWidth(), iconIng1.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledI1 = new ImageIcon(imgI1);
                    iconIng1.setIcon(scaledI1);
                } catch (NullPointerException npe) {
                    iconIng1.setIcon(new ImageIcon(""));
                }
                try {
                    iconI2 = new ImageIcon(getClass().getResource(cinta.get(3).getImagePath()));
                    Image imgI2 = iconI2.getImage().getScaledInstance(iconIng2.getWidth(), iconIng2.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledI2 = new ImageIcon(imgI2);
                    iconIng2.setIcon(scaledI2);
                } catch (NullPointerException npe) {
                    iconIng2.setIcon(new ImageIcon(""));
                }
                try {
                    iconI3 = new ImageIcon(getClass().getResource(cinta.get(2).getImagePath()));
                    Image imgI3 = iconI3.getImage().getScaledInstance(iconIng3.getWidth(), iconIng3.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledI3 = new ImageIcon(imgI3);
                    iconIng3.setIcon(scaledI3);
                } catch (NullPointerException npe) {
                    iconIng3.setIcon(new ImageIcon(""));
                }
                try {
                    iconI4 = new ImageIcon(getClass().getResource(cinta.get(1).getImagePath()));
                    Image imgI4 = iconI4.getImage().getScaledInstance(iconIng4.getWidth(), iconIng4.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledI4 = new ImageIcon(imgI4);
                    iconIng4.setIcon(scaledI4);
                } catch (NullPointerException npe) {
                    iconIng4.setIcon(new ImageIcon(""));
                }
                try {
                    iconI5 = new ImageIcon(getClass().getResource(cinta.get(0).getImagePath()));
                    Image imgI5 = iconI5.getImage().getScaledInstance(iconIng5.getWidth(), iconIng5.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon scaledI5 = new ImageIcon(imgI5);
                    iconIng5.setIcon(scaledI5);
                } catch (NullPointerException npe) {
                    iconIng5.setIcon(new ImageIcon(""));
                }
            }
        }
    };
    //Thread para leer los iconos del queue
    Runnable leerIconQ = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    try {
                        iconQ1 = new ImageIcon(getClass().getResource(UIorders.get(0).getImagePath()));
                        Image imgQ1 = iconQ1.getImage().getScaledInstance(qOrder1.getWidth(), qOrder1.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon scaledQ1 = new ImageIcon(imgQ1);
                        qOrder1.setIcon(scaledQ1);
                    } catch (NullPointerException npe) {
                        qOrder1.setIcon(new ImageIcon(""));
                    }
                    
                    try {
                        iconQ2 = new ImageIcon(getClass().getResource(UIorders.get(1).getImagePath()));
                        Image imgQ2 = iconQ2.getImage().getScaledInstance(qOrder2.getWidth(), qOrder2.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon scaledQ2 = new ImageIcon(imgQ2);
                        qOrder2.setIcon(scaledQ2);
                    } catch (NullPointerException npe) {
                        qOrder2.setIcon(new ImageIcon(""));
                    }
                    
                    try {
                        iconQ3 = new ImageIcon(getClass().getResource(UIorders.get(2).getImagePath()));
                        Image imgQ3 = iconQ3.getImage().getScaledInstance(qOrder3.getWidth(), qOrder3.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon scaledQ3 = new ImageIcon(imgQ3);
                        qOrder3.setIcon(scaledQ3);
                    } catch (NullPointerException npe) {
                        qOrder3.setIcon(new ImageIcon(""));
                    }
                    
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    //Thread para leer los iconos del platillo del jugador
    Runnable leerIconPlat = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    try {
                        iconP1 = new ImageIcon(getClass().getResource(player.getPlatillo().get(0).getImagePath()));
                        Image imgP1 = iconP1.getImage().getScaledInstance(plateImg1.getWidth(), plateImg1.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon scaledP1 = new ImageIcon(imgP1);
                        plateImg1.setIcon(scaledP1);
                    } catch (NullPointerException npe) {
                        plateImg1.setIcon(new ImageIcon(""));
                    }
                    
                    try {
                        iconP2 = new ImageIcon(getClass().getResource(player.getPlatillo().get(1).getImagePath()));
                        Image imgP2 = iconP2.getImage().getScaledInstance(plateImg2.getWidth(), plateImg2.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon scaledP2 = new ImageIcon(imgP2);
                        plateImg2.setIcon(scaledP2);
                    } catch (NullPointerException npe) {
                        plateImg2.setIcon(new ImageIcon(""));
                    }
                    
                    try {
                        iconP3 = new ImageIcon(getClass().getResource(player.getPlatillo().get(2).getImagePath()));
                        Image imgP3 = iconP3.getImage().getScaledInstance(plateImg3.getWidth(), plateImg3.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon scaledP3 = new ImageIcon(imgP3);
                        plateImg3.setIcon(scaledP3);
                    } catch (NullPointerException npe) {
                        plateImg3.setIcon(new ImageIcon(""));
                    }
                    
                    try {
                        iconP4 = new ImageIcon(getClass().getResource(player.getPlatillo().get(3).getImagePath()));
                        Image imgP4 = iconP4.getImage().getScaledInstance(plateImg4.getWidth(), plateImg4.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon scaledP4 = new ImageIcon(imgP4);
                        plateImg4.setIcon(scaledP4);
                    } catch (NullPointerException npe) {
                        plateImg4.setIcon(new ImageIcon(""));
                    }
                    
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    //Thread que genera ingredientes para la cinta
    Runnable llenarCinta = new Runnable() {
        @Override
        public void run() {

            while (true) {
                try {
                    if (cinta.getSize() == 3) {
                        while (cinta.getSize() < 5) {
                            int num;
                            num = (int) (Math.random() * (4) + 1);
                            if (num == 5) {
                                num--;
                            }
                            switch (num) {
                                case 1: {
                                    cinta.insert(pan);
                                    break;
                                }
                                case 2: {
                                    cinta.insert(carne);
                                    break;
                                }
                                case 3: {
                                    cinta.insert(queso);
                                    break;
                                }
                                case 4: {
                                    cinta.insert(lechuga);
                                    break;
                                }
                            }
                        }
                    }     
                    
                    
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
            
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ingrediente5 = new javax.swing.JPanel();
        iconIng5 = new javax.swing.JLabel();
        delIng5 = new javax.swing.JButton();
        ingrediente4 = new javax.swing.JPanel();
        iconIng4 = new javax.swing.JLabel();
        delIng4 = new javax.swing.JButton();
        ingrediente3 = new javax.swing.JPanel();
        iconIng3 = new javax.swing.JLabel();
        delIng3 = new javax.swing.JButton();
        ingrediente2 = new javax.swing.JPanel();
        iconIng2 = new javax.swing.JLabel();
        delIng2 = new javax.swing.JButton();
        ingrediente1 = new javax.swing.JPanel();
        iconIng1 = new javax.swing.JLabel();
        delIng1 = new javax.swing.JButton();
        orders = new javax.swing.JPanel();
        order1 = new javax.swing.JPanel();
        qOrder1 = new javax.swing.JLabel();
        order2 = new javax.swing.JPanel();
        qOrder2 = new javax.swing.JLabel();
        order3 = new javax.swing.JPanel();
        qOrder3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        plate1 = new javax.swing.JPanel();
        plateImg1 = new javax.swing.JLabel();
        plate2 = new javax.swing.JPanel();
        plateImg2 = new javax.swing.JLabel();
        plate3 = new javax.swing.JPanel();
        plateImg3 = new javax.swing.JLabel();
        plate4 = new javax.swing.JPanel();
        plateImg4 = new javax.swing.JLabel();
        sendOrder = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        timeLeft = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        score = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        ingrediente5.setBackground(new java.awt.Color(204, 204, 255));

        iconIng5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconIng5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconIng5MouseClicked(evt);
            }
        });

        delIng5.setText("Basurero");
        delIng5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delIng5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ingrediente5Layout = new javax.swing.GroupLayout(ingrediente5);
        ingrediente5.setLayout(ingrediente5Layout);
        ingrediente5Layout.setHorizontalGroup(
            ingrediente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ingrediente5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(delIng5)
                .addContainerGap())
            .addComponent(iconIng5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ingrediente5Layout.setVerticalGroup(
            ingrediente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingrediente5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconIng5, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delIng5)
                .addContainerGap())
        );

        ingrediente4.setBackground(new java.awt.Color(102, 102, 255));

        iconIng4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconIng4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconIng4MouseClicked(evt);
            }
        });

        delIng4.setText("Basurero");
        delIng4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delIng4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ingrediente4Layout = new javax.swing.GroupLayout(ingrediente4);
        ingrediente4.setLayout(ingrediente4Layout);
        ingrediente4Layout.setHorizontalGroup(
            ingrediente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ingrediente4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(delIng4)
                .addContainerGap())
            .addComponent(iconIng4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ingrediente4Layout.setVerticalGroup(
            ingrediente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingrediente4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconIng4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delIng4)
                .addContainerGap())
        );

        ingrediente3.setBackground(new java.awt.Color(255, 51, 51));

        iconIng3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconIng3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconIng3MouseClicked(evt);
            }
        });

        delIng3.setText("Basurero");
        delIng3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delIng3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ingrediente3Layout = new javax.swing.GroupLayout(ingrediente3);
        ingrediente3.setLayout(ingrediente3Layout);
        ingrediente3Layout.setHorizontalGroup(
            ingrediente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ingrediente3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(delIng3)
                .addContainerGap())
            .addComponent(iconIng3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ingrediente3Layout.setVerticalGroup(
            ingrediente3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingrediente3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconIng3, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delIng3)
                .addContainerGap())
        );

        ingrediente2.setBackground(new java.awt.Color(255, 153, 153));

        iconIng2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconIng2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconIng2MouseClicked(evt);
            }
        });

        delIng2.setText("Basurero");
        delIng2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delIng2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ingrediente2Layout = new javax.swing.GroupLayout(ingrediente2);
        ingrediente2.setLayout(ingrediente2Layout);
        ingrediente2Layout.setHorizontalGroup(
            ingrediente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ingrediente2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(delIng2)
                .addContainerGap())
            .addComponent(iconIng2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ingrediente2Layout.setVerticalGroup(
            ingrediente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingrediente2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconIng2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delIng2)
                .addContainerGap())
        );

        ingrediente1.setBackground(new java.awt.Color(255, 255, 102));

        iconIng1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconIng1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconIng1MouseClicked(evt);
            }
        });

        delIng1.setText("Basurero");
        delIng1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delIng1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ingrediente1Layout = new javax.swing.GroupLayout(ingrediente1);
        ingrediente1.setLayout(ingrediente1Layout);
        ingrediente1Layout.setHorizontalGroup(
            ingrediente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ingrediente1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(delIng1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(iconIng1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ingrediente1Layout.setVerticalGroup(
            ingrediente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingrediente1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconIng1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delIng1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ingrediente5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(ingrediente4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(ingrediente3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(ingrediente2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(ingrediente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ingrediente5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingrediente4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingrediente3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingrediente2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingrediente1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        orders.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        order1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout order1Layout = new javax.swing.GroupLayout(order1);
        order1.setLayout(order1Layout);
        order1Layout.setHorizontalGroup(
            order1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, order1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(qOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        order1Layout.setVerticalGroup(
            order1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, order1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(qOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        order2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout order2Layout = new javax.swing.GroupLayout(order2);
        order2.setLayout(order2Layout);
        order2Layout.setHorizontalGroup(
            order2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, order2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(qOrder2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        order2Layout.setVerticalGroup(
            order2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, order2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(qOrder2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        order3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout order3Layout = new javax.swing.GroupLayout(order3);
        order3.setLayout(order3Layout);
        order3Layout.setHorizontalGroup(
            order3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, order3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(qOrder3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        order3Layout.setVerticalGroup(
            order3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, order3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(qOrder3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cola de Órdenes");

        javax.swing.GroupLayout ordersLayout = new javax.swing.GroupLayout(orders);
        orders.setLayout(ordersLayout);
        ordersLayout.setHorizontalGroup(
            ordersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ordersLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(order3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(order2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(order1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ordersLayout.setVerticalGroup(
            ordersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ordersLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ordersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(order3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(order2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(order1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(112, 112, 112))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        plate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        plateImg1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout plate1Layout = new javax.swing.GroupLayout(plate1);
        plate1.setLayout(plate1Layout);
        plate1Layout.setHorizontalGroup(
            plate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plate1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(plateImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        plate1Layout.setVerticalGroup(
            plate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plateImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        plate2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        plateImg2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout plate2Layout = new javax.swing.GroupLayout(plate2);
        plate2.setLayout(plate2Layout);
        plate2Layout.setHorizontalGroup(
            plate2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plateImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        plate2Layout.setVerticalGroup(
            plate2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plateImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        plate3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        plateImg3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout plate3Layout = new javax.swing.GroupLayout(plate3);
        plate3.setLayout(plate3Layout);
        plate3Layout.setHorizontalGroup(
            plate3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plateImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        plate3Layout.setVerticalGroup(
            plate3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plateImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        plate4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        plateImg4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout plate4Layout = new javax.swing.GroupLayout(plate4);
        plate4.setLayout(plate4Layout);
        plate4Layout.setHorizontalGroup(
            plate4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plateImg4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        plate4Layout.setVerticalGroup(
            plate4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plateImg4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        sendOrder.setText("Entregar orden");
        sendOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendOrderActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Platillo Actual");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(plate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(plate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(plate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(plate4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(plate4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plate3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(sendOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setText("Tiempo Restante:");

        timeLeft.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        timeLeft.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        timeLeft.setText("timeleft");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setText("Puntaje");

        score.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        score.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        score.setText("score");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(score, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(orders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(timeLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(orders, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeLeft))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(score)))
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendOrderActionPerformed
        comprobarOrden(ordenes.poll().getData().getId());
        UIorders.delete(0);
    }//GEN-LAST:event_sendOrderActionPerformed

    private void iconIng1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconIng1MouseClicked
        player.getPlatillo().insert(cinta.extract(4));

    }//GEN-LAST:event_iconIng1MouseClicked

    private void iconIng2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconIng2MouseClicked
        player.getPlatillo().insert(cinta.extract(3));
    }//GEN-LAST:event_iconIng2MouseClicked

    private void iconIng3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconIng3MouseClicked
        player.getPlatillo().insert(cinta.extract(2));
    }//GEN-LAST:event_iconIng3MouseClicked

    private void iconIng4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconIng4MouseClicked
        player.getPlatillo().insert(cinta.extract(1));
    }//GEN-LAST:event_iconIng4MouseClicked

    private void iconIng5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconIng5MouseClicked
        player.getPlatillo().insert(cinta.extract(0));
    }//GEN-LAST:event_iconIng5MouseClicked

    private void delIng5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delIng5ActionPerformed
        cinta.extract(0);
    }//GEN-LAST:event_delIng5ActionPerformed

    private void delIng3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delIng3ActionPerformed
        cinta.extract(2);
    }//GEN-LAST:event_delIng3ActionPerformed

    private void delIng1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delIng1ActionPerformed
        cinta.extract(4);
    }//GEN-LAST:event_delIng1ActionPerformed

    private void delIng4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delIng4ActionPerformed
        cinta.extract(1);
    }//GEN-LAST:event_delIng4ActionPerformed

    private void delIng2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delIng2ActionPerformed
        cinta.extract(3);
    }//GEN-LAST:event_delIng2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delIng1;
    private javax.swing.JButton delIng2;
    private javax.swing.JButton delIng3;
    private javax.swing.JButton delIng4;
    private javax.swing.JButton delIng5;
    private javax.swing.JLabel iconIng1;
    private javax.swing.JLabel iconIng2;
    private javax.swing.JLabel iconIng3;
    private javax.swing.JLabel iconIng4;
    private javax.swing.JLabel iconIng5;
    private javax.swing.JPanel ingrediente1;
    private javax.swing.JPanel ingrediente2;
    private javax.swing.JPanel ingrediente3;
    private javax.swing.JPanel ingrediente4;
    private javax.swing.JPanel ingrediente5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel order1;
    private javax.swing.JPanel order2;
    private javax.swing.JPanel order3;
    private javax.swing.JPanel orders;
    private javax.swing.JPanel plate1;
    private javax.swing.JPanel plate2;
    private javax.swing.JPanel plate3;
    private javax.swing.JPanel plate4;
    private javax.swing.JLabel plateImg1;
    private javax.swing.JLabel plateImg2;
    private javax.swing.JLabel plateImg3;
    private javax.swing.JLabel plateImg4;
    private javax.swing.JLabel qOrder1;
    private javax.swing.JLabel qOrder2;
    private javax.swing.JLabel qOrder3;
    private javax.swing.JLabel score;
    private javax.swing.JButton sendOrder;
    private javax.swing.JLabel timeLeft;
    // End of variables declaration//GEN-END:variables
}
