package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox hboxControlloPartita;

    @FXML
    private TextField txtRimasti;
    //numero tentativi rimasti
    
    @FXML
    private HBox hboxControlloTentativi;

    @FXML
    private TextField txtTentativo;
    //tentativo inserito dall'utente
    @FXML
    private TextArea txtMessaggi;

    @FXML
    void HandleNuovaPartita(ActionEvent event) {
    	//Gestisce inizio nuova partita
    	
    	//logica del gioco
    	this.segreto = (int)(Math.random()*NMAX)+1;
		this.tentativiFatti = 0;
		this.inGioco = true;
    	
		//Gestione dell'interfaccia
		hboxControlloPartita.setDisable(true);
		hboxControlloTentativi.setDisable(false);
		txtMessaggi.clear();
		txtRimasti.setText(Integer.toString(this.TMAX));
    }

    @FXML
    void handleProvaTentativo(ActionEvent event) {
    	
    	//Leggiil valore del tentativo
    	String ts = txtTentativo.getText();
    	
    	//Controlla se è valido
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	}catch(NumberFormatException e) {
    		//la stringa inserita non è numero valido
    		txtMessaggi.appendText("Non è un numero valido!\n");
    		return ;
    	}
    	
    	tentativiFatti++;
    	
    	//Controlla se ha indovinato
    	if(tentativo == segreto) {
    		txtMessaggi.appendText("Complimenti,hai indovinato in "+tentativiFatti+" tentativi!\n");
    		
    		hboxControlloPartita.setDisable(false);
    		hboxControlloTentativi.setDisable(true);
    		this.inGioco=false;
    		return ;
    	}
    	
    	//Verifica se ha esaurito i tentativi  --> fine partita
    	if(tentativiFatti==TMAX) {
    		txtMessaggi.appendText("Hai Perso! Il numero segreto era"+segreto+"!\n");
    		hboxControlloPartita.setDisable(false);
    		hboxControlloTentativi.setDisable(true);
    		this.inGioco=false;
    		return ;
    	}
    	//Informa se era troppo alto/basso  --> stampa messaggio
    	if(tentativo<segreto) {
    		txtMessaggi.appendText("Tentativo troppo BASSO\n");
    	}
    	else {
    		txtMessaggi.appendText("Tentativo troppo ALTO\n");
    	}
    	//Aggiornare interfaccia con n. tentativi rimasti
    	txtRimasti.setText(Integer.toString(TMAX - tentativiFatti));
    }

    @FXML
    void initialize() {
        assert hboxControlloPartita != null : "fx:id=\"hboxControlloPartita\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Numero.fxml'.";
        assert hboxControlloTentativi != null : "fx:id=\"hboxControlloTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
        assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Numero.fxml'.";

    }
}
