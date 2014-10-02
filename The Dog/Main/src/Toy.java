/**
 *toy
 */
public class Toy {
    public int hp;
    public boolean exist;

    public Toy()
    {
        hp=50;
        exist = false;
    }

    public void buy(Player player)
    {
        if(player.money>=5) {
            hp = 50;
            exist = true;
            player.money -= 5;
        }
        else{
            System.out.println("Ты не можешь купить игрушку.");
        }
    }

    public void play(int hour)
    {
        hp-=hour;
        if (hp<=0)
        {
            exist=false;
            System.out.println("Пёс окончательно порвал свою игрушку");
        }
    }
}
