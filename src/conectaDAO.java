
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
   /* 
    public Connection connectDB(){
        Connection conn = null;
        
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=1234.Cinco6/7");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    */
    
    
    Connection conn; //criando um objeto do tipo connection chamado conn
    Statement st; //criando um objeto do tipo Statement  chamado st para execução de comando SQL

    public boolean conexao() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC carregado");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Driver JDBC nao encontrado : "
                    + cnfe.getMessage());
        }

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=1234.Cinco6/7");
            st = conn.createStatement();

        } catch (ClassNotFoundException ex) {
            System.out.println("driver não está disponível para acesso ou não existe");
            return false;

        } catch (SQLException ex) {
            System.out.println("Sintaxe de comando invalida ");
            return false;
        }

        try {
            st = conn.createStatement();
            System.out.println("Pronto para execucao de comandos sql.");
            return true;
        } catch (SQLException sqle) {
            System.out.println("Erro no acesso ao Bando de Dados : "
                    + sqle.getMessage());
            return false;
        }

    }

    public conectaDAO() {
    }


    
    public void desconectar() {
        try {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
                System.out.println("Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
    
    

    public conectaDAO(Connection conn, Statement st) {
        this.conn = conn;
        this.st = st;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }
    
}
