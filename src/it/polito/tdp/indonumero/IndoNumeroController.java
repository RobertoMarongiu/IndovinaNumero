/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	private int NMAX = 100;
	private int TMAX = 7;
	
	private int segreto; //numero da indovinare
	private int tentativi; // tentativi già fatti
	
	private boolean inGame = false; //se la partita è in corso

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtCurrent"
    private TextField txtCurrent; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxGioco"
    private HBox boxGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNuova(ActionEvent event) {//metodo implementato quando si clicca sul bottone nuova partita
    	
    	this.segreto = (int)(Math.random()*NMAX)+1 ;//più 1 perchè era tra 0 e 99
    	
    	this.tentativi= 0;
    	this.inGame= true;
    	
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	txtCurrent.setText(String.format("%d",this.tentativi));
    	txtMax.setText(String.format("%d",this.TMAX));
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText(String.format("Indovina un numero tra %d e %d.\n",1,NMAX));
    	
    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS = txtTentativo.getText();
    	
    	
    	if(numS.length()==0) {//verifico che abbia messo un numero
    		txtLog.appendText("Devi inserire un numero\n");
    		return;
    	}
    	
    	try {
    	int num= Integer.parseInt(numS);
    		//numero era effettivamente intero
    	
    	if(num<1||num>NMAX) {
    		txtLog.appendText("Valore fuori dall'intervallo consentito!\n");
    		return;
    	}
    	if(num==this.segreto) {
    		//ha indovinato
    		txtLog.appendText("Hai vinto!\n");
    		
    		//chiudere la partita
    		btnNuova.setDisable(false);
    		boxGioco.setDisable(true);
    		this.inGame = false;
    		
    	}
    	else {
    		//tentativo fallito
    		this.tentativi++;
    		txtCurrent.setText(String.format("%d",this.tentativi));
    		
    		if(this.tentativi==this.TMAX){
    			//ha perso
    			txtCurrent.setText(String.format("Hai perso!Il numero era: %d\n",this.segreto));
    			//chiudere la partita
        		btnNuova.setDisable(false);
        		boxGioco.setDisable(true);
        		this.inGame = false;
    		}
    		else {
    			//sono ancora in gioco
    			if(num<segreto) {
    				//troppo basso
    				txtLog.appendText("Troppo basso!\n");
    			}
    			if(num>segreto) {
    				//troppo alto
    				txtLog.appendText("Troppo alto!\n");
    			}
    		}
    	}
    	}catch(NumberFormatException ex){
    		txtLog.appendText("Il dado inserito è numerico\n");
    		return;
    		}
    	}



@FXML // This method is called by the FXMLLoader when initialization is complete
void initialize() {
    assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
    assert txtCurrent != null : "fx:id=\"txtCurrent\" was not injected: check your FXML file 'IndoNumero.fxml'.";
    assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
    assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
    assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
    assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

}
}
