/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.huehue;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author rudieri
 */
public class CarregaImagem {
    private void carregarImagem(){
        try {
            ImageIcon img = new ImageIcon(ImageIO.read(getClass().getResource("/br/huehue/res/img/alert2.png")));
            JTesteV1 jTesteV1 = new JTesteV1(null, true);
        } catch (IOException ex) {
            Logger.getLogger(JHueHue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
