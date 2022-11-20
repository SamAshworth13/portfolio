package week1Ex4;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class DungeonCrawler 
{

	public static void main(String[] args) 
	{
		Monster skeleton = new Monster("Skeleton", 2);
		Monster goblin = new Monster("Goblin", 5);
		Monster witch = new Monster("Witch", 8);
		Monster dragon = new Monster("Dragon", 10);
		Player player1 = new Player();
		ArrayList<Monster> monsterDeck = new ArrayList<Monster>();
		monsterDeck.add(skeleton);
		monsterDeck.add(skeleton);
		monsterDeck.add(skeleton);
		monsterDeck.add(skeleton);
		monsterDeck.add(goblin);
		monsterDeck.add(goblin);
		monsterDeck.add(goblin);
		monsterDeck.add(witch);
		monsterDeck.add(witch);
		monsterDeck.add(dragon);
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		String answer;
		boolean carryOn = true;
		Monster chosenMonster;
		int roundCount = 1;
		
		System.out.println("Welcome to the Dungeon. Would you like to fight a monster? (y/n)");
		answer = in.nextLine();
		
		while (carryOn)
		{
			if (answer.equals("n"))
			{
				carryOn = false;
				System.out.println("Game over. You had " + player1.getHP() + " health remaining. Your score was " + player1.getScore() + ".");
			}
			else if (answer.equals("y"))
			{
				chosenMonster = monsterDeck.get(rand.nextInt(monsterDeck.size()));
				player1.setHP(player1.getHP() - chosenMonster.getHP());
				player1.setScore(player1.getScore() + chosenMonster.getHP());
				System.out.println("Monster " + roundCount + " is a " + chosenMonster.getName() + ". It deals " + chosenMonster.getHP() +
						" damage to you, leaving you with " + player1.getHP() + " health. \n");
				
				if (player1.getHP() > 0)
				{
					System.out.println("Would you like to fight another monster? (y/n)");
					answer = in.nextLine();
					roundCount++;
				}
				else
				{
					System.out.println("You have lost all of your health. You died to a " + chosenMonster.getName() + ". You lose.");
					carryOn = false;
				}
				
			}
			else
			{
				System.out.println("Please answer by entering either 'y' or 'n'");
				answer = in.nextLine();
			}
		}
		
		in.close();
		
		
	}

}
