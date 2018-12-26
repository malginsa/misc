package tmw_info.old;

import java.sql.Timestamp;

public class Interval {
    private String userName;
    private Timestamp begin;
    private Timestamp end;

    public Interval(String userName, Timestamp begin, Timestamp end) {
        this.userName = userName;
        this.begin = begin;
        this.end = end;
    }

    public String getUserName() {
        return userName;
    }

    public Timestamp getBegin() {
        return begin;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    @Override
    public String toString() {
//        return "Interval{" +
//                "userName='" + userName + '\'' +
//                ", begin=" + begin +
//                ", end=" + end +
//                '}';
        return "{" + begin + " -- " + end + "}  ";
    }
}
