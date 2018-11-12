

import java.util.Scanner;


public class Combat
{
    Scanner input = new Scanner(System.in);
    
	private int damage;
	
	public Combat()
	{
		damage = 35;
	}

	/**
	 * @param damage
	 */
	public Combat(int damage)
	{
		super ( );
		this.damage = damage;
	}

	public int getDamage( )
	{
		return damage;
	}

	public void setDamage( int damage )
	{
		this.damage = damage;
	}
        
        /************
         * Takes the input for the player's combat
         * @return 
         */
	public int combatMenu ()
	{
		int actionChoice = -1;
		System.out.println ( "What will you do?" );
		System.out.println ( "1. Attack" );
		System.out.println ( "2. Guard" );
		System.out.println ( "3. Heal" );
		System.out.println ( "4. Run Away" );
		//System.out.println ( "???" ); // in case of additional actions
		
		actionChoice = input.nextInt ( );
		
		while (actionChoice < 1 || actionChoice > 4)
		{
			System.out.println ( "Invalid action choice, please try again." );
			System.out.println ( "What will you do?" );
			System.out.println ( "1. Attack" );
			System.out.println ( "2. Guard" );
			System.out.println ( "3. Heal" );
			System.out.println ( "4. Run Away" );
			actionChoice = input.nextInt ( );
		}
		
		return actionChoice;
	} // END COMBATMENU
	
        /********************
         * The actions taken in battle
         * @param characterArray 
         */
	public void combatAction (Character characterArray[], int i)
	{
		boolean fightOver = false;                                  //If the fight is over, return true
		int roll;                                                   //The result of our dice roll, 1-20
		String playerName = characterArray[0].getName ( );          
		String enemyName = characterArray[i].getName ( );
		int playerHealthPoints = characterArray[0].getHp();
		int enemyHealthPoints = characterArray[i].getHp ( );
		int randomNum;                                              //Calculates the damage dealt
		Combat item = new Combat();
		
		do
		{	
		
			System.out.println ( playerName + " has: " + playerHealthPoints + " HP" );
			System.out.println ( enemyName + " has: " + enemyHealthPoints + " HP\n" );
			int actionChoice = combatMenu();
			
                        //Attack
                        if (actionChoice == 1)
			{
				System.out.println ( playerName + " attacks!" );
				roll = d20Roll();
				int damage;
				if (roll < 2)
				{
					System.out.println ( playerName + " rolled " +  roll + ". You missed!");
				}
				else
				{
					System.out.println ( playerName + " rolled " + roll + ". Successful hit!" );
					enemyHealthPoints = dealDamage (characterArray, enemyHealthPoints);
				}
				if (enemyHealthPoints > 1)
				{
					damage = item.getDamage ( );
					int totalDamage = damage + (  35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) ;
					System.out.println ( enemyName + " does " + totalDamage + " damage!" );
					playerHealthPoints = playerHealthPoints - totalDamage;
				}
			
			}
                        
                        //Guard
			if (actionChoice == 2)
			{
				roll = d20Roll();
				boolean block = true;
				if (roll <6)
				{
					System.out.println ( playerName + " rolled " + roll + ". You fail to guard!" );
					block = false;
				}
				else
				{
					System.out.println ( playerName + " rolled " + roll + ". Successful guard!" );
				}
				if (block != false)
				{
					int totalDamage = 10 + (  35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) ;
					System.out.println ( enemyName + " does " + totalDamage + " damage!" );
					playerHealthPoints = playerHealthPoints - totalDamage;
				}
				else
				{
					int damage = item.getDamage ( );
					int totalDamage = damage + (  35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) ;
					System.out.println ( enemyName + " does " + totalDamage + " damage!" );
					playerHealthPoints = playerHealthPoints - totalDamage;
				}
				
			}
                        
                        //Heal
			if (actionChoice == 3)
			{
				int healthGained = (int) (characterArray[0].getHp ( ) * 0.5 );
				System.out.println ( playerName + " heals for " + healthGained );
				if (playerHealthPoints + healthGained <= characterArray[0].getHp ( ))
				{
					playerHealthPoints = playerHealthPoints + healthGained;
					System.out.println ( playerName + " has " + playerHealthPoints + " HP" );
				}
				else if (playerHealthPoints + healthGained > characterArray[0].getHp ( ))
				{
					playerHealthPoints = characterArray[0].getHp();
					System.out.println ( playerName + " has " + playerHealthPoints + " HP" );
                                        
				}   
                                damage = item.getDamage ( );
                                int totalDamage = damage + (  35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) ;
				System.out.println ( enemyName + " does " + totalDamage + " damage!" );
				playerHealthPoints = playerHealthPoints - totalDamage;
			}
                        
                        //Run away
			if (actionChoice == 4)
			{
				System.out.println ( "You try to run away..." );
				roll = d20Roll();
				//boolean escape = false;
				if (roll < 11)
				{
					System.out.println ( playerName + " rolled " + roll + ". Can't escape!" );
					int damage = item.getDamage ( );
					int totalDamage = damage + (  35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) ;
					System.out.println ( enemyName + " does " + totalDamage + " damage!" );
					playerHealthPoints = playerHealthPoints - totalDamage;
				}
				else
				{
					System.out.println ( playerName + " rolled " + roll + ". Successfully escaped!" );
					fightOver = true;
				}
			
			} 
			
                        //Game over
			if (playerHealthPoints <1)
			{
				System.out.println ( "\n\n" + playerName + " died! \n\n\nGame Over!" );
                                System.exit(0);
				fightOver = true;
			}
                        //Victory
			else if (enemyHealthPoints < 1)
			{
				System.out.println ( enemyName + " died!" );
				fightOver = true;
			}
			
		} while (fightOver != true);
			
		if (playerHealthPoints > 1 && enemyHealthPoints < 1)
		{
			characterArray[0].setXp ( characterArray[0].getXp() + characterArray[1].getXp() );
		}
		
	} // END COMBATACTION

        /***********
         * Make a probability roll out of 20
         * @return an int between 1 and 20
         */
	public int d20Roll()
	{
		int roll = 0;
		roll = (int) ( Math.random()*(20-1+1)+1 );
		return roll;
	}
        
        /*********
         * Takes health from the enemy
         * @param characterArray
         * @param enemyHealthPoints
         * @return the health of the enemy
         */
	public int dealDamage(Character characterArray[], int enemyHealthPoints)
	{
		String playerName = characterArray[0].getName ( );
		Combat item = new Combat ( );
		int randomNum;
		int weaponModifier = characterArray[0].getWeaponMod();
		

		int damage = item.getDamage ( );
		int totalDamage = damage + ( ( 35 % ( randomNum = (int) ( Math.random ( ) * ( 10 - 1 + 1 ) + 1 ) ) ) * weaponModifier );
		System.out.println ( playerName + " does " + totalDamage + " damage!" );
		enemyHealthPoints = enemyHealthPoints - totalDamage;
			return enemyHealthPoints;


	} // END DEALDAMAGE
}
