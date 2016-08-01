package elc.tape;

public class StandaloneRead extends Read {

    private String nucleotides;

    public StandaloneRead(String nucleotides) {
        super();
        this.nucleotides = nucleotides;
    }

    @Override
    public char getNucl(int index) {
        return this.nucleotides.charAt(index);
    }

    @Override
    public String getAllNucles() {
        return nucleotides;
    }

    public int getLength() {
        return nucleotides.length();
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.nucleotides.toString();
    }

}
