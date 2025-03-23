package at.fh.sem4.ea.game.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipDTO {
    private int x;
    private int y;
    private boolean isSunk;
    private Long gameid;
    private Long playerid;
}