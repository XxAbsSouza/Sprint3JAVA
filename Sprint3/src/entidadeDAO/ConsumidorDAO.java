package entidadeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import entidade.Consumidor;

public class ConsumidorDAO {
    //inserir, pesquisar, alterar e excluir
    private PreparedStatement ps;
    private String sql;
    private Conexao conexao;

    public ConsumidorDAO() {
        conexao = new Conexao();
    }

    //Inserir
    public void inserir(Consumidor consumidor) {
        sql = "INSERT INTO tb_consumidor(id_consumidor, nome_consumidor, nome_usuario) values(?, ?, ?)";

        try (Connection connection = conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, consumidor.getId());
            ps.setString(2, consumidor.getNomeConsumidor());
            ps.setString(3, consumidor.getNomeUsuario());
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar um consumidor " + "\n" + e);
        }
    }
    

}
