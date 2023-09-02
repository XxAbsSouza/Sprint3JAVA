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
        Feedback fb = fdao.pesquisar(id);
		String novoFb;
		String novaData;
		if (fb == null) {
			showMessageDialog(null, "Feedback não encontrado.");
		} else {
			novoFb = showInputDialog("Novo nome");
			novaData = showInputDialog("Nova Data");
            List<MeiodoFeedback> lista = mfDAO.listar();
            String aux = "";
            for (MeiodoFeedback mf : lista) {
                aux += mf.getId() + " " + mf.getNomeMeio() + "\n";
            }
            int meioFb = parseInt(showInputDialog(aux));
            MeiodoFeedback mf = new MeiodoFeedback(meioFb, aux);
            fb.setFeedback(novoFb);
            fb.setData(novaData);
            fb.setMeiodoFeedback(mf);
            
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
			aux = aux + "\n\nPlataforma #" + count + meio;
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
			showMessageDialog(null, "Fornecedor já existe, insira um diferente");
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
        String nome = showInputDialog("Nome do Cliente");

        Consumidor consumidor = new Consumidor(id, nome);
        ConsumidorDAO dao = new ConsumidorDAO();

        if (dao.pesquisar(consumidor)) {
			showMessageDialog(null, "Departamento já existe, insira um diferente");
		} else{
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
                aux += mf.getId() + " " + mf.getNomeMeio() + "\n";
            }
            String fb = showInputDialog("FeedBack: ");
            String data = showInputDialog("Data ");
            int meioFb = parseInt(showInputDialog(aux));
            MeiodoFeedback mf = new MeiodoFeedback(meioFb);
            do {
                id_consumidor = parseInt(showInputDialog("Id Cliente"));
                c = new Consumidor(id_consumidor, "");
            } while (cDAO.pesquisar(c));
            String nomeConsumidor = showInputDialog("Nome do Cliente ");
            id_consumidor = parseInt(showInputDialog("Id Cliente"));
            Consumidor consumidor = new Consumidor(id_consumidor, nomeConsumidor);
            Feedback feedback = new Feedback(id, fb, data, consumidor, mf);
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
