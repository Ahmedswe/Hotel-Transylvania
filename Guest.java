public class Guest {

    private String name ;
    private  String id ;
    private int assigned_room ;

    Guest(String name, String id, int room_no){
        this.name = name;
        this.id = id;
        this.assigned_room = room_no;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAssigned_room() {
        return assigned_room;
    }

    public void setAssigned_room(int assigned_room) {
        this.assigned_room = assigned_room;
    }

}
