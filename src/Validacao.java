private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {

        Carrinho c = new Carrinho();
        CarrinhoDAO cao = new CarrinhoDAO();

        c = getSelectedRow(jTEstoque);  // Isto está correto ?
        cao.create(c);

        readTable();
        }
        -------------------------------------------------------------------------


        package model.dao;

        import connection.ConnectionFactory;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import javax.swing.JOptionPane;
        import model.bean.Carrinho;
        import model.bean.Produto;

public class CarrinhoDAO {

    public void create(Carrinho c){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("INSERT INTO carrinho (nome,descri,qtd,preco)VALUES(?,?,?,?)");
            stmt.setString(1,c.getNome());   // Essa parte pega cada um desses itens e grava. A parte de baixo insert no BD.
            stmt.setString(2,c.getDescri()); // Essa parte pega cada um desses itens e grava. A parte de baixo insert no BD.
            stmt.setInt(3,c.getQtd());       // Essa parte pega cada um desses itens e grava. A parte de baixo insert no BD.
            stmt.setDouble(4,c.getPreco());  // Essa parte pega cada um desses itens e grava. A parte de baixo insert no BD.

            stmt.executeUpdate();
            // ESSA PARTE DA INSERT NO BANCO DE DADOS, SERVE PRA SALVAR NO BANCO DE DADOS.

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!  uhhuu '-' ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO SALVAR: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Carrinho> readCar(){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carrinho> carrinhos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM carrinho");
            rs = stmt.executeQuery();

            while (rs.next()){
                //Esse troço aqui ta rodando dentro do BD e ta pegando todos os valores e jogando na TABELA.
                Carrinho carrinho = new Carrinho();

                carrinho.setIdproduto(rs.getInt("idproduto"));
                carrinho.setNome(rs.getString("nome"));
                carrinho.setDescri(rs.getString("descri"));
                carrinho.setQtd(rs.getInt("qtd"));
                carrinho.setPreco(rs.getDouble("preco"));
                carrinhos.add(carrinho);

                //ESSA PARTE FAZ OS PRODUTOS APARECEREM NA TABELA, SE EU APAGAR O QTD AQUI, NA TABELA TB SOME '-'
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return carrinhos;
    }

}
--------------------------------------------------

        package view;

        import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
        import javax.swing.JOptionPane;
        import javax.swing.JTable;
        import javax.swing.table.DefaultTableModel;
        import javax.swing.table.TableRowSorter;
        import model.bean.Carrinho;
        import model.bean.Cliente;
        import model.bean.Produto;
        import model.dao.CarrinhoDAO;
        import model.dao.ClienteDAO;
        import model.dao.ProdutoDAO;

public class TelaEstoque extends javax.swing.JFrame {

    private Object jTCar;
    private Object txtNomeTeste;

    Object c;
    public Object getC() {
        return c;
    }

    public void setC(Object c) {
        this.c = c;
    }

    public TelaEstoque() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTEstoque.getModel();
        jTEstoque.setRowSorter(new TableRowSorter(modelo));

        readTable();
    }

    public void readJTableCar() {

        DefaultTableModel modelo = (DefaultTableModel) jTEstoque.getModel();
        modelo.setNumRows(0); // add 1 por 1 em vez de duplicar os itens.
        CarrinhoDAO pdao = new CarrinhoDAO();

        for (Carrinho c : pdao.readCar()) {
            // OLHA SO AQUI EM CIMA O   pdao.read()  esse read é o que vem la do ProdutoDAO.
            modelo.addRow(new Object[]{
                    c.getIdcarrinho(),
                    c.getNome(),
                    c.getDescri(),
                    c.getQtd(),
                    c.getPreco() // se apagar os item na lista referente a esse cara somem.
            });

        }

    }

    /*-----------------------------------------------------------------------*/

    public void readTable() {

        DefaultTableModel modelo = (DefaultTableModel) jTEstoque.getModel();
        modelo.setNumRows(0); // add 1 por 1 em vez de duplicar os itens.
        ProdutoDAO pdao = new ProdutoDAO();

        for (Produto p : pdao.read()) {

            modelo.addRow(new Object[]{
                    p.getIdproduto(),
                    p.getNome(),
                    p.getDescri(),
                    p.getQtd(),
                    p.getPreco() // se apagar os item na lista referente a esse cara somem.
            });

        }

    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSair = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTEstoque = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSair.setText("SAIR");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnComprar.setText("COMPRAR");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ESTOQUE / LOJA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(118, 118, 118)
                                                .addComponent(btnComprar)
                                                .addGap(76, 76, 76)
                                                .addComponent(btnSair))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(147, 147, 147)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnComprar)
                                        .addComponent(btnSair))
                                .addGap(19, 19, 19))
        );

        jTEstoque.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Nome", "Descrição", "Quantidade", "Preço"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTEstoque);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {

        this.dispose();
    }

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {

        Carrinho c = new Carrinho();
        CarrinhoDAO cao = new CarrinhoDAO();

        c = getSelectedRow(jTEstoque);
        cao.create(c);

        readTable();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTEstoque;
    // End of variables declaration

    private Carrinho getSelectedRow(JTable jTEstoque) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
-----------------------------------------------------------------------------


        package model.dao;

        import connection.ConnectionFactory;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import javax.swing.JOptionPane;
        import model.bean.Produto;

/**
 *
 * @author USUÁRIO
 */
public class ProdutoDAO {

    public void create(Produto p){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("INSERT INTO produto (nome, descri, qtd, preco)VALUES(?,?,?,?)");
            stmt.setString(1,p.getNome());
            stmt.setString(2,p.getDescri());
            stmt.setInt(3,p.getQtd());
            stmt.setDouble(4,p.getPreco());

            stmt.executeUpdate();
            // ESSA PARTE DA INSERT NO BANCO DE DADOS, SERVE PRA SALVAR NO BANCO DE DADOS.

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!  uhhuu '-' ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO SALVAR: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Produto> read(){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()){
                //Esse troço aqui ta rodando dentro do BD e ta pegando todos os valores e jogando na porra da TABELA.
                Produto produto = new Produto();

                produto.setIdproduto(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescri(rs.getString("descri"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);

                //ESSA PARTE FAZ OS PRODUTOS APARECEREM NA TABELA, SE EU APAGAR O QTD AQUI, NA TABELA TB SOME '-'
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }


    public void update(Produto p){
// ESSA PARTE É A QUE ENTRA DIRETO NO BANCO DE DADOS.
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("UPDATE produto SET nome = ?, descri = ?, qtd = ?, preco = ? WHERE idproduto=?");
            stmt.setString(1,p.getNome());
            stmt.setString(2,p.getDescri());
            stmt.setInt(3,p.getQtd());
            stmt.setDouble(4,p.getPreco());
            stmt.setInt(5,p.getIdproduto());

            stmt.executeUpdate();
            // ESSA PARTE FAZ A MODIFICAÇÃO NO BANCO DE DADOS, SERVE PRA SALVAR NO BANCO DE DADOS.

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!  uhhuu '-' ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO ATUALIZAR: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete (Produto p){
// ESSA PARTE É A QUE ENTRA DIRETO NO BANCO DE DADOS.
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE idproduto=?");
            stmt.setInt(1,p.getIdproduto());

            stmt.executeUpdate();
            // ESSA PARTE FAZ A MODIFICAÇÃO NO BANCO DE DADOS, SERVE PRA SALVAR NO BANCO DE DADOS.

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!  uhhuu '-' ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERRO AO EXCLUIR: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }

    }


    public List<Produto> readJTableForDesc(String descri){

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descri LIKE ?");
            stmt.setString(1, "%"+descri+"%");
            rs = stmt.executeQuery();

            while (rs.next()){

                Produto produto = new Produto();

                produto.setIdproduto(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescri(rs.getString("descri"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);

                //ESSA PARTE FAZ OS PRODUTOS APARECEREM NA TABELA, SE EU APAGAR O QTD AQUI, NA TABELA TB SOME '-'
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;

    }

}

-------------------------------------------------

        package view;

        import javax.swing.table.DefaultTableModel;
        import javax.swing.table.TableRowSorter;
        import model.bean.Carrinho;
        import model.bean.Produto;
        import model.dao.CarrinhoDAO;
        import model.dao.ProdutoDAO;


public class TelaCar extends javax.swing.JFrame {

    /** Creates new form TelaCar */
    public TelaCar() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTCar.getModel();
        jTCar.setRowSorter(new TableRowSorter(modelo));

        readJTableCar();
    }

    public void readJTableCar() {

        DefaultTableModel modelo = (DefaultTableModel) jTCar.getModel();
        modelo.setNumRows(0); // add 1 por 1 em vez de duplicar os itens.
        CarrinhoDAO pdao = new CarrinhoDAO();

        for (Carrinho c : pdao.readCar()) {
            // OLHA SO AQUI EM CIMA O   pdao.read()  esse read é o que vem la do ProdutoDAO.
            modelo.addRow(new Object[]{
                    c.getIdcarrinho(),
                    c.getNome(),
                    c.getDescri(),
                    c.getQtd(),
                    c.getPreco() // se apagar os item na lista referente a esse cara somem.
            });

        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTCar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnFinalizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNomeTeste = new javax.swing.JTextField();
        txtDescriTeste = new javax.swing.JTextField();
        txtQtdTeste = new javax.swing.JTextField();
        txtPrecoTeste = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTCar.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Nome", "Descrição", "Quantidade", "Preço"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTCar);

        btnFinalizar.setText("FINALIZAR");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnExcluir.setText("EXCLUIR");

        btnSair.setText("SAIR");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel1.setText("Total:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(66, 66, 66)
                                                .addComponent(btnFinalizar)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnExcluir)
                                                .addGap(61, 61, 61)
                                                .addComponent(btnSair)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(txtNomeTeste, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtDescriTeste)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtQtdTeste, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(txtPrecoTeste, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(43, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnFinalizar)
                                                        .addComponent(btnExcluir)
                                                        .addComponent(btnSair))
                                                .addGap(18, 18, 18))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(8, 8, 8)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTotal)
                                        .addComponent(txtQtdTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtPrecoTeste)
                                                .addComponent(txtDescriTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNomeTeste)))
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {

        this.dispose();

    }

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {

        Carrinho ca = new Carrinho();
        CarrinhoDAO cao = new CarrinhoDAO();

        ca.setNome(txtNomeTeste.getText());
        ca.setDescri(txtDescriTeste.getText());
        ca.setQtd(Integer.parseInt(txtQtdTeste.getText()));
        ca.setPreco(Double.parseDouble(txtPrecoTeste.getText()));
        cao.create(ca);


        readJTableCar();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTCar;
    private javax.swing.JTextField txtDescriTeste;
    private javax.swing.JTextField txtNomeTeste;
    private javax.swing.JTextField txtPrecoTeste;
    private javax.swing.JTextField txtQtdTeste;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration

}
----------------------------------------------------------------------------------------

        /*
         * To change this license header, choose License Headers in Project Properties.
         * To change this template file, choose Tools | Templates
         * and open the template in the editor.
         */
        package model.bean;

/**
 *
 * @author USUÁRIO
 */
public class Produto {
    private int idproduto;
    private String nome;
    private String descri;
    private int qtd;
    private double preco;

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return getDescri(); //To change body of generated methods, choose Tools | Templates.
    }






}
----------------------------------------------------------------------------------------------


        package model.bean;

public class Carrinho {
    public int idcarrinho;
    public int qtdCarr;
    public float valorTotal;
    public String nome;
    public String descri;
    public int qtd;
    public Double preco;

    public int getIdcarrinho() {
        return idcarrinho;
    }

    public void setIdcarrinho(int idcarrinho) {
        this.idcarrinho = idcarrinho;
    }

    public int getQtdCarr() {
        return qtdCarr;
    }

    public void setQtdCarr(int qtdCarr) {
        this.qtdCarr = qtdCarr;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setIdproduto(Object valueAt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addRow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addRow(Object[] dados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void add(int selectedRow) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addRow(Object jTCar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }








}
