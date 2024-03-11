package hex.platform.view.menu;

public enum Menu {

    GAME(new MenuItem[] {
            MenuItem.NEW_GAME,
            MenuItem.SEPARATOR,
            MenuItem.RULE_GAME,
            MenuItem.SEPARATOR,
            MenuItem.SAVE_GAME,
            MenuItem.LOAD_GAME
    }),
    HISTORIC(new MenuItem[] {
            MenuItem.REDO_HIST,
            MenuItem.UNDO_HIST
    }),
    OTHER(new MenuItem[] {
            MenuItem.ABOUT_OTH,
            MenuItem.SEPARATOR,
            MenuItem.LOG_OTH
    });

    private final MenuItem[] items;

    private Menu(MenuItem[] items) {
        this.items = items;
    }

    // REQUESTS

    public MenuItem[] getItems() {
        return this.items;
    }


}
