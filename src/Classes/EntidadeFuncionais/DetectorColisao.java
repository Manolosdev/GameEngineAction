/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeFuncionais;

import Classes.EntidadeSimples.Entidade;
import Classes.EntidadeEvento.EntidadeEvento;
import Classes.Game.Jogo;
import java.awt.geom.Rectangle2D;


/**
 *
 * @author Seven
 */
public class DetectorColisao {

    public void update(int currentTick) {
        for (Entidade o : Jogo.entidades) {
            for (int i = 0; i < 4; i++) {
                if (o instanceof EntidadeEvento) {
                    ((EntidadeEvento) o).colisaoEntidades[i] = null;
                }
            }
        }
        for (int i1 = 0; i1 < Jogo.entidades.size(); i1++) {
            if (Jogo.entidades.get(i1) instanceof EntidadeEvento) {
                EntidadeEvento o1 = (EntidadeEvento) Jogo.entidades.get(i1);
                for (int i2 = i1 + 1; i2 < Jogo.entidades.size(); i2++) {
                    Entidade o2 = Jogo.entidades.get(i2);
                    if (o1.pos.intersects(o2.pos) && o2.blockEntidade == true) {
                        Rectangle2D rect = o1.pos.createIntersection(o2.pos);
                        if (rect.getWidth() > rect.getHeight()) {
                            if (o1.pos.getCenterY() < o2.pos.getCenterY()) {
                                o1.colisaoEntidades[EntidadeEvento.COLISAO_BAIXO] = o2;
                                if (o2 instanceof EntidadeEvento) {
                                    ((EntidadeEvento) o2).colisaoEntidades[EntidadeEvento.COLISAO_CIMA] = o1;
                                }
                            } else {
                                if (o2 instanceof EntidadeEvento) {
                                    ((EntidadeEvento) o2).colisaoEntidades[EntidadeEvento.COLISAO_BAIXO] = o1;
                                }
                                o1.colisaoEntidades[EntidadeEvento.COLISAO_CIMA] = o2;
                            }
                        } else {
                            if (o1.pos.getCenterX() < o2.pos.getCenterX()) {
                                o1.colisaoEntidades[EntidadeEvento.COLISAO_DIREITA] = o2;
                                if (o2 instanceof EntidadeEvento) {
                                    ((EntidadeEvento) o2).colisaoEntidades[EntidadeEvento.COLISAO_ESQUERDA] = o1;
                                }
                            } else {
                                if (o2 instanceof EntidadeEvento) {
                                    ((EntidadeEvento) o2).colisaoEntidades[EntidadeEvento.COLISAO_DIREITA] = o1;
                                }
                                o1.colisaoEntidades[EntidadeEvento.COLISAO_ESQUERDA] = o2;
                            }
                        }
                    }
                }
            }
        }
    }
}
