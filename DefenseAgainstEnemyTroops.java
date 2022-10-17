
import java.util.ArrayList;

import static java.lang.StrictMath.min;

public class DefenseAgainstEnemyTroops {
    private final ArrayList<Integer> numberOfEnemiesArrivingPerHour;
    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;}

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;}

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;}

    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){
        ArrayList<ArrayList<Integer>> HOURS = new ArrayList<>();
        ArrayList<Integer> SOL= new ArrayList<>();
        int max = 0;
        for ( int k=0; k<getNumberOfEnemiesArrivingPerHour().size()+1; k++){
            SOL.add(0);
            ArrayList<Integer> arr = new ArrayList<>();
            HOURS.add(k,arr);}

        for(int j=1; j<getNumberOfEnemiesArrivingPerHour().size()+1; j++){
            for(int i=0 ;i<j; i++){
                int min=min(getNumberOfEnemiesArrivingPerHour().get(j-1),(j-i)*(j-i));
                if(SOL.get(i)+ min > max){
                    max = SOL.get(i)+ min;
                    SOL.set(j,SOL.get(i)+min(getNumberOfEnemiesArrivingPerHour().get(j-1),(j-i)*(j-i)));
                    ArrayList<Integer> Arr = new ArrayList<>(HOURS.get(i));
                    Arr.add(j);
                   HOURS.set(j,Arr);}}}

        int a = 0;
        for(int b : SOL){
            if(a<b){
                a=b;}}

        OptimalEnemyDefenseSolution oeds = new OptimalEnemyDefenseSolution(a,HOURS.get(HOURS.size()-1));
        return oeds;
    }


}
