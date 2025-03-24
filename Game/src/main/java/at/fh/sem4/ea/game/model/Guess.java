package at.fh.sem4.ea.game.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Guess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int x;
    private int y;
    private boolean hit;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
