package ma.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Component{
    private List<Component> components = new ArrayList<>();
     public Folder(String name) {
        super(name);
    }

    @Override
    public void print() {
        String tab = "";
        for (int i = 0; i < level; i++) {
            tab += "\t";
        }
        System.out.println(tab +"Folder : " + name);
        for (Component c: components){
            c.print();
        }
    }
    public Component addChild(Component com){
         com.level = this.level + 1;
         this.components.add(com);
         return com;
    }
}
