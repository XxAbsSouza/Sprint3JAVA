package entidade;

public class MeiodoFeedback {
    private int id_Meio;
    private String nomeMeio;

    public MeiodoFeedback(int id, String nomeMeio) {
        this.id_Meio = id;
        this.nomeMeio = nomeMeio;
    }
    
    public MeiodoFeedback(int id) {
        this.id_Meio = id;
    }

    public int getId_Meio() {
        return id_Meio;
    }
    public void setId(int id) {
        this.id_Meio = id;
    }
    public String getNomeMeio() {
        return nomeMeio;
    }
    public void setNomeMeio(String nomeMeio) {
        this.nomeMeio = nomeMeio;
    }

	@Override
	public String toString() {
		return nomeMeio;
	}
    
    
    
}
