import java.security.SecureRandom;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class GetGetSet extends Application{
  Stage mStageMain;
  Scene mSceneMain;
  Label mLblDown;
  TextArea mTxaContent;
  TextArea mTxaMagic;


  public void start(Stage primaryStage){
    initialLayout();
    createLetter();
    mStageMain.setTitle("變呀！Accessor 、 Mutator");
    mStageMain.show();
    // primaryStage.show();
  }
  public void initialLayout(){
    // mLblDown.setFont(Font.font("Amble CN", FontWeight.BOLD, 40));
    mTxaContent = new TextArea("變數型態與變數名稱貼在這裡\n好了按ENTER");
    mLblDown = new Label("↓");
    mTxaMagic = new TextArea("都快忘了怎麼樣戀一個愛");

    VBox mVbAll = new VBox(mTxaContent, mLblDown, mTxaMagic);
    mVbAll.setSpacing(20);
    mVbAll.setAlignment(Pos.CENTER);
    mVbAll.setPadding(new Insets(10, 50, 50, 50));
    mSceneMain = new Scene(mVbAll, 1000, 600);
    mStageMain = new Stage();
    mStageMain.setScene(mSceneMain);
  }

  public void createLetter(){
    String sampleGet = "public ###TYPE get###CAMEL(){\n\treturn ###NAME;\n}\n";
    String sampleSet = "public void set###CAMEL(###TYPE ###NAME){\n\tthis.###NAME = ###NAME;\n}\n";
    mTxaContent.setOnKeyPressed(keyPress->{
      switch(keyPress.getCode()){
        case ENTER:
        String[] vars = mTxaContent.getText().split("\n");
        String answer = "";
        for(String str : vars){
          if(str.length() < 2) continue;
          int spaceIndex = str.trim().indexOf(" ");
          String type = str.trim().substring(0, spaceIndex);
          String name = str.trim().substring(spaceIndex+1, str.trim().length()-1);
          String camel = name.substring(0, 1).toUpperCase() + name.substring(1);
          answer += (sampleGet.replaceAll("###TYPE", type).replaceAll("###NAME", name).replaceAll("###CAMEL", camel));
          answer += (sampleSet.replaceAll("###TYPE", type).replaceAll("###NAME", name).replaceAll("###CAMEL", camel));
        }
        mTxaMagic.setText(answer);
        placeTextOnClipboard(answer);
        Tooltip tooltip = new Tooltip("已複製get/set");
        tooltip.setFont(Font.font("Amble CN", FontWeight.BOLD, 25));
        tooltip.show(mTxaMagic, 600, 600);
        break;
        
        default:
        //do-nothing
      }
    });
  }

  //複製到剪貼簿
  public void placeTextOnClipboard(String text) {
    //Get the toolkit
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    //Get the clipboard
    Clipboard clipboard = toolkit.getSystemClipboard();
    //The setContents method of the Clipboard instance takes a Transferable
    //as first parameter. The StringSelection class implements the Transferable
    //interface.
    StringSelection stringSel = new StringSelection(text);
    //We specify null as the clipboard owner
    clipboard.setContents(stringSel, null);
  }



  public static void main(String[] args){
   launch(args);
  }
}
