package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entities.ScannerAL;
import entities.Token;
import enums.TokenName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PleaseProvideControllerClassName implements Initializable {
		
		@FXML
		private TableView<Token> table;
		
	    @FXML
	    private TableColumn<Token, String> text;
	    
	    @FXML
	    private TableColumn<Token, TokenName> type;
	    
	    @FXML
	    private TableColumn<Token, Integer> row;
	    
	    @FXML
	    private TableColumn<Token, Integer> column;
	    
		@FXML
		private TableView<Token> table2;
		
	    @FXML
	    private TableColumn<Token, String> text2;
	    
	    @FXML
	    private TableColumn<Token, TokenName> type2;
	    
	    @FXML
	    private TableColumn<Token, Integer> row2;
	    
	    @FXML
	    private TableColumn<Token, Integer> column2;

	    ObservableList<Token> list;
	    ObservableList<Token> list2;
	    
    	ScannerAL sc = new ScannerAL("code.al");
    	Token token;
    	ArrayList<Token> listToken = new ArrayList<Token>();
	    ArrayList<Token> listIncorrectToken = new ArrayList<Token>();
	    
		@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {
			text.setCellValueFactory(new PropertyValueFactory<Token, String>("text"));
			type.setCellValueFactory(new PropertyValueFactory<Token, TokenName>("type"));
			row.setCellValueFactory(new PropertyValueFactory<Token, Integer>("row"));
			column.setCellValueFactory(new PropertyValueFactory<Token, Integer>("column"));
	    	list = FXCollections.observableArrayList(returnTable());
	    	table.setItems(list);
	    	
			text2.setCellValueFactory(new PropertyValueFactory<Token, String>("text"));
			type2.setCellValueFactory(new PropertyValueFactory<Token, TokenName>("type"));
			row2.setCellValueFactory(new PropertyValueFactory<Token, Integer>("row"));
			column2.setCellValueFactory(new PropertyValueFactory<Token, Integer>("column"));
	    	list2 = FXCollections.observableArrayList(returnTable2());
	    	table2.setItems(list2);
		}
		
		public ArrayList<Token> returnTable() {

	    	
	    	
	    	do {
	    		token = sc.getToken();
	    		
	    		if (token != null) {
	    			listToken.add(new Token(token.getType(), token.getText(), token.getRow(), token.getColumn()));
	    			if (token.getType() == TokenName.TOKEN_MAL_FORMADO) listIncorrectToken.add(new Token(token.getType(), token.getText(), token.getRow(), token.getColumn()));
	    		}
	    	} while (token != null);
	    	
	    	
	    	
			return listToken;
		}
		
		public ArrayList<Token> returnTable2() {
			return listIncorrectToken;
		}
}
