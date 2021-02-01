import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class main_class {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter in champion name(s) (separate with commas): ");
        Scanner scanner = new Scanner(System.in);
        PrintWriter clearFile = new PrintWriter("Champion_Info.txt");
        clearFile.close();
        String line = scanner.nextLine();
        while (line.indexOf(",") != -1) {
            stats_loader.main(line.substring(0,line.indexOf(",")));
            line = line.substring(line.indexOf(",")+1+hasSpaces(line));
        }
        stats_loader.main(line);
        
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("C:\\WINDOWS\\system32\\notepad.exe Champion_Info.txt"); 
    }

    private static int hasSpaces(String line) {
        if (line.charAt(line.indexOf(",")+1) == ' ') return 1;
        return 0;
    }
}