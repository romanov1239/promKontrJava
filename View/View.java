package View;

import Presenter.Presenter;

public interface View {
    void print(String outString);
    void start();
    void setPresenter(Presenter presenter);

    String read(String msg);
    Integer readInt(String msg);
    Boolean readBool(String msg);
}
