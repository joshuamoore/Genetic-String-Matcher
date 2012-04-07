//Program: Assignment1.jsl
//Course: COSC306
//Description: This is a program attempts to find a DNA or amino acid sequence in a
//huge collection of genetic sequences by allowing the user to input a sequence of 
//Cs, As, Ts, or Gs along with the corresponding name.  The program will then
//ask for a combination to be searched for and then iterate through all the strings of DNA.  
//If the program finds a match, then it will print out the matching sequence and 
//the name which the sequence belongs to.
//Author: Josh Moore
//Revised: 2-9-09
//Compiler: Microsoft Visual J#
//Comments: This program should cycle through the options and allow the user to select as many
//options as they want unless the program is terminated by the user.
//Notes: None

//Class: Assignment1.jsl
//Description: None
//******************************************************************************
public class Assignment1
{

	//Method: main
	//Description:  Creates all variables and arrays, allows the user to input and choose 
	//				options based off the inputted file.  The program can then add the variables
	//				to the array, print the array, search the array, and exit
	//the program.
	//Parameters:   addGenome, addName, genome[], name[], option, finalCount, userInput, endGenome,
	//				nameValue, codeLine, searchString, search, answer, insertMarkers, finalSpot, modify
	//Returns:     	None
	//Calls:       	textFile, KeyboardInputClass
	//Globals:	None
	//*******************************************************************************
	public static void main(String[] args)
	{

		TextFileClass textFile = new TextFileClass(0);
		StringBuffer addGenome;
		StringBuffer addName;
		String genome[];
		String name[];

		int option = 5;
		int finalCount = 0;

		while (option != 4)
		{

			KeyboardInputClass keyboardInput = new KeyboardInputClass();
			String userInput = "";
			userInput = keyboardInput.getKeyboardInput("1: Specify the input file \n" +
					"2: Display the contents of the file \n3: Search for contents" +
					"\n4: Exit the program");

			option = Integer.parseInt(userInput);

			if (option == 1)
			{
				System.out.println();
				textFile.getFileName("Please enter the text file name to be searched");

				if (textFile.fileName.length() > 0)
				{
					textFile.getFileContents();
				}
			}


			finalCount = textFile.getFileContents();
			genome = new String[finalCount];
			name = new String[finalCount];
			int endOfGenome = 0;
			int nameValue = 0;

			for (int i = 0; i < finalCount; i++)
			{
				//finalCount = # of rows
				String codeLine = textFile.text[i];
				endOfGenome = 0;
				nameValue = 0;
				int j = 0;
				addGenome = new StringBuffer();
				addName = new StringBuffer();

				while (codeLine.charAt(j) != 32)
				{
					//codeLine.length() = columns
					addGenome.append(codeLine.charAt(j));
					endOfGenome++;
					j++;
				}
				genome[i] = addGenome.ToString();
				nameValue = codeLine.length() - (endOfGenome + 1);


				for (int k = endOfGenome + 1; k < codeLine.length(); k++)
				{
					addName.append(codeLine.charAt(k));
					if (k == (codeLine.length() - 1))
					{
						name[i] = addName.ToString();
					}
				}

			}

			if (option == 2)
			{
				System.out.println();
				for (int i = 0; i < finalCount; i++)
				{
					System.out.println(textFile.text[i]);
				}
			}

			if (option == 3)
			{
				System.out.println();
				KeyboardInputClass search = new KeyboardInputClass();
				String searchString = "";
				searchString = search.getKeyboardInput("Please enter the desired string to search for");
				searchString = searchString.toUpperCase();
				System.out.println();

				for (int i = 0; i < finalCount; i++)
				{
					boolean answer = genome[i].Contains(searchString);
					int indexOf = genome[i].IndexOf(searchString);
					if (answer == true)
					{
						String modify = genome[i];
						StringBuffer insertMarkers = new StringBuffer(modify);
						insertMarkers.insert((indexOf), ">");
						int finalSpot = searchString.length();
						insertMarkers.insert((finalSpot + indexOf + 1), "<");
						genome[i] = insertMarkers.ToString();
						System.out.println(genome[i] + " " + name[i]);
					}
				}
			}

			if (option == 4)
			{
				System.exit(0);
			}
			System.out.println();
		}
	}
	//****************************************************************************** 
	//******************************************************************************
}
//******************************************************************************
//******************************************************************************