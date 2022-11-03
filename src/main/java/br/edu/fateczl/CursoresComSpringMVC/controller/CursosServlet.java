package br.edu.fateczl.CursoresComSpringMVC.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.CursoresComSpringMVC.model.Disciplina;
import br.edu.fateczl.CursoresComSpringMVC.persistence.CursoDAO;
import jakarta.servlet.ServletException;

@Controller
public class CursosServlet {
	List<Disciplina> disciplinas = new ArrayList<>();
	String erro = "";
	@RequestMapping(name = "obterPrimeiroCurso", value = "/", method = RequestMethod.GET)
    public ModelAndView doGet(ModelMap model) throws ServletException, IOException {
		disciplinas = obterCurso(0);
    	return retorno(model,"index");
	}
	
	@RequestMapping(name = "obterCurso", value = "/", method = RequestMethod.POST)
	public ModelAndView doPost(@RequestParam Map<String, String> params,ModelMap model) throws ServletException, IOException {
		String cod = params.get("txtCodCurso");
		int codigo = Integer.parseInt(cod);
		disciplinas = obterCurso(codigo);
    	return retorno(model,"index");
	}
	
	protected ModelAndView retorno(ModelMap model,String pagina) throws ServletException, IOException {
		model.addAttribute("disciplinas", disciplinas);
		model.addAttribute("erro",erro);
		return new ModelAndView(pagina);
	}
	
	private List<Disciplina> obterCurso(int cod){
		List<Disciplina> c = new ArrayList<>();
		CursoDAO cd = new CursoDAO();
		try {
			if(cod == 0) {
				c = cd.getDisciplinas();
			}else {
				c = cd.getDisciplinas(cod);
			}			
		} catch (SQLException e) {
			erro = e.getMessage();
		} catch (Exception e) {
			erro = e.getMessage();
		}
		return c;
	}
}
