package elc.tape;

public abstract class Read implements Comparable {

    private int id;

    private static int next_id = 0;

    public Read() {
        this.id = next_id++;
    }

    public int getId() {
        return id;
    }

    public abstract char getNucl(int index);

    public abstract String getAllNucles();

    public abstract int getLength();

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        if (this == obj) return true;
        Read other = (Read) obj;
        if (this.getId() != other.getId()) return true;
        return false;
    }

    @Override
    public int compareTo(Object o) {
        Read other = (Read) o;
        return Integer.compare(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public String toString() {
        return "id=" + this.getId();
    }
}
