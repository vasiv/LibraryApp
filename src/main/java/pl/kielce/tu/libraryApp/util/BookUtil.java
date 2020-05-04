package pl.kielce.tu.libraryApp.util;

import pl.kielce.tu.libraryApp.model.Book;

import java.util.List;

/**
 * @author ciepluchs
 */
public abstract class BookUtil {

    private static final String NEXT_LINE = "\n";
    private static final String SPACE_DELIMITER = " ";
    private static final int TABLE_WIDTH = 30;

    private BookUtil() {
    }

    //TODO refactor
    public static String getTableWithBooks(List<Book> books) {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------|".repeat(4) + "\n");
        sb.append("          TITLE          |");
        sb.append("          AUTHOR         |");
        sb.append("          GENRE          |");
        sb.append("          ISBN           |\n");
        sb.append("-------------------------|".repeat(4) + "\n");
        for (Book book : books) {
            String title = book.getTitle();
            String author = book.getAuthor();
            String genre = book.getGenre().toString();
            String isbn = book.getIsbn();
            sb.append(title).append(SPACE_DELIMITER.repeat(TABLE_WIDTH - title.length()));
            sb.append(author).append(SPACE_DELIMITER.repeat(TABLE_WIDTH - author.length()));
            sb.append(genre).append(SPACE_DELIMITER.repeat(TABLE_WIDTH - genre.length()));
            sb.append(isbn).append(SPACE_DELIMITER.repeat(TABLE_WIDTH - isbn.length())).append(NEXT_LINE);
        }
        return sb.toString();
    }
}
