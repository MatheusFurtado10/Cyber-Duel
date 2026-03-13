# Cyber Duel: Guerra de Hackers

## 1. Título do projeto
**Cyber Duel: Guerra de Hackers**

## 2. Descrição breve do projeto
Cyber Duel é um jogo de cartas estratégico em Java, executado no terminal, onde dois jogadores (humanos ou bots) se enfrentam em um duelo de ataque, defesa e suporte.  
Cada rodada exige gestão de energia, escolha tática de cartas e leitura do estado de vida dos oponentes.  
O projeto usa arquivos CSV para definir cartas, deixando o jogo flexível e orientado a dados.

## 3. Demonstração ou objetivo do projeto
Este projeto foi desenvolvido para praticar lógica de jogo por turnos, programação orientada a objetos e manipulação de arquivos em Java.

Objetivo principal:
- Simular um duelo hacker com mecânicas de cartas, custo de energia e efeitos de suporte.

Modos disponíveis:
- `0`: Bot vs Bot
- `1`: Jogador vs Bot
- `2`: Jogador vs Jogador

Fluxo de uma partida:
1. Seleção de modo de jogo.
2. Montagem da mão (manual ou aleatória).
3. Rodadas com jogadas, cálculo de ataque/defesa e aplicação de suportes.
4. Encerramento por derrota (vida <= 0) ou rendição.
5. Geração de `replay.txt` com histórico da partida.

## 4. Tecnologias utilizadas
- Java 21
- Maven (estrutura de build e execução)
- Programação Orientada a Objetos (herança, polimorfismo, encapsulamento)
- Leitura de dados via CSV (`ataque.csv`, `defesa.csv`, `suporte.csv`)
- I/O com `java.nio.file` e `java.io` para persistência e replay
- Aplicação de console com `Scanner` para interação

## 5. Funcionalidades principais

- Sistema de cartas com três categorias:
	- Ataque
	- Defesa
	- Suporte
- Efeitos de suporte implementados:
	- `AUMENTA_ATAQUE`
	- `DIMINUI_ATAQUE`
	- `AUMENTA_VIDA`
- Controle de energia por turno para limitar jogadas
- Cálculo de confronto por rodada (ataque x defesa)
- IA de bot com tomada de decisão baseada em vida e energia
- Seleção manual ou aleatória de cartas para formação da mão
- Registro completo da partida em arquivo `replay.txt`
- Validação de entrada numérica para evitar erro em runtime

## 6. Como executar o projeto localmente
### Pré-requisitos
- JDK 21 instalado
- Maven instalado e disponível no PATH

### Passo a passo
1. Clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
```

2. Acesse a pasta do módulo Maven:

```bash
cd Cyber-Duel/cyberDuel
```

3. Compile o projeto:

```bash
mvn clean compile
```

4. Execute a aplicação:

```bash
mvn exec:java -Dexec.mainClass=br.ufjf.jogo.Jogo
```

Opcional (após compilar), executar direto pela JVM:

```bash
java -cp target/classes br.ufjf.jogo.Jogo
```

## 7. Estrutura do projeto
```text
Cyber-Duel/
	README.md
	cyberDuel/
		pom.xml
		ataque.csv
		defesa.csv
		suporte.csv
		src/main/java/br/ufjf/
			componentes/
				Carta.java
				CartaAtk.java
				CartaDef.java
				CartaSup.java
				LeArquivo.java
			jogo/
				Jogo.java
				Jogador.java
				Bot.java
```

Resumo dos principais arquivos:
- `Jogo.java`: fluxo principal da partida, turnos, rodada, confronto e encerramento.
- `Jogador.java`: regras do jogador humano, seleção de cartas e aplicação de ações.
- `Bot.java`: comportamento automatizado para partidas contra IA.
- `Carta*.java`: modelagem das cartas e especializações por tipo.
- `LeArquivo.java`: leitura dos CSVs e escrita do replay em arquivo.
- `*.csv`: base de dados das cartas e efeitos do jogo.

## 8. Aprendizados ou conceitos aplicados no projeto
- Modelagem orientada a objetos para representar entidades de domínio (cartas, jogador, bot e jogo).
- Uso de herança para especializar tipos de cartas sem duplicar estrutura base.
- Separação de responsabilidades entre motor do jogo, regras de jogada e camada de dados.
- Manipulação de arquivos para leitura de configuração e persistência de histórico.
- Desenvolvimento de jogo por turnos com regras de estado, recursos limitados e validação de entrada.
