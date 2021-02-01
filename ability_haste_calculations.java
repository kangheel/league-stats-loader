import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ability_haste_calculations {
    public static void main(String cooldowns) throws FileNotFoundException {
        Scanner fileCopy = new Scanner(new File("Champion_Info.txt"));
        PrintWriter fileExport = new PrintWriter("Champion_Info_temp.txt");
        while (fileCopy.hasNextLine()) {
            fileExport.println(fileCopy.nextLine());
        }
        cooldowns = cooldowns.replaceAll("[a-zA-Z]", "");
        cooldowns = cooldowns.replaceAll("[()]", "");
        cooldowns = cooldowns.replaceAll(" ", "");
        if (cooldowns.indexOf("/") != -1) { // ult cd has scaling per rank
            double[] arr_cooldowns = new double[3];
            for (int i = 0; i < 2; i++) {
                arr_cooldowns[i] = Double.parseDouble(cooldowns.substring(0,cooldowns.indexOf("/")));
                cooldowns = cooldowns.substring(cooldowns.indexOf("/")+1);
            }
            arr_cooldowns[2] = Double.parseDouble(cooldowns);

            double[] tenAH = new double[3];
            double[] fifteenAH = new double[3];
            double[] twentyAH = new double[3];
            double[] thirtyAH = new double[3];
            double[] fiftyAH = new double[3];

            for (int i = 0; i < arr_cooldowns.length; i++) {
                tenAH[i] = calculateReducedCD(arr_cooldowns[i],10);
                fifteenAH[i] = calculateReducedCD(arr_cooldowns[i],15);
                twentyAH[i] = calculateReducedCD(arr_cooldowns[i],20);
                thirtyAH[i] = calculateReducedCD(arr_cooldowns[i],30);
                fiftyAH[i] = calculateReducedCD(arr_cooldowns[i],50);
            }

            fileExport.println("10 AH (9.09 CDR)");
            fileExport.println("----------------");
            for (int i = 0; i < tenAH.length; i++) {
                fileExport.println("Rank " + (i+1) + ": " + tenAH[i] + " s");
            }
            fileExport.println();

            fileExport.println("15 AH (13.04 CDR)");
            fileExport.println("----------------");
            for (int i = 0; i < fifteenAH.length; i++) {
                fileExport.println("Rank " + (i+1) + ": " + fifteenAH[i] + " s");
            }
            fileExport.println();

            fileExport.println("20 AH (16.67 CDR)");
            fileExport.println("----------------");
            for (int i = 0; i < twentyAH.length; i++) {
                fileExport.println("Rank " + (i+1) + ": " + twentyAH[i] + " s");
            }
            fileExport.println();

            fileExport.println("30 AH (23.08 CDR)");
            fileExport.println("----------------");
            for (int i = 0; i < thirtyAH.length; i++) {
                fileExport.println("Rank " + (i+1) + ": " + thirtyAH[i] + " s");
            }
            fileExport.println();

            fileExport.println("50 AH (33.33 CDR)");
            fileExport.println("----------------");
            for (int i = 0; i < fiftyAH.length; i++) {
                fileExport.println("Rank " + (i+1) + ": " + fiftyAH[i] + " s");
            }
        }
        else { // ult cd no scaling per level
            double cooldown = Double.parseDouble(cooldowns);

            double tenAH = calculateReducedCD(cooldown,10);
            double fifteenAH = calculateReducedCD(cooldown,15);
            double twentyAH = calculateReducedCD(cooldown,20);
            double thirtyAH = calculateReducedCD(cooldown,30);
            double fiftyAH = calculateReducedCD(cooldown,50);

            fileExport.println("10 AH (9.09 CDR)");
            fileExport.println("----------------");
            fileExport.println("Rank 1,2,3: " + tenAH + " s");
            fileExport.println();

            fileExport.println("15 AH (13.04 CDR)");
            fileExport.println("----------------");
            fileExport.println("Rank 1,2,3: " + fifteenAH + " s");
            fileExport.println();

            fileExport.println("20 AH (16.67 CDR)");
            fileExport.println("----------------");
            fileExport.println("Rank 1,2,3: " + twentyAH + " s");
            fileExport.println();

            fileExport.println("30 AH (23.08 CDR)");
            fileExport.println("----------------");
            fileExport.println("Rank 1,2,3: " + thirtyAH + " s");
            fileExport.println();

            fileExport.println("50 AH (33.33 CDR)");
            fileExport.println("----------------");
            fileExport.println("Rank 1,2,3: " + fiftyAH + " s");
        }
        fileExport.println("----------------");
        fileExport.println();
        fileExport.close();
        Scanner copyTemp = new Scanner(new File("Champion_Info_temp.txt"));
        PrintWriter finalExport = new PrintWriter("Champion_Info.txt");
        while (copyTemp.hasNextLine()) {
            finalExport.println(copyTemp.nextLine());
        }
        finalExport.close();
    }

    private static double calculateReducedCD(double cooldown, int ability_haste) {
        return round(cooldown*100/(100.0+ability_haste),2);
    }

    private static double round(double number, int places) {
        int temp = (int) (number*Math.pow(10,places));
        return temp/Math.pow(10,places);
    }
}
