package elc.tape;

public class StandaloneRead implements Comparable{

    private int id;

    private String nucleotides;

    static int next_id = 0;

    public StandaloneRead(String nucleotides) {
        this.nucleotides = nucleotides;
        this.id = next_id++;
    }

    public int getId() {
        return id;
    }

    public String getNucleotides() {
        return nucleotides;
    }

    public int length() {
        return nucleotides.length();
    }

    @Override
    public String toString() {
        return this.nucleotides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public int compareTo(Object o) {
        StandaloneRead other = (StandaloneRead) o;
        return Integer.compare(this.getId(), other.getId());
    }
}
