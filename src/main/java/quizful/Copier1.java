package quizful;

//class Copier<T extends Cloneable> {
class Copier<T extends PublicClone> {
    public T copy(T param) throws CloneNotSupportedException {
        return  (T) param.clone(); // error
    }
}

class PublicClone implements Cloneable{
    @Override
    public PublicClone clone() throws CloneNotSupportedException {
        return (PublicClone) super.clone();
    }
}

public class Copier1 {
    public static void main(String[] args) throws CloneNotSupportedException {
        PublicClone b = new PublicClone();
        Copier<PublicClone> copier = new Copier<>();
        PublicClone a = copier.copy(b);
    }
}
