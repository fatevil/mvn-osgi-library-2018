package org.lib.protocol;

import javax.xml.bind.annotation.XmlRootElement;
import org.lib.business.LibraryFacadeService;
import org.lib.model.BookId;
import org.lib.utils.LibException;

@XmlRootElement
public class DeleteBook extends Command {

    public DeleteBook() {
    }

    private BookId bookId;

    @Override
    public <T> T execute() throws LibException {
        LibraryFacadeService.service().delete(bookId);
        return (T) OK;
    }

    public DeleteBook(BookId bookId) {
        this.bookId = bookId;
    }

    /**
     * @return the bookId
     */
    public BookId getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

}
