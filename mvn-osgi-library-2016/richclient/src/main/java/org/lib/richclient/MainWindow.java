package org.lib.richclient;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lib.model.MyBook;
import org.lib.richclient.controller.FileMenu;
import org.lib.richclient.view.BookPane;
import org.lib.richclient.view.LibToolBar;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainWindow extends Stage {

	public static MainWindow instance = new MainWindow();

	private MenuBar menuBar;
	private LibToolBar libToolBar;
	private BundleContext context;
	private BookPane bp;

	public void addMenu(Menu menu) {
		menuBar.getMenus().add(menu);
	}

	public void addTollButton(Button but) {
		libToolBar.getItems().add(but);
	}

	public ObservableList<MyBook> getBooks() {
		return bp.getBooks();
	}

	public ObservableList<MyBook> getSelectedBooks() {
		return bp.getSelectedBooks();
	}

	public MainWindow() {
		instance = this;
		setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				stop();
			}
		});

		bp = new BookPane();
		bp.refresh();
		VBox vbox = new VBox();
		vbox.getChildren().addAll(menuBar = new MenuBar(new FileMenu()), libToolBar = new LibToolBar(), bp);
		Scene s = new Scene(vbox, 800, 600);
		setScene(s);
		show();
	}

	public LibToolBar getLibToolBar() {
		return libToolBar;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public BundleContext getContext() {
		return context;
	}

	public void setContext(BundleContext aContext) {
		context = aContext;
	}

	public void stop() {
		Framework p = (Framework) context.getBundle(0);
		try {
			p.waitForStop(1000);
			p.stop();
		} catch (BundleException | InterruptedException ex) {
			Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// @Override
	// public void start(Stage primaryStage) throws Exception {
	// primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	// @Override
	// public void handle(WindowEvent event) {
	// stop();
	// }
	// });
	//
	// bp = new BookPane();
	// bp.refresh();
	// VBox vbox = new VBox();
	// vbox.getChildren().addAll(menuBar = new MenuBar(new FileMenu()),
	// libToolBar = new LibToolBar(), bp);
	// Scene s = new Scene(vbox, 800, 600);
	// primaryStage.setScene(s);
	// primaryStage.show();
	// }

}
