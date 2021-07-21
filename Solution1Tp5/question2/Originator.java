package question2;
import java.util.List;

class Originator {
    private List<String> state;

    public void set(List<String> newState) {
        System.out.println("Originator: current version \n"+ newState);
        this.state = newState;
    }

    public Memento saveToMemento() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(this.state);
    }

    public List<String> restoreFromMemento(Memento memento) {
        this.state = memento.getSavedState();
        System.out.println("Originator: previous article saved in Memento.");
        return state;
    }
}