import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {

        int[] size = {800,600} ;
        JFrame Hotel_management_system = new JFrame();
        Hotel_management_system.setSize(size[0] , size[1]);
        Hotel_management_system.setLocationRelativeTo(null);
        Hotel_management_system.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Hotel_management_system.setLayout(null);
        Hotel_management_system.setResizable(false);
        JPanel loginPagePanel = new login_page(size);
        loginPagePanel.setBounds(0, 0, size[0] , size[1]);
        Hotel_management_system.add(loginPagePanel);
        Hotel_management_system.setVisible(true);
    }
}
