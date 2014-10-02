import java.util.*;

/**
 * собачка
 */
public class Dog {
    public double lovely;               //коффициент доброты
    public double wicked;               //коэффициент злобности
    public double flair;                //чутьё
    public int hungry;                  //маркер голода
    public boolean sleeppy;             //маркер желания поспать
    public boolean frisky;              //маркер желания поиграть
    public boolean affectionate;        //маркер желания ласки
    public boolean sleep;               //спит

    //private int asked;

    public Dog()
    {
        lovely = 0;
        wicked = 0;
        flair = 0;
        hungry = 0;
        sleeppy = false;
        frisky = false;
        affectionate = false;
        sleep = false;
    }

    public void eat(Bowl bowl, Time time, Player player)
    {
        if(bowl.foodCount == 0)
        {
            ask(0,4, bowl, time, player);
            return;
        }
        time.add(1);
        if(hungry>bowl.foodCount)
        {
            hungry -= bowl.foodCount;
            bowl.foodCount = 0;
            System.out.println("Твоя собака не наелась");
        }
        else
        {
            bowl.foodCount-=hungry;
            hungry=0;
            System.out.println("Твоя собака наелась");
        }
        return;
    }

    public void ask(int asked, int k, Bowl bowl, Time time, Player player)
    {
        asked+=1;
        System.out.println("Твоя собака просит есть");
        asked+=1;
        Scanner sc = new Scanner(System.in);
        System.out.println("накормить? (Введи число от 0 до "+player.food+" )");
        int answer = The_Dog.tryRead(player.food);
        if (answer > 0)
        {
            lovely +=1;
            bowl.foodCount+=answer;
            player.food-=answer;
            time.add(1);
            eat(bowl, time, player);
            return;
        }
        else {
            if (asked >= k) {
                wicked += 1;
                time.add(1);
                return;
            }
            else
            {
                time.add(2);
                ask(asked, k, bowl, time, player);
                return;
            }
        }
    }

    public void train(Time time)
    {
        Random rand = new Random();
        int upTime = rand.nextInt(6);
        time.add(upTime);
        double up = upTime*0.01;
        flair+=up;
        String s = "Ты тренируешь своего пса. Его навыки охранника повышаются";
        System.out.println(s);
    }

    public void caress(int hour, Time time)
    {
        time.add(hour);
        double up = hour*0.1;
        lovely+=up;
        String s = "Ты гладишь пса.";
        System.out.println(s);
    }

    public  void play(Time time, Player player, Toy toy)
    {
        if (!toy.exist)
        {
            System.out.println("У пса нет игрушки. Купить? (1-да)");
            //Scanner sc = new Scanner(System.in);
            int answer = The_Dog.tryRead(1);
            if (answer==1)
            {
                toy.buy(player);
            }
        }
        if(toy.exist){
            Random rand = new Random();
            int upTime = rand.nextInt(6);
            toy.play(upTime);
            time.add(upTime);
            System.out.println("Ты играешь с псом. Когда вы прекращаете, оказывается, что прошло уже "+upTime+" часов.");
        }
    }

    public void sleep(Time time)
    {
        Random rand = new Random();
        int upTime = rand.nextInt(6);
        time.add(upTime);
        String s = "Твой пёс решил отдохнуть. Он проспал "+Integer.toString(upTime)+" часов.";
        System.out.println(s);
    }

    public void waiting(Player player, Time time, Toy toy)
    {
        System.out.println("Твой пёс ждёт, что ты будешь делать.");
        System.out.println("1-играть с псом\n2-тренировать пса\n3-пусть сам чем-нибудь займётся");
        //Scanner sc = new Scanner(System.in);
        int answer = The_Dog.tryRead(3);
        switch (answer)
        {
            case 1: play(time, player,toy); break;
            case 2: train(time); break;
            case 3: time.add(3); break;
        }
    }
}
