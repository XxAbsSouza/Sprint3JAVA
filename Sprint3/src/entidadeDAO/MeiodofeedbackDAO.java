package entidadeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import conexao.Conexao;
import entidade.MeiodoFeedback;

public class MeiodofeedbackDAO {
    private PreparedStatement ps;
    private String sql;
    private Conexao conexao;
    private ResultSet rs;

    public MeiodofeedbackDAO() {
        conexao = new Conexao();
    }

    //inserir
    public void inserir(MeiodoFeedback meioFeedback) {
        sql = "INSERT INTO tb_feedback_meio(id_feedback_meio, nome_meio) values(?, ?)";

        try (Connection connection = conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, meioFeedback.getId());
            ps.setString(2, meioFeedback.getNomeMeio());
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
                System.out.println("Erro ao cadastrar uma plataforma de feedback " + "\n" + e);
        }
    }
}
