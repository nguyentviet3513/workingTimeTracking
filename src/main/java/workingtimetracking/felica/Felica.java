package workingtimetracking.felica;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class Felica {
    final public static short WILDCARD = (short) 0xFFFF;        // ワイルドカード
    final public static short SUICA = 0x03;
    public interface FelicaLib extends Library {

        FelicaLib INSTANCE = (FelicaLib) Native.loadLibrary("felicalib",
                FelicaLib.class);
        Pointer pasori_open(String dummy);
        int pasori_init(Pointer pasoriHandle);
        void pasori_close(Pointer pasoriHandle);
        Pointer felica_polling(Pointer pasoriHandle, short systemCode, byte rfu, byte time_slot);
        void felica_free(Pointer felicaHandle);
        void felica_getidm(Pointer felicaHandle, byte[] data);
        void felica_getpmm(Pointer felicaHandle, byte[] data);
        int felica_read_without_encryption02(Pointer felicaHandle, int serviceCode, int mode, byte addr, byte[] data);
    }
    @SuppressWarnings("serial")
    public class FelicaException extends Exception{
        public FelicaException(String string) {
            super(string);
        }
    }
    Pointer pasoriHandle;
    Pointer felicaHandle;
    /**
     * コンストラクタ。ここでFelicaカードリーダへのハンドルを取得している
     * @throws FelicaException
     */
    public Felica() throws FelicaException {
        pasoriHandle = FelicaLib.INSTANCE.pasori_open(null);
        if(pasoriHandle==null) {
            throw new FelicaException("felicalib.dllを開けません");
        }
        if(FelicaLib.INSTANCE.pasori_init(pasoriHandle)!=0) {
            throw new FelicaException("PaSoRiに接続できません");
        }
    }
    /**
     * Felicaカードリーダに関する処理を終了するさいに呼ぶメソッド
     */
    public void close(){
        if(felicaHandle!=Pointer.NULL) {
            FelicaLib.INSTANCE.felica_free(felicaHandle);
        }
        if(pasoriHandle!=Pointer.NULL) {
            FelicaLib.INSTANCE.pasori_close(pasoriHandle);
        }
    }
    public void polling(short systemCode) throws FelicaException{
        FelicaLib.INSTANCE.felica_free(felicaHandle);
        felicaHandle = FelicaLib.INSTANCE.felica_polling(pasoriHandle, systemCode, (byte)0, (byte)0);
        if(felicaHandle==Pointer.NULL) {
            throw new FelicaException("カード読み取り失敗");
        }
    }
    public byte[] getIDm() throws FelicaException{
        if(felicaHandle==Pointer.NULL) {
            throw new FelicaException("no polling executed.");
        }
        byte[] buf = new byte[8];
        FelicaLib.INSTANCE.felica_getidm(felicaHandle, buf);
        return buf;
    }

    /**
     * FelicaカードのID番号を取得するメソッド
     * @param systemCode システムコード(例えばSuicaは0x03、ワイルドカードは0xFF)
     * @return カードのID番号？
     * @throws FelicaException
     */
    public String getID(short systemCode) throws FelicaException {
        FelicaLib.INSTANCE.felica_free(felicaHandle);
        felicaHandle = FelicaLib.INSTANCE.felica_polling(pasoriHandle, systemCode, (byte)0, (byte)0);
        if(felicaHandle==Pointer.NULL) {
            throw new FelicaException("カード読み取り失敗");
        }
        byte[] buf = new byte[8];
        FelicaLib.INSTANCE.felica_getidm(felicaHandle, buf);
        return String.format("%02X%02X%02X%02X%02X%02X%02X%02X", buf[0],buf[1],buf[2],buf[3],buf[4],buf[5],buf[6],buf[7]);
    }
    /**
     * メイン関数。これだけで読み取りテストを行うことができるように50秒間読み取りを試みる
     * @param args コマンドラインオプション
     * @throws FelicaException
     */
    public static void main(String[] args) throws FelicaException {
        Felica felica = new Felica();
        for(int i=0;i<10;i++) {
            try {
                System.out.println(i+":"+felica.getID((short)0xFF));
            } catch (FelicaException e) {    //読み取れなかったらそのまま
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        felica.close();
    }
}
