package br.com.s2it.logistica.service;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PathExpanders;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;

import br.com.s2it.logistica.domain.Cidade;
import br.com.s2it.logistica.domain.CidadeRepository;
import br.com.s2it.logistica.domain.RelTypes;

@Service
public class RouteService {

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	GraphDatabaseService graphDatabaseService;

	@Autowired
	Neo4jOperations neo4jOperations;

	static final PathFinder<WeightedPath> PATH_FINDER = GraphAlgoFactory
			.dijkstra(PathExpanders.forTypeAndDirection(RelTypes.ROTA_PARA,
					Direction.OUTGOING), "distancia ");

	@SuppressWarnings("deprecation")
	public List<Cidade> recuperarCaminhoMaisCurto(String cidadeOrigem,
			String cidadeDestino, String mapa) {
		Transaction transaction = graphDatabaseService.beginTx();

		WeightedPath path = getWeightPath(cidadeOrigem, cidadeDestino, mapa);

		List<Cidade> resultList = new LinkedList<Cidade>();
		if (path != null) {
			for (Node n : path.nodes()) {
				resultList.add(cidadeRepository.findOne(n.getId()));
			}
		}
		transaction.success();
		transaction.finish();

		return resultList;
	}

	private WeightedPath getWeightPath(String cidadeOrigem,
			String cidadeDestino, String mapa) {
		Node nodeStation1 = getCidadeNode(cidadeOrigem, mapa);
		Node nodeStation2 = getCidadeNode(cidadeDestino, mapa);

		WeightedPath path = PATH_FINDER.findSinglePath(nodeStation1,
				nodeStation2);

		return path;
	}

	private Node getCidadeNode(String cidade, String mapa) {
		Cidade cidadeOrigem = cidadeRepository.findByNomeCidadeAndNomeMapa(
				cidade, mapa);
		Node nodeCidade = neo4jOperations.getNode(cidadeOrigem.getId());
		return nodeCidade;
	}

	@SuppressWarnings("deprecation")
	public Double calcularCusto(String cidadeOrigem, String cidadeDestino,
			String mapa, Integer autonomia, Double custoCombustivel) {
		Transaction transaction = graphDatabaseService.beginTx();
		WeightedPath path = getWeightPath(cidadeOrigem, cidadeDestino, mapa);

		int distanciaTotal = 0;
		if (path != null) {
			Iterable<Node> itNodes = path.nodes();

			if (itNodes.iterator().hasNext()) {

				Node startNode = itNodes.iterator().next();

				for (Node node : itNodes) {
					if (node != startNode) {
						Relationship r = neo4jOperations
								.getRelationshipBetween(startNode, node,
										"ROTA_PARA");

						int distanciaRota = (Integer) r
								.getProperty("distancia");
						distanciaTotal += distanciaRota;
						startNode = node;
					}
				}
			}
		}
		transaction.success();
		transaction.finish();

		return (distanciaTotal / autonomia) * custoCombustivel;
	}

}
