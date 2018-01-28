package org.lib.richclient.view;

import org.lib.richclient.MyAlert;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import org.lib.business.LibraryFacadeService;
import org.lib.model.MyBook;
import org.lib.richclient.LibObservable;
import org.lib.utils.LibException;
import org.lib.utils.Messages;
import org.lib.richclient.controller.DeleteSelectedAction;

public class BookPane extends TitledPane implements Observer {

    public TableView<MyBook> getTableView() {
        return tableView;
    }

    public ObservableList<MyBook> getBooks() {
        return books;
    }

    private ObservableList<MyBook> books = FXCollections.observableArrayList();
    private TableView<MyBook> tableView;

    public BookPane() {
        super(Messages.Books.createMessage(), null);
        setContent(createContent());
        LibObservable.instance.addObserver(this);
    }

    public ObservableList<MyBook> getSelectedBooks() {
        return getTableView().getSelectionModel().getSelectedItems();
    }

    private Node createContent() {
        tableView = new TableView<>();
        TableColumn<MyBook, Integer> idCol = new TableColumn<>(Messages.Id.createMessage());
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        TableColumn<MyBook, String> titleCol = new TableColumn<>(Messages.Title.createMessage());
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<MyBook, String> authCol = new TableColumn<>(Messages.Author.createMessage());
        authCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        tableView.getColumns().addAll(idCol, titleCol, authCol);
        tableView.setItems(getBooks());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getSelectionModel().getSelectedItems().
                addListener(new ListChangeListener<MyBook>() {
                    @Override
                    public void onChanged(ListChangeListener.Change<? extends MyBook> c) {
                        DeleteSelectedAction.inst.update(null, null);
                    }
                });

        return tableView;
    }

    public void refresh() {
        if (!LibraryFacadeService.service().isConnected()) {
            return;
        }
        try {
            getBooks().setAll(LibraryFacadeService.service().allBook().getBooks());
        } catch (LibException ex) {
            MyAlert.error(ex);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        refresh();
    }

}
