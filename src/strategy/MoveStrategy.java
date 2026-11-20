package strategy;

import model.enums.CellState;
import model.Board;
import model.Position;

public interface MoveStrategy {
    Position move(Board board, CellState mark);
}
