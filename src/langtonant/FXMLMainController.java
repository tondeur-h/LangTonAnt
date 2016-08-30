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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private Spinner<Integer> antposx1;
    @FXML
    private Spinner<Integer> antposy1;
    @FXML
    private ComboBox<String> antdirection1;
    @FXML
    private Spinner<Integer> nbiteration;
    @FXML
    private Spinner<Integer> iterspeed;
    @FXML
    private Button btnSaveImg;
    @FXML
    private ColorPicker colorAnt1;
    @FXML
    private Button btnRandSim;
       @FXML
    private Spinner<Integer> antposx2;
    @FXML
    private Spinner<Integer> antposy2;
    @FXML
    private ComboBox<String> antdirection2;
    @FXML
    private ColorPicker colorAnt2;
    @FXML
    private CheckBox ant2Activate;
    @FXML
    private Spinner<Integer> antposx3;
    @FXML
    private Spinner<Integer> antposy3;
    @FXML
    private ComboBox<String> antdirection3;
    @FXML
    private ColorPicker colorAnt3;
    @FXML
    private CheckBox ant3Activate;
    @FXML
    private Spinner<Integer> antposx4;
    @FXML
    private Spinner<Integer> antposy4;
    @FXML
    private ComboBox<String> antdirection4;
    @FXML
    private ColorPicker colorAnt4;
    @FXML
    private CheckBox ant4Activate;
    @FXML
    private Button btnStop;  
      
    
    //init values
    private int WIDTH=100;
    private int HEIGHT=100;
    private int maxLife=4000;
    private int xAnt=0;
    private int yAnt=0;
    private int initDirection=World.SOUTH;
    private int speed=5;
    private long lifeCycle=0;
    private javafx.scene.paint.Color colorizedAnt1=javafx.scene.paint.Color.WHITE;
    private javafx.scene.paint.Color colorizedAnt2=javafx.scene.paint.Color.GREEN;
    private javafx.scene.paint.Color colorizedAnt3=javafx.scene.paint.Color.RED;
    private javafx.scene.paint.Color colorizedAnt4=javafx.scene.paint.Color.BLUE;
    private boolean stop=false;
    
    //world and Ant
    private World world;
    private Ant ant1; 
    private Ant ant2;
    private Ant ant3; 
    private Ant ant4;
    
    //writable image
    private WritableImage wimgWorld;
    
    //and factories for Spinner
    ObservableList<String> olDirection=javafx.collections.FXCollections.observableArrayList();
  
    SpinnerValueFactory<Integer> wsx;
   
    SpinnerValueFactory<Integer> nbi;
    SpinnerValueFactory<Integer> spe; 
    
    SpinnerValueFactory<Integer> apx1;
    SpinnerValueFactory<Integer> apy1;
     SpinnerValueFactory<Integer> apx2;
    SpinnerValueFactory<Integer> apy2;
     SpinnerValueFactory<Integer> apx3;
    SpinnerValueFactory<Integer> apy3;
     SpinnerValueFactory<Integer> apx4;
    SpinnerValueFactory<Integer> apy4;
 
  
  
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize world / Ant / Spinner / etc...
        wsx = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 500);
        wsx.setValue(100);
            apx1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
            apx1.setValue(0);
        apy1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
        apy1.setValue(0);
           apx2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
           apx2.setValue(99);
        apy2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
        apy2.setValue(0);
           apx3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
            apx3.setValue(0);
        apy3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
         apy3.setValue(99);
           apx4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
           apx4.setValue(99);
        apy4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
        apy4.setValue(99);
        nbi=new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 1000000);
        nbi.setValue(20000);
        worldsizex.setValueFactory(wsx);
        worldsizex.setEditable(true);
            antposx1.setValueFactory(apx1);
        antposx1.setEditable(true);
        antposy1.setValueFactory(apy1);
        antposy1.setEditable(true);
              antposx2.setValueFactory(apx2);
        antposx2.setEditable(true);
        antposy2.setValueFactory(apy2);
        antposy2.setEditable(true);
              antposx3.setValueFactory(apx3);
        antposx3.setEditable(true);
        antposy3.setValueFactory(apy3);
        antposy3.setEditable(true);
              antposx4.setValueFactory(apx4);
        antposx4.setEditable(true);
        antposy4.setValueFactory(apy4);
        antposy4.setEditable(true);
        nbiteration.setValueFactory(nbi);
        nbiteration.setEditable(true);
        olDirection.add("NORTH");
        olDirection.add("WEST");
        olDirection.add("SOUTH");
        olDirection.add("EST");
        antdirection1.setItems(olDirection);
        antdirection1.getSelectionModel().select(2);
         antdirection2.setItems(olDirection);
        antdirection2.getSelectionModel().select(3);
         antdirection3.setItems(olDirection);
        antdirection3.getSelectionModel().select(1);
         antdirection4.setItems(olDirection);
        antdirection4.getSelectionModel().select(0);
        spe=new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        spe.setValue(1);
        iterspeed.setValueFactory(spe);
        lifeCycle=0; //fix nb iter at zero
        world=new World();
        ant1=new Ant(); 
        colorAnt1.setValue(javafx.scene.paint.Color.WHITE);
        ant2=new Ant();
        colorAnt2.setValue(javafx.scene.paint.Color.GREEN);
        ant3=new Ant(); 
        colorAnt3.setValue(javafx.scene.paint.Color.RED);
        ant4=new Ant(); 
        colorAnt4.setValue(javafx.scene.paint.Color.BLUE);
    }    

    
    @FXML
    private void hBtnRun(ActionEvent event) {  
        //initialise world and Ant with the user chooses
         btnSaveImg.setDisable(true);
         btnRun.setDisable(true);
         btnStop.setDisable(false);
         stop=false;
        //first the World
        WIDTH=wsx.getValue();
        HEIGHT=wsx.getValue(); 
        world.init_world(WIDTH, HEIGHT);
        //Caracteristics of the life Cycle
        speed=iterspeed.getValue();
        lifeCycle=0;      
        maxLife=nbiteration.getValue();
        
        //and the Ants attributes
        //define ant 1
        switch(antdirection1.getSelectionModel().getSelectedItem()){
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
        xAnt=apx1.getValue();
        yAnt=apy1.getValue();
        colorizedAnt1=colorAnt1.getValue(); //get the color choose
        ant1.set_Ant_pos(xAnt, yAnt, initDirection);   
        
        
        //define ant2
        switch(antdirection2.getSelectionModel().getSelectedItem()){
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
        xAnt=apx2.getValue();
        yAnt=apy2.getValue();
        colorizedAnt2=colorAnt2.getValue(); //get the color choose
        ant2.set_Ant_pos(xAnt, yAnt, initDirection);   
        

        //define ant3
        switch(antdirection3.getSelectionModel().getSelectedItem()){
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
        xAnt=apx3.getValue();
        yAnt=apy3.getValue();
        colorizedAnt3=colorAnt3.getValue(); //get the color choose
        ant2.set_Ant_pos(xAnt, yAnt, initDirection);   
        
        
        //define ant4
        switch(antdirection4.getSelectionModel().getSelectedItem()){
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
        xAnt=apx4.getValue();
        yAnt=apy4.getValue();
        colorizedAnt4=colorAnt4.getValue(); //get the color choose
        ant4.set_Ant_pos(xAnt, yAnt, initDirection);   
        
        
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
               ant1.move();
               
               if (ant2Activate.isSelected()) {ant2.move();}
               if (ant3Activate.isSelected()) {ant3.move();}
               if (ant4Activate.isSelected()) {ant4.move();}
               
               //evaluate direction and color
               ant1.rules.evaluate(ant1.getPosX(), ant1.getPosY(), world,ant1, colorizedAnt1);
               
               if (ant2Activate.isSelected()) {ant2.rules.evaluate(ant2.getPosX(), ant2.getPosY(), world,ant2, colorizedAnt2);}
                 if (ant3Activate.isSelected()) {ant3.rules.evaluate(ant3.getPosX(), ant3.getPosY(), world,ant3, colorizedAnt3);}
                   if (ant4Activate.isSelected()) {ant4.rules.evaluate(ant4.getPosX(), ant4.getPosY(), world,ant4, colorizedAnt4);}
               //ooh not too speed please!!!
               Thread.sleep(speed);
               //and repeat repeat repeat ----::>
               //until stop is asking
               if (stop) return null;
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
                    wimgWorld.getPixelWriter().setColor(x, y, world.getLocation().get((WIDTH*y)+x).getColorS());
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

    /**
     * Randomize values for a simulation
     * @param event 
     */
    @FXML
    private void hbtnRandSim(ActionEvent event) {
        
        //select nb of iterations
        nbi.setValue((int)(Math.random()*1000000));
         
        //select size of the Screen
        wsx.setValue((int)(Math.random()*400));
        
        //select ant Position
        apx1.setValue((int)(Math.random()*wsx.getValue()));
        apy1.setValue((int)(Math.random()*wsx.getValue()));
        
        //select direction
        antdirection1.getSelectionModel().select((int)(Math.random()*4));
    }

    
    /**
     * Stop the calculation
     * ie: stop the Thread that make calculation
     * by sending a stop semaphore to the Thread
     * @param event 
     */
    @FXML
    private void hbtnStop(ActionEvent event) {
        stop=true;
        btnSaveImg.setDisable(true);
         btnRun.setDisable(false);
         btnStop.setDisable(true);
    }

    
    /**
     * constrol the maximum value for the Ant location
     * in the World (depend of the world Size
     * @param event 
     */
    @FXML
    private void mcSpinWorldChange(MouseEvent event) {
    int max;
    if (antposx1.getValue()>wsx.getValue()) {max=wsx.getValue();} else {max=antposx1.getValue();}
    apx1=new SpinnerValueFactory.IntegerSpinnerValueFactory(0, wsx.getValue(), max);
    antposx1.setValueFactory(apx1);
    if (antposy1.getValue()>wsx.getValue()) {max=wsx.getValue();} else {max=antposy1.getValue();}
    apy1=new SpinnerValueFactory.IntegerSpinnerValueFactory(0, wsx.getValue(), max);
    antposy1.setValueFactory(apy1);
    
    }

    @FXML
    private void mtcSpinWorldChange(InputMethodEvent event) {
        mcSpinWorldChange(null);
    }

    @FXML
    private void kpSpinWorldChange(KeyEvent event) {
        mcSpinWorldChange(null);
    }

    
} //end controller
