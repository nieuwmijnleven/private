package jplus.editor;

public class DefaultBufferManager implements BufferManager {
    private final StringBuilder originalBuffer;
    private final StringBuilder addBuffer;

    private int absStart;

    public DefaultBufferManager(String original, int absStart) {
        this.originalBuffer = new StringBuilder(original);
        this.addBuffer = new StringBuilder();
        this.absStart = absStart;
    }

    public DefaultBufferManager(String original) {
        this(original, 0);
    }

    // 문자열 추가 (ADD buffer)
    @Override
    public int add(String text) {
        int start = absStart + addBuffer.length();
        addBuffer.append(text);
        return start;
    }

    public String getOriginal() {
        return originalBuffer.toString();
    }

    // 단일 문자 접근
    @Override
    public char charAt(BufferType buffer, int start) {
        int relStart = start - absStart;
        return buffer == BufferType.ORIGINAL ? charAtFromOriginalBuffer(relStart)
                                             : charAtFromAddBuffer(relStart);
    }

    private char charAtFromAddBuffer(int relStart) {
        if (relStart < 0 || relStart >= addBuffer.length()) throw new IndexOutOfBoundsException();
        return addBuffer.charAt(relStart);
    }

    private char charAtFromOriginalBuffer(int relStart) {
        if (relStart < 0 || relStart >= originalBuffer.length()) throw new IndexOutOfBoundsException();
        return originalBuffer.charAt(relStart);
    }

    // 부분 문자열 접근
    @Override
    public String substring(BufferType buffer, int start, int length) {
        int relStart = start - absStart;
        return buffer == BufferType.ORIGINAL ? substringFromOrignalBufffer(relStart, length)
                                             : substringFromAddBuffer(relStart, length);
    }

    private String substringFromAddBuffer(int relStart, int length) {
        if (relStart < 0 || relStart + length > addBuffer.length()) throw new IndexOutOfBoundsException();
        return addBuffer.substring(relStart, relStart + length);
    }

    private String substringFromOrignalBufffer(int relStart, int length) {
        if (relStart < 0 || relStart + length > originalBuffer.length()) throw new IndexOutOfBoundsException();
        return originalBuffer.substring(relStart, relStart + length);
    }
}
