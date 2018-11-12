

import java.io.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Map
{
        //File modification
        public java.io.File inFile;
	public Scanner inputFile;
    
    Scanner input = new Scanner(System.in);         //Input object
    
    Combat battle = new Combat();                   //Battle object
    Character jim = new Character(  );
    Character characterArray[] = new Character[50];   //Character object
//    makePlayerCharacter(characterArray);
    
//    		inFile = new java.io.File("stats.txt");
//		inputFile = new Scanner (inFile);
//		
//		if (!inFile.exists ( ))
//		{
//			System.out.println ( "Input file not found." );
//			System.exit(-1);
//		}
    
    char[][] grid = new char[5][5];                 //Our map is constructed as a square array
    int playerLocationH = 0;                        //Shows which column the player room is in
    int playerLocationV = 0;                        //Shows which row the player room is in
    char nextRoom;                                  //Stores the char of the next room that the player is in, checks if an event needs to be triggered
    Boolean key = false;                               //To access the boss room, the player needs a key
    
                //characterArray.makePlayerCharacter();
    
    /*  Map Array (Initialized in constructor)
        {'H', 'R', 'R', ' ', ' '},
        {'R', ' ', 'R', 'R', ' '},
        {'R', 'R', 'R', 'R', 'R*'},
        {' ', 'R', 'R', 'R', ' '},
        {' ', 'R*', 'R', 'R', 'B'}};
    H is where the player starts
    R is a standard room
    R* has special events that the player will find
    B is the boss room
    */
    public Map()
    {

        
        grid[0][0] = '\u25A3';
        grid[0][1] = '\u25A1';
        grid[0][2] = '\u25A1';
        grid[0][3] = ' ';
        grid[0][4] = ' ';
        grid[1][0] = '\u25A1';
        grid[1][1] = ' ';
        grid[1][2] = '\u25A1';
        grid[1][3] = '\u25A1';
        grid[1][4] = ' ';
        grid[2][0] = '\u25A1';
        grid[2][1] = '\u25A1';
        grid[2][2] = '\u25A1';
        grid[2][3] = '\u25A1';
        grid[2][4] = '\u25A1';
        grid[3][0] = ' ';
        grid[3][1] = '\u25A1';
        grid[3][2] = '\u25A1';
        grid[3][3] = '\u25A1';
        grid[3][4] = ' ';
        grid[4][0] = ' ';
        grid[4][1] = '\u25A1';
        grid[4][2] = '\u25A1';
        grid[4][3] = '\u25A1';
        grid[4][4] = '\u26BF';
        
        getPlayerCoords();          //Getting values for playerLocationH and playerLocationV
    }
    
    //Accessor for the grid
    char[][] getMap()
    {
        return grid;
    }
    
    //Setter for the grid (not currently in use)
    void setMap(char[][] gridMod)
    {
        grid = gridMod;
    }
    
    //Finds what room the player is in and sets their coordinates accordingly
    void getPlayerCoords()
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(grid[i][j] == '\u25A3')
                {
                    playerLocationH = j;
                    playerLocationV = i;
                }
            }
        }
    }
    
    //Print out our map in a readable array
    void printMap()
    {
        System.out.println("------------");
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------");
    }
    
    //Moves the player into another room based off of input
    void moveInMap()
    {
        int tempH = playerLocationH;
        int tempV = playerLocationV;
        
        do
        {
            printMap();
            System.out.print("Which direction would you like to move?\n1 Up\n2 Down\n3 Left\n4 Right\n");
            int choice = input.nextInt();
        
            switch(choice)
            {
                case 1: //Up 
                {
                    if(playerLocationV - 1 >= 0 && grid[playerLocationV - 1][playerLocationH] != ' ')
                    {
                        grid[playerLocationV][playerLocationH] = '\u2612';
                        nextRoom = grid[playerLocationV - 1][playerLocationH];
                        grid[playerLocationV - 1][playerLocationH] = '\u25A3';
                    }
                    else 
                    {
                        System.out.println("A wall blocks your path. You cannot proceed in this direction.");
                    }
                        break;
                }
                case 2: //Down
                {
                    if(playerLocationV + 1 <=4 && grid[playerLocationV + 1][playerLocationH] != ' ')
                    {
                        grid[playerLocationV][playerLocationH] = '\u2612';
                        nextRoom = grid[playerLocationV + 1][playerLocationH];
                        grid[playerLocationV + 1][playerLocationH] = '\u25A3';
                    }                
                    else 
                    {
                        System.out.println("A wall blocks your path. You cannot proceed in this direction.");
                    }
                    break;
                }
                case 3: //Left
                {
                    if(playerLocationH - 1 >= 0 && grid[playerLocationV][playerLocationH - 1] != ' ')
                    {
                        grid[playerLocationV][playerLocationH] = '\u2612';
                        nextRoom = grid[playerLocationV][playerLocationH - 1];
                        grid[playerLocationV][playerLocationH - 1] = '\u25A3';
                    }
                    else 
                    {
                        System.out.println("A wall blocks your path. You cannot proceed in this direction.");
                    }
                        break;
                }
                case 4: //Right
                {
                    if(playerLocationH + 1 <= 4 && grid[playerLocationV][playerLocationH + 1] != ' ')
                    {
                        if(playerLocationV == 4 && playerLocationH + 1 == 4)        //If the player tries to enter the boss room
                        {
                            if(key == true)         //The player can continue if they have the key
                            {
                                System.out.println("You used the key to open the door.");
                                key = false;
                                grid[playerLocationV][playerLocationH] = '\u2612';
                                nextRoom = grid[playerLocationV][playerLocationH + 1];
                                grid[playerLocationV][playerLocationH + 1] = '\u25A3';
                            }
                            else
                            {
                                System.out.println("The door is locked. You need a key to enter.");
                            }
                        }
                        else
                        {
                            grid[playerLocationV][playerLocationH] = '\u2612';
                            nextRoom = grid[playerLocationV][playerLocationH + 1];
                            grid[playerLocationV][playerLocationH + 1] = '\u25A3';
                        }
                    }
                    else 
                    {
                        System.out.println("A wall blocks your path. You cannot proceed in this direction.");
                    }
                    break;
                }
                default:
                {
                    System.out.println("Invalid input. Please try again");
                    break;
                }
            }
        
            //Once the player moves into a new room, we need to update their coordinates
            getPlayerCoords();
        } while(playerLocationH == tempH && playerLocationV == tempV);      //If the coordinates remain unchanged, we go back through the loop
    }
    
    //If the room has not been visited, activate an event
    int checkRoom()
    {
        if(nextRoom == '\u2612')      //The room has already been explored
        {
            System.out.println("The room is empty. You take a moment to catch your breath.");
        }
        else if (nextRoom == '\u25A1')        //Regular room
        {
                return pullEvent();
        }
        else if (nextRoom == '\u26BF')        //Boss room
        {
            //Boss engage
            System.out.println("You have found the dragon, now you must test your might and slay this foul beast!");
            return 10;
        }
        return 13;
    }
    
    //Based on the map coordinates, determine the event that takes place
    int pullEvent()
    {
        switch(playerLocationV)
        {
            case 0:
            {
                switch(playerLocationH)
                {
                    case 1:     //grid[0][1]
                    {
                        //Easy enemy
                        System.out.println("A wild Roach appRoaches!");
                        return 1;
                    }
                    case 2:     //grid[0][2]
                    {
                        //Easy enemy
                        System.out.println("A Golem blocks your path!");
                        return 2;
                    }    
                }
            }
            case 1:
            {
                switch(playerLocationH)
                {
                    case 0:     //grid[1][0]
                    {
                        //Easy enemy
                        System.out.println("A wild Roach appRoaches!");
                        return 1;
                    }
                    case 2:     //grid[1][2]
                    {
                        //Normal enemy
                        System.out.println("A Flying Bat swings in!");
                        return 3;
                    }  
                    case 3:     //grid[1][3]
                    {
                        //Normal enemy
                        System.out.println("A Dancing Skeleton shimmies in!");
                        return 4;
                    }
                }
            }
            case 2:
            {
                switch(playerLocationH)
                {
                    case 0:     //grid[2][0]
                    {
                        //Easy enemy
                        System.out.println("A Golem blocks your path!");
                        return 2;
                    }
                    case 1:     //grid[2][1]
                    {
                        //Normal enemy
                        System.out.println("A Flying Bat swings in!");
                        return 3;
                    }
                    case 2:     //grid[2][2]
                    {
                        //Normal enemy
                        System.out.println("A Dancing Skeleton shimmies in!");
                        return 4;
                    }  
                    case 3:     //grid[2][3]
                    {
                        //Normal enemy
                        System.out.println("A gross Goblin approaches. Yuck!");
                        return 5;
                    }
                    case 4:     //grid[2][4]
                    {
                        //Special event
                        System.out.println("A wild Roach appRoaches!");
                        return 1;
                    }
                }
            }
            case 3:     
            {
                switch(playerLocationH)
                {
                    case 1:     //grid[3][1]
                    {
                        //Normal enemy
                        System.out.println("A gross Goblin approaches. Yuck!");
                        return 5;
                    }
                    case 2:     //grid[3][2]
                    {
                        //Normal enemy
                        System.out.println("A frothing Hellhound jumps in the way! Stay doggy!");
                        return 6;
                    }  
                    case 3:     //grid[3][3]
                    {
                        //Hard enemy
                        System.out.println("A Drunken Dwarf staggers into you!");
                        return 8;
                    }
                }
            }
            case 4:
            {
                switch(playerLocationH)
                {
                    case 1:     //grid[4][1]
                    {
                        //Special event
                        System.out.println("You found a key, but a Troll steals it away!");
                        key = true;
                        return 9;
                    }
                    case 2:     //grid[4][2]
                    {
                        //Hard enemy
                        System.out.println("A devilish Demon blocks your path!");
                        return 7;
                    }  
                    case 3:     //grid[4][3]
                    {
                        //Hard enemy
                        System.out.println("A devilish Demon blocks your path!");
                        return 7;
                    }
                }
            }
            default: return 8;
        }
    }
}
