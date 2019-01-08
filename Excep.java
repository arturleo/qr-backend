public class Excep {
    static class MsgTooLongException extends Exception{
        public MsgTooLongException(String msg){
            super(msg);
        }
    }
    static class ChooingWrongEncodingException extends Exception{
        public ChooingWrongEncodingException(String msg){
            super(msg);
        }
    }

}


