package controllers;


import algorithm.Plecaczek;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import values.StaticStuff;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;


public class MainController {
    @FXML
    private ListView listview;


    private File file;
    private String path;
    private int size;
    private String extension;
    private Plecaczek plecaczek;
    @FXML
    public void initialize() {   }


    @FXML
    public void encrypt() {
        this.path = getItem();
        this.file = new File(path);
        plecaczek = new Plecaczek();
plecaczek.setMsg(saveAsBytesArray());
plecaczek.setSize(this.size);
plecaczek.encrypt();
StaticStuff.saveStringToFile(plecaczek.getSize(), plecaczek.getMsgg(),extension);


    }

    @FXML
    public void decrypt() {
        this.path = getItem();
        this.file = new File(path);
        plecaczek = new Plecaczek();
        plecaczek.setMsgg(StaticStuff.saveFileToString(plecaczek,extension));
       plecaczek.decrypt();
StaticStuff.saveBytestoFile(plecaczek,extension);
    }

    @FXML
    void buttonAE() {
        openFileChooser();
    }
    public void openFileChooser() {
        FileChooser fc = new FileChooser();  //Inicjalizacja fc
        fc.setInitialDirectory(new File((System.getProperty("user.dir"))+"\\test"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt", "*.txt"),new FileChooser.ExtensionFilter("pdf","*.pdf"),
        new FileChooser.ExtensionFilter("jpg", "*.jpg"));

        File selectedFile = fc.showOpenDialog(null); //przypisz wybrany w fc plik do selectedFile
        fileIsNull(selectedFile,fc);
    }

    private void fileIsNull(File selectedFile,FileChooser fc) {
        if (selectedFile != null) {
            listview.getItems().add(selectedFile.getAbsolutePath()); //dodaje wybrany plik do listyitemów
            this.extension = fc.getSelectedExtensionFilter().getDescription();

        } else {
            System.out.println("file is not valid");
        }
    }

    private String getItem() {
        String absolutePath = (String) listview.getSelectionModel().getSelectedItem();
        if (absolutePath == null) {
            if(listview.getItems().get(0) == null)
            {

                throw new NullPointerException(" U have to choose item");
            }
            absolutePath = (String) listview.getItems().get(0);
        }
        System.out.println(absolutePath);
        return absolutePath;
    }

    public byte[] saveAsBytesArray() {
        if (file != null) {
            byte[] fileContent = null;
            try {
                fileContent = Files.readAllBytes(file.toPath());

            } catch (IOException e) {
                e.printStackTrace();
            }
            this.size = (fileContent.length % 4 == 0) ? (fileContent.length / 4) : ((fileContent.length / 4) + 1);

            return fileContent;
        } else {
            System.out.println("ERROR! U HAVE TO CHOOSE FILE");
            return null;
        }
    }



}
