package entidadeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import conexao.Conexao;
import entidade.Feedback;

public class FeedbackDAO {

    private PreparedStatement ps;
    private String sql;
    private Conexao conexao;
    private ResultSet rs;

    public FeedbackDAO() {
        conexao = new Conexao();
    }

    //Inserir
    public void inserir(Feedback feedback) {
        sql = "INSERT INTO tb_feedback(id_feedback, feedback, data_feedback, post, id_consumidor, id_feedback_meio) values(?, ?, ?, ?, ?, ?)";

        try (Connection connection = conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, feedback.getId());
            ps.setString(2, feedback.getFeedback());
            ps.setString(3, feedback.getData());
            ps.setString(4, feedback.getPost());
            ps.setInt(5, feedback.getConsumidor().getId());
            ps.setInt(6, feedback.getMeiodoFeedback().getId());
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar um feedback " + "\n" + e);
        }
    }

    //pesquisar pelo id
    public Feedback pesquisar(int id) {
        Feedback feedback = null;
        //FIXME
        sql = "select \n" + //
                    "    e.nome, e.salario, d.nome as nome_departamento \n" + //
                    "FROM\n" + //
                    "    java_empregado e\n" + //
                    "inner JOIN\n" + //
                    "    java_departamento d\n" + //
                    "on\n" + //
                    "    e.id_departamento = d.id where e.id = ?";
            try (Connection connection = conexao.conectar()) {
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    double salario = rs.getDouble("salario");
                    String nomedp = rs.getString("nome_departamento");
                    Departamento dp = new Departamento(0, nomedp);
                    empregado = new Empregado(0, nome, salario, dp);
                }
                ps.close();
                rs.close();

            } catch (Exception e) {
                System.out.println("Erro ao pesquisar o empregado " + e);
            }
        //FIXME

            return feedback;
    }
}
