
# Solução rodando em [https://desafio-s2it-logistica.herokuapp.com/](https://desafio-s2it-logistica.herokuapp.com/)

Ao acessar o endereço acima será exbida uma pagina de ERRO 404, pois o acesso é feito todos através das APIs.

# Descrição do Desafio
Entregando mercadorias

A Companhia LevaEtraz está desenvolvendo um novo sistema de logística e sua ajuda é muito importante neste momento. Sua tarefa será desenvolver o novo sistema de entregas visando sempre o menor custo. Para popular sua base de dados o sistema precisa expor um Webservices que aceite o formato de malha logística (exemplo abaixo), nesta mesma requisição o requisitante deverá informar um nome para este mapa. É importante que os mapas sejam persistidos para evitar que a cada novo deploy todas as informações desapareçam. O formato de malha logística é bastante simples, cada linha mostra uma rota: ponto de origem, ponto de destino e distância entre os pontos em quilômetros.

A B 10

B D 15

A C 20

C D 30

B E 50

D E 30

Com os mapas carregados o requisitante irá procurar o menor valor de entrega e seu caminho, para isso ele passará o mapa, nome do ponto de origem, nome do ponto de destino, autonomia do caminhão (km/l) e o valor do litro do combustivel, agora sua tarefa é criar este Webservices. Um exemplo de entrada seria, mapa SP, origem A, destino D, autonomia 10, valor do litro 2,50; a resposta seria a rota A B D com custo de 6,25.

Você está livre para definir a melhor arquitetura e tecnologias para solucionar este desafio, mas não se esqueça de contar sua motivação no arquivo README que deve acompanhar sua solução, junto com os detalhes de como executar seu programa. Documentação e testes serão avaliados também =) Lembre-se de que iremos executar seu código com malhas beeemm mais complexas, por isso é importante pensar em requisitos não funcionais também!!

Também gostaríamos de acompanhar o desenvolvimento da sua aplicação através do código fonte. Por isso, solicitamos a criação de um repositório (GitHub) que seja compartilhado com a gente. Para o desenvolvimento desse sistema, nós solicitamos que você utilize a sua (ou as suas) linguagem de programação principal. Pode ser Java, JavaScript, Ruby ou Grails. 

Nós solicitamos que você trabalhe no desenvolvimento desse sistema sozinho e não divulgue a solução desse problema pela internet.

Bom desafio!

# Executando a Solução

* git clone git@github.com:lucasnascimento/desafio\_logistica\_s2it.git
* cd desafio\_logistica\_s2it
* mvn package
* java -jar target/logistica-0.0.1-SNAPSHOT.jar

A solução estará rodando em http://localhost:8080/api/mapa

# Definições Arquiteturais da Solução

* **Banco de Dados:** [Neo4J](http://neo4j.com/), por se tratar de um problema de roterização, que computacionamente pode ser resolvido utilizando Grafos como estruturas que mapeam o problema, utilizarei o Neo4J, que é uma solução de Banco de Dados justamente orientada a Grafos.

* **Servidor de Aplicação:** [Spring Boot](http://projects.spring.io/spring-boot/) o Framework Spring tem se mostrado um padrão de mercado no desenvolvimento de aplicativos comerciais, cuja escala,  performance e manutenebilidade são caracteriscas fundamentais para seu sucesso. Em particular utilizando o Spring Boot, o aplicativo pode ser facilmente embarcado com uma caracterisca de Arquitetura conhecida atualmente como [MicroServiços](http://martinfowler.com/articles/microservices.html).

* **Comunicação WebService:** [REST](https://pt.wikipedia.org/wiki/REST) será o protocolo escolhido para dispobilização dos Serviços Web atendidos pela aplicação. [JSON](https://pt.wikipedia.org/wiki/JSON) será o formato escolhido para troca de mensagens pelos serviços web disponibilizados pela aplicação.

* **Implantação:** [Heroku](https://www.heroku.com/) será a plataforma onde o aplicativo será disponibilizado. [https://desafio-s2it-logistica.herokuapp.com/api/mapa/SP](https://desafio-s2it-logistica.herokuapp.com/api/mapa/SP)

## Descrição dos webservices (apis) propostos 

### Criar um novo MAPA

* POST /api/mapa - Cria um novo mapa com a malha viária

> Exemplo de mensagem Enviada:
> {nomeMapa:"SP", rotas:["A B 10","B D 15","A C 20","C D 30","B E 50","D E 30"]}

* POST /api/mapa/{:NOME\_MAPA}/{:CIDADE\_ORIGEM}/{:CIDADE\_DESTINO} - Retorna o trageto de menor custo entre a origem e o destino

> Exemplo de mensagem Enviada:
> {autonomia: 10, valor_combustivel: 2.50}

> Exemplo de mensagem Retorno:
> {rota: "A B D", custo: 6.25}

> * Status: 200 OK ou 404 PAGE\_NOT\_FOUND Não existe rota entre origem e destino.

* GET /api/mapa/{nomeMapa} - Exibe as rotas de um mapa

> Exemplo de mensagem de Retorno:
> {"nomeMapa":"SP","rotas":["A B 10","A C 20","C D 30","B E 50","B D 15","D E 30"]}
