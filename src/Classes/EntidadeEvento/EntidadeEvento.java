/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeEvento;

import Classes.EntidadeSimples.Entidade;
import Classes.EntidadeSimples.EntidadeMedia;
import Classes.Core.ImageManager;
import java.awt.Graphics2D;

/**
 *
 * @author Seven
 */
public class EntidadeEvento extends EntidadeMedia {

    public static final int COLISAO_BAIXO = 0;
    public static final int COLISAO_ESQUERDA = 1;
    public static final int COLISAO_CIMA = 2;
    public static final int COLISAO_DIREITA = 3;

    public Entidade[] colisaoEntidades;

    public EntidadeEvento(int IDSprite,int posX, int posY) {
        super(IDSprite,posX, posY);
        this.entidadeTopo = false;
        this.colisaoEntidades = new Entidade[4];
    }

    @Override
    public void init() {
    }

    @Override
    public void update(int currentTick) {
    }

    @Override
    public void render(Graphics2D g) {
        if (this.IDSprite != 0) {
            g.drawImage(ImageManager.getInstance().getSpriteMapa(this.IDSprite), (int) this.pos.x, (int) this.pos.y, null);
        } else {
            g.drawImage(ImageManager.getInstance().getSpriteMapa(1), (int) this.pos.x, (int) this.pos.y, null);
        }
    }
}
