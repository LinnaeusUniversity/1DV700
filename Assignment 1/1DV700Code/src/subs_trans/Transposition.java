package subs_trans;

/**
 * The type Transposition.
 */
public class Transposition {
private String text="";
private char[] tempValue;
    private String newText = "";
    private static Transposition _occurrence;

    /**
     * Instance transposition.
     *
     * @return the transposition
     */
    public static Transposition instance() {
        if (_occurrence == null) {
            _occurrence = new Transposition();
        }
        return _occurrence;
    }

    /**
     * Instantiates a new Transposition.
     */
    public Transposition() {
        initialize();
    }

    private void initialize() {
    }

    /**
     * Encryption string.
     *
     * @param plainText the plain text
     * @param key       the key
     * @return the string
     */
    public String encryption(String plainText, int key) {
        StringBuilder builder = new StringBuilder();
        text = plainText.substring(String.valueOf(key).length());
        text += plainText.substring(0, String.valueOf(key).length());

        tempValue = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            tempValue[i] = text.charAt(i);
        }

        for (int i = 0; i < tempValue.length / 2; i++) {
            char c = tempValue[i];
            tempValue[i] = tempValue[tempValue.length - 1 - i];
            tempValue[tempValue.length - 1 - i] = c;
        }
        newText = String.copyValueOf(tempValue);
        builder.append(newText);

        System.out.println("The encrypted cipher text is :" + builder.toString());
        return builder.toString();

    }

    /**
     * Decryption string.
     *
     * @param plainText the plain text
     * @param key       the key
     * @return the string
     */
    public String decryption(String plainText, int key) {

        if(text.length() > key){
            System.err.println("The text you are willing to encrypt is less than the your key length!!!");
            System.exit(-1);
        }

        StringBuilder builder = new StringBuilder();
        text = plainText.substring(String.valueOf(key).length());
        text += plainText.substring(0, String.valueOf(key).length());

        tempValue = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            tempValue[i] = text.charAt(i);
        }
        for (int i = tempValue.length / 2; i >= 0; i--) {
            char a = tempValue[i];
            tempValue[i] = tempValue[tempValue.length - 1 - i];
            tempValue[tempValue.length - 1 - i] = a;
        }
        newText = String.copyValueOf(tempValue);
        builder.append(newText);

        System.out.println("The encrypted cipher text is :" + builder.toString());
        return builder.toString();

    }
}