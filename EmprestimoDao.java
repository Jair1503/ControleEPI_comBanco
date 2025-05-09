import java.sql.*;
import java.util.ArrayList;

public class EmprestimoDao {
    public void inserirEmprestimo(Emprestimo emp) {
        String sql = "INSERT INTO emprestimo (id_usuario, id_epi, data_retirada, data_prevista_devolucao, confirmacao_retirada) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emp.getIdUsuario());
            stmt.setInt(2, emp.getIdEpi());
            stmt.setString(3, emp.getDataRetirada());
            stmt.setString(4, emp.getDataPrevistaDevolucao());
            stmt.setBoolean(5, emp.isConfirmacaoRetirada());
            stmt.executeUpdate();
            System.out.println("Empréstimo registrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir empréstimo: " + e.getMessage());
        }
    }

    public ArrayList<Emprestimo> listarEmprestimos() {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimo emp = new Emprestimo(
                        rs.getInt("id_emprestimo"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_epi"),
                        rs.getString("data_retirada"),
                        rs.getString("data_prevista_devolucao"),
                        rs.getBoolean("confirmacao_retirada")
                );
                lista.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarEmprestimo(Emprestimo emp) {
        String sql = "UPDATE emprestimo SET id_usuario = ?, id_epi = ?, data_retirada = ?, data_prevista_devolucao = ?, confirmacao_retirada = ? WHERE id_emprestimo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emp.getIdUsuario());
            stmt.setInt(2, emp.getIdEpi());
            stmt.setString(3, emp.getDataRetirada());
            stmt.setString(4, emp.getDataPrevistaDevolucao());
            stmt.setBoolean(5, emp.isConfirmacaoRetirada());
            stmt.setInt(6, emp.getId());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Empréstimo atualizado com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }

    public void excluirEmprestimo(int id) {
        String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Empréstimo excluído com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir empréstimo: " + e.getMessage());
        }
    }
}



