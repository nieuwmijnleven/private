package jplus.editor;

public interface BufferManager {
    // 문자열 추가 (ADD buffer)
    int add(String text);

    // 단일 문자 접근
    char charAt(BufferType buffer, int start);

    // 부분 문자열 접근
    String substring(BufferType buffer, int start, int length);

    String getOriginal();
}
