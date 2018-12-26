package tmw_info.old;

import org.h2.tools.Server;
import tmw_info.UserSnapshot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class SnapshotToIntervalsConverter {

    private static final String INSERT_INTERVAL =
            "INSERT INTO interval (username, begin, end) VALUES (?, ?, ?)";

    public static void main(String[] args) throws SQLException {
        SnapshotToIntervalsConverter converter = new SnapshotToIntervalsConverter();
        Connection conn;
        conn = converter.establishDbConnection();
        List<UserSnapshot> snapshots = converter.readFromH2(conn);
        Map<String, List<Timestamp>> collect = snapshots.stream()
                .collect(groupingBy(
                        UserSnapshot::getUserName,
                        TreeMap::new,
                        mapping(UserSnapshot::getTimestamp, toList())));
        converter.recreateTableInterval(conn);
        for (String userName : collect.keySet()) {
            List<Timestamp> timestamps = collect.get(userName);
            List<Interval> intervals = converter.createIntervals(userName, timestamps);
            converter.storeIntervalsToDb(conn, intervals);
        }
        conn.close();
    }

    private void storeIntervalsToDb(Connection conn, List<Interval> intervals) {
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT_INTERVAL);
            for (Interval interval : intervals) {
                statement.setString(1, interval.getUserName());
                statement.setTimestamp(2, interval.getBegin());
                statement.setTimestamp(3, interval.getEnd());
                statement.execute();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void recreateTableInterval(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS interval");
            statement.executeUpdate("CREATE TABLE interval (id IDENTITY, " +
                    "username VARCHAR(255) NOT NULL, begin TIMESTAMP, " +
                    "end TIMESTAMP)");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Interval> createIntervals(String user, List<Timestamp> timestamps) {
        timestamps.sort(Timestamp::compareTo);
        List<Interval> intervals = new ArrayList<>();
        Timestamp beginOfInterval = timestamps.get(0);
        Timestamp previous = beginOfInterval;
        for (Timestamp timestamp : timestamps) {
            if (isBrokenInterval(previous, timestamp)) {
                intervals.add(new Interval(user, beginOfInterval, previous));
                beginOfInterval = timestamp;
            }
            previous = timestamp;
        }
        Timestamp now = Timestamp.from(Instant.now());
        if (isBrokenInterval(previous, now)) {
            intervals.add(new Interval(user, beginOfInterval, previous));
        }
        return intervals;
    }

    private boolean isBrokenInterval(Timestamp previous, Timestamp current) {
        return current.getTime() - previous.getTime() > 10_000;
    }

    private void startDbServer() {
        try {
            Server.createWebServer("-webAllowOthers","-webPort","8083").start();
            Server.createTcpServer("-tcpAllowOthers","-tcpPort","9093").start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<UserSnapshot> readFromH2(Connection conn) {
        ArrayList<UserSnapshot> result = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery(
                    "SELECT name, timestamp FROM snapshot");
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                Timestamp timestamp = resultSet.getTimestamp(2);
                result.add(new UserSnapshot(name, timestamp));
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private Connection establishDbConnection() {
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
//            conn = DriverManager.getConnection(
//                    "jdbc:h2:~/test;AUTO_SERVER=TRUE;AUTO_SERVER_PORT=41440",
//                    "sa",
//                    "");
            conn = DriverManager.getConnection(
                    "jdbc:h2:~/tmw_informer;AUTO_SERVER=TRUE;AUTO_SERVER_PORT=41444",
                    "admin",
                    "Yw3w79Ziw8DuhDZ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            System.exit(1);
        }
        return conn;
    }
}