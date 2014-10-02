/**
 * игрок
 */
public class Player {
    public int food;
    public int money;

    public Player()
    {
        food = 100;
        money = 100;
    }

    public boolean ok()
    {
        boolean res = false;
        if ((food>0)|(money>0))
            res=true;
        return res;
    }

    public void buyFood( Time time)
    {
        if (food == 0 & money>0) {
            System.out.println("У тебя закончилась еда. Хочешь купить? (Введи число от 0 до" + money + " )");
            int n = The_Dog.tryRead(money);
            if (n > 0) {
                food += n;
                money -= n;
                time.add(1);
            }
        }
    }

    public void showResureces()
    {
        String s = "money: "+Integer.toString(money);
        System.out.println(s);
        s="food: "+Integer.toString(food);
        System.out.println(s);
    }
}
