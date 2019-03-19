package presentation;

import businessLogic.ClientAdmin;
import businessLogic.OrderProcessing;
import businessLogic.SellerAdmin;
import businessLogic.WarehouseAdmin;
import dataAccess.ConnectionFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Clasa Graphics implementeaza interfata grafica a aplicatiei curente
 * Aceasta clasa este compusa din doua metode: metoda main, necesara oricarei aplicatii Java, respectiv
 * metoda graphicalInterface, in care este realizata grafica efectiva a aplicatiei
 * Aplicatia e bazata pe un meniu din intermediul caruia pot fi accesate diversele facilitati ale acesteia
 */

public class Graphics {

    public static void main(String[] args) {
        graphicalInterface();
    }

    public static void graphicalInterface(){
        JFrame frame = new JFrame("Order Management");
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar=new JMenuBar();
        JMenu menu1=new JMenu("Cusotmer");
        JMenu menu2=new JMenu("Product");
        JMenu menu3=new JMenu("Seller");
        JMenu menu4=new JMenu("Order");

        JMenuItem item1=new JMenuItem("View table");
        JMenuItem item2=new JMenuItem("Add on table");
        JMenuItem item3=new JMenuItem("Edit on table");
        JMenuItem item4=new JMenuItem("Delete on table");

        JMenuItem item5=new JMenuItem("View table");
        JMenuItem item6=new JMenuItem("Add on table");
        JMenuItem item7=new JMenuItem("Edit on table");
        JMenuItem item8=new JMenuItem("Delete on table");

        JMenuItem item9=new JMenuItem("View table");
        JMenuItem item10=new JMenuItem("Add on table");
        JMenuItem item11=new JMenuItem("Edit on table");
        JMenuItem item12=new JMenuItem("Delete on table");

        JMenuItem item13=new JMenuItem("View past orders");
        JMenuItem item14=new JMenuItem("Add a new order");

        ConnectionFactory c=new ConnectionFactory();

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientAdmin clientAdmin=new ClientAdmin();
                JTable myTable= null;
                try {
                    myTable = clientAdmin.showCustomers();
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                } catch (IntrospectionException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                JPanel panel=new JPanel();
                JScrollPane pane=new JScrollPane(myTable);
                panel.setLayout(new BorderLayout());
                panel.add(pane, BorderLayout.CENTER);
                panel.revalidate();
                frame.setContentPane(panel);
                frame.revalidate();

            }});

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Label label=new Label("Insert a customer: ");
                    TextField tf0=new TextField("Id");
                    TextField tf1=new TextField("Name");
                    TextField tf2=new TextField("Address");
                    TextField tf3=new TextField("Phone");
                    TextField tf4=new TextField("Email");

                    Button button =new Button("Confirm");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            String s2=tf1.getText();
                            String s3=tf2.getText();
                            String s4=tf3.getText();
                            String s5=tf4.getText();

                            ClientAdmin clientAdmin=new ClientAdmin();
                            try {
                                clientAdmin.addCustomer(s1,s2,s3,s4,s5);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(tf1);
                    panel.add(tf2);
                    panel.add(tf3);
                    panel.add(tf4);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


                }catch (Exception exeception){
                    System.out.println("Exception...");
                }


            }
        });

        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Label label=new Label("Edit a customer by Id: ");
                    Button button =new Button("Confirm");
                    Label l=new Label("                                                                                                                           ");
                    TextField tf0=new TextField("Id");
                    TextField tf1=new TextField("Name");
                    TextField tf2=new TextField("Address");
                    TextField tf3=new TextField("Phone");
                    TextField tf4=new TextField("Email");

                    Button b2=new Button("Confirm Name");
                    Button b3=new Button("Confirm Address");
                    Button b4=new Button("Confirm Phone");
                    Button b5=new Button("Confirm Email");

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            ClientAdmin clientAdmin=new ClientAdmin();
                                b2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String s2=null, s3=null, s4=null, s5=null;
                                        s2=tf1.getText();
                                        try {
                                            clientAdmin.editCustomer(s1, s2, s3, s4, s5);
                                        } catch (SQLException e1) {
                                            e1.printStackTrace();
                                        } catch (IllegalAccessException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                });

                            b3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null, s5=null;
                                    s3=tf2.getText();
                                    try {
                                        clientAdmin.editCustomer(s1, s2, s3, s4, s5);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }

                                }
                            });

                            b4.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null, s5=null;
                                    s4=tf3.getText();
                                    try {
                                        clientAdmin.editCustomer(s1, s2, s3, s4, s5);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                            b5.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null, s5=null;
                                    s5=tf4.getText();
                                    try {
                                        clientAdmin.editCustomer(s1, s2, s3, s4, s5);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(button);
                    panel.add(l);
                    panel.add(tf1);
                    panel.add(b2);
                    panel.add(tf2);
                    panel.add(b3);
                    panel.add(tf3);
                    panel.add(b4);
                    panel.add(tf4);
                    panel.add(b5);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


                }catch (Exception exeception){
                    System.out.println("Exception...");
                }



            }
        });

        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Label label=new Label("Delete a customer by Id: ");
                    TextField tf0=new TextField("Id");
                    Button button =new Button("Confirm");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            ClientAdmin clientAdmin=new ClientAdmin();
                            try {
                                clientAdmin.deleteCustomer(s1);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


                }catch (Exception exeception){
                    System.out.println("Exception...");
                }



            }
        });

        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               WarehouseAdmin warehouseAdmin=new WarehouseAdmin();
                JTable myTable= null;
                try {
                    myTable = warehouseAdmin.showProducts();
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                } catch (IntrospectionException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                JPanel panel=new JPanel();
                JScrollPane pane=new JScrollPane(myTable);
                panel.setLayout(new BorderLayout());
                panel.add(pane, BorderLayout.CENTER);
                panel.revalidate();
                frame.setContentPane(panel);
                frame.revalidate();
            }
        });

        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Label label=new Label("Insert a product: ");

                    TextField tf0=new TextField("Id");
                    TextField tf1=new TextField("Name");
                    TextField tf2=new TextField("Type");
                    TextField tf3=new TextField("Others");
                    TextField tf4=new TextField("Quantity_in_stock");
                    TextField tf5=new TextField("Price_per_unit");
                    TextField tf6=new TextField("IdSeller");

                    Button button =new Button("Confirm");

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            String s2=tf1.getText();
                            String s3=tf2.getText();
                            String s4=tf3.getText();
                            String s5=tf4.getText();
                            String s6=tf5.getText();
                            String s7=tf6.getText();

                            WarehouseAdmin warehouseAdmin=new WarehouseAdmin();
                            try {
                                warehouseAdmin.addProduct(s1, s2, s3, s4, s5, s6, s7);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(tf1);
                    panel.add(tf2);
                    panel.add(tf3);
                    panel.add(tf4);
                    panel.add(tf5);
                    panel.add(tf6);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


                }catch (Exception exeception){
                    System.out.println("Exception...");
                }

            }
        });

        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Label label=new Label("Edit a product by Id: ");
                    Button button =new Button("Confirm");
                    Label l=new Label("                                                                                                                                              ");
                    Label l1=new Label("               ");
                    TextField tf0=new TextField("Id");
                    TextField tf1=new TextField("Name");
                    TextField tf2=new TextField("Type");
                    TextField tf3=new TextField("Others");
                    TextField tf4=new TextField("Quantity_in_stock");
                    TextField tf5=new TextField("Price_per_unit");
                    TextField tf6=new TextField("IdSeller");

                    Button b2=new Button("Confirm Name");
                    Button b3=new Button("Confirm Type");
                    Button b4=new Button("Confirm Others");
                    Button b5=new Button("Confirm Quantity");
                    Button b6=new Button("Confirm Price");
                    Button b7=new Button("Confirm IdSeller");

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            WarehouseAdmin warehouseAdmin=new WarehouseAdmin();
                            b2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null, s5=null, s6=null, s7=null;
                                    s2=tf1.getText();
                                    try {
                                        warehouseAdmin.editProduct(s1, s2, s3, s4, s5, s6, s7);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                            b3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null, s5=null, s6=null, s7=null;
                                    s3=tf2.getText();
                                    try {
                                        warehouseAdmin.editProduct(s1, s2, s3, s4, s5, s6, s7);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                            b4.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null, s5=null, s6=null, s7=null;
                                    s4=tf3.getText();
                                    try {
                                        warehouseAdmin.editProduct(s1, s2, s3, s4, s5, s6, s7);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                            b5.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null, s5=null, s6=null, s7=null;
                                    s5=tf4.getText();
                                    try {
                                        warehouseAdmin.editProduct(s1, s2, s3, s4, s5, s6, s7);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                            b6.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null, s5=null, s6=null, s7=null;
                                    s6=tf5.getText();
                                    try {
                                        warehouseAdmin.editProduct(s1, s2, s3, s4, s5, s6, s7);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                            b7.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null, s5=null, s6=null, s7=null;
                                    s7=tf6.getText();
                                    try {
                                        warehouseAdmin.editProduct(s1, s2, s3, s4, s5, s6, s7);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(button);
                    panel.add(l);
                    panel.add(tf1);
                    panel.add(b2);
                    panel.add(tf2);
                    panel.add(b3);
                    panel.add(tf3);
                    panel.add(b4);
                    panel.add(l1);
                    panel.add(tf4);
                    panel.add(b5);
                    panel.add(tf5);
                    panel.add(b6);
                    panel.add(tf6);
                    panel.add(b7);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


                }catch (Exception exeception){
                    System.out.println("Exception...");
                }

            }
        });

        item8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Label label=new Label("Delete a product by Id: ");
                    TextField tf0=new TextField("Id");
                    Button button =new Button("Confirm");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            WarehouseAdmin warehouseAdmin=new WarehouseAdmin();
                            try {
                                warehouseAdmin.deleteProduct(s1);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


                }catch (Exception exeception){
                    System.out.println("Exception...");
                }



            }
        });

        item9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SellerAdmin sellerAdmin=new SellerAdmin();
                JTable myTable= null;
                try {
                    myTable = sellerAdmin.showSellers();
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                } catch (IntrospectionException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                JPanel panel=new JPanel();
                JScrollPane pane=new JScrollPane(myTable);
                panel.setLayout(new BorderLayout());
                panel.add(pane, BorderLayout.CENTER);
                panel.revalidate();
                frame.setContentPane(panel);
                frame.revalidate();

            }
        });

        item10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Label label=new Label("Insert a seller: ");
                    TextField tf0=new TextField("Id");
                    TextField tf1=new TextField("Name");
                    TextField tf2=new TextField("Phone");
                    TextField tf3=new TextField("Address");

                    Button button =new Button("Confirm");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            String s2=tf1.getText();
                            String s3=tf2.getText();
                            String s4=tf3.getText();

                            SellerAdmin sellerAdmin=new SellerAdmin();
                            try {
                                sellerAdmin.addSeller(s1,s2,s3,s4);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(tf1);
                    panel.add(tf2);
                    panel.add(tf3);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


                }catch (Exception exeception){
                    System.out.println("Exception...");
                }


            }
        });

        item11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Label label=new Label("Edit a seller by Id: ");
                    Button button =new Button("Confirm");
                    Label l=new Label("                                                                                                                                         ");
                    TextField tf0=new TextField("Id");
                    TextField tf1=new TextField("Name");
                    TextField tf2=new TextField("Phone");
                    TextField tf3=new TextField("Address");

                    Button b2=new Button("Confirm Name");
                    Button b3=new Button("Confirm Phone");
                    Button b4=new Button("Confirm Address");

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            SellerAdmin sellerAdmin=new SellerAdmin();
                            b2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null;
                                    s2=tf1.getText();
                                    try {
                                        sellerAdmin.editSeller(s1, s2, s3, s4);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                            b3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null;
                                    s3=tf2.getText();
                                    try {
                                        sellerAdmin.editSeller(s1, s2, s3, s4);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                            b4.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String s2=null, s3=null, s4=null;
                                    s4=tf3.getText();
                                    try {
                                        sellerAdmin.editSeller(s1, s2, s3, s4);
                                    } catch (SQLException e1) {
                                        e1.printStackTrace();
                                    } catch (IllegalAccessException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });

                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(button);
                    panel.add(l);
                    panel.add(tf1);
                    panel.add(b2);
                    panel.add(tf2);
                    panel.add(b3);
                    panel.add(tf3);
                    panel.add(b4);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


                }catch (Exception exeception){
                    System.out.println("Exception...");
                }



            }
        });

        item12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Label label=new Label("Delete a seller by Id: ");
                    TextField tf0=new TextField("Id");
                    Button button =new Button("Confirm");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            SellerAdmin sellerAdmin=new SellerAdmin();
                            try {
                                sellerAdmin.deleteSeller(s1);
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


                }catch (Exception exeception){
                    System.out.println("Exception...");
                }

            }
        });

        item13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderProcessing orderProcessing=new OrderProcessing();
                JTable myTable= null;
                try {
                    myTable = orderProcessing.showOrders();
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                } catch (IntrospectionException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                JPanel panel=new JPanel();
                JScrollPane pane=new JScrollPane(myTable);
                panel.setLayout(new BorderLayout());
                panel.add(pane, BorderLayout.CENTER);
                panel.revalidate();
                frame.setContentPane(panel);
                frame.revalidate();
            }
        });

        item14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel=new JPanel();
                Label label=new Label("Insert a new order: ");
                Label labelMessage=new Label("");
                TextField tf0=new TextField("IdProduct");
                TextField tf1=new TextField("IdCustomer");
                TextField tf2=new TextField("Quantity");
                Button button =new Button("Confirm");


                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        String s1=tf0.getText();
                        String s2=tf1.getText();
                        String s3=tf2.getText();

                        OrderProcessing orderProcessing=new OrderProcessing();

                        String message= null;
                        try {
                            message = orderProcessing.addOrder(s1, s2, s3);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (NoSuchMethodException e1) {
                            e1.printStackTrace();
                        } catch (IntrospectionException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (InvocationTargetException e1) {
                            e1.printStackTrace();
                        }
                        if(message!=null){
                                labelMessage.setText(message);
                                panel.revalidate();
                            }

                    }
                });


                panel.setLayout(new FlowLayout());
                panel.add(label);
                panel.add(tf0);
                panel.add(tf1);
                panel.add(tf2);
                panel.add(button);
                panel.add(labelMessage);
                panel.revalidate();
                frame.setContentPane(panel);
                frame.revalidate();


            }
        });

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);

        menu2.add(item5);
        menu2.add(item6);
        menu2.add(item7);
        menu2.add(item8);

        menu3.add(item9);
        menu3.add(item10);
        menu3.add(item11);
        menu3.add(item12);

        menu4.add(item13);
        menu4.add(item14);

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);

        frame.setJMenuBar(menuBar);
        frame.revalidate();
        frame.setVisible(true);

    }


}