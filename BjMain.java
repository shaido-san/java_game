import java.util.Scanner;

public class BjMain{
    public static Integer start_money = 100000; //初期金額
    public static Integer goal_money = 500000; //目標金額
    public static Integer start_min_bet = 1000; //開始時の最小賭け金
    public static Double mul_min_bet = 2.0; //最小賭け金の最大増加率

    public static void title(){
        System.out.println("=============");
        System.out.println("ブラックジャックゲーム");
        System.out.println("エンターキーを押してね");
        System.out.println("============");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
    public static void print_dealer_turn(Player player, Dealer dealer){
        System.out.println(("========="));
        System.out.println("ディーラーの点数：" + dealer.get_point());
        System.out.print("ディーラー：");
        dealer.print_hands();
        System.out.println();
        System.out.print("プレイヤー");
        player.print_hands();
        System.out.println("プレイヤの点数：" + player.get_point());
    }
    public static void print_player_turn(Player player, Dealer dealer){
        System.out.println("===========");
        System.out.print("ディーラー：");
        dealer.print_hands();
        System.out.println(); //改行しているだけ
        System.out.print("プレイヤー：");
        player.print_hands();
        System.out.println("現在の点数：" + player.get_point());
    }

    public static Integer play_bj(Deck deck, Player player, Dealer dealer){
    Scanner sc = new Scanner(System.in);
        //2枚ずつ配る
        player.draw(deck, true);
        player.draw(deck, true);
        dealer.draw(deck, true);
        dealer.draw(deck, false);

        Integer result = -1; //←０：負け, １：勝ち, 2:大勝ち, 3:引き分け
        while(true){
            print_player_turn(player, dealer); //盤面を表示
            if(player.get_point() > 21){
                System.out.println("21を超えました。You lose");
                sc.nextLine();
                return 0;
            }
            Integer c = player.action();       //カードを引くか選択を行う
            if(c == 1){
                player.draw(deck, true);
            }
            else{
                break;
            }
    }
    //ディーラーのターン
    dealer.hands.get(1).is_open = true; //ディーラーの2枚目のカードを表向きにしている。get(1)は配列の二個目という意味。
    while(true){
        print_dealer_turn(player, dealer); //盤面を表示
        if(dealer.get_point() > 21){
            if(player.get_point() == 21){
                System.out.println("ピッタリボーナス、勝ちよ");
                sc.nextLine();
                return 1;
            }
            else{
                System.out.println("ディーラーがバースト。You win");
                sc.nextLine();
                return 1;
            }
        }
        Integer c = dealer.action(player.get_point()); //ディーラーが引くかやめるか選択
        if(c == 1){
            System.out.println("ディーラーももう一枚引きます");
            dealer.draw(deck, true); //もう一枚引く
            sc.nextLine();
        }
        else if(c == 2){
            break;
        }
    }

    //勝敗判定
    if(player.get_point() > dealer.get_point()){
        if(player.get_point() == 21){
            System.out.println("21ちょうどで勝利 ピッタリボーナス");
            sc.nextLine();
            result = 2;
        }
        else{
            System.out.println("あなたの勝ちです");
            sc.nextLine();
            result = 1;
        }
    }
    else if(player.get_point() < dealer.get_point()){
        System.out.println("ディーラーが勝ちました。");
        sc.nextLine();
        result = 0;
    }
    else{
        System.out.println("引き分けです。");
        sc.nextLine();
        result =3;
    }
    return result;
}
   public static void payout(Integer result, Player player, Integer bet_money){
    if(result == 1){
        player.money += bet_money * 2;
    }
    else if(result == 2){
        player.money += bet_money * 3;
    }
    else if(result == 3){
        player.money += bet_money * 1;
    }
   }
    public static void set_level(Dealer dealer, Player player){
        if(player.money <= goal_money / 4){
            dealer.level = 1;
        }
        else if(player.money <= goal_money / 2){
            dealer.level = 2;
        }
        else if(player.money <= goal_money){
            dealer.level = 3;
        }
    }

    public static void reset(Player player, Dealer dealer, Deck deck){
        player.reset();
        dealer.reset();
        deck.reset();
    }


    public static void run(){
        Deck deck = new Deck();
        Player player = new Player(start_money);
        Dealer dealer = new Dealer();

        title();  //タイトル画面を表示
        Integer min_bet = start_min_bet;
        while(true){
        Integer bet_money = player.bet(min_bet);        //掛金を指定
        deck.shuffle();                                 //シャッフル
        Integer result = play_bj(deck, player, dealer); //1ゲームプレイする
        payout(result, player, bet_money);            //結果に応じてお金を増やす
        if(player.money <= 0){
            System.out.println("目の前が真っ暗になった");
            break;
        }
        else if (player.money >= goal_money){
            System.out.println("君は勝ち組だ");
        }
        reset(player, dealer, deck);                    //オブジェクトをリセットする
        set_level(dealer, player);                      //ディーラーのレベルを変更
        //min_bet = (int)(min_bet * mul_min_bet);          //最小掛金を倍増
        }


    }
    public static void main(String... args){
        run();
    }
}