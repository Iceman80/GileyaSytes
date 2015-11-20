import java.io.IOException;

public class Biblio {

    public void biblio() throws IOException {
        FindText findText = new FindText();
        findText.findTxt();
        for (String tmp : findText.text) {
            System.out.println(tmp);
        }
    }
}