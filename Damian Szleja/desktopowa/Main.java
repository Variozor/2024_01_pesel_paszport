import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Main extends JFrame {
    JTextField numer = new JTextField();
    JTextField imie = new JTextField("Damian");
    JTextField nazwisko = new JTextField("Szleja");
    JRadioButton niebieskie = new JRadioButton("niebieskie", true);
    JRadioButton zielone = new JRadioButton("zielone");
    JRadioButton piwne = new JRadioButton("piwne");
    JLabel zdjecie = new JLabel();
    JLabel odcisk = new JLabel();
    Color tlo = new Color(95, 158, 160);
    Color jasny = new Color(240, 255, 255);

    public Main() {
        setTitle("Wprowadzenie danych do paszportu. Wykonał: Damian Szleja");
        setSize(790, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(null);
        panel.setBackground(tlo);
        setContentPane(panel);
        dodajPole(panel, "Numer", numer, 30);
        dodajPole(panel, "Imię", imie, 70);
        dodajPole(panel, "Nazwisko", nazwisko, 110);
        JPanel oczy = new JPanel(new GridLayout(3, 1));
        oczy.setBorder(BorderFactory.createTitledBorder("Kolor oczu"));
        oczy.setBackground(tlo);
        oczy.setBounds(25, 155, 330, 130);
        ButtonGroup grupa = new ButtonGroup();
        for (JRadioButton pole : new JRadioButton[] {niebieskie, zielone, piwne}) {
            pole.setBackground(tlo);
            grupa.add(pole);
            oczy.add(pole);
        }
        panel.add(oczy);
        zdjecie.setBounds(390, 30, 170, 180);
        odcisk.setBounds(585, 30, 155, 180);
        panel.add(zdjecie);
        panel.add(odcisk);
        JButton ok = new JButton("OK");
        ok.setBackground(jasny);
        ok.setBounds(445, 245, 250, 32);
        panel.add(ok);
        ok.addActionListener(e -> zatwierdz());
        numer.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                wczytajObrazy(numer.getText().trim());
            }
        });
        wczytajObrazy("000");
    }

    void dodajPole(JPanel panel, String tekst, JTextField pole, int y) {
        JLabel etykieta = new JLabel(tekst);
        etykieta.setBounds(25, y, 100, 28);
        pole.setBounds(135, y, 220, 28);
        pole.setBackground(jasny);
        panel.add(etykieta);
        panel.add(pole);
    }

    void wczytajObrazy(String numer) {
        wczytajObraz(zdjecie, numer + "-zdjecie.jpg");
        wczytajObraz(odcisk, numer + "-odcisk.jpg");
    }

    void wczytajObraz(JLabel pole, String nazwa) {
        pole.setIcon(null);
        File plik = new File("obrazy", nazwa);
        if (plik.isFile()) {
            ImageIcon obraz = new ImageIcon(plik.getPath());
            if (obraz.getIconHeight() > 0) {
                Image skalowany = obraz.getImage().getScaledInstance(-1, 180, Image.SCALE_SMOOTH);
                pole.setIcon(new ImageIcon(skalowany));
            }
        }
    }

    void zatwierdz() {
        if (numer.getText().trim().isEmpty() || imie.getText().trim().isEmpty()
                || nazwisko.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Wprowadź dane");
            return;
        }
        String kolor = "niebieskie";
        if (zielone.isSelected()) kolor = "zielone";
        if (piwne.isSelected()) kolor = "piwne";
        JOptionPane.showMessageDialog(this,
            imie.getText() + " " + nazwisko.getText() + " kolor oczu " + kolor);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
