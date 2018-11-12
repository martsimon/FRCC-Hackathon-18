

import java.io.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Character
{
        //File modification
        public static java.io.File inFile;
	static Scanner input;
	public static Scanner inputFile;
    
    
	private String name;
	private int hp;
	private int mp;
	private String weapon;
	private int weaponMod;
	private int xp;
	




	public Character()
	{
		name = "null";
		hp = -1;
		mp = -1;
		weapon = "null";
		weaponMod = 2;
		xp = -1;
	}

	/**
	 * @param name
	 * @param hp
	 * @param mp
	 * @param weapon
	 */
	public Character(String name, int hp, int mp, String weapon, int weaponMod, int xp)
	{
		super ( );
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.weapon = weapon;
		this.weaponMod = weaponMod;
		this.xp = xp;
	}
	
	public Character(String name, int hp, int xp)
	{
		this.name = name;
		this.hp = hp;
	}
	


	public String getName( )
	{
		return name;
	}


	public void setName( String name )
	{
		this.name = name;
	}


	public int getHp( )
	{
		return hp;
	}


	public void setHp( int hp )
	{
		this.hp = hp;
	}


	public int getMp( )
	{
		return mp;
	}


	public void setMp( int mp )
	{
		this.mp = mp;
	}


	public String getWeapon( )
	{
		return weapon;
	}


	public void setWeapon( String weapon )
	{
		this.weapon = weapon;
	}


	public int getWeaponMod( )
	{
		return weaponMod;
	}

	public void setWeaponMod( int weaponMod )
	{
		this.weaponMod = weaponMod;
	}

	public int getXp( )
	{
		return xp;
	}


	public void setXp( int xp )
	{
		this.xp = xp;
	}

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
	
}