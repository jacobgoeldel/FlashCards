import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
	
	//loads an array of cards from the given file
	public String[] loadCards(String fileName) {
		ArrayList<String> list = new ArrayList<String>();

		Scanner scan;
		
		//try catch is just so if there is no file
		try {
			//load the file
			scan = new Scanner(new File(fileName));
			//read each line and add it to the list
			while(scan.hasNext()) {
				list.add(scan.nextLine());
			}
			scan.close();
			
			//convert to array for output
			String[] listArray = list.toArray(new String [0]);
			
			return listArray;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	//save an array as a file
	public void saveCards(ArrayList<String> arrayToSave, String fileName) {
		
		PrintWriter out;
		//try for if it fails
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			
			//add every string of the array to a line in the file
			for( int x = 0; x < arrayToSave.size(); x++)
			{
				out.println(arrayToSave.get(x));
			}

			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
