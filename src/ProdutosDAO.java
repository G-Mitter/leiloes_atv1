/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    ArrayList<ProdutosDTO> listagemStatus = new ArrayList<>();
    

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getPrep() {
        return prep;
    }

    public void setPrep(PreparedStatement prep) {
        this.prep = prep;
    }

    public ResultSet getResultset() {
        return resultset;
    }

    public void setResultset(ResultSet resultset) {
        this.resultset = resultset;
    }

    public ArrayList<ProdutosDTO> getListagem() {
        return listagem;
    }

    public void setListagem(ArrayList<ProdutosDTO> listagem) {
        this.listagem = listagem;
    }

    public boolean cadastrarProduto(ProdutosDTO p) {

        conectaDAO conexaoInsert = new conectaDAO();

        conexaoInsert.conexao();

        if (conexaoInsert.conexao()) {

            String nome = p.getNome();
            Integer valor = p.getValor();
            String status = p.getStatus();

            try {

                String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
                PreparedStatement stmt = conexaoInsert.getConn().prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setInt(2, valor);
                stmt.setString(3, status);

                stmt.executeUpdate();
                conexaoInsert.desconectar();

                return true;

            } catch (SQLException sqle) {

                System.out.println("Erro inserindo : " + sqle.getMessage());
                return false;
            }

            //conn = new conectaDAO().connectDB();
        }
        return false;
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        return listagem;
    }

    public void Listar() {

        for (int i = 0; i <= listagem.size(); i++) {

        }
    }

    public void Select() {

        conectaDAO conexaoSelect = new conectaDAO();
        conexaoSelect.conexao();

        try {

            String sql = "Select * from produtos;";
            PreparedStatement stmt = conexaoSelect.getConn().prepareStatement(sql);

            ResultSet resposta = stmt.executeQuery();

            while (resposta.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(Integer.parseInt(resposta.getString("id")));
                p.setNome(resposta.getString("nome"));
                p.setValor(Integer.parseInt(resposta.getString("valor")));
                p.setStatus(resposta.getString("status"));

                listagem.add(p);
            }
            conexaoSelect.desconectar();

        } catch (SQLException sqle) {
            System.out.println("Erro ao buscar dados Dados : " + sqle.getMessage());

        }

    }

    public void Adicionar(ProdutosDTO p) {
        listagem.add(p);
    }
    public void AdicionarStatus(ProdutosDTO p) {
        listagemStatus.add(p);
    }
    public void venderProduto(ProdutosDTO p) {

        conectaDAO conexaoUpdate = new conectaDAO();
        conexaoUpdate.conexao();

        if (conexaoUpdate.conexao()) {

            String status = "Vendido";
            int id = p.getId();

            try {

                String sql = "UPDATE produtos SET status = ? Where id = ?";
                PreparedStatement stmt = conexaoUpdate.getConn().prepareStatement(sql);
                stmt.setString(1, status);
                stmt.setInt(2, id);
                stmt.executeUpdate();

            } catch (SQLException sqle) {
                System.out.println("Erro ao atualizar Dados : " + sqle.getMessage());

            }
        } else {

        }

    }

    public ProdutosDTO listarProdutosVendidos() {

        conectaDAO conexaoSelect = new conectaDAO();
        conexaoSelect.conexao();
        String status = "Vendido";

        try {

            String sql = "Select * from produtos where status = '" + status + "';";
            PreparedStatement stmt = conexaoSelect.getConn().prepareStatement(sql);

            ResultSet resposta = stmt.executeQuery();

            while (resposta.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("status"));

                AdicionarStatus(p);
                return p;
            }

            conexaoSelect.desconectar();

        } catch (SQLException sqle) {
            System.out.println("Erro ao buscar dados Dados : " + sqle.getMessage());

        }
        return null;

    }
}
