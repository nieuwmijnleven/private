package jplus.editor;

public interface BufferManager {

    int add(String text);

    char charAt(BufferType buffer, int start);

    String substring(BufferType buffer, int start, int length);

    String getOriginal();
}
