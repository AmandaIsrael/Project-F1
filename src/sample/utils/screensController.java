package sample.utils;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
//import sample.main;

import java.net.URL;
import java.util.HashMap;

public class screensController extends StackPane {
    //holds the screens to be displayed
    private final HashMap<String, Node> screens = new HashMap<>();
    public screensController(){
        super();
    }
    //Add the screen to the collection
    public void addScreen(String name, Node screen){
        screens.put(name, screen);
    }
    //returns the node with the appropriate name
    public Node getScreen(String name){
        return screens.get(name);
    }
    /*loads the fxml file, add the screen to the screens collection and
    finally injects the screenPane to the controller
    */

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

    /*This method tries to displayed the screen with a predefined name.
    First it makes sure the screen has been already loaded. Then if there is more than
    one screen the new screen is been added second, and then the current screen is removed
    If there isn't any screen displayed, the new screen is just added to the root.
    */
    public void setScreen(final String name){
        if(getScreen(name) != null){ //screen loaded
            final DoubleProperty opacity = opacityProperty();

            if(!getChildren().isEmpty()){ // if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), t -> {
                            getChildren().remove(0); //remove the displayed screen
                            getChildren().add(0, screens.get(name)); //add the screen
                            Timeline fadeIn = new Timeline(
                                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                    new KeyFrame(new Duration(1500), new KeyValue(opacity, 1.0)));
                            fadeIn.play();
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            }else{
                setOpacity(0.0);
                getChildren().add(screens.get(name)); //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(1500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
        }else {
            System.out.println("Screen hasn't been loaded!\n");
        }
    }

    //This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name){
        if(screens.remove(name) == null){
            System.out.println("Screen didn't exist!");
            return false;
        }else{
            return true;
        }
    }
}
