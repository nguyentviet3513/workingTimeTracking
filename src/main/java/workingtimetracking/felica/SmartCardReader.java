package workingtimetracking.felica;

import javax.smartcardio.*;
import javax.xml.bind.DatatypeConverter;

public class SmartCardReader implements Runnable {
    private CardTerminal terminal;
    private Card card;
    private static byte[] readUID = { (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x04 };    //IDmを読むための？
    public SmartCardReader() {
        try {
            terminal = TerminalFactory.getDefault().terminals().list().get(0);    //一台目を決め打ち
        } catch (CardException e) {
            System.err.println("読み取り機を取得できませんでした");
            System.exit(-1);
        }
    }
    public String readIDm() throws CardException{
        try {
            card = terminal.connect("*");
            ResponseAPDU response = card.getBasicChannel().transmit(new CommandAPDU(readUID));
            return DatatypeConverter.printHexBinary(response.getData());    //byte[]を文字列に変換
        } finally {
            card.disconnect(false);    //例外が発生してもとりあえず接続を切る
        }
    }
    @Override
    public void run() {
        //カードが置かれて、取られるまでの一連をひとまとめにしているが、感度により、一つのカードについて短期間に連続して起こる可能性があることを想定すべき
        while(true) {
            try {
                terminal.waitForCardPresent(0);
                System.out.println(readIDm());
                terminal.waitForCardAbsent(0);
            } catch (CardException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        //無限ループスレッドでカードが置かれた時にIDmを表示するアプリケーション
        Thread thread = new Thread(new SmartCardReader());
        thread.start();
    }
}