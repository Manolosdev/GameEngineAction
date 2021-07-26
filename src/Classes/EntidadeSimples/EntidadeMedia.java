/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeSimples;

import java.awt.Graphics2D;

/**
 *
 * @author Manolo
 */
public class EntidadeMedia extends EntidadeSimples {

    public boolean entidadeTopo;

    public EntidadeMedia(int IDSprite, int posX, int posY) {
        super(IDSprite, posX, posY);
        this.entidadeTopo = false;
    }

    @Override
    public void init() {
    }

    @Override
    public void update(int currentTick) {
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }

}
