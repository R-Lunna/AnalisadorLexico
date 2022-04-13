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

	    ObservableList<Token> list;
	    
		@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {
			text.setCellValueFactory(new PropertyValueFactory<Token, String>("text"));
			type.setCellValueFactory(new PropertyValueFactory<Token, TokenName>("type"));
			row.setCellValueFactory(new PropertyValueFactory<Token, Integer>("row"));
			column.setCellValueFactory(new PropertyValueFactory<Token, Integer>("column"));
		
	    	list = FXCollections.observableArrayList(returnTable());
	    	table.setItems(list);
		}
		
		public ArrayList<Token> returnTable() {
	    	ScannerAL sc = new ScannerAL("code.al");
	    	Token token;
	    	ArrayList<Token> listToken = new ArrayList<Token>();
	    	
	    	do {
	    		token = sc.getToken();
	    		
	    		if (token != null) listToken.add(new Token(token.getType(), token.getText(), token.getRow(), token.getColumn()));
	    	} while (token != null);
	    	
	    	
			return listToken;
		}
}
