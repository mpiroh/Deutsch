package sk.upjs.ics.deutsch;

import java.util.List;

public interface WordDao {
    public List<Word> getAll();
    public void add(Word word);
    public void delete(Word word);
    public Word getByRowIndex(int idx);
}
