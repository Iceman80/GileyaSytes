import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindText {

    ArrayList<String> text = new ArrayList<>();

    OpenFile openF = new OpenFile();

    public void findTxt() throws IOException {
        String endEn;
        Scanner scannerUa = new Scanner(openF.file("Ua.txt"));
//        Scanner scannerRu = new Scanner(openF.file("Ru.txt"));
//        Scanner scannerEn = new Scanner(openF.file("En.txt"));
        FindREplace(scannerUa);
    }


    public void FindREplace(Scanner scannerLg) {
        while (scannerLg.hasNext()) {
            String stTmp = (scannerLg.nextLine());
            Pattern patern1 = Pattern.compile("\\s\\D*\\.\\s\\D\\."); //Поиск инициалов имени и отчества
            Matcher mat1 = patern1.matcher(stTmp);
            Pattern patern2 = Pattern.compile("\\s\\D\\.");//Поиск инициалов имени без отчества
            Matcher mat2 = patern2.matcher(stTmp);
            Pattern patern3 = Pattern.compile("\\.\\,\\s\\D+\\s\\D\\.\\s\\D\\.\\s"); //Поиск инициалов имени и отчества 2
            Matcher mat3 = patern3.matcher(stTmp);

            if (mat3.find()) {
                String start = stTmp.substring(0, mat3.end());
                String end = stTmp.substring(mat3.end());
                String fin = "Несколько <strong>" + start + "</strong>" + end + "<br/>";
                text.add(fin);

            } else if (mat1.find()) {
                String start = stTmp.substring(0, mat1.end());
                String end = stTmp.substring(mat1.end());
                String fin = "Имя Инициалы <strong>" + start + "</strong>" + end + "<br/>";
                text.add(fin);

            } else if (mat2.find()) {
                String start = stTmp.substring(0, mat2.end());
                String end = stTmp.substring(mat2.end());
                String fin = "Одна буква <strong>" + start + "</strong>" + end + "<br/>";
                text.add(fin);

            } else { //если что то не так
                String fin = "! " + stTmp + "<br/>";
                text.add(fin);
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
