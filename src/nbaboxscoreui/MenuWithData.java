package nbaboxscoreui;

public abstract class MenuWithData<T> extends Menu {
    T data;
    MenuWithData(Menu prevMenu) {
        super(prevMenu);
    }

    public void switchHere(T data) {
        this.data = data;
        Menu.switchMenu(this);
    }
}
