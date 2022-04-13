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
	    private TableColumn<Table, String> text;
	    
	    @FXML
	    private TableColumn<Table, TokenName> type;
	    
	    @FXML
	    private TableColumn<Table, Integer> row;
	    
	    @FXML
	    private TableColumn<Table, Integer> column;

	    ObservableList<Token> list;
	    
	    public void setView() {

	    }

	   
		@Override
		public void initialize(URL url, ResourceBundle resourceBundle) {
			text.setCellValueFactory(new PropertyValueFactory<Table, String>("text"));
			type.setCellValueFactory(new PropertyValueFactory<Table, TokenName>("type"));
			row.setCellValueFactory(new PropertyValueFactory<Table, Integer>("row"));
			column.setCellValueFactory(new PropertyValueFactory<Table, Integer>("column"));
		
	    	list = FXCollections.observableArrayList(returnTable());
	    	table.setItems(list);
		}
		
		public ArrayList<Token> returnTable() {
	    	ScannerAL sc = new ScannerAL("code.al");
	    	Token token;
	    	ArrayList<Token> listToken = new ArrayList<Token>();
	    	
	    	
	    	do {
	    		token = sc.getToken();
	    		
	    		if (token != null) {
	    			listToken.add(new Token(token.getType(), token.getText(), token.getRow(), token.getColumn()));
	    		}
	    	} while (token != null);
	    	
	    	
			return listToken;
		}
}
