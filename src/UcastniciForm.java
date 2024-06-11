import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
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
        load.addActionListener(_-> loadFile());

        JMenuItem writeIntoFile = new JMenuItem("Ulož");
        mbar.add(writeIntoFile);
        writeIntoFile.addActionListener(_-> loadFile2());

        JMenuItem numPpl = new JMenuItem("Počet lidí");
        mbar.add(numPpl);
        numPpl.addActionListener(_-> getPplNum());

        JMenuItem numPplIfTheirStartingNumberIsHighterThanTheNumericalValueOfTen = new JMenuItem("Počet lidí, jejichž číslo přesahuje hodnotu čísla deset");
        mbar.add(numPplIfTheirStartingNumberIsHighterThanTheNumericalValueOfTen);
        numPplIfTheirStartingNumberIsHighterThanTheNumericalValueOfTen.addActionListener(_-> getnumPplIfTheirStartingNumberIsHighterThanTheNumericalValueOfTen());
    }

    private void getPplNum() {
        int numOfPpl = 0;
        for (Ucastnik s : seznamPanicu){
            numOfPpl++;
        }
        JOptionPane.showMessageDialog(this, "počet lidí: " + numOfPpl);
    }

    private void getnumPplIfTheirStartingNumberIsHighterThanTheNumericalValueOfTen() {
        int getnumPplIfTheirStartingNumberIsHighterThanTheNumericalValueOfTen123456789 = 0;
        for (Ucastnik s : seznamPanicu){
            if(s.getStartNum() > 10){
                getnumPplIfTheirStartingNumberIsHighterThanTheNumericalValueOfTen123456789++;
            }
        }
        JOptionPane.showMessageDialog(this, "počet lidí: " + getnumPplIfTheirStartingNumberIsHighterThanTheNumericalValueOfTen123456789);
    }

    public void loadFile(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("text", "txt"));
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            readFile(selectedFile);
            renderTable();
        }
    }
    public void loadFile2(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("text", "txt"));
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            writeIntoFile(selectedFile);
            renderTable();
        }
    }
    public void readFile(File selectedFile){
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
