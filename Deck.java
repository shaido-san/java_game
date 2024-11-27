import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public ArrayList<Card> cards;
    public Integer num; //山札の残り枚数

    //コンストラクタ コンストラクタは基本クラスに必要。いらないのはクラスに変数がないもののみ。
    Deck(){
        this.cards = new ArrayList<>();
        String suits = "shdc";
        for(int i = 0; i < 52;i++){
            this.cards.add(new Card(i / 4 + 1, suits.charAt(i % 4))); //s~cの中から一つを選んでいる。
        }
        this.num = 52; //山札の初期値
        }
        //山札を混ぜる関数
        public void shuffle(){
             Collections.shuffle(this.cards);
        }

        // 一枚引く
        public Card draw(){
            Card ret = this.cards.get(0);
            this.cards.remove(0);
            this.num -= 1;
            return ret;
        }

        public void reset(){
            this.cards = new ArrayList<>();
            String suits = "shdc";
            for(int i = 0; i < 52;i++){
            this.cards.add(new Card(i / 4 + 1, suits.charAt(i % 4))); //s~cの中から一つを選んでいる。
        }
            this.num = 52;
            this.shuffle();
        }

        //山札の中身を表示する（show_modeがtrueの場合、強制的に表向きで表示）
        public void print(Boolean show_mode){
            System.out.print("[");
            for(int i = 0; i < this.num; i++){
                if(show_mode == true){
                    boolean temp = this.cards.get(i).is_open;
                    this.cards.get(i).is_open = true;
                    this.cards.get(i).print();
                    this.cards.get(i).is_open = temp;

                }
                else{
                    this.cards.get(i).print();
                }
                System.out.print(",");
            }
            System.out.println("]");
        }

}
