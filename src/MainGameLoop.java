public class MainGameLoop {

    public static void main(String[] args){
        Board.Manager boardOne = new Board.Manager(4,5);
        boardOne.fill((int) Math.random());
        Display.main();
        //System.out.println(boardOne);
    }
}