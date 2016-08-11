package bioinf.tape;

public class StandaloneRead extends Read {

    private String bases;

    public StandaloneRead(String bases) {
        super();
        this.bases = bases;
    }

    @Override
    public char getBase(int index) {
        return this.bases.charAt(index);
    }

    @Override
    public String getAllBases() {
        return bases;
    }

    public int getLength() {
        return bases.length();
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.bases.toString();
    }

}
