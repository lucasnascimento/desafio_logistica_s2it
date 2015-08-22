package br.com.s2it.logistica.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.s2it.logistica.domain.Cidade;
import br.com.s2it.logistica.domain.CidadeRepository;
import br.com.s2it.logistica.domain.Rota;
import br.com.s2it.logistica.service.RotaService;
import br.com.s2it.logistica.util.RotaLoader;
import br.com.s2it.logistica.web.dto.MapaDTO;
import br.com.s2it.logistica.web.dto.MapaRequestDTO;
import br.com.s2it.logistica.web.dto.RotaDTO;

@RestController
@RequestMapping(value = "api/mapa")
public class MapaController {

	@Autowired
	private RotaService rotaService;

	@Autowired
	private RotaLoader rotaLoader;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@RequestMapping(value = "/{nomeMapa}", method = RequestMethod.GET)
	public MapaDTO recuperarInformacaoDoMapa(@PathVariable String nomeMapa) {
		
		MapaDTO mapa = new MapaDTO();
		mapa.setNomeMapa(nomeMapa);
		List<Cidade> todasCidadesDoMapa = cidadeRepository.findAllByNomeMapa(nomeMapa);
		ArrayList<String> rotas = new ArrayList<String>();
		
		for (Cidade cidadeDoMapa : todasCidadesDoMapa){
			for (Rota rotaDaCidade : cidadeDoMapa.getRotas()){
				rotas.add(""+rotaDaCidade);
			}
		}	
		mapa.setRotas(rotas);
		return mapa;
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String adicionarMapa(@RequestBody MapaDTO mapa) {
		
		for (String rota : mapa.getRotas()) {
			rotaLoader.carregaRota(rota, mapa.getNomeMapa());
		}

		return "";
	}

	@RequestMapping(value = "/{nomeMapa}/{cidadeOrigem}/{cidadeDestino}", method = RequestMethod.POST)
	public RotaDTO recuperarCustoRota(@PathVariable String nomeMapa,
			@PathVariable String cidadeOrigem,
			@PathVariable String cidadeDestino,
			@RequestBody MapaRequestDTO mapaRequest) {

		List<Cidade> caminhoMaisCurto = rotaService.recuperarCaminhoMaisCurto(
				cidadeOrigem, cidadeDestino, nomeMapa);
		Double custo = rotaService.calcularCusto(cidadeOrigem, cidadeDestino,
				nomeMapa, mapaRequest.getAutonomia(),
				mapaRequest.getValorCombustivel());

		return new RotaDTO(Arrays.toString(caminhoMaisCurto.toArray()), custo);
	}

}
