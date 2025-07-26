import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MeetingRoomManager {
    List<MeetingRoom>meetingRoomList;
    NotificationManager notificationManager;

    public MeetingRoomManager() {

        this.meetingRoomList = new ArrayList<>();
        this.meetingRoomList.add( new MeetingRoom(1, 4, 5));
        this.meetingRoomList.add( new MeetingRoom(2, 4, 3));
        this.meetingRoomList.add( new MeetingRoom(3, 4, 2));

        this.notificationManager = new NotificationManager();
    }

    //All CRUD on Meeting Room
    void addMeetingRoom( MeetingRoom meetingRoom){
        meetingRoomList.add( meetingRoom );
    }

    boolean removeRoom( MeetingRoom meetingRoom ){
        if(meetingRoomList.contains(meetingRoom)){
            meetingRoomList.remove(meetingRoom);
            return true;
        }
        return false;
    }

    List<MeetingRoom> getAvailableMeetingRoom(int capacity, String startTime, String endTime){
        List<MeetingRoom>enoughCapacityRoom = new ArrayList<>();

        //check overlapping with interval
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startInterval = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endInterval = LocalDateTime.parse(endTime, formatter);


        Iterator<MeetingRoom> it = meetingRoomList.iterator();
        List<MeetingRoom>availableMeetingRoom = new ArrayList<>();

        //check capacity
        while( it.hasNext()){
            MeetingRoom meetingRoom = (MeetingRoom) it.next();
            if(capacity <= meetingRoom.capacity ){
                enoughCapacityRoom.add(meetingRoom);

                if( checkRoomAvailability(meetingRoom.bookedMeeting, startInterval, endInterval) ){
                    availableMeetingRoom.add(meetingRoom);
                }
            }
        }


        return availableMeetingRoom;
    }


    boolean checkRoomAvailability(List<Meeting> bookedMeeting, LocalDateTime inputStart, LocalDateTime inputEnd){
        Iterator itr = bookedMeeting.iterator();

        while(itr.hasNext()){
            Meeting meeting = (Meeting) itr.next();
            if( inputStart.isBefore(meeting.endTime) || inputEnd.isAfter(meeting.startTime) ){ //overlapping
                return false;
            }
        }

        return true;
    }


    void createMeeting(MeetingRoom meetingRoom, int capacity, String startTime, String endTime, List<User>userList){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startInterval = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endInterval = LocalDateTime.parse(endTime, formatter);


        if(capacity <= meetingRoom.capacity ){

            if( checkRoomAvailability(meetingRoom.bookedMeeting, startInterval, endInterval) ){
                meetingRoom.bookedMeeting.add(new Meeting( startInterval, endInterval, Day.MONDAY ));
                System.out.println( "Meeting room is booked successfully with id " + meetingRoom.roomId +" at "+ meetingRoom.floor);

                notificationManager.sendNotification(userList);
            }
            else{
                System.out.println("This meeting room is not available, please choose different one");
            }
        }

    }
}
