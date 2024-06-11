public class Ucastnik {
    private String jmeno;
    private Tym tym;
    private int startNum;

    public Ucastnik(String jmeno, Tym tym, int startNum) {
        this.jmeno = jmeno;
        this.tym = tym;
        this.startNum = startNum;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public Tym getTym() {
        return tym;
    }

    public void setTym(Tym tym) {
        this.tym = tym;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }
}
