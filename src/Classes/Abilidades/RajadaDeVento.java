/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Abilidades;

import Classes.EntidadeEvento.EntidadeEvento;
import Classes.Core.ImageManager;
import Classes.EntidadeEvento.EntidadeMob;
import Classes.EntidadeSimples.Entidade;
import Classes.Game.Jogo;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Seven
 */
public class RajadaDeVento extends AbilidadeMapa {

    public int contador;
    public final int DELAY = 60;

    public RajadaDeVento(int posX, int posY, int direcao, EntidadeEvento e) {
        pos = new Rectangle2D.Double(posX, posY, 32, 32);
        donoAbilidade = e;
        abilidadeMapaConstante = true;
        this.direcao = direcao;
        contador = 0;
        active = true;
        dano = 10;
    }

    @Override
    public void init() {
    }

    @Override
    public void update(int currentTick) {
        if (contador < DELAY) {
            contador++;
            if (direcao == 0) {
                pos.y += 2;
            } else if (direcao == 1) {
                pos.x -= 2;
            } else if (direcao == 2) {
                pos.y -= 2;
            } else {
                pos.x += 2;
            }
        } else {
            active = false;
        }
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage img;
        if (contador < 20) {
            g.setComposite(AlphaComposite.SrcOver);
        } else if (contador < 40) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        } else {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        if (direcao == 0) {
            img = ImageManager.getInstance().getImageAbilidade(1, 0, 0, 40, 40);
            g.drawImage(img, (int) pos.x, (int) pos.y, null);
        } else if (direcao == 1) {
            img = ImageManager.getInstance().getImageAbilidade(1, 0, 40, 40, 40);
            g.drawImage(img, (int) pos.x, (int) pos.y, null);
        } else if (direcao == 2) {
            img = ImageManager.getInstance().getImageAbilidade(1, 0, 120, 40, 40);
            g.drawImage(img, (int) pos.x, (int) pos.y, null);
        } else {
            img = ImageManager.getInstance().getImageAbilidade(1, 0, 80, 40, 40);
            g.drawImage(img, (int) pos.x, (int) pos.y, null);
        }
        g.setComposite(AlphaComposite.SrcOver);

    }

    @Override
    public void terminar(Entidade e) {
        if (direcao == 0) {
            if (donoAbilidade instanceof EntidadeMob) {
                Jogo.abilidadesMob.add(new Explossao2((int) (pos.x + 10), (int) (pos.y + pos.height - 5)));
            } else {
                Jogo.abilidadesJogador.add(new Explossao2((int) (pos.x + 20), (int) pos.y + 8));
            }
        } else if (direcao == 1) {
            if (donoAbilidade instanceof EntidadeMob) {
                Jogo.abilidadesMob.add(new Explossao2((int) pos.x - 10, (int) pos.y + 8));
            } else {
                Jogo.abilidadesJogador.add(new Explossao2((int) (pos.x + 20), (int) pos.y + 8));
            }
        } else if (direcao == 2) {
            if (donoAbilidade instanceof EntidadeMob) {
                Jogo.abilidadesMob.add(new Explossao2((int) pos.x + 6, (int) pos.y - 5));
            } else {
                Jogo.abilidadesJogador.add(new Explossao2((int) (pos.x + 20), (int) pos.y + 8));
            }
        } else if (direcao == 3) {
            if (donoAbilidade instanceof EntidadeMob) {
                Jogo.abilidadesMob.add(new Explossao2((int) (pos.x + 20), (int) pos.y + 8));
            } else {
                Jogo.abilidadesJogador.add(new Explossao2((int) (pos.x + 20), (int) pos.y + 8));
            }
        }
    }

}
