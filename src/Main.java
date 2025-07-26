
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        MeetingRoomManager meetingRoomManager = new MeetingRoomManager();

        List<MeetingRoom> meetingRoomList = meetingRoomManager.getAvailableMeetingRoom(3, "2025-07-20 10:00", "2025-07-20 11:00");


        System.out.println(meetingRoomList.size());
        if(meetingRoomList.isEmpty()) {
            System.out.println("no meeting room available right now");
        }


        //book meeting

        List<User> userList = new ArrayList<User>();
        userList.add(new User(1, "Sheetal"));
        userList.add(new User(2, "Shreya"));
        userList.add(new User(3, "Monti"));

        meetingRoomManager.createMeeting(meetingRoomList.get(0), 3,"2025-07-20 10:00", "2025-07-20 11:00" , userList);

        meetingRoomManager.createMeeting(meetingRoomList.get(0), 3,"2025-07-20 10:00", "2025-07-20 11:00" , userList);

        meetingRoomManager.createMeeting(meetingRoomList.get(1), 3,"2025-07-20 10:00", "2025-07-20 11:00" , userList);

    }
}