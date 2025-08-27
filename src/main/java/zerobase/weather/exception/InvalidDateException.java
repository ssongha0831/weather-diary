package zerobase.weather.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() { super("유효하지 않은 날짜입니다."); }
    public InvalidDateException(String msg) { super(msg); }
}
