public interface Encryptable
{
    public void encrypt(String A);
    public void decrypt(String B);
}

class Password implements Encryptable {
    @Override
    public void encrypt(String pass) {
        int key = 6;
        char[] chars = pass.toCharArray();
        System.out.print("Encrypted password: ");
        for(char f : chars){
            f += 6;
            System.out.print(f);
        }
        System.out.print("\n");
    }

    @Override
    public void decrypt(String as) {
        int key = 6;
        char[] chars = as.toCharArray();
        System.out.print("Decrypted password: ");
        for (char f : chars) {
            f -= key;
            System.out.print(f);
        }

    }
}
class DriverMain{
    public static void main(String[] args) {
        Password acti = new Password();
        acti.encrypt("Sreehari");
        acti.decrypt("Yxkkngxo");
    }
}
