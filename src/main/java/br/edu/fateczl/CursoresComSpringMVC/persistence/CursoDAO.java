package br.edu.fateczl.CursoresComSpringMVC.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.CursoresComSpringMVC.model.Disciplina;

public class CursoDAO {
	public List<Disciplina> getDisciplinas() throws SQLException,Exception{
		return getDisciplinas(48);
	}
	
	public List<Disciplina> getDisciplinas(int codigo) throws SQLException,Exception{
		List<Disciplina> lista = new ArrayList<>();
		Conexao conn = new Conexao();
		Connection cn = null;
		cn = conn.getConexao();
		
		String query = "SELECT * FROM fn_obterDisciplinasCurso(?)";
		PreparedStatement pstm = cn.prepareStatement(query);
		pstm.setInt(1, codigo);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Disciplina d = new Disciplina();
			d.setCodigoCurso(codigo);
			d.setNomeCurso(rs.getString("nomeCurso"));
			d.setNomeDisciplina(rs.getString("nomeDisciplina"));
			d.setCodigoDisciplina(rs.getString("codDisciplina"));
			d.setCargaHorariaDisciplina(rs.getInt("cargaHorariaDisc"));
			lista.add(d);
		}
		
		return lista;
	}
}
