package workingtimetracking.felica;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;


import workingtimetracking.felica.Felica;

@SuppressWarnings("serial")
public class FelicaIdReader extends JFrame {
    private Felica felica;
    private JLabel label = new JLabel("ID=????????????????");
    boolean active = true;

    public FelicaIdReader() {
        super("フェリカカードリーダ");
        try {
            felica = new Felica();
        } catch (Felica.FelicaException e) {
            System.err.println("フェリカカードリーダにアクセスできません");
            System.exit(-1);
        }
        // 終了時処理の追加
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                active = false;
                synchronized (felica) {
                    felica.close();
                }
                System.exit(0);
            }
        });
        add(label);
        pack();
        // IDが全部表示されないこともあるため、幅を変更する
        Dimension dim = getPreferredSize();
        if (dim.getWidth() < 200) {
            setPreferredSize(new Dimension(200, (int) dim.getHeight()));
            pack();
        }
        setVisible(true);
        // Felicaカードの読み取りループ
        String Id = null;
        while (active) {
            try {
                Id = felica.getID(Felica.WILDCARD);
            } catch (Felica.FelicaException e) {
                Id = null;
            }
            if (Id != null) {
                label.setText("id=" + Id);
            } else {
                label.setText("現在カードは置かれていません");
            }
            try {
                Thread.sleep(500); // 0.5秒おきに読み取り
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new FelicaIdReader();
    }
}