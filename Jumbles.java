import java.io.*;
import java.util.*;
public class Jumbles{
    public static void main(String[] args) throws Exception
    {
        BufferedReader dicFile = new BufferedReader(new FileReader(args[0]));
        BufferedReader jumbleFile = new BufferedReader(new FileReader(args[1]));
        TreeMap<String,TreeSet<String>> together = new TreeMap<String,TreeSet<String>>();
        String [] unconDic = loadArray(dicFile);
        dicFile.close();
        String [] unconJumb = loadArray(jumbleFile);
        jumbleFile.close();
        String[]conDic = toCanonical(unconDic);
        String[]conJumb = toCanonical(unconJumb);
        //for the matches map, every jumble word will be the String in <String, TreeSet<String>>
        //the TreeSet<String> shoud be a tree set of all the cononical mathces to the Jumbled word
        //loop through the dictionary and add every match to the map
        //create a tree map of all matches, then add the tree map and the jumbled word
        for(int i = 0; i < unconJumb.length;i++){//for words in jumble
            TreeSet<String> matches = new TreeSet<String>();
            for(int j = 0; j <conDic.length; j++){//words in dictinary
                
                if(conJumb[i].equals(conDic[j])){
                    matches.add(unconDic[j]);
                }
            }
            together.put(unconJumb[i], matches);
        }
        printMap(together);
    }
    
    public static String[] toCanonical( String[] arr ) // assume s = "zebra"
    {
        String [] conArr = new String[arr.length];
        for(int i=0; i<arr.length; i++){
	    char[] letters = arr[i].toCharArray(); // letters -> [z][e][b][r][a]
	    Arrays.sort( letters ); // now letters -gt; [a][b][e][r][z]
	    String word = new String( letters ); // read String API it has a constructor that accepts a char array and does the obvious with it
        conArr[i] = word;
        }
        return conArr;
    }
    static String[] doubleLength( String[] old )
	{
		String[] doubled = new String[ old.length * 2 ];
		for ( int i = 0; i < old.length ; i++ )
			doubled[i] = old[i];
		return doubled;
	}
    static String[] loadArray(BufferedReader infile) throws Exception
    {
        String[] arr = new String[5];
        int counter = 0;
        while(infile.ready()){
            if (counter >= arr.length){
                arr = doubleLength(arr);
            }
            arr[counter] = infile.readLine();
            counter++;
        }
        return trimArray(arr,counter);
    }
    static String[] trimArray( String[] old, int count )
	{
		String[] shortened = new String[count];
		for ( int i = 0; i<count ; i++ )
			shortened[i] = old[i];
		return shortened;
	}
    static void printMap(TreeMap<String,TreeSet<String>> map){
		for (String key : map.keySet()) {
            System.out.print(key + " ");
            TreeSet<String> set = map.get(key);
            for (String value : set)
                System.out.print(value + " ");
            System.out.println();
		}
	}
}