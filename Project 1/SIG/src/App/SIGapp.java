package App;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SIGapp extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadMenuItem;
    private JMenuItem SaveMenuItem;

    private JTable table1;
    private String[] cols1= {"NO.","Data", "Customer","Total"};
    private String[][] data1={
            {"1","new","Ahmed","200"},
    };

    private JTable table2;
    private String[] cols2= {"NO.","Item Name", "Item Price","Count","Item Total"};
    private String[][] data2={
           {"1","T.shirt","50","4","200"},
    };

    private JTextField invoicenubmer;
    private JTextField invoicedata;
    private JTextField name;
    private JTextField total;

    private JButton btn;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;


    public SIGapp (){
        super("SIG");
        setLayout(new FlowLayout());

// menu Bar
        menuBar = new JMenuBar();
        loadMenuItem = new JMenuItem("Load File");
        loadMenuItem.addActionListener(this);
        loadMenuItem.setActionCommand("L");
        SaveMenuItem = new JMenuItem("Save File");
        SaveMenuItem.addActionListener(this);
        SaveMenuItem.setActionCommand("S");
        fileMenu = new JMenu("File");

        fileMenu.add(loadMenuItem);
        fileMenu.add(SaveMenuItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
// Table 1
        table1 = new JTable(data1, cols1);
        add(new JScrollPane(table1));
        //int i=table1.getSelectedRow();
      //  TableModel model1= table1.getModel();
        //invoicenubmer.setText(model1.getValueAt(i,1).toString());
       //invoicedata.setText(model1.getValueAt(i,2).toString());
        //name.setText(model1.getValueAt(i,3).toString());
       // total.setText(model1.getValueAt(i,4).toString());
// Table 2
        table2 = new JTable(data2, cols2);
        add(new JScrollPane(table2));

        // data
        invoicenubmer = new JTextField( 10);
        add(new JLabel("Invoice Nubmer"));
        add(invoicenubmer);
        //invoicenubmer.setText("welcome");

        invoicedata = new JTextField( 10);
        add(new JLabel("Invoice Data"));
        add(invoicedata);

        name = new JTextField( 10);
        add(new JLabel("Customer Name"));
        add(name);

        total = new JTextField( 10);
        add(new JLabel("Invoice Total"));
        add(total);
// Button
        btn = new JButton("Create New Invoice");
        btn.addActionListener(this);
        btn.setActionCommand("Create");
        add(btn);

        btn2 = new JButton("Delete Invoice");
        btn2.addActionListener(this);
        btn2.setActionCommand("Delete");
        add(btn2);

        btn3 = new JButton("Save");
        add(btn3);

        btn4 = new JButton("Cancel");
        add(btn4);


        setSize(1000,600);
        setLocation(200,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        new SIGapp().setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
        case "L":
            openFile();

            break;

            case "S" :
                saveContent();

                break;

            case "Create" :
                if( invoicenubmer.getText().equals("") || invoicedata.getText().equals("") || name.getText().equals("") || total.getText().equals("")){
                    JOptionPane.showMessageDialog(this,"please enter All data !");
                }else {
                    String data[] = {invoicenubmer.getText(), invoicedata.getText(), name.getText(), total.getText()};
                    DefaultTableModel tb1model = (DefaultTableModel) table1.getModel();
                    tb1model.addRow(data);
                }

                break;

            case "Delete" :
                DefaultTableModel tb1Model = (DefaultTableModel) table1.getModel();

                if (table1.getSelectedRowCount()==1){
                    tb1Model.removeRow(table1.getSelectedRow());
                } else {
                    if(table1.getRowCount()==0){
                        JOptionPane.showMessageDialog(this,"Table is Empty.");

                    } else {
                        JOptionPane.showMessageDialog(this,"please select single Row For Delete.");

                    }
                }

                break;




    }
}

private Void openFile(){
        JFileChooser fc = new JFileChooser();
       int result = fc.showOpenDialog(this);
       if (result == JFileChooser.APPROVE_OPTION){
          String path = fc.getSelectedFile().getPath();
          FileInputStream fis = null;
          try {
             fis = new FileInputStream(path);
              int size = fis.available();
              byte[] b = new byte[size];
              fis.read(b);
              //ta.setText(new String(b));
          } catch (FileNotFoundException e){
              e.printStackTrace();

          } catch (IOException e){
              e.printStackTrace();

          } finally {
              try {
                  fis.close();
              } catch (IOException e) {}
          }
       }
    return null;
}



    private void saveContent (){
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path);
                //byte[] b = invoicenubmer.getText().getBytes();
                int size = 0;
                byte[] b = new byte[size];
                fos.write(b);
            }catch (FileNotFoundException e){
                e.printStackTrace();

            } catch (IOException e){
                e.printStackTrace();

            } finally {
                try {
                    fos.close();
                }catch (IOException e) {}

            }
        }
        }

    }

