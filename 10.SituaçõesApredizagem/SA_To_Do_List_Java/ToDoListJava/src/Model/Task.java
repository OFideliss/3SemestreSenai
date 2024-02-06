package Model;

public class Task {
    // ================Atributos================
    String descricaoTarefa;
    boolean done;

    // ================Construtor================
    public Task(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
        this.done = false;
    }

    // ================Get and Set================
    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
