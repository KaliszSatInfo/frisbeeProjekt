import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UcastniciForm extends JFrame {
    private List<Ucastnik> seznamPanicu =  new ArrayList<>();
    private JPanel panel;
    private JTable tabulka;
    private File selectedFile;

    public UcastniciForm(){
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        initMenu();
    }

    public void initMenu(){
        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);

        JMenuItem load = new JMenuItem("Načti");
        mbar.add(load);
        load.addActionListener(e-> {
          loadFile();
          readFile(selectedFile);
          renderTable();
        });

        JMenuItem writeIntoFile = new JMenuItem("Ulož");
        mbar.add(writeIntoFile);
        writeIntoFile.addActionListener(e -> {
            loadFile();
            writeIntoFile(selectedFile);
            renderTable();
        });

                JMenuItem numPpl = new JMenuItem("Počet lidí");
        mbar.add(numPpl);
        numPpl.addActionListener(e-> getPplNum());

        JMenuItem numPplMore = new JMenuItem("> 10");
        mbar.add(numPplMore);
        numPplMore.addActionListener(e-> getHigherNum());
    }

    private void getPplNum() {
        int numOfPpl = 0;
        for (Ucastnik s : seznamPanicu){
            numOfPpl++;
        }
        JOptionPane.showMessageDialog(this, "počet lidí: " + numOfPpl);
    }

    private void getHigherNum() {
        int numOfPpl = 0;
        for (Ucastnik s : seznamPanicu){
            if(s.getStartNum() > 10){
                numOfPpl++;
            }
        }
        JOptionPane.showMessageDialog(this, "počet lidí: " + numOfPpl);
    }

    public void loadFile(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("text", "txt"));
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
        }
    }
    public void readFile(File selectedFile){
        seznamPanicu.clear();
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))){
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] bloky = line.split("\\|");
                String jmeno = bloky[0];
                int startNum = Integer.parseInt(bloky[1]);
                seznamPanicu.add(new Ucastnik(jmeno, new Tym(0, "Tym1", null, null, null ),startNum));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeIntoFile(File selectedFile){
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.selectedFile)))){
            for(Ucastnik u : seznamPanicu){
                pw.write(u.getJmeno() + "|" + u.getStartNum() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void renderTable(){
        tabulka.setModel(new TabulkaModel());
    }


    public class TabulkaModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return seznamPanicu.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Ucastnik u = seznamPanicu.get(rowIndex);
            switch (columnIndex){
                case 0: return u.getJmeno();
                case 1: return u.getStartNum();
            }
            return null;
        }

        public String getColumnName(int columnIndex){
            switch (columnIndex){
                case 0: return "Jméno";
                case 1: return "Číslo";
            }
            return super.getColumnName(columnIndex);
        }
    }
}
