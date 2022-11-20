package week1Ex4;

public class Monster 
{
	private String name;
	private int hp;
	
	Monster(String name, int hp)
	{
		this.name = name;
		this.hp = hp;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public int getHP()
	{
		return hp;
	}
	
	public void setHP(int newHP)
	{
		this.hp = newHP;
	}
}
