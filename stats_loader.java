import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class stats_loader {
    public static void main(String champion_name) throws IOException {
        Scanner fileCopy = new Scanner(new File("Champion_Info.txt"));
        PrintWriter fileExport = new PrintWriter("Champion_Info_temp.txt");
        while (fileCopy.hasNextLine()) {
            fileExport.println(fileCopy.nextLine());
        }
        champion_name = champion_name.replaceAll(" ", "_");
        if (champion_name.indexOf("_") != -1) {
            champion_name = champion_name.substring(0,champion_name.indexOf("_")) + "_" + champion_name.substring(champion_name.indexOf("_")+1,champion_name.indexOf("_")+2).toUpperCase() + champion_name.substring(champion_name.indexOf("_")+2); 
        }
        if (champion_name.toLowerCase().equals("reksai")) champion_name = "Rek'Sai";

        if (champion_name.toLowerCase().equals("udyr")) { 

        }
        else {
            String url = "https://leagueoflegends.fandom.com/wiki/"+champion_name;
            Document document = Jsoup.connect(url).get();
    
            String cooldowns = document.select("div.skill.skill_r").text();
            
            cooldowns = cooldowns.substring(cooldowns.indexOf("COOLDOWN:")+"COOLDOWN:".length(),cooldowns.indexOf("Active:"));
            if (cooldowns.indexOf("Passive: ") != -1) {
                cooldowns = cooldowns.substring(0,cooldowns.indexOf("Passive:"));
            }
    
            if (champion_name.indexOf("_") != -1) {
                champion_name = champion_name.substring(0,1).toUpperCase()+champion_name.substring(1,champion_name.indexOf("_")).toLowerCase()+" "+champion_name.substring(champion_name.indexOf("_")+1,champion_name.indexOf("_")+2).toUpperCase()+champion_name.substring(champion_name.indexOf("_")+2).toLowerCase();
            }
            else {
                champion_name = champion_name.substring(0,1).toUpperCase()+champion_name.substring(1).toLowerCase();
            }
            fileExport.println(champion_name+": " + cooldowns);
            fileExport.println("------------------------------------------");
            fileExport.close();
    
            Scanner copyTemp = new Scanner(new File("Champion_Info_temp.txt"));
            PrintWriter finalExport = new PrintWriter("Champion_Info.txt");
            while (copyTemp.hasNextLine()) {
                finalExport.println(copyTemp.nextLine());
            }
            finalExport.close();
            ability_haste_calculations.main(cooldowns);
        }
    }
}