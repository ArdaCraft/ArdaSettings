package mc.ardacraft.ardasettings.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;

public class PanelDialog {
    public static void showAllocatedRamDialog(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ignored) {}

        JDialog dialog = new JDialog((Frame) null, "Performance Warning", true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout(15,15));
        dialog.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));



        URL url = PanelDialog.class.getResource("/assets/ardasettings/icon.png");
        ImageIcon icon = null;

        if (url != null){
            Image rawImage = new ImageIcon(url).getImage();

            int targetWidth = 128;
            int targetHeight = -1;
            Image scaledImage = rawImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

            icon = new ImageIcon(scaledImage);
        }

        if (icon != null){
            JLabel imageLabel = new JLabel(icon);
            panel.add(imageLabel, BorderLayout.WEST);
        }


        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("⚠ Performance Warning");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        messagePanel.add(title);
        messagePanel.add(Box.createVerticalStrut(5));

        JLabel text = new JLabel("<html>You have less then <b> 8192 MB</b> of memory allocated.<br>"
                + "ArdaCraft <b>needs</b> at least <b>8192 MB</b> to function properly!</html>");
        messagePanel.add(text);

        JLabel link = new JLabel("<html><a href=''>View the guide on how to increase allocated memory here.</a></html>");
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://ardacraft.me/join"));
                } catch (Exception ignored) {}
            }
        });
        messagePanel.add(link);
        messagePanel.add(Box.createVerticalStrut(10));

        JLabel warning = new JLabel("<html> If you continue without allocating at least 8192 MB of memory you <b>will </b>run into issues.<br>" +
                "We cannot provide support for you if you have not allocated the correct amount of memory.</html>");
        messagePanel.add(warning);
        messagePanel.add(Box.createVerticalStrut(10));

        JCheckBox confirmBox = new JCheckBox("I understand the risk and want to continue anyway.");
        messagePanel.add(confirmBox);

        panel.add(messagePanel, BorderLayout.CENTER);

        dialog.add(panel, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton quitButton = new JButton("Quit");
        JButton continueButton = new JButton("Continue");
        continueButton.setEnabled(false);

        buttons.add(quitButton);
        buttons.add(continueButton);
        dialog.add(buttons, BorderLayout.SOUTH);

        quitButton.addActionListener(e -> {
            dialog.dispose();
            System.exit(0);
        });

        continueButton.addActionListener(e -> {
            dialog.dispose();
        });

        confirmBox.addItemListener(e -> continueButton.setEnabled(confirmBox.isSelected()));

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);


    }
}



