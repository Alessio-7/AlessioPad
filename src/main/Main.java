package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

import gui.Finestra;
import gui.MenuBar;
import gui.ScrollPane;
import gui.TextArea;
import preferenze.PreferenzeGUI;
import utility.ListaOggettiMenu;
import utility.WrongValueException;

public class Main {

	public PreferenzeGUI gui;

	TextArea textArea;
	TextArea righe;
	final Font font = new Font( "JetBrains Mono NL SemiBold", Font.PLAIN, 20 );

	public static void main( String[] args ) {
		Main m = new Main();
	}

	public Main() {
		gui = new PreferenzeGUI();
		textArea = gui.creaTextArea( 1, 1 );
		righe = gui.creaTextArea( "1", false );
		ScrollPane sc = gui.creaScrollPane( null );
		sc.getViewport().add( textArea );
		sc.setRowHeaderView( righe );

		righe.setFont( font );
		textArea.setFont( font );
		textArea.setBorder( gui.bordi.bordoVuotoGenerico() );
		textArea.getDocument().addDocumentListener( righeAreaTesto() );

		Finestra f = gui.creaFinestra( "AlessioPad", null, creaMenuBar(), false );
		f.add( sc, BorderLayout.CENTER );
		f.setVisible( true );
	}

	private MenuBar creaMenuBar() {
		ListaOggettiMenu menu = new ListaOggettiMenu();
		ListaOggettiMenu file = new ListaOggettiMenu();
		menu.add( "File", file );
		file.add( "Nuovo", new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
			}
		} );
		file.add( "Apri", new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
			}
		} );
		file.add( "Salva", new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
			}
		} );
		file.add( "Salva come", new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
			}
		} );
		file.add( "Esci", new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
			}
		} );

		try {
			return MenuBar.creaMenuBarDaListaOggettiMenu( gui, menu );
		} catch ( WrongValueException e1 ) {
			e1.printStackTrace();
		}
		return null;
	}

	private DocumentListener righeAreaTesto() {
		return new DocumentListener() {
			public String getText() {
				int caretPosition = textArea.getDocument().getLength();
				Element root = textArea.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty( "line.separator" );
				for ( int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++ ) {
					text += i + System.getProperty( "line.separator" );
				}
				return text;
			}

			@Override
			public void changedUpdate( DocumentEvent de ) {
				righe.setText( getText() );
			}

			@Override
			public void insertUpdate( DocumentEvent de ) {
				righe.setText( getText() );
			}

			@Override
			public void removeUpdate( DocumentEvent de ) {
				righe.setText( getText() );
			}
		};
	}

}
