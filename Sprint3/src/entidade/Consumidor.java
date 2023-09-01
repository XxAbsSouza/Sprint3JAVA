package entidade;

public class Consumidor {
    private int id;
    private String nomeConsumidor, nomeUsuario;
    public Consumidor(int id, String nomeConsumidor, String nomeUsuario) {
        this.id = id;
        this.nomeConsumidor = nomeConsumidor;
        this.nomeUsuario = nomeUsuario;
    }
    public Consumidor(int id, String nomeUsuario) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomeConsumidor() {
        return nomeConsumidor;
    }
    public void setNomeConsumidor(String nomeConsumidor) {
        this.nomeConsumidor = nomeConsumidor;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    
}
