package ma.patterns;

import ma.patterns.composite.Component;
import ma.patterns.composite.File;
import ma.patterns.composite.Folder;

public class Main {
    public static void main(String[] args) {
        Folder folder = new Folder("/");
        folder.addChild(new File("Test.java"));
        folder.addChild(new File("Config.xml"));
        Folder entities = (Folder) folder.addChild(new Folder("entities"));
        Folder repo = (Folder) folder.addChild(new Folder("repositories"));
        entities.addChild(new File("Dao.java"));

        folder.print();

    }
}