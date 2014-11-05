package Controller;

/**
 *
 * @author Lucas
 */
import Model.Aluno; 
import Model.Disciplina; 
import com.oracle.jrockit.jfr.ContentType;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Timestamp;
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Calendar; 
import java.util.Date; 
import java.util.List;
import Model.Professor;

public class BancoDadosController { 

    //-----------DISCIPLINAS-SQL-------------------------------------------------------------------------------
    private final String DELETE_DISCIPLINA = "DELETE FROM disciplinas WHERE codigo = ?";
    private final String INSERT_DISCIPLINA = "INSERT INTO disciplinas (codigo,professor,limite_alunos,nome) VALUES (?,?,?,?)";
    private final String SELECT_ALL_DISCIPLINA = "SELECT * FROM disciplinas";
    private final String SELECT_ONE_DISCIPLINA = "SELECT * FROM disciplinas WHERE codigo = ?";
    
    //-----------ALUNOS-SQL-------------------------------------------------------------------------------------
    private final String DELETE_ALUNO = "DELETE FROM alunos WHERE matricula = ?";
    private final String INSERT_ALUNO = "INSERT INTO alunos (matricula,nome,endereco,telefone,sexo,email,senha,login,data_matricula) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String SELECT_ALL_ALUNO = "SELECT * FROM alunos";
    private final String SELECT_ONE_ALUNO = "SELECT * FROM alunos WHERE matricula = ?";
    
    //-----------PROFESSORES-SQL-------------------------------------------------------------------------------------
    private final String DELETE_PROFESSOR = "DELETE FROM professores WHERE matricula = ?";
    private final String INSERT_PROFESSOR = "INSERT INTO professores (codigo,data_admissao,nome,endereco,telefone,sexo,email,senha,login) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String SELECT_ALL_PROFESSOR = "SELECT * FROM professores";
    private final String SELECT_ONE_PROFESSOR = "SELECT * FROM professores WHERE codigo = ?"; 
    
    
    private final String SELECT_NEXT_SEQUENCE = "SELECT NEXTVAL('SEQUENCE_ID')";
    private boolean retorno = false; 
    
    public Connection getConnection() throws SQLException { 
        Connection con = null; 
        con = DriverManager .getConnection("jdbc:postgresql://localhost/mpsdb?user=postgres&password=hbzu7d22");
        return con; 
    } 
    
