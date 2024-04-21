package hex.platform.view.menu;

public enum Menu {

    GAME(new MenuItem[] {
            MenuItem.NEW_GAME,
            MenuItem.SEPARATOR,
            MenuItem.RULE_GAME,
            MenuItem.HISTORY_GAME,
            MenuItem.SEPARATOR,
            MenuItem.SAVE_GAME,
            MenuItem.LOAD_GAME,
            MenuItem.SEPARATOR,
            MenuItem.QUIT
    });

    private final MenuItem[] items;

    Menu(MenuItem[] items) {
        this.items = items;
    }

    // REQUESTS

    public MenuItem[] getItems() {
        return this.items;
    }


}
