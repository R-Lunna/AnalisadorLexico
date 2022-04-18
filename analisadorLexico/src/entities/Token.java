package entities;

import enums.TokenName;

public class Token {
	
	// Tipo do token
	private TokenName type;
	
	// Texto do Token
	private String text;
	
	// Linha do token
	private int row;
	
	// Coluna do Token
	private int column;
	
	public Token() {
		
	}
	
	public Token (TokenName type, String text) {
		this.type = type;
		this.text = text;
	}
	
	public Token(TokenName type, String text, int row, int column) {
		this.type = type;
		this.text = text;
		this.row = row;
		this.column = column;
	}

	public TokenName getType() {
		return type;
	}
	
	public void setType(TokenName type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
}
