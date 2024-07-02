import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherApp extends JFrame {
    private JSONObject  weatherData ;
    public WeatherApp() {

        //title
        super("Weather App");

        // end program's process
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //size
        setSize(450, 650);
        // center of the screen
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents(){
        // search field
        JTextField searchTextField = new JTextField();
        // set the locattion  and size
        searchTextField.setBounds(15 , 15 , 351 , 45 );
         // font style and size
        searchTextField.setFont(new Font("Dialog", Font.PLAIN , 24));
        add(searchTextField) ;

        // weather image
        JLabel weatherConditionImage = new JLabel(loadImage("src/assets/cloudy.png"));
        weatherConditionImage.setBounds(0 , 125 , 450 ,  217);
        add(weatherConditionImage);


        //temerature text
        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0 ,350 , 450 ,54);
        temperatureText.setFont(new Font( "Dialog0" , Font.BOLD , 48));

        //center the text
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText) ;

        // weather  condition description
        JLabel weatherConditionDesc = new JLabel( "Cloudy");
        weatherConditionDesc.setBounds(0 , 405 , 450 , 36);
        weatherConditionDesc.setFont(new Font("Dialog " , Font.PLAIN , 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);


        //humidty image
        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(15 , 500 , 74 , 66);
        add(humidityImage);


        //humidity text
        JLabel humiditytext = new JLabel("<html><b>Humidity</b> 100%</html>");
        humiditytext.setBounds(90 , 500 , 85 , 55 );
        humiditytext.setFont(new Font("Dialog"  , Font.PLAIN , 16));
        add(humiditytext);


        //windspeed image
        JLabel windspeedImage = new JLabel(loadImage("src/assets/windspeed.png"));
        windspeedImage.setBounds(220 , 500 , 74 , 66);
        add(windspeedImage);

        //windpeed text
        JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 15Km/h</html>");
        windspeedText.setBounds(310 , 500 , 85 , 55 );
        windspeedText.setFont(new Font("Dialog"  , Font.PLAIN , 16));
        add(windspeedText);




        // search button
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));

        // change the cursor  to a  hand cursor
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375 , 13 , 47 , 45);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get location from user
                String userInput  = searchTextField.getText();
                //validate input - remove whiteSpace to ensure non empty textt
                if (userInput.replaceAll("\\s" , "").length()<=0){
                    return;
                }
                //retrive weather data
            weatherData = Weather.getWeatherData(userInput);
                //update weather img
                String weatherCondition = (String) weatherData.get("weather_condition");
                switch(weatherCondition){
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("src/assets/clear.png"));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("src/assets/cloudy.png"));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("src/assets/rain.png"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("src/assets/snow.pngImage"));
                        break;
                }

                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + " C");

                // update weather condition text
                weatherConditionDesc.setText(weatherCondition);

                // update humidity text
                long humidity = (long) weatherData.get("humidity");
                humiditytext.setText("<html><b>Humidity</b> " + humidity + "%</html>");

                // update windspeed text
                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>Windspeed</b> " + windspeed + "km/h</html>");
            }
        });
        add(searchButton);
    }
            // to create image in the gui compo
    private ImageIcon loadImage( String resourcePath){
        try{

            //read the image file from the path given
                BufferedImage image = ImageIO.read(new File(resourcePath));
            // return an image icon
            return new ImageIcon(image) ;
        }catch(IOException e) {
            e.printStackTrace();
        }
            System.out.println("Could not find  resource ");
        return null ;
    }

}