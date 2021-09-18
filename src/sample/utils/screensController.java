package sample.utils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
//import sample.main;

import java.net.URL;
import java.util.HashMap;

public class screensController extends StackPane {
    private final HashMap<String, Node> screens = new HashMap<>();
    public screensController(){
        super();
    }
    public void addScreen(String name, Node screen){
        screens.put(name, screen);
    }
    public Node getScreen(String name){
        return screens.get(name);
    }

    public void loadScreen(String name, String resource){
        try{
            URL url = getClass().getResource(resource);
            FXMLLoader myLoader = new FXMLLoader(url);
            Parent loadScreen = myLoader.load();
            controlledScreen myScreenController = myLoader.getController();
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setScreen(final String name){
        if(getScreen(name) != null){ //screen loaded

            if(!getChildren().isEmpty()){ // if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.seconds(5)),
                        new KeyFrame(new Duration(0), t -> {
                            getChildren().remove(0); //remove the displayed screen
                            getChildren().add(0, screens.get(name)); //add the screen
                            Timeline fadeIn = new Timeline(
                                    new KeyFrame(Duration.seconds(5)),
                                    new KeyFrame(new Duration(0)));
                            fadeIn.play();
                        }));
                fade.play();
            }else{
                getChildren().add(screens.get(name)); //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.seconds(5)));
                fadeIn.play();
            }
        }else {
            System.out.println("Screen hasn't been loaded!\n");
        }
    }
}
