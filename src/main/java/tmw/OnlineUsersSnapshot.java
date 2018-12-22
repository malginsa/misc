package tmw;

import java.time.LocalDateTime;
import java.util.List;

public class OnlineUsersSnapshot {

    List<String> users;

    LocalDateTime dateTime;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.dateTime)
                .append("  ")
                .append(this.users)
                .toString();
    }
}
