package tmw;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.Server;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OnlineUsersTracker {

    private static final Logger LOG = LogManager.getLogger();
    private static final Pattern DATE_TIME = Pattern.compile(
            "\\((\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\)");
    private static final DateTimeFormatter TMW_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String INSERT_PARAMETERIZED =
            "INSERT INTO snapshot(name, timestamp) VALUES (?, ?)";
    private static final List<String> IGNORABLE_USERS = Arrays.asList(
            "Corson", "Bukavac", "playerone", "Aamon", "Abezethibou",
            "Abraxax", "Abyzou", "Adrammelech", "Aeshma", "Agaliarept",
            "Agrat", "Agiel", "Haborym", "Alloces", "Allu", "Amaymon",
            "Amdusias", "Anammelech", "Ancitif", "Armaros", "Andhaka",
            "Andrealphus", "Anzu", "Arunasura", "Asag", "Asakku", "Barong",
            "Bael", "Bakasura", "Baku", "Balberith", "Bali", "Barbas",
            "Barbatos", "Bathin", "Beleth", "Berith", "Bifrons", "Botis",
            "Buer", "Bune", "Bushyasta", "Chemosh", "Cimejes", "Crocell",
            "Culsu", "Daeva", "Dajjal", "Dantalion", "Danjal", "Decarabia",
            "Demiurge", "Drekavac", "Dzoavits", "Eblis", "Eisheth", "Eligos",
            "Foras", "Forcas", "Forras", "Forneus", "Furcas", "Caprice",
            "Gaap", "Gaderel", "Gaki", "Gamigin", "Glasya", "Gremory",
            "Grigori", "Gualichu", "Guayota", "Haagenti", "Hauras", "Haures",
            "Havres", "Hinn", "Ipos", "Jikininki", "Kabandha", "Kasadya",
            "Kroni", "Killakee", "Kukudh", "Kumbhakarna", "Lechies"
    );


    public static void main(String[] args) throws Exception {
        OnlineUsersTracker watcher = new OnlineUsersTracker();
        Connection conn;
        conn = watcher.establishDbConnection();
        watcher.startDbServer();
        while (conn != null) {
            OnlineUsersSnapshot snapshot = watcher.getSnapshotNow();
            watcher.removeBots(snapshot.users);
            watcher.storeSnapshotToDb(snapshot, conn);
            System.out.println(snapshot);
//            watcher.readH2(conn);
            Utils.delay(9_000);
        }
        conn.close();
    }

    private void startDbServer() {
        try {
            Server.createWebServer("-webAllowOthers","-webPort","8082").start();
            Server.createTcpServer("-tcpAllowOthers","-tcpPort","9092").start();
        } catch (SQLException e) {
            LOG.error("Can't instantiate d2 database driver" + e);
        }
    }

    private void removeBots(List<String> users) {
        users.removeAll(IGNORABLE_USERS);
    }

    private Connection establishDbConnection() {
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver").newInstance();
        } catch (InstantiationException e) {
            LOG.error("Can't instantiate d2 database driver" + e);
        } catch (IllegalAccessException e) {
            LOG.error("Can't get access to d2 database driver" + e);
        } catch (ClassNotFoundException e) {
            LOG.error("Can't find d2 database driver" + e);
        }
        try {
            conn = DriverManager.getConnection(
                    "jdbc:h2:~/tmw_informer;AUTO_SERVER=TRUE;AUTO_SERVER_PORT=41444",
                    "admin",
                    "Yw3w79Ziw8DuhDZ");
        } catch (SQLException e) {
            LOG.error("Can't get connection to d2 database driver" + e);
        }
        if (conn == null) {
            System.exit(1);
        }
        return conn;
    }

    private void storeSnapshotToDb(OnlineUsersSnapshot snapshot, Connection conn) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_PARAMETERIZED);
            for (String user : snapshot.users) {
                preparedStatement.setString(1, user);
                preparedStatement.setTimestamp(2, Timestamp.valueOf(snapshot.dateTime));
                preparedStatement.execute();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OnlineUsersSnapshot getSnapshotNow() throws IOException {
        Elements body = Jsoup.connect("https://server.themanaworld.org/")
                .get()
                .getElementsByTag("body");

        String h3Text = body.select("h3").toString();
        OnlineUsersSnapshot snapshot = new OnlineUsersSnapshot();
        snapshot.dateTime = extractDateTimeOrSetNow(h3Text);

        snapshot.users = body
                .select("td")
                .stream()
                .map(Element::text)
                .collect(Collectors.toList());

        return snapshot;
    }

    private LocalDateTime extractDateTimeOrSetNow(String h3Text) {
        Matcher matcher = DATE_TIME.matcher(h3Text);
        LocalDateTime dateTime;
        if (matcher.find() && matcher.groupCount() == 1) {
            String dateTimeAsString = matcher.group(1);
            try {
                dateTime = LocalDateTime.parse(dateTimeAsString, TMW_DATE_TIME_FORMAT);
            } catch (DateTimeParseException e) {
                dateTime = LocalDateTime.now();
            }
        } else {
            dateTime = LocalDateTime.now();
        }
        return dateTime;
    }

    private void readH2(Connection conn) {
        try {
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM snapshot;");
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
