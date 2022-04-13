package gui;

import enums.TokenName;

public class Table {
	private String text;
	private TokenName type;
	private int row;
	private int column;
	
	public Table(String text, TokenName type, int row, int column) {
		this.text = text;
		this.type = type;
		this.row = row;
		this.column = column;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TokenName getType() {
		return type;
	}

	public void setType(TokenName type) {
		this.type = type;
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
