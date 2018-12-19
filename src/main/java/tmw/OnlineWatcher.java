package tmw;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OnlineWatcher
{
    public static void main(String[] args) throws IOException
    {
        List<String> listNow = new OnlineWatcher().getListNow();
        System.out.println(listNow);
    }

    private void dbGo() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        Class.forName("org.h2.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:h2:test", "sa", "");
//        Statement st = null;
//        st = conn.createStatement();
//        st.execute("INSERT INTO TEST VALUES(default,'HELLO')");
//        st.execute("INSERT INTO TEST(NAME) VALUES('JOHN')");
//        String name1 = "Jack";
//        String q = "insert into TEST(name) values(?)";
//        PreparedStatement st1 = null;
//
//        st1 = conn.prepareStatement(q);
//        st1.setString(1, name1);
//        st1.execute();
//
//        ResultSet result;
//        result = st.executeQuery("SELECT * FROM TEST");
//        while (result.next())
//        {
//            String name = result.getString("NAME");
//            System.out.println(result.getString("ID") + " " + name);
//        }
    }

    private List<String> getListNow() throws IOException
    {
        return Jsoup.connect("https://server.themanaworld.org/")
                .get()
                .getElementsByTag("body")
                .select("td")
                .stream()
                .map(Element::text)
                .collect(Collectors.toList());
    }
}
