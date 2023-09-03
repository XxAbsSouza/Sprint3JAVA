package entidadeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.Conexao;
import entidade.Consumidor;
import entidade.Feedback;
import entidade.MeiodoFeedback;

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
            ps.setInt(6, feedback.getMeiodoFeedback().getId_Meio());
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

        sql = "select \n" + //
                "    f.feedback, f.data_feedback, f.post, c.nome_usuario as nome_Usuario, fm.nome_meio as Plataforma\n"
                + //
                "FROM\n" + //
                "    tb_feedback f\n" + //
                "inner JOIN\n" + //
                "    tb_consumidor c\n" + //
                "on\n" + //
                "    f.id_consumidor = c.id_consumidor\n" + //
                "INNER JOIN\n" + //
                "   tb_feedback_meio fm\n" + //
                "on\n" + //
                "    f.id_feedback_meio = fm.id_feedback_meio\n" +
                "where f.id_feedback = ?";
        try (Connection connection = conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String feedBack = rs.getString("feedback");
                String data_feedback = rs.getString("data_feedback");
                String post = rs.getString("post");
                String nomeC = rs.getString("nome_Usuario");
                String plataforma = rs.getString("Plataforma");
                Consumidor consum = new Consumidor(0, nomeC);
                MeiodoFeedback mFeedback = new MeiodoFeedback(0, plataforma);
                feedback = new Feedback(0, feedBack, data_feedback, post, consum, mFeedback);
            }
            ps.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar o feedback " + e);
        }

        return feedback;
    }

    //Atualizar/alterar
    public void atualizar(Feedback feedback) {
    	System.out.println(feedback);
        sql = "UPDATE tb_feedback SET id_feedback_meio = ? where id_feedback = ?";
        try (Connection connection = conexao.conectar()) {
        		ps = connection.prepareStatement(sql);
            ps.setInt(2, feedback.getId());
            ps.setInt(1, feedback.getMeiodoFeedback().getId_Meio());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("erro ao atualizar dados " + e);
        }
    }

    //Excluir/Remover
    public void excluir(int id) {
            sql = "delete from tb_feedback where id_feedback = ?";
            try (Connection connection = conexao.conectar()) {
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
                ps.close();
            } catch (Exception e) {
                System.out.println("Erro ao excluir o feedback " + e);
            }
        }
}
