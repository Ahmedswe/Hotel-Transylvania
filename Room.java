public class Room {

    private int room_id;
    private boolean availability;
    private String Booking_date;
    Room(int room_id, boolean availability , String Booking_date ){
        this.room_id = room_id;
        this.availability = availability;
        this.Booking_date = Booking_date;
    }

    public int getRoom_id() {
        return room_id;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getBooking_date() {
        return Booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.Booking_date = booking_date;
    }
}
