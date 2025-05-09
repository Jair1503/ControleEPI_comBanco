public class Emprestimo {
    private int id;
    private int idUsuario;
    private int idEpi;
    private String dataRetirada;
    private String dataPrevistaDevolucao;
    private boolean confirmacaoRetirada;

    public Emprestimo(int id, int idUsuario, int idEpi, String dataRetirada, String dataPrevistaDevolucao, boolean confirmacaoRetirada) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idEpi = idEpi;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.confirmacaoRetirada = confirmacaoRetirada;
    }

    public Emprestimo(int idUsuario, int idEpi, String dataRetirada, String dataPrevistaDevolucao, boolean confirmacaoRetirada) {
        this.idUsuario = idUsuario;
        this.idEpi = idEpi;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.confirmacaoRetirada = confirmacaoRetirada;
    }

    public int getId() {
        return id;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public int getIdEpi() {
        return idEpi;
    }
    public String getDataRetirada() {
        return dataRetirada;
    }
    public String getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
    public boolean isConfirmacaoRetirada() {
        return confirmacaoRetirada;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setIdEpi(int idEpi) {
        this.idEpi = idEpi;
    }
    public void setDataRetirada(String dataRetirada) {
        this.dataRetirada = dataRetirada;
    }
    public void setDataPrevistaDevolucao(String dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }
    public void setConfirmacaoRetirada(boolean confirmacaoRetirada) {
        this.confirmacaoRetirada = confirmacaoRetirada;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Usuário: " + idUsuario + ", EPI: " + idEpi +
                ", Retirada: " + dataRetirada + ", Prev. Devolução: " + dataPrevistaDevolucao +
                ", Confirmado: " + confirmacaoRetirada;
    }
}



