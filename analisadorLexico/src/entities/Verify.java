package entities;


import enums.ReservedWord;

public class Verify {
	
	// E numero?
	public boolean isNumber(char x) {
		return x >= '0' && x <= '9';
	}
	
	// E caracter?
	public boolean isChar(char x) {
		return (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z');
	}
	
	// E operador matematico?
	public boolean isOperatorMath(char x) {
		return x == '*' || x == '/' || x == '%'; 
	}
	
	// E operador soma?
	public boolean isOperatorMost(char x) {
		return x == '+';
	}
	
	// E operador subtracao?
	public boolean isOperatorLess(char x) {
		return x == '-';
	}
	
	// E operador igualdade?
	public boolean isOperatorEqual(char x) {
		return x == '=';
	}
	
	// E operador de comparacao?
	public boolean isOperatorComparison(char x) {
		return x == '|' || x == '&';
	}
	
	// E operador de maior ou menor que?
	public boolean isOperatorComparisonLM(char x) {
		return x == '>' || x == '<';
	}
	
	// E espaco ou quebra de linha?
	public boolean isSpace(char x) {
		return x == ' ' || x == '\n' || x == '\r' || x == '\t';
	}
	
	// E operador final de linha?
	public boolean isFinal(char x) {
		return x == ';';
	}
	
	// E simbolo?
	public boolean isSymbol(char x) {
		return x == '(' || x == ')' || x == '{' || x == '}' || x == '"' || x == ',' || x == '[' || x == ']' || x == '.' || x == ':';
	}
	
	// E negacao?
	public boolean isDenial(char x) {
		return x == '!';
	}
	
	// E comentario?
	public boolean isComment(char x) {
		return x == '#';
	}
	
	// E uma palavra reservada?
	// Neste caso e feito um tratamento somente para retornar falso ou verdadeiro
	public boolean isReservedWord(String x) {
		try {
			ReservedWord.valueOf(x.toUpperCase());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	// E qualquer coisa fora do padrao?
	public boolean notToken(char x) {
		return x == '@' || x == '$' || x == '^'; 
	}
} 
