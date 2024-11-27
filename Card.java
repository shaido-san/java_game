public class Card {
    public Integer num; //トランプの数字
    public Character suit; //トランプのマーク s:スペード、 h:ハート、 d:ダイヤ、 c:クローバー
    public Boolean is_open; //true:表向き、 false:裏向き
    //コンストラクタ
    Card(Integer num, Character suit){
        this.num = num;
        this.suit = suit;
        this.is_open = false;
    }
    ///点数をリターンする関数
    public Integer get_point(){
        if(this.num > 10){
            return 10;
        }
        else{
            return this.num;
        }
    }
    //トランプの内容を表示
    public void print(){
        if(this.is_open == false){
            System.out.print("??");
            return;
        }
        
        if (this.num == 1){
            System.out.print("A");
        }
        else if(this.num < 10){
            System.out.print(this.num);
        }
        else if(this.num == 10){
            System.out.print("T");
        }
        else if(this.num == 11){
            System.out.print("J");
        }
        else if(this.num == 12){
            System.out.print("Q");
        }
        else if(this.num == 13){
            System.out.print("K");
        }
        else{
            System.out.print("Joker");
        }
        System.out.print(this.suit);
    }
}
