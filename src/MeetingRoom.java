import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {
    int roomId;
    int floor;
    int capacity;

    List<Meeting> bookedMeeting;

    public MeetingRoom(int roomId, int floor, int capacity) {
        this.roomId = roomId;
        this.floor = floor;
        this.capacity = capacity;
        this.bookedMeeting = new ArrayList<>();
    }
}
