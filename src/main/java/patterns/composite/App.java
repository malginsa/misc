package patterns.composite;

public class App {

    public static void main(String[] args) {

        Component src = new Folder("src")
                .add(new File("App.java", 2))
                .add(new File("File.java", 3))
                .add(new File("Folder.java", 6));

        Component target = new Folder("target")
                .add(new File("App.class", 3))
                .add(new File("File.class", 4))
                .add(new File("Folder.class", 8));

        Component project = new Folder("project")
                .add(src)
                .add(new File("pom.xml", 2))
                .add(target);

        System.out.println(project);

    }
}
