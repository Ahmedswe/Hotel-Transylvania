import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class login_page extends JPanel implements ActionListener {

    private int[] size;
    private BufferedImage bg_img;
    private JButton login_btn = new JButton("Login");
    private JLabel ID_label = new JLabel("Enter ID");
    private JLabel password_label = new JLabel("Enter Password");
    private JTextField ID_field = new JTextField();
    private JPasswordField password_field = new JPasswordField();


    login_page(int[] size) {
        this.size = size;
        this.setLayout(null);
        this.setBounds(0, 0, size[0] , size[1]);


        try {
            bg_img = ImageIO.read(new File("Hotel_Transylvania.png"));
            if (bg_img == null) {
                throw new IOException("Image file not found or could not be read.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Font boldFont = new Font("Arial", Font.BOLD, 14);

        ID_label.setFont(boldFont);
        ID_label.setForeground(Color.lightGray);
        ID_label.setBounds((size[0] / 2) - 100, (int) (size[1] / 4.6), 200, 30);
        ID_field.setBounds((size[0] / 2) - 100, (int) (size[1] / 3.8), 200, 30);

        password_label.setFont(boldFont);
        password_label.setForeground(Color.lightGray);
        password_label.setBounds((size[0] / 2) - 100, (int) (size[1] / 3), 200, 30);
        password_field.setBounds((size[0] / 2) - 100, (int) (size[1] / 2.6), 200, 30);

        login_btn.setBounds((size[0] / 2) - 50, (int) (size[1] / 2), 100, 30);


        this.add(ID_label);
        this.add(ID_field);
        this.add(login_btn);
        this.add(password_field);
        this.add(password_label);
        login_btn.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login_btn) {

            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            parent.remove(this);
            this.size[0] = 500;
            this.size[1] = 500;
            JPanel hotelPanel = new Hotel_transylvania(this.size);
            hotelPanel.setBounds(0, 0, size[0] , size[1]);
            parent.setSize(this.size[0] , this.size[0]);
            parent.add(hotelPanel);

            parent.revalidate();
            parent.repaint();
        }
    }
}
