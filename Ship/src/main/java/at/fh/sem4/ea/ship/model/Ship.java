package at.fh.sem4.ea.ship.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int x;
    private int y;

    private boolean isSunk;

//    @ManyToOne
//    @JoinColumn(name = "game_id")
    private Long gameid;

//    @ManyToOne
//    @JoinColumn(name = "player_id", nullable = false)
    private Long playerid;
}