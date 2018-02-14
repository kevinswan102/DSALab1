import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtility {

	public Scanner RequestForValidFile(Scanner scanObject)
	{
		System.out.println("Enter the location of your log file:");
		System.out.flush();
		String fileName = scanObject.nextLine();
		
		File file = new File(fileName);
		try {
			Scanner readFile = new Scanner(file);
			return readFile;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Invalid file name " + fileName);
			return null;
		}
	}
}
