package sk.upjs.ics.deutsch;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class WordTableModel extends AbstractTableModel {
    private static final int NUMBER_OF_COLUMNS = 2;
    private static final String[] COLUMNS_NAMES = {"Deu", "Eng"};
    private static final Class[] COLUMNS_TYPES = {String.class, String.class};
    private WordDao wordDao = DaoFactory.INSTANCE.getWordDao();
    private List<Word> words = new LinkedList<>();
    
    @Override
    public int getRowCount() {
        return words.size();
    }

    @Override
    public int getColumnCount() {
        return NUMBER_OF_COLUMNS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Word word = words.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return word.getDeu();
            case 1:
                return word.getEng();
            default:
                return "???";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return COLUMNS_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMNS_TYPES[columnIndex];
    }
    
    public void refresh() {
        words = wordDao.getAll();
        fireTableDataChanged();
    }
    
    public Word getByRowIndex(int idx) {
        return words.get(idx);
    }
}
