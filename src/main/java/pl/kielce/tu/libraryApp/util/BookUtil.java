package pl.kielce.tu.libraryApp.util;

import org.apache.commons.lang3.StringUtils;
import pl.kielce.tu.libraryApp.model.Book;

import java.util.List;

/**
 * @author ciepluchs
 */
public abstract class BookUtil {

    private static final String NEXT_LINE = "\n";
    private static final String SPACE_DELIMITER = " ";
    private static final int TABLE_WIDTH = 30;
    private static final int NUMBER_OF_COLUMNS = 5;

    private BookUtil() {
    }

    //TODO refactor, fix numbering
    public static String getTableWithBooks(List<Book> books) {
        String tableHeader = getTableHeader();
        String tableContent = getTableContent(books);
        return tableHeader + "\n" + tableContent;
    }

    private static String getTableContent(List<Book> books) {
        String tableContent = StringUtils.EMPTY;
        for (Book book : books) {
            StringBuilder sb = new StringBuilder();
            String title = book.getTitle();
            String author = book.getAuthor();
            String genre = book.getGenre().toString();
            String isbn = book.getIsbn();
            int quantity = book.getQuantity();
            sb.append(books.indexOf(book)+1);
            sb.append(SPACE_DELIMITER.repeat(9 - sb.length())).append(title);
            sb.append(SPACE_DELIMITER.repeat(TABLE_WIDTH - author.length())).append(author);
            sb.append(SPACE_DELIMITER.repeat(TABLE_WIDTH - genre.length())).append(genre);
            sb.append(SPACE_DELIMITER.repeat(TABLE_WIDTH - isbn.length())).append(isbn);
            sb.append(SPACE_DELIMITER.repeat(TABLE_WIDTH - String.valueOf(quantity).length())).append(quantity);
            sb.append(NEXT_LINE);
            tableContent += sb.toString();
        }
        return tableContent;
    }

    private static String getTableHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------|");
        sb.append("-------------------------------|".repeat(NUMBER_OF_COLUMNS) + "\n");
        sb.append("   No.  |");
        sb.append("             TITLE             |");
        sb.append("             AUTHOR            |");
        sb.append("             GENRE             |");
        sb.append("             ISBN              |");
        sb.append("        AVAILABLE COPIES       |\n");
        sb.append("--------|");
        sb.append("-------------------------------|".repeat(NUMBER_OF_COLUMNS) + "\n");
        return sb.toString();
    }
}
