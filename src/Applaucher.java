import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Applaucher {

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // display our weather app
                new WeatherApp().setVisible(true);
             //
            }
        });
    }
}