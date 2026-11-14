public class Player {
    private final String name;
    private final CellState mark;

    public Player(String name, CellState mark){
        this.name = name;

        if(mark == CellState.EMPTY){
            throw new IllegalArgumentException("Player mark can not be EMPTY");
        }
        this.mark = mark;
    }

    public String getName(){
        return name;
    }

    public CellState getMark(){
        return mark;
    }
}
