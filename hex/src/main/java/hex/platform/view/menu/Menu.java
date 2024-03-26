package hex.platform.view.menu;

public enum Menu {

    GAME(new MenuItem[] {
            MenuItem.NEW_GAME,
            MenuItem.JOIN_GAME,
            MenuItem.SEPARATOR,
            MenuItem.RULE_GAME,
            MenuItem.SEPARATOR,
            MenuItem.SAVE_GAME,
            MenuItem.LOAD_GAME
    }),
    OTHER(new MenuItem[] {
            MenuItem.ABOUT_OTH,
            MenuItem.SEPARATOR,
            MenuItem.LOG_OTH
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
