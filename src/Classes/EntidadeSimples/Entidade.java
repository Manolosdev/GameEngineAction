/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeSimples;

import Classes.EntidadeEvento.EntidadeEvento;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Manolo
 */
public abstract class Entidade implements Comparable<Entidade> {

    public Rectangle2D.Double pos;
    public boolean blockEntidade;
    public int IDSprite;

    public Entidade(int IDSprite, int posX, int posY) {
        this.pos = new Rectangle2D.Double(posX, posY, 32, 32);
        this.IDSprite = IDSprite;
        this.blockEntidade = false;
    }

    public abstract void init();

    public abstract void update(int currentTick);

    public abstract void render(Graphics2D g);

    @Override
    public int compareTo(Entidade o) {
        if (this instanceof EntidadeEvento && o instanceof EntidadeEvento) {
            if (this.pos.y > o.pos.y) {
                return -1;
            }
            if (this.pos.y < o.pos.y) {
                return 1;
            }
        }
        return 0;
    }

}
