package entidadeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import entidade.Fornecedor;

public class FornecedorDAO {
    private PreparedStatement ps;
    private String sql;
    private Conexao conexao;

    public FornecedorDAO() {
        conexao = new Conexao();
    }

    //inserir
    public void inserir(Fornecedor fornecedor) {
        sql = "INSERT INTO tb_fornecedor(id_fornecedor, nome_fornecedor, telefone, email, cpf) values(?, ?, ?, ?, ?)";

        try (Connection connection = conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, fornecedor.getId());
            ps.setString(2, fornecedor.getNome());
            ps.setString(3, fornecedor.getTelefone());
            ps.setString(4, fornecedor.getEmail());
            ps.setString(5, fornecedor.getCnpj());
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
                System.out.println("Erro ao cadastrar um fornecedor " + "\n" + e);
        }
    }
}
