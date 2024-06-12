import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tym {
    private int num;
    private String name;
    private List<Ucastnik> seznamUcastniku =  new ArrayList<>();
    private BigDecimal cena;
    private LocalDate datumRegistrace;

    public Tym(int num, String name, List<Ucastnik> seznamUcastniku, BigDecimal cena, LocalDate datumRegistrace) {
        Main man = new Main();
        this.num = num;
        this.name = name;
        this.seznamUcastniku = seznamUcastniku;
        this.cena = man.vychoziCenaTurnaje;
        this.datumRegistrace = LocalDate.now();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ucastnik> getSeznamUcastniku() {
        return seznamUcastniku;
    }

    public void setSeznamUcastniku(List<Ucastnik> seznamUcastniku) {
        this.seznamUcastniku = seznamUcastniku;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public LocalDate getDatumRegistrace() {
        return datumRegistrace;
    }

    public void setDatumRegistrace(LocalDate datumRegistrace) {
        this.datumRegistrace = datumRegistrace;
    }
}
