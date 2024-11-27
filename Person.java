import java.util.ArrayList;

public class Person {
   public ArrayList<Card> hands;
   public Integer num;  //持っている手札の枚数

   // コンストラクタ
   public Person() {
    this.hands = new ArrayList<>();
    this.num = 0;
   }
   public Integer get_point(){
    Integer ret = 0;
    for(int i = 0; i < this.num ;i++){
        ret += this.hands.get(i).get_point();
    }
    return ret;

   }
   // 一枚引く
   // is_open:true->表向きにしてひく、false->裏向きのまま引く
   public void draw(Deck deck, boolean is_open){
    Card card = deck.draw();
    card.is_open = is_open;
    this.num += 1;
    this.hands.add(card);
   }

   // リセットする
   public void reset(){
    this.hands = new ArrayList<>();
    this.num = 0;
   }
  
   //持っている手札をprintする
   public void print_hands(){
    System.out.print("[");
    for(int i = 0; i < this.num; i++){
        this.hands.get(i).print();
        if(i != this.num - 1){
            System.out.print(",");
        }
    }
    System.out.println("]");
   }

}
