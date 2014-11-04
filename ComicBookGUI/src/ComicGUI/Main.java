package ComicGUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/*
* TODO: Write functionality for remove and view comics function;
* TODO: Write functionality for submit function in the Add Comics section;
* TODO: Learn jexcelapi in order to write all comics added to a new file;
* */
public class Main extends Application {
    private Stage primaryStage;
    private String[] addNew;
    private Scene scene;
    private File Storage = new File("Storage.txt");
    PrintWriter pw;


    @Override
    public void start(Stage primaryStage) throws Exception {
//        Storage = new File("Storage.txt");

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Comic Books");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Actions: ");
        sceneTitle.setId("welcome-text");
        grid.add(sceneTitle, 0, 0, 2, 1);

        Button newComic = new Button("Add a comic");
        HBox hbNewComic = new HBox(10);
        hbNewComic.getChildren().add(newComic);
        grid.add(hbNewComic, 0, 1);

        newComic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                newComicScreen();
                } catch(IOException e) {}
            }
        });

        Button rmComic = new Button("Remove a comic");
        HBox hbRmComic = new HBox(10);
        hbRmComic.getChildren().add(rmComic);
        grid.add(hbRmComic, 0, 2);

        Button viewComic = new Button("View all comics");
        HBox hbViewComic = new HBox(10);
        hbViewComic.getChildren().add(viewComic);
        grid.add(hbViewComic, 0, 3);

        viewComic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    newViewComicScreen();
                } catch(IOException e) {}
            }
        });

        scene = new Scene(grid, 300, 350);
        primaryStage.setScene(scene);
        scene.getStylesheets().add
                (Main.class.getResource("ComicGUI.css").toExternalForm());
        primaryStage.show();
    }

    public void newComicScreen() throws IOException {
        addNew = new String[7];
//        try {
//        FileOutputStream foo = new FileOutputStream(Storage, true);
//        } catch(IOException e) {
//            FileInputStream fis = new FileInputStream(Storage);
//            FileOutputStream foo = new FileOutputStream(Storage, true);
//        }
//        final FileWriter fw = new FileWriter(Storage);
//        final BufferedWriter out = new BufferedWriter(fw);


        GridPane ComicGrid = new GridPane();
        ComicGrid.setAlignment(Pos.TOP_LEFT);
        ComicGrid.setHgap(10);
        ComicGrid.setVgap(10);
        ComicGrid.setPadding(new Insets(25, 25, 25, 25));


        Text title = new Text("Fill Out All Fields Possible: ");
        title.setId("Secondary");
        ComicGrid.add(title, 0, 0, 2, 1);

        Text comicTitle = new Text("Title: ");
        comicTitle.setId("label");
        final TextField titleText = new TextField();
        ComicGrid.add(comicTitle, 0, 2);
        ComicGrid.add(titleText, 1, 2);

        Text comicVolume = new Text("Volume: ");
        comicVolume.setId("label");
        final TextField volumeText = new TextField();
        ComicGrid.add(comicVolume, 0, 3);
        ComicGrid.add(volumeText, 1, 3);

        Text comicIssue = new Text("Issue: ");
        comicIssue.setId("label");
        final TextField issueText = new TextField();
        ComicGrid.add(comicIssue, 0, 4);
        ComicGrid.add(issueText, 1, 4);

        Text comicDate = new Text("Date Published: ");
        comicDate.setId("label");
        final TextField dateText = new TextField("MON-YY");
        ComicGrid.add(comicDate, 0, 5);
        ComicGrid.add(dateText, 1, 5);

        Text comicPrice = new Text("Retail Price: ");
        comicPrice.setId("label");
        final TextField priceText = new TextField("x.xx");
        ComicGrid.add(comicPrice, 0, 6);
        ComicGrid.add(priceText, 1, 6);

        Text comicCondition = new Text("Condition: ");
        comicCondition.setId("label");
        final TextField conditionText = new TextField();
        ComicGrid.add(comicCondition, 0, 7);
        ComicGrid.add(conditionText, 1, 7);

        Text comicBox = new Text("Box: ");
        comicBox.setId("label");
        final TextField boxText = new TextField("L#");
        ComicGrid.add(comicBox, 0, 8);
        ComicGrid.add(boxText, 1, 8);

        Button submit = new Button("Submit");
        HBox hbSubmit = new HBox(10);
        hbSubmit.getChildren().add(submit);
        hbSubmit.setAlignment(Pos.BOTTOM_RIGHT);
        ComicGrid.add(hbSubmit, 1, 9);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    pw = new PrintWriter(new FileWriter(Storage,true));
                } catch(IOException e) {}
                addNew[0] = titleText.getCharacters().toString();
                addNew[1] = volumeText.getCharacters().toString();
                addNew[2] = issueText.getCharacters().toString();
                addNew[3] = dateText.getCharacters().toString();
                addNew[4] = priceText.getCharacters().toString();
                addNew[5] = conditionText.getCharacters().toString();
                addNew[6] = boxText.getCharacters().toString();

                for(int i = 0; i < addNew.length; i++) {
                    pw.write(addNew[i] + " ");
//                    try {
//                        out.write(addNew[i] + " ");
//                    } catch(IOException e) {}
                }
                pw.write("\n");
                pw.close();
//                try {
//                    out.write("\n");
//                    fw.flush();
//                    out.close();
//                } catch(IOException e) {}
           }
        });

        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.getChildren().add(back);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        ComicGrid.add(hbBack, 0, 9);

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    start(primaryStage);
                } catch (Exception e) {}
            }
        });

        Scene comicScene = new Scene(ComicGrid, 300, 350);
        comicScene.getStylesheets().add
                (Main.class.getResource("ComicGUI.css").toExternalForm());
        primaryStage.setScene(comicScene);
        primaryStage.setTitle("Add a New Comic");

    }

    public void newViewComicScreen() throws IOException {
        //FileOutputStream fos = new FileOutputStream(Storage, true);
        FileReader fr = new FileReader(Storage);
        BufferedReader br = new BufferedReader(fr);

        while(true) {
            String s = br.readLine();
            if (s == null)
                break;
            System.out.println(s);
        }

        fr.close();
        br.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
