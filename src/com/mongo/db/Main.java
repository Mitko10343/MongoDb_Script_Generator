/*
 *3rd Year MongoDb Assignment
 *Written By: Dimiter Dinkov
 *Student Number : C15334276
 */

package com.mongo.db;

//import java packages
import java.util.*;
import java.io.*;


public class Main {

	//Declare an array list of Games objects
	static ArrayList<Games> games = new ArrayList<Games>();
	//declare a buffered reader to read from a file
	static BufferedReader fileReader = null;
	//Declare a new file path for the javascript file that create the collection
	static File flatCollectionFile = new File("E:/Eclipse/MongoAssignment/flatCollection.js");
	static File newCollectionFile = new File("E:/Eclipse/MongoAssignment/newCollection.js");
	
	//main
	public static void main(String[] args) {
		//Read all the games from file into array list
		ReadGames("E:/Eclipse/MongoAssignment/ign.txt");
		
		//If this file doesnt exist try to create a new file with that name in that path 
		//else catch an exception and print an error message
		if(!flatCollectionFile.exists())
		{
			try{
				flatCollectionFile.createNewFile();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//generate a file for the original collection		
		generateFile();
		
		//If this file doesnt exist try to create a new file with that name in that path 
		//else catch an exception and print an error message
		if(!newCollectionFile.exists())
		{
			try{
				newCollectionFile.createNewFile();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//Generate a javascript file for my personaly designed collection
		generateNewCollectionFile();
	}
	
	//Method to read the data from a .tsv file into an array list
	public static void ReadGames(String filePath)
	{
		//initialise a counter to keep track of the amount of games you have read into the array list
		int counter = 0;
		//try initialise a buffered reader else catch an exception and print an error message
		try{
			//initialising the buffered reader
			fileReader = new BufferedReader(new FileReader(filePath));
			//read in the first line into a string
			String line = fileReader.readLine();
			//read in the next line which is just the column names and we dont need them
			line = fileReader.readLine();
			//read in the first 1000 rows from the data set
			while(counter <=1000)
			{
				//Read every column data into a string array
				String[] gameData = line.split("\t");
				//call a method add game that returns a game object and assign that to anothe game object
				Games ga = addGame(gameData);
				//add the new game object into the array list
				games.add(ga);
				//read the next line
				line = fileReader.readLine();
				//increment counter
				counter++;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Add game methods that parse the gama data from string array into variables
	//and returns a game object
	public static Games addGame(String[] gameData)
	{
		//declare variables and assign the column data to them
		int id = Integer.parseInt(gameData[0]);
		String score_phrase = gameData[1];
		String title = gameData[2];
		String url = gameData[3];
		String platform = gameData[4];
		float score = Float.parseFloat(gameData[5]);
		String genre = gameData[6];
		String editors_choice = gameData[7];
		int ry = Integer.parseInt(gameData[8]);
		int rm = Integer.parseInt(gameData[9]);
		int rd = Integer.parseInt(gameData[10]);
		
		//create and return a new game object 
		return new Games(id,score_phrase,title,url,score,platform,genre,editors_choice,ry,rm,rd);
		
	}
	
	//generate a file with java script to create the flat collection
	public static void generateFile()
	{
		//create a buffered writer
		BufferedWriter bw =null;
		//Intialize a string that would tell monogo what db to use
		String useCollection = "use Games\n";
		//create a string builde
		StringBuilder script = new StringBuilder();
		
		//try to initialise the file writer and write the first line to the file
		//else catch an exception and print an error
		try{
			FileWriter fw = new FileWriter(flatCollectionFile);
			bw = new BufferedWriter(fw);
			bw.write(useCollection);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//for every element of the games array list generate a string of javascript code for inserting data
		//into the mongoDB collection 
		for(int i=0; i<games.size()-1;i++)
		{
			Games game = games.get(i);
			
			
			script.append("db.Games.insert({id:"+game.getId()+
					",score_phrase:\""+game.getScore_phrase()+"\","
					+ "title:\""+game.getTitle()+
					"\",url:\""+game.getUrl()+
					"\",platform:\""+game.getPlatform()+
					"\",score:"+game.getScore()+
					",genre:'"+game.getGenre()+
					"',editors_choice:\""+game.getEditors_choice()+
					"\",release_year:"+game.getRelese_year()+
					",release_month:"+game.getRelease_month()+
					",release_day:"+game.getRelease_day()+"})\n");
			//Try writing the script line to the file esle catch an exception and print an error
			try{
				bw.write(script.toString());
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			//Print the script line for debugging purposes
			System.out.println(script.toString());
			//set the string builder pointer to the beginning of the string
			script.setLength(0);
		}
		//try close the file else catch an exception and print an error
		try{
		bw.close();}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Method that generates a new file with the javascript to create the collection I designed
	public static void generateNewCollectionFile()
	{
		//create a buffered writer
		BufferedWriter bw = null;
		String useCollection = "use Games\n";
		//create a string builder
		StringBuilder script = new StringBuilder();
		
		//try to initialise the file writer and write the first line to the file
		//else catch an exception and print an error
		try{
			FileWriter fw = new FileWriter(newCollectionFile);
			bw = new BufferedWriter(fw);
			bw.write(useCollection);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//for every element of the games array list generate a string of javascript code for inserting data
				//into the mongoDB collection 
		for(int i=0; i<games.size()-1;i++)
		{
			Games game = games.get(i);
			
			
			script.append("db.Platforms.insert({id:"+game.getId()+",platforms:[{ platform_name:\""+game.getPlatform()+
					"\",game_title:\""+game.getTitle()+"\",score:"+game.getScore()+",score_phrase:\""+game.getScore_phrase()+
					"\"}],release_year:"+game.getRelese_year()+"})\n");
			//Try writing the script line to the file esle catch an exception and print an error
			try{
				bw.write(script.toString());
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			//Print the script line for debugging purposes
			System.out.println(script.toString());
			//set the string builder pointer to the beginning of the string
			script.setLength(0);
		}
		//try close the file else catch an exception and print an error
		try{
		bw.close();}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
