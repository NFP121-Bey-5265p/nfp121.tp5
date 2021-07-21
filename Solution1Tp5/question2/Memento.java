package question2;
import java.util.List;

class Memento {
    private final List<String> state;

    public Memento(List<String> stateToSave) {
        state = stateToSave;
    }

    public List<String> getSavedState() {
        return state;
    }
}

