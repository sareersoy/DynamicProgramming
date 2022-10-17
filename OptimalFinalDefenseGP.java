import java.util.ArrayList;
import java.util.Collections;

public class OptimalFinalDefenseGP
{
    private ArrayList<Integer> bombWeights;
    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }
    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }
    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity)
    {
        Collections.sort(bombWeights);
        Collections.reverse(bombWeights);
        //Here, I used two for loops to iterate the bombWeights and AUAV storages at the same time.
        int[] rem = new int[maxNumberOfAvailableAUAVs];
        if(Collections.max(bombWeights)>maxAUAVCapacity)
            return -1;
        for(int i=0; i<maxNumberOfAvailableAUAVs; i++){rem[i]=0;}
        int count;
        for(int k=0; k<bombWeights.size(); k++){
            count = 0;

            for(int j=0; j<maxNumberOfAvailableAUAVs; j++){
                if(bombWeights.get(k)<=(maxAUAVCapacity-rem[j])){
                    //Here I filled the storages according to current weight if it can fit there.
                    rem[j]+=bombWeights.get(k);
                    count++;
                        break;}}
        if(count==0){ //If the filled storage count is zero, then it booms.
            return -1;}
        }

        int c=0;
for(int i:rem){
    if(i==0){
        c++;}
}
//Here, I found the number of storages I used and return them.
        return maxNumberOfAvailableAUAVs-c;}
    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);}
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");}}
}
