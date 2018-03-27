import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CharacterReplacer {
    private static final String originalText = "original.txt";
    private static final String originalFrequency = "originalFrequency.txt";
    private static final String replaceIndicator = "replace.txt";
    private static final String outputFileName = "output.txt";

    public static void main(String args[]) {
        CharacterReplacer replacer = new CharacterReplacer();

        ArrayList<Character> originalList = replacer.getOriginalText();
        ArrayList<Character> replaceIndicatorList = replacer.getReplaceIndicator();
        ArrayList<Character> originalFrequencyList = replacer.getOriginalFrequencyList();
        ArrayList<Character> resultList = replacer.getResult(originalList, originalFrequencyList, replaceIndicatorList);
        replacer.outputToFile(resultList);
    }

    private ArrayList<Character> getOriginalText() {
        Scanner originalFile = null;
        try {
            originalFile = new Scanner(new File(originalText));
            System.out.println("Original Text File loaded!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Character> list = new ArrayList<>();
        getList(list, originalFile);
        return list;
    }

    private ArrayList<Character> getReplaceIndicator() {
        Scanner replaceIndicatorFile = null;
        try {
            replaceIndicatorFile = new Scanner(new File(replaceIndicator));
            System.out.println("Replace File loaded!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Character> list = new ArrayList<>();
        getList(list, replaceIndicatorFile);
        return list;
    }

    private ArrayList<Character> getOriginalFrequencyList() {
        Scanner originalFrequencyFile = null;
        try {
            originalFrequencyFile = new Scanner(new File(originalFrequency));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Character> list = new ArrayList<>();
        getList(list, originalFrequencyFile);
        return list;
    }

    private void getList(ArrayList<Character> list, Scanner file) {
        if (file != null) {
            while (file.hasNext()) {
                String str = file.next();
                char[] charArray = str.toCharArray();
                for (char aChar : charArray) {
                    list.add(aChar);
                }
            }
            file.close();
        }
    }

    private ArrayList<Character> getResult(ArrayList<Character> encodeText, ArrayList<Character> frequencyList, ArrayList<Character> decodeKey) {
        ArrayList<Character> resultList = new ArrayList<>();
        for (Character currentChar : encodeText) {
            int replaceIndicatorIndex = 0;
            for (int j = 0; j < frequencyList.size(); j++) {
                if (currentChar.equals(frequencyList.get(j))) {
                    replaceIndicatorIndex = j;
                    break;
                }
            }
            resultList.add(decodeKey.get(replaceIndicatorIndex));
        }
        return resultList;
    }

    private void outputToFile(ArrayList<Character> resultList) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileOutputStream(outputFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (printWriter != null) {
            for (Character aChar : resultList) {
                printWriter.print(aChar);
            }
            printWriter.close();
        }
    }
}
