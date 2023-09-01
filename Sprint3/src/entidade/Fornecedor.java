package entidade;

public class Fornecedor {
    private int id;
    private String nome, telefone, email, cnpj;
    public Fornecedor(int id, String nome, String telefone, String email, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
    }
    public Fornecedor(int id, String nome, String email, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    
}
