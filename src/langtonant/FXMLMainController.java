/*
MIT License

Copyright (c) 2016 Tondeur Herve

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package langtonant;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 *
 * @author herve
 */
public class FXMLMainController implements Initializable {
    
    //UI Widget
    @FXML
    private Button btnRun;
    @FXML
    private ImageView imgWorld;
    @FXML
    private Label lbIter;
    @FXML
    private Spinner<Integer> worldsizex;
    @FXML
    private Spinner<Integer> worldsizey;
    @FXML
    private Spinner<Integer> antposx;
    @FXML
    private Spinner<Integer> antposy;
    @FXML
    private ComboBox<String> antdirection;
    @FXML
    private Spinner<Integer> nbiteration;
     @FXML
    private Spinner<Integer> iterspeed;
      @FXML
    private Button btnSaveImg;
    @FXML
    private ColorPicker colorAnt;
    
    //init values
    private int WIDTH=100;
    private int HEIGHT=100;
    private int maxLife=4000;
    private int xAnt=0;
    private int yAnt=0;
    private int initDirection=World.SOUTH;
    private int speed=5;
    private long lifeCycle=0;
    private javafx.scene.paint.Color colorizedAnt=javafx.scene.paint.Color.WHITE;
    
    //world and Ant
    private World world;
    private Ant antRed;
    
    //writable image
    private WritableImage wimgWorld;
    
    //and factories for Spinner
    ObservableList<String> olDirection=javafx.collections.FXCollections.observableArrayList();
  
    SpinnerValueFactory<Integer> wsx;
    SpinnerValueFactory<Integer> wsy;
    SpinnerValueFactory<Integer> apx;
    SpinnerValueFactory<Integer> apy;
    SpinnerValueFactory<Integer> nbi;
    SpinnerValueFactory<Integer> spe;
  
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize world / Ant / Spinner / etc...
        wsx = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 800);
        wsx.setValue(100);
        wsy = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 800);
        wsy.setValue(100);
        apx = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 499);
        apy = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 499);
        nbi=new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 1000000);
        nbi.setValue(20000);
        worldsizex.setValueFactory(wsx);
        worldsizex.setEditable(true);
        worldsizey.setValueFactory(wsy);
        worldsizey.setEditable(true);
        antposx.setValueFactory(apx);
        antposx.setEditable(true);
        antposy.setValueFactory(apy);
        antposy.setEditable(true);
        nbiteration.setValueFactory(nbi);
        nbiteration.setEditable(true);
        olDirection.add("NORTH");
        olDirection.add("WEST");
        olDirection.add("SOUTH");
        olDirection.add("EST");
        antdirection.setItems(olDirection);
        antdirection.getSelectionModel().select(2);
        spe=new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        spe.setValue(1);
        iterspeed.setValueFactory(spe);
        lifeCycle=0; //fix nb iter at zero
        world=new World();
        antRed=new Ant();
    }    

    
    @FXML
    private void hBtnRun(ActionEvent event) {  
        //initialise world and Ant with the user chooses
         btnSaveImg.setDisable(true);
         btnRun.setDisable(true);
        //first the World
        WIDTH=wsx.getValue();
        HEIGHT=wsy.getValue(); 
        world.init_world(WIDTH, HEIGHT);
        //Caracteristics of the life Cycle
        speed=iterspeed.getValue();
        lifeCycle=0;      
        maxLife=nbiteration.getValue();
        
        //and the Ant attributes
        switch(antdirection.getSelectionModel().getSelectedItem()){
            case "NORTH":
                initDirection=World.NORTH;
                break;
            case "WEST":
                initDirection=World.WEST;
                break;
            case "SOUTH":
                initDirection=World.SOUTH;
                break;
            case "EST":
                initDirection=World.EST;
                break;
        }
        xAnt=apx.getValue();
        yAnt=apy.getValue();
        colorizedAnt=colorAnt.getValue(); //get the color choose
        antRed.set_Ant_pos(xAnt, yAnt, initDirection);   
        
        //draw the first empty world
        wimgWorld=new WritableImage(WIDTH, HEIGHT);
        imgWorld.setSmooth(true);
         draw_World(); //first time black screen
        
//prepare a Task for calculate Langton Automate         
Task task = new Task<Void>() {
    @Override 
    public Void call() {
       while(lifeCycle<maxLife){ 
           try {
               //update GUI
               updateMessage("Iteration N° :"+lifeCycle+"/"+maxLife);
                //move the ant
               antRed.move();
               //evaluate direction and color
               antRed.rules.evaluate(antRed.getPosX(), antRed.getPosY(), world,antRed);
               //ooh not too speed please!!!
               Thread.sleep(speed);
               //and repeat repeat repeat ----::>
               lifeCycle++;
           } catch (InterruptedException ex) {
               Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
           }
        }  
           btnSaveImg.setDisable(false);
           btnRun.setDisable(false);
        return null; //when finished
    }
};

           //say that an update GUI must update the label itration and the world image
           task.messageProperty().addListener((obs, oldMessage, newMessage) -> {lbIter.setText(newMessage);draw_World();});
           //yes GO run it now please!
           new Thread(task).start();         
        
    }
    
    /**
     * Drawing the image of the world and assign on the ImageViewer in the GUI
     * not very fast, can be improved...
     */
    public synchronized void draw_World(){
        for (int x=0;x<WIDTH;x++){
            for (int y=0;y<HEIGHT;y++){
                if (world.getLocation().get((WIDTH*y)+x).isColorized()){
                    //System.out.print("(x,y)=("+x+","+y+")&");
                    wimgWorld.getPixelWriter().setColor(x, y, colorizedAnt);
                }
                else 
                {
                     wimgWorld.getPixelWriter().setColor(x, y, javafx.scene.paint.Color.BLACK);
                }
            }
        }
        imgWorld.setImage(wimgWorld);
    }

    @FXML
    private void hbtnSaveImg(ActionEvent event) {
        screenShot();
    }
    
      /***********************
     * ScreenShot of the World
     ***********************/
    public void screenShot()
    {
       WritableImage image= imgWorld.snapshot(new SnapshotParameters(), null);
       String fileName="langtonAnt"+System.currentTimeMillis()+".png";
        File file = new File(fileName);

    try {
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Screen Shot OK!");
        alert.setContentText("save : "+fileName);
        alert.showAndWait();
        
    } catch (IOException e) {
       //say nothing...
    }  
    
    btnSaveImg.setDisable(true);
    
    }

    
} //end controller
