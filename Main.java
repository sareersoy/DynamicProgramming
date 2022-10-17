import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static final int AUAV_CAPACITY = 10;
    public static void main(String[] args) throws IOException {
        FileWriter Writer = new FileWriter("scanLog.txt");
        System.out.print("##MISSION FIREWALL INITIATED##\nStarted scanning...\n");

        Map<String, Malware> malwareDB = XMLParser.parse(args[0]);
        MalwareScanner scanner = new MalwareScanner(malwareDB);
        scanner.scanFile(args[1]);
        int count=0;
        int count2=0;
        for(int i=0; i<scanner.txtdata.size();i++){
            count2++;
            for(int j=0; j<XMLParser.MalArrList.size();j++){

                if(XMLParser.MalArrList.get(j).getHash().contains(scanner.txtdata.get(i))){
                    Writer.write(XMLParser.MalArrList.get(j).getHash()+"@"+count2+"\n");
                    System.out.print("--------------------------------------------------------------------------------\nDetected malware!\nName: "+XMLParser.MalArrList.get(j).getTitle()+"\nThreat Level: "+ XMLParser.MalArrList.get(j).getLevel()
                    +"\nHash:"+XMLParser.MalArrList.get(j).getHash()+"\n");
                    count++;}}}
        System.out.print("--------------------------------------------------------------------------------\n" + "Scan complete! "+count+" threats are found and eliminated. Generating log file...");
        System.out.println("\n##MISSION FIREWALL COMPLETED##");
            Writer.close();
            System.out.println("##MISSION NUKE'M INITIATED##");

            ArrayList<Integer> numberOfEnemiesArrivingPerHour = Util.readTroopsDeploymentSchedule(args[2]);
            DefenseAgainstEnemyTroops defense = new DefenseAgainstEnemyTroops(numberOfEnemiesArrivingPerHour);
            OptimalEnemyDefenseSolution solution = defense.getOptimalDefenseSolutionDP();
            solution.printEnemyDefenseSolution(numberOfEnemiesArrivingPerHour);
            System.out.println("##MISSION NUKE'M COMPLETED##");

            System.out.println("##MISSION EXTERMINATE INITIATED##");

            ArrayList<Integer> bombWeights = Util.readBombWeights(args[3]);
            int maxNumberOfAvailableAUAVs = Util.readNumberOfAvailableAUAVs(args[3]);
            OptimalFinalDefenseGP finalDefense = new OptimalFinalDefenseGP(bombWeights);
            finalDefense.printFinalDefenseOutcome(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
            System.out.println("##MISSION EXTERMINATE COMPLETED##");


    }
}
