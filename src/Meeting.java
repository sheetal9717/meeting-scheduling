import java.time.LocalDateTime;

public class Meeting {
    LocalDateTime startTime;
    LocalDateTime endTime;
    Day day;

    public Meeting(LocalDateTime start, LocalDateTime end, Day day) {
        startTime = start;
        endTime = end;
        this.day = day;
    }
}
