import controller.Controller;
import model.Model;
import view.ControlPanel;
import view.View;

public class ThreadLab {

    public ThreadLab() {
        System.out.println("MyTask creado");
        new Controller();
    }

    public static void main(String[] args) {
        ThreadLab task = new ThreadLab();
    }

}