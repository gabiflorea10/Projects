package view;

import controller.BankAdmin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics {

    public static void main(String[]args){
        BankAdmin bankAdmin=new BankAdmin();
        graphicalInterface(bankAdmin);
    }

    public static void graphicalInterface(BankAdmin bankAdmin) {
        JFrame frame = new JFrame("Bank");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JMenuBar menuBar=new JMenuBar();
        JMenu menu1=new JMenu("Person");
        JMenu menu2=new JMenu("Account");

        JMenuItem item1=new JMenuItem("View");
        JMenuItem item2=new JMenuItem("Add person");
        JMenuItem item4=new JMenuItem("Delete person");
        JMenuItem item3=new JMenuItem("Edit person");


        JMenuItem item5=new JMenuItem("View");
        JMenuItem item6=new JMenuItem("Add spending account");
        JMenuItem item7=new JMenuItem("Add saving account");
        JMenuItem item8=new JMenuItem("Delete account");
        JMenuItem item9=new JMenuItem("Edit account");
        JMenuItem item10=new JMenuItem("Deposit");
        JMenuItem item11=new JMenuItem("Withdrawal");


        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTable myTable= null;

                myTable = bankAdmin.showPersons();

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
                    Label label=new Label("Insert a person: ");
                    TextField tf0=new TextField("personId");
                    TextField tf1=new TextField("Name");
                    TextField tf2=new TextField("Phone");

                    Button button =new Button("Confirm");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            String s2=tf1.getText();
                            String s3=tf2.getText();
                            bankAdmin.addPersonInTable(Integer.parseInt(s1), s2, s3);
                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(tf1);
                    panel.add(tf2);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();


            }
        });

        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Label label=new Label("Delete a person: ");
                    TextField tf0=new TextField("Name");
                    Button button =new Button("Confirm");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            bankAdmin.removePersonFromTable(s1);
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

            }
        });

        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Label label=new Label("Edit a person by Name: ");
                Button button =new Button("Confirm");
                Label l=new Label("");
                TextField tf1=new TextField("Name");
                TextField tf2=new TextField("Phone");

                Button b1=new Button("Confirm Phone");

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s1=tf1.getText();

                        b1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s2=null;
                                s2=tf2.getText();
                                bankAdmin.editPersonInTabel(s1, s2);
                            }
                        });
                    }
                });
                JPanel panel=new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(label);
                panel.add(tf1);
                panel.add(button);
                panel.add(tf2);
                panel.add(b1);
                panel.revalidate();
                frame.setContentPane(panel);
                frame.revalidate();
            }
        });

        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTable myTable= null;

                myTable = bankAdmin.showAccounts();

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
                    Label label=new Label("Insert an spending account: ");

                    TextField tf0=new TextField("accountId");
                    TextField tf2=new TextField("holderName");
                    TextField tf3=new TextField("balance");
                    TextField tf4=new TextField("SpendingAccount");

                    Button button =new Button("Confirm");

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            String s3=tf2.getText();
                            String s4=tf3.getText();

                            bankAdmin.addSpendingAccountInTable(Integer.parseInt(s1), s3, Double.parseDouble(s4), "SpendingAccount");

                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(tf2);
                    panel.add(tf3);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();

            }
        });

        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Label label=new Label("Insert an saving account: ");

                    TextField tf0=new TextField("accountId");
                    TextField tf2=new TextField("holderName");
                    TextField tf3=new TextField("balance");
                    TextField tf5=new TextField("period");

                    Button button =new Button("Confirm");

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            String s3=tf2.getText();
                            String s4=tf3.getText();
                            String s6=tf5.getText();

                            bankAdmin.addSavingAccountInTable(Integer.parseInt(s1), s3, Double.parseDouble(s4), "SavingAccount", Integer.parseInt(s6));

                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(tf2);
                    panel.add(tf3);
                    panel.add(tf5);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();

            }
        });

        item8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Label label=new Label("Delete an account for a person: ");
                    TextField tf0=new TextField("Name");
                    TextField tf1=new TextField("AccountId");
                    Button button =new Button("Confirm");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String s1=tf0.getText();
                            String s2=tf1.getText();
                            bankAdmin.removeAccountFromTable(s1, Integer.parseInt(s2));
                        }
                    });

                    JPanel panel=new JPanel();
                    panel.setLayout(new FlowLayout());
                    panel.add(label);
                    panel.add(tf0);
                    panel.add(tf1);
                    panel.add(button);
                    panel.revalidate();
                    frame.setContentPane(panel);
                    frame.revalidate();
            }
        });

        item9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Label label=new Label("Edit an account by Id: ");
                Button button =new Button("Confirm");
                Label l=new Label("");
                TextField tf1=new TextField("Id");
                TextField tf2=new TextField("HolderName");

                Button b1=new Button("Confirm Name");

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s1=tf1.getText();

                        b1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s2=null;
                                s2=tf2.getText();
                                bankAdmin.editAccountInTabel(Integer.parseInt(s1), s2);
                            }
                        });
                    }
                });
                JPanel panel=new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(label);
                panel.add(tf1);
                panel.add(button);
                panel.add(tf2);
                panel.add(b1);
                panel.revalidate();
                frame.setContentPane(panel);
                frame.revalidate();

            }
        });

        item10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Label label=new Label("Deposit: ");
                Button button =new Button("Confirm");
                TextField tf1=new TextField("Insert account Id");
                TextField tf2=new TextField("Insert the amount");
                TextArea message=new TextArea(1, 30);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s1=tf1.getText();
                        String s2=null;
                        s2=tf2.getText();
                        String[] mess;
                        mess=bankAdmin.depositAction(Integer.parseInt(s1), Integer.parseInt(s2));
                        message.setText(mess[0]);
                    }
                });
                JPanel panel=new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(label);
                panel.add(tf1);
                panel.add(tf2);
                panel.add(button);
                panel.revalidate();
                panel.add(message);
                panel.revalidate();
                frame.setContentPane(panel);
                frame.revalidate();


            }
        });

        item11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Label label=new Label("Withdrawal: ");
                Button button =new Button("Confirm");
                TextField tf1=new TextField("Insert account Id");
                TextField tf2=new TextField("Insert the amount");
                TextArea message=new TextArea(1, 30);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                                String s1=tf1.getText();
                                String s2=null;
                                s2=tf2.getText();
                                String[] mess;
                                mess=bankAdmin.withdrawalAction(Integer.parseInt(s1), Integer.parseInt(s2));
                                message.setText(mess[0]);
                            }
                        });
                JPanel panel=new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(label);
                panel.add(tf1);
                panel.add(tf2);
                panel.add(button);
                panel.revalidate();
                panel.add(message);
                panel.revalidate();
                frame.setContentPane(panel);
                frame.revalidate();


            }
        });

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item4);
        menu1.add(item3);

        menu2.add(item5);
        menu2.add(item6);
        menu2.add(item7);
        menu2.add(item8);
        menu2.add(item9);
        menu2.add(item10);
        menu2.add(item11);

        menuBar.add(menu1);
        menuBar.add(menu2);

        frame.setJMenuBar(menuBar);
        frame.revalidate();
        frame.setVisible(true);

    }
}
