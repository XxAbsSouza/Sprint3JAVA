package menu;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;
import java.util.List;

import entidade.Consumidor;
import entidade.Feedback;
import entidade.Fornecedor;
import entidade.MeiodoFeedback;
import entidadeDAO.ConsumidorDAO;
import entidadeDAO.FeedbackDAO;
import entidadeDAO.FornecedorDAO;
import entidadeDAO.MeiodofeedbackDAO;
import excecao.OpcaoInvalidaException;

public class Menu {
    public void menu() {
        int opcao = 0;

        do {
            try {
                opcao = parseInt(showInputDialog(gerarMenu()));
                if (opcao < 1 || opcao > 9) {
                    throw new OpcaoInvalidaException("Opção inválida");
                } else {
                    switch (opcao) {
                        case 1:
                            cadastrarFeedback();
                            break;
                        case 2:
                            cadastrarConsumidor();
                            break;
                        case 3:
                            cadastrarFornecedor();
                            break;
                        case 4:
                            cadastrarMeioFeedback();
                            break;
                        case 5:
                            listarMeioFeedback();
                            break;
                        case 6:
                            pesquisarFeedback();
                            break;
                        case 7:
                            alterarFeedback();
                            break;
                        case 8:
                            excluirFeedback();
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                showMessageDialog(null, "Você deve digitar um número\n" + e);
            } catch (OpcaoInvalidaException e) {
                showMessageDialog(null, e.getMessage());
            }
        } while (opcao != 9);
    }

    private void excluirFeedback() {
        FeedbackDAO fDao = new FeedbackDAO();
        int id = parseInt(showInputDialog("ID"));
        Feedback fb = fDao.pesquisar(id);
		if (fb == null) {
			showMessageDialog(null, "Feedback não encontrado.");
		} else {
			fDao.excluir(id);
			showMessageDialog(null, "Feedback removido com sucesso.");
		}
    }

    private void alterarFeedback() {
        FeedbackDAO fdao = new FeedbackDAO();
        MeiodofeedbackDAO mfDAO = new MeiodofeedbackDAO();
        int id = parseInt(showInputDialog("ID"));
        int novoIdMeio;
        Feedback fb = fdao.pesquisar(id);
		if (fb == null) {
			showMessageDialog(null, "Feedback não encontrado.");
		} else {
            List<MeiodoFeedback> lista = mfDAO.listar();
            String aux = "";
            String nomeNova = "";
            for (MeiodoFeedback mf : lista) {
                aux += mf.getId_Meio() + " " + mf.getNomeMeio() + "\n";
            }
            novoIdMeio = parseInt(showInputDialog("Id da nova plataforma: " + aux));
            for (MeiodoFeedback mf : lista) {
                if(mf.getId_Meio() == novoIdMeio) {
                	nomeNova = mf.getNomeMeio();
                }
            }
            MeiodoFeedback novoMf = new MeiodoFeedback(0, "");
            novoMf.setId(novoIdMeio);
            novoMf.setNomeMeio(nomeNova);
            fb.setMeiodoFeedback(novoMf);
            System.out.println(fb);
            fdao.atualizar(fb);
		}
    }

    private void pesquisarFeedback() {
        FeedbackDAO fDao = new FeedbackDAO();
		int id = parseInt(showInputDialog("ID"));
		Feedback fb = fDao.pesquisar(id);
		if (fb == null) {
			showMessageDialog(null, "Feedback não encontrado.");
		} else {
			showMessageDialog(null, fb);
		}
    }

    private void listarMeioFeedback() {
        MeiodofeedbackDAO dao = new MeiodofeedbackDAO();
        List<MeiodoFeedback> lista = dao.listar();
        String aux = "";
		int count = 1;
		for (MeiodoFeedback meio : lista) {
			aux = aux + "\n\nPlataforma #" + count + "\n" + meio;
			count++;
		}
		showMessageDialog(null, aux);
    }

    private void cadastrarMeioFeedback() {
        int id = parseInt(showInputDialog("ID"));
        String nome = showInputDialog("Plataforma");

        MeiodoFeedback mf = new MeiodoFeedback(id, nome);
        MeiodofeedbackDAO dao = new MeiodofeedbackDAO();

        if (dao.pesquisar(mf)) {
			showMessageDialog(null, "Plataforma já existe, insira um diferente");
		} else{
			dao.inserir(mf);
		}
    }

    private void cadastrarFornecedor() {
        //int id, String nome, String telefone, String email, String cnpj
        int id = parseInt(showInputDialog("ID"));
        String nome = showInputDialog("Nome");
        String telefone = showInputDialog("Telefone");
        String email = showInputDialog("Email");
        String cnpj = showInputDialog("CNPJ");

        Fornecedor fornecedor = new Fornecedor(id, nome, telefone, email, cnpj);
        FornecedorDAO dao = new FornecedorDAO();

        if (dao.pesquisar(fornecedor)) {
			showMessageDialog(null, "Fornecedor já existe, insira um diferente");
		} else{
			dao.inserir(fornecedor);
		}
    }

    private void cadastrarConsumidor() {
        int id = parseInt(showInputDialog("ID"));

        Consumidor consumidor = new Consumidor(id, "");
        ConsumidorDAO dao = new ConsumidorDAO();

        if (dao.pesquisar(consumidor)) {
			showMessageDialog(null, "Consumidor já existe, insira um diferente");
		} else{
			String nomeP = showInputDialog("Nome Social");
			String nomeU = showInputDialog("Nome do Usuário");
			consumidor = new Consumidor(id, nomeP, nomeU);
			dao.inserir(consumidor);
		}
    }

    private void cadastrarFeedback() {
        MeiodofeedbackDAO mfDAO = new MeiodofeedbackDAO();
        ConsumidorDAO cDAO = new ConsumidorDAO();
        FeedbackDAO fDAO = new FeedbackDAO();

        Consumidor c;
        int id = parseInt(showInputDialog("ID"));
        int id_consumidor;

        if (fDAO.pesquisar(id) != null) {
            showMessageDialog(null, "Feedback já existe com esse id, insira um diferente");
        } else {
            List<MeiodoFeedback> lista = mfDAO.listar();
            String aux = "";
            for (MeiodoFeedback mf : lista) {
                aux += mf.getId_Meio() + " " + mf.getNomeMeio() + "\n";
            }
            String fb = showInputDialog("FeedBack: ");
            String data = showInputDialog("Data ");
            String post = showInputDialog("Link do Post ");
            int meioFb = parseInt(showInputDialog(aux));
            MeiodoFeedback mf = new MeiodoFeedback(meioFb);
            id_consumidor = parseInt(showInputDialog("Id Cliente"));
            c = new Consumidor(id_consumidor, "");
            Feedback feedback = new Feedback(id, fb, data, post, c, mf);
            fDAO.inserir(feedback);
        }
    }

    private String gerarMenu() {
		String menu = "CONTROLE DE EMPREGADOS - ESCOLHA UMA OPÇÃO\n";
		menu += "1. Cadastrar FeedBack\n";
		menu += "2. Cadastrar Consumidor\n";
		menu += "3. Cadastrar Fornecedor\n";
		menu += "4. Cadastrar Plataforma em que será retirado o FeedBack\n";
		menu += "5. Listar plataformas disponíveis\n";
		menu += "6. Pesquisar FeedBack\n";
		menu += "7. Alterar FeedBack\n";
		menu += "8. Excluir FeedBack\n";
		menu += "9. Finalizar\n";
		return menu;
	}
}
