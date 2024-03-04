package hex.game.player;

public class Player {

    // Attributs

    private final PlayerName name;
    private final PlayerType type;

    // Constructeurs

    public Player(PlayerName name, PlayerType type) {
        this.name = name;
        this.type = type;
    }

    // Requêtes

    public PlayerName getName() {
        return name;
    }

    public PlayerType getType() {
        return type;
    }

    // Commandes

}
