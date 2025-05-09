public class Devolucao {
    private int id;
    private int idEmprestimo;
    private String dataDevolucao;

    public Devolucao(int id, int idEmprestimo, String dataDevolucao) {
        this.id = id;
        this.idEmprestimo = idEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Devolucao(int idEmprestimo, String dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int getId() {
        return id;
    }
    public int getIdEmprestimo() {
        return idEmprestimo;
    }
    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }
    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Empréstimo: " + idEmprestimo + ", Data: " + dataDevolucao;
    }
}