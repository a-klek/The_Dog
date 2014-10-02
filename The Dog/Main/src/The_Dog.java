import java.util.*;

/**
 * main
 */
public class The_Dog {
    public static void main(String[] args)
    {
        String text1 = "Вчера ты устроился на работу. Обещают неплохо платить, но первая зарплата только через месяц.";
        String text2 = "А еды и денег у тебя не много. Да ещё друзья подарили тебе здоровенного пса, о котором нужно заботиться...";
        String text3 = "Продержишься ли ты до зарплаты?";
        System.out.println(text1);
        System.out.println(text2);
        System.out.println(text3);

        Player player = new Player();
        Time time = new Time();
        Dog dog = new Dog();
        Bowl bowl = new Bowl();
        Toy toy = new Toy();

        int thisDay = 1;

        while (player.ok() & time.day < 30)
        {
            time.showTime();
            player.showResureces();
            Random rand = new Random();
            dog.hungry = rand.nextInt(100);
            dog.affectionate = rand.nextBoolean();
            dog.sleeppy = rand.nextBoolean();
            dog.frisky = rand.nextBoolean();
            dog.sleep = false;
            while (player.ok() & time.day < 30 & (thisDay==time.day&time.hour<23))
            {
                if (dog.hungry>0)
                {
                    dog.eat(bowl,time, player);
                }
                player.buyFood(time);
                randomEvent(player,dog,time);
                player.buyFood(time);
                time.showTime();
                player.showResureces();

                if (dog.frisky)
                {
                    dog.play(time,player, toy);
                }
                randomEvent(player,dog,time);
                player.buyFood(time);

                time.showTime();
                player.showResureces();

                dog.waiting(player,time,toy);
                player.buyFood(time);

                time.showTime();
                player.showResureces();

                if (dog.sleeppy)
                {
                    dog.sleep(time);
                    time.showTime();
                    player.showResureces();
                }
            }

            dog.sleep=true;
            randomEvent(player,dog,time);
            player.buyFood(time);
            time.newDay();
        }

        String finish="";
        player.buyFood(time);
        if (player.ok()&time.day>30)
        {
            finish="Поздравляем, ты выиграл";
        }
        else
        {
            finish="Ты проиграл";
        }
        System.out.println(finish);
        player.showResureces();
    }

    public static void randomEvent(Player player, Dog dog, Time time)
    {
        Random rand = new Random();
        int r = rand.nextInt(20);
        if ((r%3 != 1)&dog.sleep)
        {
            eventThief(player, dog, time);
            time.add(3);
            return;
        }

        if (r%5==0 & time.day>10)
        {
            eventAunt(player, dog);
            time.add(5);
            return;
        }
        if (r%4==0 & time.day>15)
        {
            eventBills(player);
            time.add(2);
            return;
        }
    }

    private static void eventThief(Player player, Dog dog, Time time)
    {
        System.out.println("Ночью в твой дом проник вор.");
        String s;
        Random rand = new Random();
        double thiefSkill = rand.nextInt(10)*time.day/30;                 //скилл вора
        if(thiefSkill > dog.flair)
        {
            int m = rand.nextInt(20);
            if (player.money-m<0)
                m=player.money;
            player.money-= m;
            int f = rand.nextInt(20);
            if (player.food-f<0)
                f=player.food;
            player.food-=f;
            s = "К сожалению, вы с псом крепко спали. Вор унёс "+Integer.toString(m)+" денег и "+Integer.toString(f)+" еды";
        }
        else
        {
            s="К счастью, твой пёс учуял чужака. Вор был пойман.";
        }
        System.out.println(s);
    }

    private static void eventAunt(Player player, Dog dog)
    {
        System.out.println("К тебе внезапно приехала тётя.");
        String s;
        Random rand = new Random();
        if(dog.lovely>dog.wicked)
        {
            s="Вы мило беседуете. Но стоило появиться псу, как у неё возникает непреодолимое желание покормить его.\n";
            int f = rand.nextInt(20);
            if (player.food-f<0)
                f=player.food;
            player.food-=f;
            s+="Она скормила ему"+Integer.toString(f)+"еды!";
        }
        else
        {
            s="Пёс напал на неё! Тёте удалось убежать, но она выставила тебе счёт за моральный ущерб.\n";
            int m = rand.nextInt(20);
            if (player.money-m<0)
                m=player.money;
            player.money-= m;
            s+="Тебе пришлось заплатить ей"+Integer.toString(m)+"денег. Мда, ну и тётя у тебя...";
        }
        System.out.println(s);
    }

    private static void eventBills(Player player)
    {
        System.out.println("Сегодня пришли счета. Надо платить...");
        Random rand = new Random();
        int m = rand.nextInt(20);
        if (player.money-m<0)
            m=player.money;
        player.money-= m;
        String s="Тебе пришлось отдать"+Integer.toString(m)+"денег.";
        System.out.println(s);
    }

    public static int tryRead(int lastVar)
    {
        Scanner sc = new Scanner(System.in);
        int res;
        while (true)
        {
            String str = sc.nextLine();
            try {
                res=Integer.parseInt(str);

                if (res>=0 & res<=lastVar) return res;
            }
            catch (NumberFormatException e){
                System.out.println("Некорректная команда");
            }
        }
    }
}
