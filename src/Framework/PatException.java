package Framework;

public class PatException extends Exception {

    public PatException(String e, String clase, String metodo) {
        System.out.println("[Error ]" + clase + "." + metodo + ":" + e);
    }

    // @Override
    // public String getMessage(){
    // return "IPs..! se presento un error..";
    // }
}
