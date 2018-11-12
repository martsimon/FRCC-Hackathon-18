

import java.io.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;


public class rpgproject
{
	
	public static java.io.File inFile;
	static Scanner input;
	public static Scanner inputFile;
	
	public static void main( String[ ] args ) throws IOException
	{
		System.out.println("You have finally reached the cave of the evil dragon that ate your family."
				+ "\n The revenge you have wanted for years is within your grasp.\n The dragon is hiding "
				+ "in the depths of this cave, but is surrounded by minions. \n Defeat the minions and slay the dragon!");
		inFile = new java.io.File("stats.txt");
		inputFile = new Scanner (inFile);
		
		if (!inFile.exists ( ))
		{
			System.out.println ( "Input file not found." );
			System.exit(-1);
		}
		
                input = new Scanner(System.in);
		
		Character characterArray[] = new Character[50];
		Combat battle = new Combat();
                
		makePlayerCharacter (characterArray);
		  
                Map dungeon = new Map();
                int eventNum = 0;
                
                while(true)
                {
                    dungeon.moveInMap();
                    eventNum = dungeon.checkRoom();
                    if(eventNum < 13) battle.combatAction(characterArray, eventNum);
                }
		
		//inputFile.close ( );

	} // END MAIN
        /**********************************************
         * Create the player and enemy objects
         * @param characterArray 
         */ 
	public static void makePlayerCharacter(Character characterArray[])
	{
		Character player = new Character ( );
		
		System.out.println ( "Enter your character's name:" );
		player.setName (input.next ( ));

			player.setHp ( 200 );
			player.setMp ( 100 );
			player.setWeapon ( "Sword" );
			player.setXp (0);
			characterArray[0] = player;

			int i = 1; // represents the location in the array
			
                        //If specified, create different enemy stats from our text file
			while ( inputFile.hasNext ())
			{
				Character enemy = new Character ( );
				enemy.setName ( inputFile.next() );
				enemy.setHp ( inputFile.nextInt ( ) );
				enemy.setWeaponMod ( inputFile.nextInt ( ) );       //Needs to be a double
				enemy.setXp (inputFile.nextInt ( ));
				characterArray[i] = enemy;
				i++;
			}
			
	} // ENDMAKEPLAYERCHARACTER

//
//        /************
//         * Takes the input for the player's combat
//         * @return 
//         */
//	public static int combatMenu ()
//	{
//		int actionChoice = -1;
//		System.out.println ( "What will you do?" );
//		System.out.println ( "1. Attack" );
//		System.out.println ( "2. Guard" );
//		System.out.println ( "3. Heal" );
//		System.out.println ( "4. Run Away" );
//		//System.out.println ( "???" ); // in case of additional actions
//		
//		actionChoice = input.nextInt ( );
//		
//		while (actionChoice < 1 || actionChoice > 4)
//		{
//			System.out.println ( "Invalid action choice, please try again." );
//			System.out.println ( "What will you do?" );
//			System.out.println ( "1. Attack" );
//			System.out.println ( "2. Guard" );
//			System.out.println ( "3. Heal" );
//			System.out.println ( "4. Run Away" );
//			actionChoice = input.nextInt ( );
//		}
//		
//		return actionChoice;
//	} // END COMBATMENU
//	
//        /********************
//         * The actions taken in battle
//         * @param characterArray 
//         */
//	public static void combatAction (Character characterArray[])
//	{
//		boolean fightOver = false;                                  //If the fight is over, return true
//		int roll;                                                   //The result of our dice roll, 1-20
//		String playerName = characterArray[0].getName ( );          
//		String enemyName = characterArray[1].getName ( );
//		int playerHealthPoints = characterArray[0].getHp();
//		int enemyHealthPoints = characterArray[1].getHp ( );
//		int randomNum;                                              //Calculates the damage dealt
//		Combat item = new Combat();
//		
//		do
//		{	
//		
//			System.out.println ( playerName + " has: " + playerHealthPoints + " HP" );
//			System.out.println ( enemyName + " has: " + enemyHealthPoints + " HP\n" );
//			int actionChoice = combatMenu();
//			
//                        //Attack
//                        if (actionChoice == 1)
//			{
//				System.out.println ( playerName + " attacks!" );
//				roll = d20Roll();
//				//boolean hit = true;
//				int damage;
//				if (roll < 2)
//				{
//					System.out.println ( playerName + " rolled " +  roll + ". You missed!");
//					//hit = false;
//				}
//				else
//				{
//					System.out.println ( playerName + " rolled " + roll + ". Successful hit!" );
//					enemyHealthPoints = dealDamage (characterArray, enemyHealthPoints);
//				}
//				if (enemyHealthPoints > 1)
//				{
//					damage = item.getDamage ( );
//					int totalDamage = damage + (  35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) ;
//					System.out.println ( enemyName + " does " + totalDamage + " damage!" );
//					playerHealthPoints = playerHealthPoints - totalDamage;
//				}
//			
//			}
//                        
//                        //Guard
//			if (actionChoice == 2)
//			{
//				roll = d20Roll();
//				boolean block = true;
//				if (roll <6)
//				{
//					System.out.println ( playerName + " rolled " + roll + ". You fail to guard!" );
//					block = false;
//				}
//				else
//				{
//					System.out.println ( playerName + " rolled " + roll + ". Successful guard!" );
//				}
//				if (block != false)
//				{
//					int totalDamage = 10 + (  35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) ;
//					System.out.println ( enemyName + " does " + totalDamage + " damage!" );
//					playerHealthPoints = playerHealthPoints - totalDamage;
//				}
//				else
//				{
//					int damage = item.getDamage ( );
//					int totalDamage = damage + (  35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) ;
//					System.out.println ( enemyName + " does " + totalDamage + " damage!" );
//					playerHealthPoints = playerHealthPoints - totalDamage;
//				}
//				
//			}
//                        
//                        //Heal
//			if (actionChoice == 3)
//			{
//				int healthGained = (int) (playerHealthPoints * 0.25 );
//				System.out.println ( playerName + " heals for " + healthGained );
//				if (playerHealthPoints + healthGained <= characterArray[0].getHp ( ))
//				{
//					playerHealthPoints = playerHealthPoints + healthGained;
//					System.out.println ( playerName + " has " + playerHealthPoints + " HP" );
//				}
//				else if (playerHealthPoints + healthGained > characterArray[0].getHp ( ))
//				{
//					playerHealthPoints = characterArray[0].getHp();
//					System.out.println ( playerName + " has " + playerHealthPoints + " HP" );
//
//				}
//			}
//                        
//                        //Run away
//			if (actionChoice == 4)
//			{
//				System.out.println ( "You try to run away..." );
//				roll = d20Roll();
//				//boolean escape = false;
//				if (roll < 11)
//				{
//					System.out.println ( playerName + " rolled " + roll + ". Can't escape!" );
//					int damage = item.getDamage ( );
//					int totalDamage = damage + (  35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) ;
//					System.out.println ( enemyName + " does " + totalDamage + " damage!" );
//					playerHealthPoints = playerHealthPoints - totalDamage;
//				}
//				else
//				{
//					System.out.println ( playerName + " rolled " + roll + ". Successfully escaped!" );
//					fightOver = true;
//				}
//			
//			} 
//			
//                        //Game over
//			if (playerHealthPoints <1)
//			{
//				System.out.println ( playerName + " died!" );
//				fightOver = true;
//			}
//                        //Victory
//			else if (enemyHealthPoints < 1)
//			{
//				System.out.println ( enemyName + " died!" );
//				fightOver = true;
//			}
//			
//		} while (fightOver != true);
//			
//		if (playerHealthPoints > 1 && enemyHealthPoints < 1)
//		{
//			characterArray[0].setXp ( characterArray[0].getXp() + characterArray[1].getXp() );
//		}
//		
//	} // END COMBATACTION
//	
//        /***********
//         * Make a probability roll out of 20
//         * @return an int between 1 and 20
//         */
//	public static int d20Roll ()
//	{
//		int roll = 0;
//		roll = (int) ( Math.random()*(20-1+1)+1 );
//		return roll;
//	}
//        
//        /*********
//         * Takes health from the enemy
//         * @param characterArray
//         * @param enemyHealthPoints
//         * @return the health of the enemy
//         */
//	public static int dealDamage(Character characterArray[], int enemyHealthPoints)
//	{
//		String playerName = characterArray[0].getName ( );
//		Combat item = new Combat ( );
//		int randomNum;
//		int weaponModifier = characterArray[0].getWeaponMod();
//		
//
//		int damage = item.getDamage ( );
//		int totalDamage = damage + ( ( 35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) * weaponModifier );
//		System.out.println ( playerName + " does " + totalDamage + " damage!" );
//		enemyHealthPoints = enemyHealthPoints - totalDamage;
//			return enemyHealthPoints;
//
//
//	} // END DEALDAMAGE
}
