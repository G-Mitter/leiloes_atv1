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
    
    
    public void cadastrarProduto (ProdutosDTO p){
        
          
        
        conectaDAO conexaoInsert = new conectaDAO();
        conexaoInsert.conexao();

        if (conexaoInsert.conexao()) {

            String nome = p.getNome();
            Integer valor  = p.getValor();
            String status = p.getStatus();
            

            try {

                String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
                PreparedStatement stmt = conexaoInsert.getConn().prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setInt(2, valor);
                stmt.setString(3, status);
                
                stmt.executeUpdate();
                conexaoInsert.desconectar();
               

            } catch (SQLException sqle) {
                System.out.println("Erro inserindo : " + sqle.getMessage());
               
            }
        } else {
           
        }
 
        //conn = new conectaDAO().connectDB();
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

