import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindText {

    ArrayList<String> text = new ArrayList<>();
    OpenFile openUa = new OpenFile();
    OpenFile openRu = new OpenFile();
    OpenFile openEn = new OpenFile();

    public void findTxt() throws IOException {
        String endEn;
        Scanner scannerUa = new Scanner(openUa.file("Ua.txt"));
        Scanner scannerRu = new Scanner(openUa.file("Ru.txt"));
        Scanner scannerEn = new Scanner(openEn.file("En.txt"));

        while (scannerUa.hasNext() && scannerEn.hasNext()) {
            String stUa = (scannerUa.nextLine());
            String stEn = (scannerEn.nextLine());

            Pattern patern1 = Pattern.compile("[А-Я,І,Є,A-Z]\\.\\s[А-Я,A-Z,І,Є]\\.\\s"); //Поиск инициалов имени и отчества Ua
            Matcher mat1 = patern1.matcher(stUa);
            Pattern patern1En = Pattern.compile("[A-Z,А-Я]\\.\\s[A-Z,А-Я]\\.\\s"); //Поиск инициалов имени и отчества En
            Matcher mat1En = patern1En.matcher(stEn);

            Pattern patern2 = Pattern.compile("[А-Я,A-Z,І,Є]\\.\\s");//Поиск инициалов имени без отчества Ua
            Matcher mat2 = patern2.matcher(stUa);
            Pattern patern2En = Pattern.compile("[A-Z,А-Я]\\.\\s");//Поиск инициалов имени без отчества En
            Matcher mat2En = patern2En.matcher(stEn);
            Pattern patern3En = Pattern.compile("[A-Z,А-Я][a-z,а-я]\\.\\s");//Поиск инициалов имени 2 знака без отчества En
            Matcher mat3En = patern3En.matcher(stEn);

            if (mat1.find()) {
                String start = stUa.substring(0, mat1.end());
                String endUa = stUa.substring(mat1.end());
                if (mat1En.find()) {
                    endEn = stEn.substring(mat1En.end());
                    String fin = start + "[" + endUa + "]{" + endEn + "}";
                    text.add(fin);
                } else if (mat2En.find()) {
                    endEn = stEn.substring(mat2En.end());
                    String fin = start + "[" + endUa + "]{" + endEn + "}";
                    text.add(fin);
                } else if (mat3En.find()) {
                    endEn = stEn.substring(mat3En.end());
                    String fin = start + "[" + endUa + "]{" + endEn + "}";
                    text.add(fin);
                }

            } else if (mat2.find()) {
                String start = stUa.substring(0, mat2.end());
                String endUa = stUa.substring(mat2.end());
                if (mat1En.find()) {
                    endEn = stEn.substring(mat1En.end());
                    String fin = start + "[" + endUa + "]{" + endEn + "}";
                    text.add(fin);
                } else if (mat2En.find()) {
                    endEn = stEn.substring(mat2En.end());
                    String fin = start + "[" + endUa + "]{" + endEn + "}";
                    text.add(fin);
                } else if (mat3En.find()) {
                    endEn = stEn.substring(mat3En.end());
                    String fin = start + "[" + endUa + "]{" + endEn + "}";
                    text.add(fin);
                }
            } else { //если что то не так
                String fin = "!" + stUa + "}{" + stEn + "}";
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

