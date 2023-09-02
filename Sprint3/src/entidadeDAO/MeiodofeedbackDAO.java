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
    
    //listar as plataformas em que se encontram os feedbackas
    public List<MeiodoFeedback> listar() {
        List<MeiodoFeedback> lista = new LinkedList<>();
        sql = "select * from tb_feedback_meio order by id_feedback_meio";

        try (Connection connection = conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new MeiodoFeedback(rs.getInt("id_feedback_meio"), rs.getString("nome_meio")));
            }
            ps.close();
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("erro ao listar plataformas\n" + e);
        }
        return lista;

    }

    public boolean pesquisar(MeiodoFeedback mf) {

        boolean aux = false;
        sql = "SELECT * FROM tb_feedback_meio WHERE id_feedback_meio = ?";
        try (Connection connection = conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mf.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                aux = true;
            }
            ps.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar a Plataforma " + e);
        }

        return aux;
    }
}
