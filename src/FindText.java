import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindText {

    ArrayList<String> text = new ArrayList<>();

    public void findTxt() throws IOException {
        text.add("<em><strong>ЗМІСТ - СОДЕРЖАНИЕ - CONTENT </strong></em>\n" +
                " [cut]<br />\n" +
                " </div>");

        Scanner scannerUa = new Scanner(new File("/Users/ice/Desktop/Gileya/Ua.txt"));
        FindREplace(scannerUa);
        scannerUa = new Scanner(new File("/Users/ice/Desktop/Gileya/Ru.txt"));
        FindREplace(scannerUa);
        scannerUa = new Scanner(new File("/Users/ice/Desktop/Gileya/En.txt"));
        FindREplace(scannerUa);
    }

    public void FindREplace(Scanner scannerLg) {
        while (scannerLg.hasNext()) {
            String stTmp = (scannerLg.nextLine());
            Pattern patern1 = Pattern.compile("^\\D{3,19}\\s\\D{0,3}\\.\\s\\D{0,3}\\.\\s"); //Поиск инициалов имени и отчества
            Matcher mat1 = patern1.matcher(stTmp);
            Pattern patern2 = Pattern.compile("^\\D{4,15}\\s\\D{0,7}\\.\\s");//Поиск инициалов имени без отчества
            Matcher mat2 = patern2.matcher(stTmp);
            Pattern patern3 = Pattern.compile("\\.\\,\\s\\D{3,19}\\s\\D{0,3}\\.\\s\\D{0,3}\\.\\s"); //Поиск инициалов имени и отчества 2
            Matcher mat3 = patern3.matcher(stTmp);
            Pattern patern4 = Pattern.compile("^\\D{4,15}\\s\\D{4,15}$"); //Поиск названия темы
            Matcher mat4 = patern4.matcher(stTmp);

            if (mat3.find()) {
                String start = stTmp.substring(0, mat3.end());
                String end = stTmp.substring(mat3.end());
                String fin = "<strong>" + start + "</strong>" + end + "<br/>";
                text.add(fin);

            } else if (mat1.find()) {
                String start = stTmp.substring(0, mat1.end());
                String end = stTmp.substring(mat1.end());
                String fin = "<strong>" + start + "</strong>" + end + "<br/>";
                text.add(fin);

            } else if (mat2.find()) {
                String start = stTmp.substring(0, mat2.end());
                String end = stTmp.substring(mat2.end());
                String fin = "<strong>" + start + "</strong>" + end + "<br/>";
                text.add(fin);

            } else if (mat4.find()) {
                String start = stTmp.substring(0, mat4.end());
                String fin = "\n      <p align=\"center\"><strong>" + start + "</strong></p>";
                text.add(fin);

            } else { //если что то не так
                switch (stTmp) {
                    case "ЗМІСТ": {
                        String fin = "\n        <p align=\"center\"><em><strong>ЗМІСТ</strong></em></p>";
                        text.add(fin);
                    }
                    break;
                    case "СОДЕРЖАНИЕ": {
                        String fin = "\n        <p align=\"center\"><em><strong>СОДЕРЖАНИЕ</strong></em></p>";
                        text.add(fin);
                    }
                    break;
                    case "CONTENT": {
                        String fin = "\n        <p align=\"center\"><em><strong>CONTENT</strong></em></p>";
                        text.add(fin);
                    }
                    break;
                    default: {
                        String fin = "!!!! <strong>" + stTmp + " </strong> <br/>";
                        text.add(fin);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "FindText{" +
                "text=" + text +
                '}';
    }
}

