public class Dealer extends Person {
    public Integer level; //ディーラーの強さ(1~3)

    public Dealer(){
        super();
        this.level = 1;
    }

    //一枚引くか引かないか決定する
    //return 1->引く 2->引かない
    public Integer action(Integer player_point){
        Integer boarder = 0; //手札の合計点がboarder以上なら引かない
        if(this.level == 1){
            boarder = 15;
        }
        else if(this.level == 2){
            boarder = 17;
        }
        else if(this.level == 3){
            boarder = player_point;
        }

        if(this.get_point() >= boarder){
            return 2;
        }
        else{
            return 1;
        }
    }
}
