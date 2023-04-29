package ma.patterns.composite;

public class File extends Component{
    public File(String name) {
        super(name);
    }

    @Override
    public void print() {
        String tab = "";
        for (int i = 0; i < level; i++) {
            tab += "\t";
        }
        System.out.println(tab + "File : " +name);
    }
}