    public void closeConnnection(Connection con) { 
        try { 
            con.close(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
    } 
    
    
    //---------- DISCIPLINAS -----------------------------------
    public boolean insertDisciplina(Disciplina disciplina) {
        Connection con = null; 
        try { 
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(INSERT_DISCIPLINA); //Query

            prepared.setInt(1, disciplina.getCodigo());
            prepared.setString(2, disciplina.getProfessor());
            prepared.setInt(3, disciplina.getLimiteAlunos());
            prepared.setString(4, disciplina.getNome());
            
            retorno = prepared.execute(); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { 
            closeConnnection(con); 
        }
        return retorno;
    }
   
    public boolean removeDisciplina(int codigo) throws SQLException { 
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(DELETE_DISCIPLINA);
            prepared.setLong(1, codigo);
            retorno = prepared.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnnection(con);
        }
        return retorno; 
    }
    
    //Retorna uma List com os elementos (objetos Disciplina) encontrados na pesquisa
    public List<Disciplina> selectDisciplina(Disciplina disciplina) throws SQLException { 
        Connection con = null; 
        List<Disciplina> listDisciplina = new ArrayList<Disciplina>();
        try { 
            con = getConnection(); 
            PreparedStatement prepared = con.prepareStatement(SELECT_ALL_DISCIPLINA); 
            ResultSet resultSet = prepared.executeQuery(); 
            
            while (resultSet.next()) {
                Disciplina disciplinaTmp = new Disciplina();
                disciplinaTmp.setCodigo(resultSet.getInt("codigo"));
                disciplinaTmp.setLimiteAlunos(resultSet.getInt("limite_alunos"));
                disciplinaTmp.setProfessor(resultSet.getString("professor"));
                disciplinaTmp.setNome(resultSet.getString("nome"));
                listDisciplina.add(disciplinaTmp); 
            } 
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally { 
            closeConnnection(con);
        }
        
        return listDisciplina; 
    }
    
    //Retorna um objetos Disciplina encontrados na pesquisa
    public Disciplina pesquisaDisciplina(int codigo) throws SQLException {
        Disciplina disciplinaTmp = new Disciplina();
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(SELECT_ONE_DISCIPLINA);
            prepared.setLong(1, codigo);
            
            ResultSet resultSet = prepared.executeQuery();
            
            /*
                Se tiver mais de um item:
                while(resultSet.next()){
                    resultSet.getString("nome")); //Por exemplo
                }
            */
            resultSet.next(); //Se tiver mais de um item
            
            disciplinaTmp.setCodigo(        resultSet.getInt("codigo"));
            disciplinaTmp.setLimiteAlunos(  resultSet.getInt("limite_alunos"));
            disciplinaTmp.setProfessor(     resultSet.getString("professor"));
            disciplinaTmp.setNome(          resultSet.getString("nome"));
                
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnnection(con);
        }
        
        return disciplinaTmp;
    }
    //-----------------------------------------------------------
    
    
    //------------- ALUNOS --------------------------------------
    //Retorna uma List com os elementos (objetos Aluno) encontrados na pesquisa
    public List<Aluno> selectAluno(Aluno aluno) throws SQLException { 
        Connection con = null; 
        List<Aluno> listAluno = new ArrayList<Aluno>();
        try { 
            con = getConnection(); 
            PreparedStatement prepared = con.prepareStatement(SELECT_ALL_ALUNO); 
            ResultSet resultSet = prepared.executeQuery(); 
            
            while (resultSet.next()) {
                Calendar data_matricula = Calendar.getInstance();
                data_matricula.setTimeInMillis(resultSet.getTimestamp("data_matricula").getTime());
                
                Aluno alunoTmp = new Aluno(resultSet.getString("nome"), resultSet.getString("endereco"), resultSet.getString("telefone"),
                resultSet.getString("sexo"), resultSet.getString("email"), resultSet.getString("senha"), resultSet.getString("login"),
                resultSet.getInt("matricula"), data_matricula);

                listAluno.add(alunoTmp); 
            } 
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally { 
            closeConnnection(con);
        }
        
        return listAluno; 
    }
    
    //Retorna um objeto Aluno encontrados na pesquisa
    public Aluno pesquisaAluno(int matricula) throws SQLException {
        Aluno alunoTmp = null;
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(SELECT_ONE_ALUNO);
            prepared.setLong(1, matricula);
            
            ResultSet resultSet = prepared.executeQuery();
            resultSet.next();
            
            Calendar data_matricula = Calendar.getInstance();
            data_matricula.setTimeInMillis(resultSet.getTimestamp("data_matricula").getTime());
                
            alunoTmp = new Aluno(resultSet.getString("nome"), resultSet.getString("endereco"), resultSet.getString("telefone"),
                resultSet.getString("sexo"), resultSet.getString("email"), resultSet.getString("senha"), resultSet.getString("login"),
                resultSet.getInt("matricula"), data_matricula);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnnection(con);
        }
        
        return alunoTmp;
    }
    
    public boolean insertAluno(Aluno aluno) {
        Connection con = null; 
        try { 
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(INSERT_ALUNO); //Query

            //O formato da data no banco é Timestamp
            //Cria um Timestamp da data corrente
            Date date = new java.util.Date();
            Timestamp data_matricula = new Timestamp(date.getTime());
            
            //
            data_matricula.setTime(aluno.getDataMatricula().getTimeInMillis());
            
            prepared.setInt   (1, aluno.getMatricula());
            prepared.setString(2, aluno.getNome());
            prepared.setString(3, aluno.getEndereceo());
            prepared.setString(4, aluno.getTelefone());
            prepared.setString(5, aluno.getSexo());
            prepared.setString(6, aluno.getEmail());
            prepared.setString(7, aluno.getSenha());
            prepared.setString(8, aluno.getLogin());
            prepared.setTimestamp(9, data_matricula);
            
            retorno = prepared.execute(); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { 
            closeConnnection(con); 
        }
        return retorno;
    }
    
    public boolean removeAluno(int matricula) throws SQLException { 
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(DELETE_ALUNO);
            prepared.setLong(1, matricula);
            retorno = prepared.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnnection(con);
        }
        return retorno; 
    }
      //-----------------------------------------------------------
    
    
    //------------- PROFESSORES -----------------------------------
    //Retorna uma List com os elementos (objetos Professor) encontrados na pesquisa
    public List<Professor> selectProfessor(Professor professor) throws SQLException { 
        Connection con = null; 
        List<Professor> listProfessor = new ArrayList<Professor>();
        try { 
            con = getConnection(); 
            PreparedStatement prepared = con.prepareStatement(SELECT_ALL_PROFESSOR); 
            ResultSet resultSet = prepared.executeQuery(); 
            
            while (resultSet.next()) {
                Calendar data_admissao = Calendar.getInstance();
                data_admissao.setTimeInMillis(resultSet.getTimestamp("data_admissao").getTime());
                
                Professor professorTmp = new Professor(resultSet.getInt("codigo"),data_admissao, resultSet.getString("nome"), resultSet.getString("endereco"), resultSet.getString("telefone"),
                resultSet.getString("sexo"), resultSet.getString("email"), resultSet.getString("senha"), resultSet.getString("login"));

                listProfessor.add(professorTmp); 
            } 
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally { 
            closeConnnection(con);
        }
        
        return listProfessor; 
    }
    
    //Retorna um objeto Professor encontrados na pesquisa
    public Professor pesquisaProfessor(int codigo) throws SQLException {
        Professor professorTmp = null;
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(SELECT_ONE_PROFESSOR);
            prepared.setLong(1, codigo);
            
            ResultSet resultSet = prepared.executeQuery();
            resultSet.next();
            
            Calendar data_admissao = Calendar.getInstance();
            data_admissao.setTimeInMillis(resultSet.getTimestamp("data_admissao").getTime());

            professorTmp = new Professor(resultSet.getInt("codigo"),data_admissao, resultSet.getString("nome"), resultSet.getString("endereco"), resultSet.getString("telefone"),
            resultSet.getString("sexo"), resultSet.getString("email"), resultSet.getString("senha"), resultSet.getString("login"));
                
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnnection(con);
        }
        
        return professorTmp;
    }
    
    public boolean insertProfessor(Professor professor) {
        Connection con = null; 
        try { 
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(INSERT_PROFESSOR); //Query

            //O formato da data no banco é Timestamp
            //Cria um Timestamp da data corrente
            Date date = new java.util.Date();
            Timestamp data_admissao = new Timestamp(date.getTime());
            
            //
            data_admissao.setTime(professor.getDataAdmissao().getTimeInMillis());
            
            //(codigo,data_admissao,nome,endereco,telefone,sexo,email,senha,login)
            prepared.setInt      (1, professor.getCodigo());
            prepared.setTimestamp(2, data_admissao);
            prepared.setString   (3, professor.getNome());
            prepared.setString   (4, professor.getEndereceo());
            prepared.setString   (5, professor.getTelefone());
            prepared.setString   (6, professor.getSexo());
            prepared.setString   (7, professor.getEmail());
            prepared.setString   (8, professor.getSenha());
            prepared.setString   (9, professor.getLogin());
            
            retorno = prepared.execute(); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { 
            closeConnnection(con); 
        }
        return retorno;
    }
    
    public boolean removeProfessor(int codigo) throws SQLException { 
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(DELETE_PROFESSOR);
            prepared.setLong(1, codigo);
            retorno = prepared.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnnection(con);
        }
        return retorno; 
    }  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //----------------------------------------------------
    public long getId() throws SQLException {
        Connection con = null;
        long id = 0;
        try {
            con = getConnection();
            PreparedStatement prepared = con.prepareStatement(SELECT_NEXT_SEQUENCE);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next())
                id = resultSet.getLong("nextval");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnnection(con);
        }
        return id;
    }
    
}