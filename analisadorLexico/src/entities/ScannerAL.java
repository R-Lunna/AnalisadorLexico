package entities;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import enums.TokenName;

public class ScannerAL {
	// Array que recebe todos os caracters do arquivo de texto code.al
	private char[] group;
	
	// Variavel auxiliar para avançar o array do grupo de caracter
	private int next = 0;
	
	// Objeto da classe Token que ira receber um tipo e o texto escrito no codigo que representa um determinado token
	Token token = new Token();
	
	// Variavel que recebe o texto que representa o token
	private String aux;
	
	// Objeto que faz a verificacao de qual tipo pertence aquele token
	Verify verify = new Verify();
	
	// Linha e Coluna
	private int row = 1;
	private int column = 0;
	
	// Metodo que no momento de instanciar o objeto ja le o arquivo e seta um array de caracter na variavel grupo
	public ScannerAL(String file) {
		try {
			String text = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
			group = text.toCharArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Metodo principal do analisador
	// -- Faz toda a mecanica do automato
	public Token getToken() {
		
		// Variavel responsavel pelos estados do automato
		int state = 0;
		
		// Variavel utilizada para fazer a analise de cada caracter do grupo
		char caracter;	
		
		// Variavel que fara a verificacao se a palavra que sera retornada possui um caracter invalido
		boolean invalideCaracter = false;
		
		// Reseta a variavel auxiliar
		aux = "";
		
		// Laco que roda ate encontrar o retorno do metodo, ou seja, o token
		while (true) { 
			// Se chegar no final do arquivo retorna nulo e encerra o programa
			if (next == group.length) return null;
			
			// Recebe um caracter do grupo
			caracter = group[next];
			next++;
			column++;
			
			if (caracter == '\n') {
				row++;
				column = 0;
			}
			
			// Verifica em qual estado esta do autômato
			switch (state) {
				case 0 : {
					
					// Espacos ou quebras de linha
					if (verify.isSpace(caracter)) state = 0;
					
					
					else if (verify.isChar(caracter)) { // Caracter
						state = 1;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isNumber(caracter)) { // Numero
						state = 3;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isOperatorEqual(caracter)) { // Igualdade
						state = 5;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isOperatorComparisonLM(caracter)) { // Comparacao maior ou menor
						state = 8;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isOperatorComparison(caracter)) { // Comparacao
						state = 9;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isOperatorMost(caracter)) { // Soma
						state = 10;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isOperatorMath(caracter)) { // Operadores matematicos
						state = 13;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isSymbol(caracter)) { // Simbolos
						state = 14;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isFinal(caracter)) { // Final de linha
						state = 16;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isOperatorLess(caracter)) { // Subtracao
						state = 17;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isDenial(caracter)) { // Negacao
						state = 18;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isComment(caracter)) {
						state = 22;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isCaracter(caracter)) {
						state = 25;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else if (verify.isText(caracter)) {
						state = 28;
						if (!invalideCaracter) token.setColumn(column);
						mountWord(caracter);
					} else { // Caracter invalido
						state = 0;
						token.setColumn(column);
						mountWord(caracter);
						invalideCaracter = true;
					}
					break;
				} case 1 : {
					if (verify.isChar(caracter) || verify.isNumber(caracter)) {
						state = 1;
						mountWord(caracter);
					} else if (verify.notToken(caracter)) {
						state = 1;
						mountWord(caracter);
						invalideCaracter = true;
					} else { 
						
						if (invalideCaracter) {
							back();
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						} else if (verify.isReservedWord(aux)) { 
							back();
							setToken(TokenName.PALAVRA_RESERVADA, aux, row); // Estado 20 do automato, retorna um token do tipo palavra reservada
							return token;
						} else {
							back();
							setToken(TokenName.NOME_VARIAVEL, aux, row); // Estado 2 do automato, retorna um token do tipo variavel (ID)
							return token;
						}
					}
					break;
				} case 3 : {
					if (verify.isNumber(caracter)) {
						state = 3;
						mountWord(caracter);	
					} else if (verify.notToken(caracter)) {
						state = 3;
						mountWord(caracter);
						invalideCaracter = true;
					} else {
						if (invalideCaracter) {
							back();
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						} else if (verify.isChar(caracter)) {
							mountWord(caracter);
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						} else {
							back();
							setToken(TokenName.NUMEROS, aux, row); // Estado 4 do automato, retornar um token do tipo numerico
							return token;
						}
					}
					break;
				} case 5 : {
					if (verify.isOperatorEqual(caracter)) {
						mountWord(caracter);
						
						if (invalideCaracter) {
							back();
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						}
						
						setToken(TokenName.OPERADOR_COMPARACAO, aux, row); // Estado 6 do automato, retorna um token do tipo comparacao
						return token;
					} else if (verify.notToken(caracter)) {
						state = 5;
						mountWord(caracter);
						invalideCaracter = true;
					} else {
						if (invalideCaracter) {
							back();
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						} else {
							back();
							setToken(TokenName.OPERADOR_ATRIBUICAO, aux, row); // Estado 7 do automato, retorna um token do tipo atribuicao
							return token;	
						}
					}
					break;
				} case 8 : {
					if (verify.isOperatorEqual(caracter)) {
						mountWord(caracter);
						setToken(TokenName.OPERADOR_COMPARACAO, aux, row); // Estado 6 do automato, retorna um token do tipo comparacao
						return token;
					} else if (verify.notToken(caracter)) {
						mountWord(caracter);
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;
					} else {
						if (invalideCaracter) {
							back();
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						} else {
							back();
							setToken(TokenName.OPERADOR_COMPARACAO, aux, row); // Estado 6 do automato, retorna um token do tipo comparacao
							return token;
						}
					}
				} case 9 : {
					if (verify.isOperatorComparison(caracter) && aux.equalsIgnoreCase(String.valueOf(caracter))) {
						mountWord(caracter);
						setToken(TokenName.OPERADOR_COMPARACAO, aux, row); // Estado 6 do automato, retorna um token do tipo comparacao
						return token;
					} else {
						mountWord(caracter);
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;
					}
				} case 10 : {
					if (verify.isOperatorMost(caracter) || verify.isOperatorEqual(caracter)) {
						mountWord(caracter);
						setToken(TokenName.OPERADOR_ATRIBUICAO, aux, row); // Estado 7 do automato, retorna um token do tipo atribuicao
						return token;
					} else if (verify.notToken(caracter)) {
						mountWord(caracter);
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;
					} else {
						if (invalideCaracter) {
							back();
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						} else {
							back();
							setToken(TokenName.OPERADOR_MATEMATICO, aux, row); // Estado 11 do automato, retorna um token do tipo operador matematico
							return token;
						}
					}
				} case 13 : {
					if (verify.isOperatorEqual(caracter)) {
						mountWord(caracter);
						setToken(TokenName.OPERADOR_ATRIBUICAO, aux, row); // Estado 7 do automato, retorna um token do tipo atribuicao
						return token;
					} else if (verify.notToken(caracter)) {
						mountWord(caracter);
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;
					} else {
						if (invalideCaracter) {
							back();
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						} else {
							back();
							setToken(TokenName.OPERADOR_MATEMATICO, aux, row); // Estado 11 do automato, retorna um token do tipo operador matematico
							return token;
						}
					}
				} case 14 : {
					if (invalideCaracter) {
						back();
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;
					} else {
						back();
						setToken(TokenName.SIMBOLO, aux, row); // Estado 15 do automato, retorna um token do tipo simbolo
						return token;
					}
				} case 16 : {
					if (invalideCaracter) {
						back();
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;
					} else {
						back();
						setToken(TokenName.FINAL_LINHA, aux, row); // Estado 11 do automato, retorna um token do tipo final de linha
						return token;
					}
				} case 17 : {
					if (verify.isOperatorEqual(caracter) || verify.isOperatorLess(caracter)) {
						mountWord(caracter);
						setToken(TokenName.OPERADOR_ATRIBUICAO, aux, row); // Estado 7 do automato, retorna um token do tipo atribuicao
						return token;
					} else if (verify.notToken(caracter)) {
						mountWord(caracter);
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;
					} else {
						if (invalideCaracter) {
							back();
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						} else {
							back();
							setToken(TokenName.OPERADOR_MATEMATICO, aux, row); // Estado 11 do automato, retorna um token do tipo operador matematico
							return token;
						}
					}
				} case 18 : {
					if (verify.isOperatorEqual(caracter)) {
						mountWord(caracter);
						setToken(TokenName.OPERADOR_COMPARACAO, aux, row); // Estado 6 do automato, retorna um token do tipo comparacao
						return token;
					} else if (verify.notToken(caracter)) {
						mountWord(caracter);
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;
					} else {
						if (invalideCaracter) {
							back();
							setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
							return token;
						} else {
							back();
							setToken(TokenName.NEGACAO, aux, row); // Estado 19 do automato, retorna um token do tipo negacao
							return token;
						}
					}
				} case 22 : {
					if (caracter == '\r' || caracter == '\n') {
						back();
						setToken(TokenName.COMENTARIO, aux, row);
						return token;
					
					} else if (invalideCaracter) {
						back();
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;	
					} else {
						state = 22;
						mountWord(caracter);
						break;
					}
				} case 25 : {
					state = 26;
					mountWord(caracter);
					break;
				} case 26 : {
					if (verify.isCaracter(caracter)) {
						mountWord(caracter);
						setToken(TokenName.CARACTER, aux, row);
						return token;
					} else {
						mountWord(caracter);
						setToken(TokenName.TOKEN_MAL_FORMADO, aux, row);
						return token;
					}
				} case 28 : {
					if (verify.isText(caracter)) {
						mountWord(caracter);
						setToken(TokenName.TEXTO, aux, row);
						return token;
					} else {
						state = 28;
						mountWord(caracter);
						break;
					}
				}
			}
		}
		
	}
	
	// Metodo responsavel pela montagem do token antes de retornar
	private void setToken(TokenName type, String word, int row) { 
		token.setType(type);
		token.setText(word);
		token.setRow(row);
	}
	
	// Metodo responsavel pela montagem do texto do token
	private void mountWord(char caracter) {
		aux += String.valueOf(caracter);  
	}
	
	// Metodo que retorna uma casa para nao perder o caracter encontrado depois de retornar um token
	private void back() {
		column--;
		next--;
	}
}
