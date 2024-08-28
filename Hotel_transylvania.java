import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Hotel_transylvania extends JPanel {

    private int width,height ;
    private JButton view_rooms = new JButton("View Rooms");
    private JButton view_guests = new JButton("View Guests");
    private JButton add_guest = new JButton("Add Guest");
    private JButton remove_guest = new JButton("Remove Guest");
    private BufferedImage bg_img;
    
    
    private Room[] rooms = new Room[20];
    private ArrayList<Guest> guests = new ArrayList<Guest>();

    Hotel_transylvania(int[] size) {
        this.setLayout(null);
        this.width = size[0];
        this.height = size[1];

        int button_width = 150;
        int button_height = 50;
        int centerX = (width - button_width) / 2;

        view_guests.setBounds(centerX, (int)(height/5 - button_height), button_width, button_height);
        add_guest.setBounds(centerX, (int)(height/2.4 - button_height), button_width, button_height);
        remove_guest.setBounds(centerX, (int)(height/1.6 - button_height), button_width, button_height);
        view_rooms.setBounds(centerX, (int)(height/1.2- button_height), button_width, button_height);

        try {
            BufferedReader roomReader = new BufferedReader(new FileReader("rooms.txt"));
            BufferedReader guestReader = new BufferedReader(new FileReader("guests.txt"));
            bg_img = ImageIO.read(new File("WhatsApp Image 2024-08-22 at 08.35.41_6e0ea06e.jpg"));

            String room_data;
            String guest_data;
            int room_index = 0;

            while ((room_data = roomReader.readLine()) != null && room_index < rooms.length) {
                String[] room = room_data.split(",");
                if (room.length == 3) {
                    rooms[room_index] = new Room(Integer.parseInt(room[0].trim()), Boolean.parseBoolean(room[1].trim()), room[2].trim());
                    room_index++;
                }
            }

            while ((guest_data = guestReader.readLine()) != null) {
                String[] guest = guest_data.split(",");
                if (guest.length == 3) {
                    guests.add(new Guest(guest[1].trim(), guest[0].trim(), Integer.parseInt(guest[2].trim())));
                }
            }

            roomReader.close();
            guestReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        add(view_guests);
        add(add_guest);
        add(remove_guest);
        add(view_rooms);


        view_rooms.addActionListener(event -> view_rooms());
        add_guest.addActionListener(event -> add_guest());
        view_guests.addActionListener(event -> view_guests());
        remove_guest.addActionListener(event -> remove_guest());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bg_img != null) {
            g.drawImage(bg_img, 0, 0, getWidth(), getHeight(), this);
        } else {
            System.out.println("Image is null");
        }
    }


    public void setframe(JFrame frame, int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }



    public Room getRoom(int room_no) {
        for (Room room : rooms) {
            if(room.getRoom_id() == room_no){
                return room;
            }
        }

        return  null;
    }






    private JLabel createLabel(String data){
        JLabel label = new JLabel(data);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }


    public void view_rooms() {
        JFrame roomsFrame = new JFrame("Rooms");
        setframe(roomsFrame, 500, 500);

        String[] columns = {"ID", "Status", "Booking Date"};
        String[][] data = new String[rooms.length][3];

        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                data[i][0] = String.valueOf(rooms[i].getRoom_id());
                data[i][1] = rooms[i].getAvailability() ? "Available" : "Occupied";
                data[i][2] = rooms[i].getBooking_date();
            }
        }

        JTable table = new JTable(data, columns);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        roomsFrame.add(scrollPane);

        roomsFrame.setVisible(true);
    }




    public void add_guest() {
        JFrame roomsFrame = new JFrame("Add Guest");
        int size = 350;
        setframe(roomsFrame, size , size );


        roomsFrame.setLayout(null);

        JLabel guest_id_label = new JLabel("Guest ID:");
        JTextField guest_id_field = new JTextField();
        JLabel guest_name_label = new JLabel("Guest Name:");
        JTextField guest_name_field = new JTextField();
        JLabel room_label = new JLabel("Room number:");
        JTextField room_field = new JTextField();
        JLabel booking_date_label = new JLabel("Booking Date:");
        JTextField booking_date_field = new JTextField();
        JButton addBtn = new JButton("ADD");
        JLabel error_msg_label = new JLabel("");


        guest_id_label.setBounds((int)(size/4.5), (int)(size/5.5) , 120,20);
        guest_id_field.setBounds((int)(size/2.2), (int)(size/5.5), 120,20);

        guest_name_label.setBounds((int)(size/4.5), (int)(size/3.5), 120,20);
        guest_name_field.setBounds((int)(size/2.2), (int)(size/3.5) , 120,20);

        room_label.setBounds((int)(size/4.5), (int)(size/2.5) , 120,20);
        room_field.setBounds((int)(size/2.2), (int)(size/2.5), 120,20);

        booking_date_label.setBounds((int)(size/4.5), (int)(size/2) , 120,20);
        booking_date_field.setBounds((int)(size/2.2), (int)(size/2), 120,20);


        addBtn.setBounds((int)((size - 80)/2), (int)(size/1.7), 80,30);
        error_msg_label.setBounds((int)((size - 100)/2), (int)(size/1.5),180,50);


        roomsFrame.add(guest_id_label); roomsFrame.add(guest_id_field);
        roomsFrame.add(guest_name_label); roomsFrame.add(guest_name_field);
        roomsFrame.add(room_field); roomsFrame.add(room_label);
        roomsFrame.add(booking_date_label); roomsFrame.add(booking_date_field);
        roomsFrame.add(addBtn);
        roomsFrame.add(error_msg_label);
        roomsFrame.setVisible(true);

        addBtn.addActionListener(event -> {
            error_msg_label.setText("");
            boolean valid_room_number = false;
            boolean valid_guest_id = true;

            if (guest_id_field.getText().isEmpty() || guest_name_field.getText().isEmpty() || room_field.getText().isEmpty() || booking_date_field.getText().isEmpty()) {
                error_msg_label.setText("Please fill all the fields");
            } else {
                for (Room room : rooms) {
                    if (room_field.getText().trim().equals(String.valueOf(room.getRoom_id()).trim())) {
                        valid_room_number = true;
                        break;
                    }
                }

                for (Guest guest : guests) {
                    if (guest_id_field.getText().trim().equals(guest.getId())) {
                        valid_guest_id = false;
                        break;
                    }
                }

                if (!valid_guest_id && !valid_room_number) {
                    error_msg_label.setText("<html>Invalid room number<br><br>Duplicate guest ID</html>");
                } else if (!valid_guest_id) {
                    error_msg_label.setText("Duplicate guest ID");
                } else if (!valid_room_number) {
                    error_msg_label.setText("Invalid room number");
                } else {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("guests.txt", true));
                        writer.append(String.format("%s,%s,%s", guest_id_field.getText(), guest_name_field.getText(), room_field.getText()));
                        writer.newLine();
                        writer.close();

                        guests.add(new Guest(guest_name_field.getText(), guest_id_field.getText(), Integer.parseInt(room_field.getText())));

                        Room room = getRoom(Integer.parseInt(room_field.getText()));
                        if (room != null) {
                            room.setAvailability(false);
                            room.setBooking_date(booking_date_field.getText());

                            BufferedWriter roomWriter = new BufferedWriter(new FileWriter("rooms.txt"));
                            for (Room r : rooms) {
                                if (r != null) {
                                    roomWriter.write(String.format("%d,%b,%s", r.getRoom_id(), r.getAvailability(), r.getBooking_date()));
                                    roomWriter.newLine();
                                }
                            }
                            roomWriter.close();
                        }

                        JOptionPane.showMessageDialog(null, "Guest has been added successfully");
                        roomsFrame.dispose();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }


    public void view_guests() {
        JFrame roomsFrame = new JFrame("Guests");
        setframe(roomsFrame, 500, 500);

        String[] columns = {"Guest ID", "Guest Name", "Assigned room"};
        String[][] data = new String[guests.size()][3];

        for (int i = 0; i < guests.size(); i++) {
            Guest guest = guests.get(i);
            data[i][0] = guest.getId();
            data[i][1] = guest.getName();
            data[i][2] = String.valueOf(guest.getAssigned_room());
        }

        JTable table = new JTable(data, columns);

        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        roomsFrame.add(scrollPane);

        roomsFrame.setVisible(true);
    }

    public void remove_guest(){
        JFrame roomsFrame = new JFrame("Remove Guest");
        int size = 350;
        setframe(roomsFrame, size , size);

        roomsFrame.setLayout(null);

        JLabel guest_id_label = new JLabel("Guest ID:");
        JTextField guest_id_field = new JTextField();
        JButton removeBtn = new JButton("REMOVE");
        JLabel error_msg_label = new JLabel("");

        guest_id_label.setBounds((int)(size/4.5), (int)(size/5.5) , 120, 20);
        guest_id_field.setBounds((int)(size/2.2), (int)(size/5.5), 120, 20);
        removeBtn.setBounds((int)((size - 80)/2), (int)(size/1.7), 100, 30);
        error_msg_label.setBounds((int)((size - 100)/2), (int)(size/1.5), 180, 50);

        // Add components to the frame
        roomsFrame.add(guest_id_label);
        roomsFrame.add(guest_id_field);
        roomsFrame.add(removeBtn);
        roomsFrame.add(error_msg_label);

        removeBtn.addActionListener(event -> {
            error_msg_label.setText("");
            if (guest_id_field.getText().isEmpty()) {
                error_msg_label.setText("Please enter guest ID");
            } else {
                boolean found = false;
                Guest removed_guest = null;

                for (Guest guest : guests) {
                    if (guest.getId().equals(guest_id_field.getText())) {
                        found = true;
                        removed_guest = guest;
                    }
                }

                if (found) {
                    for (Room room : rooms) {
                        if (room.getRoom_id() == removed_guest.getAssigned_room()) {
                            room.setAvailability(true);
                            room.setBooking_date("N/A");
                        }
                    }
                    guests.remove(removed_guest);
                    try {
                        BufferedWriter guest_writer = new BufferedWriter(new FileWriter("guests.txt"));
                        BufferedWriter room_writer = new BufferedWriter(new FileWriter("rooms.txt"));

                        for (Guest guest : guests) {
                            guest_writer.append(String.format("%s,%s,%s", guest.getId(), guest.getName(), guest.getAssigned_room()));
                            guest_writer.newLine();
                        }

                        for (Room room : rooms) {
                            room_writer.write(String.format("%d,%b,%s", room.getRoom_id(), room.getAvailability(), room.getBooking_date()));
                            room_writer.newLine();
                        }
                        room_writer.close();
                        guest_writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    JOptionPane.showMessageDialog(null, "Guest has been removed successfully");
                    roomsFrame.dispose();
                } else {
                    error_msg_label.setText("Guest ID not found");
                }
            }
        });

        roomsFrame.setVisible(true);
    }

}
