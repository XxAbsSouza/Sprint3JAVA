package entidade;

public class MeiodoFeedback {
    private int id;
    private String nomeMeio;

    public MeiodoFeedback(int id, String nomeMeio) {
        this.id = id;
        this.nomeMeio = nomeMeio;
    }
    
    public MeiodoFeedback(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomeMeio() {
        return nomeMeio;
    }
    public void setNomeMeio(String nomeMeio) {
        this.nomeMeio = nomeMeio;
    }

    
}
