/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Abilidades;

import Classes.Core.ImageManager;
import Classes.EntidadeSimples.Entidade;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Seven
 */
public class Explossao1 extends AbilidadeMapa {

    public int contador;
    public final int DELAY = 30;

    public Explossao1(int posX, int posY) {
        pos = new Rectangle2D.Double(posX, posY, 32, 32);
        contador = 0;
        active = true;
        abilidadeMapaConstante = true;
        abilidadeMapaAtiva = true;
    }

    @Override
    public void init() {
    }

    @Override
    public void update(int currentTick) {
        if (contador < DELAY) {
            contador++;
        } else {
            active = false;
        }
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage img;
        if (contador < 10) {
            //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            img = ImageManager.getInstance().getImageAbilidade(2, 0, 0, 20, 19);
            g.drawImage(img, (int) pos.x, (int) pos.y, null);
        } else if (contador < 20) {
            //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            img = ImageManager.getInstance().getImageAbilidade(2, 20, 0, 20, 19);
            g.drawImage(img, (int) pos.x, (int) pos.y, null);
        } else {
            //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            img = ImageManager.getInstance().getImageAbilidade(2, 40, 0, 20, 19);
            g.drawImage(img, (int) pos.x, (int) pos.y, null);
        }
        g.setComposite(AlphaComposite.SrcOver);

    }

    @Override
    public void terminar(Entidade e) {
    }
}
