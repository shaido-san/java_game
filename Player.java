import java.util.Scanner;

public class Player extends Person {
    public Integer money; //持ってい所持金

    public Player(Integer start_money){
        super();
        this.money = start_money;
    }
    //お金をかける
    public Integer bet(Integer min_bet){
        
        System.out.println("==========");
        Scanner sc = new Scanner(System.in);
        Integer input;
        if(this.money <= min_bet){
            System.out.println("金足りないよ。全部かけるね。");
            input = this.money;
            return input;
        }
        while(true){
            System.out.print("賭け金を入力してください");
            System.out.print("(現在の所持金:" + this.money + ", 最小賭け金：" + min_bet +")->");
            input = sc.nextInt();
            if(this.money < input){
                System.out.println("サラ金行ってこい");
            }
            else if(min_bet > input){
                System.out.println("最小金額以上の賭け金にしてください");
            }
            else{
                this.money -= input;
                break;
            }
        }
        return input;
    }

    //一枚引くかどうか選択する
    //return 1->引く、return 2->引かない
    public Integer action(){
        Scanner sc = new Scanner(System.in);
        Integer input;
        while(true){
        System.out.print("どうする？（１：引く ２：引かない）->");
        input = sc.nextInt();
        if(input == 1 || input == 2){
            break;
        }
    else{
        System.out.println("１か２を入力してください");
        }
    }
    return input;

 } 
}
