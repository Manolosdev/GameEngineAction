/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeFuncionais;

import Classes.EntidadeEvento.EntidadeEvento;
import Classes.EntidadeEvento.EntidadeJogador;
import Classes.EntidadeEvento.EntidadeMob;
import Classes.Game.Jogo;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Manolo
 */
public class InterfaseJogo {

    public final int INTERFASE_LOGIN = 0;
    public final int INTERFASE_CLASSE = 1;
    public final int INTERFASE_JOGO = 2;

    public int estado;
    public boolean active;
    public String nomeJogador;

    public Jogo jogo;

    public InterfaseJogo(Jogo jogo) {
        this.jogo = jogo;
        this.active = true;
        this.estado = 2;
        this.nomeJogador = "JOGADOR_NOME";

    }

    public void update(int currentTick) {

    }

    public void render(Graphics2D g) {
        if (this.active) {
            if (estado == INTERFASE_JOGO) {

                //Barra de vida
                g.setColor(Color.white);
                g.drawRoundRect(38, 60, 100, 13, 3, 3);
                int porcentagemVida = ControleJogador.getInstance().getVidaAtual() * 100 / ControleJogador.getInstance().getVidaMaxima();
                g.setColor(Color.green);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g.fillRect(41, 63, 95 * porcentagemVida / 100, 8);
                g.setComposite(AlphaComposite.SrcOver);
                g.setFont(new Font("", Font.BOLD, 10));
                g.setColor(Color.white);
                g.drawString("" + ControleJogador.getInstance().getVidaAtual() + " / " + ControleJogador.getInstance().getVidaMaxima(), 68, 70);
                //Barra de mana
                g.setColor(Color.white);
                g.drawRoundRect(38, 75, 100, 13, 3, 3);
                int porcentagemMana = ControleJogador.getInstance().getManaAtual() * 100 / ControleJogador.getInstance().getManaMaxima();
                g.setColor(Color.blue);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g.fillRect(41, 78, 95 * porcentagemMana / 100, 8);
                g.setComposite(AlphaComposite.SrcOver);
                g.setColor(Color.white);
                g.drawString("" + ControleJogador.getInstance().getManaAtual() + " / " + ControleJogador.getInstance().getManaMaxima(), 68, 85);
                g.setFont(new Font("", Font.BOLD, 14));
                g.drawString(ControleJogador.getInstance().getNome(), 40, 55);

                //Cx Level
                g.setColor(Color.darkGray);
                g.fillRect(21, 66, 19, 19);
                g.setColor(Color.white);
                g.drawRoundRect(20, 65, 20, 20, 3, 3);
                g.setFont(new Font("", Font.BOLD, 14));
                if (ControleJogador.getInstance().getNivelAtual() < 10) {
                    g.drawString("" + ControleJogador.getInstance().getNivelAtual(), 30, 79);
                } else {
                    g.drawString("" + ControleJogador.getInstance().getNivelAtual(), 22, 79);
                }

                //Cx inferiores
                //Cx de experiencia
                g.setFont(new Font("", Font.BOLD, 10));

                int xpAtual = ControleJogador.getInstance().getXpAtual();
                int xpProximoNivel = (int) ControleJogador.getInstance().getProximoNivel();
                int porcentagem = (xpAtual * 100) / xpProximoNivel;
                g.drawRoundRect(20, jogo.getHeight() - 22, jogo.getWidth() - 40, 10, 3, 3);
                g.setColor(Color.darkGray);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g.fillRect(20, jogo.getHeight() - 22, jogo.getWidth() - 40, 10);
                g.setComposite(AlphaComposite.SrcOver);
                g.setColor(Color.MAGENTA);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g.fillRect(23, jogo.getHeight() - 19, (jogo.getWidth() - 43 + 7) * porcentagem / 100, 6);
                g.setComposite(AlphaComposite.SrcOver);
                g.setColor(Color.white);
                g.drawString("" + porcentagem + " %", jogo.getWidth() / 2, jogo.getHeight() - 13);

                g.setFont(new Font("", Font.BOLD, 12));
                g.setComposite(AlphaComposite.SrcOver);
                g.setColor(Color.white);
                g.drawRoundRect(jogo.getWidth() / 2 - 19, jogo.getHeight() - 58, 33, 32, 3, 3);
                g.drawRoundRect(jogo.getWidth() / 2 + 19, jogo.getHeight() - 58, 33, 32, 3, 3);
                g.setFont(new Font("", Font.BOLD, 12));
                g.drawString("Q", jogo.getWidth() / 2 + 2, jogo.getHeight() - 30);
                g.drawString("E", jogo.getWidth() / 2 + 43, jogo.getHeight() - 30);

                //CX dialogo
                g.setColor(Color.darkGray);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g.fillRect(20, jogo.getHeight() - 150, 250, 100);
                g.setComposite(AlphaComposite.SrcOver);
                g.setColor(Color.white);
                g.drawRoundRect(20, jogo.getHeight() - 150, 250, 100, 3, 3);
                g.setColor(Color.darkGray);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g.fillRect(20, jogo.getHeight() - 46, 250, 20);
                g.setComposite(AlphaComposite.SrcOver);
                g.setColor(Color.white);
                g.drawRoundRect(20, jogo.getHeight() - 46, 250, 20, 3, 3);

                //Cx Mapa
                g.setColor(Color.darkGray);
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g.fillRect(jogo.getWidth() - 100, 60, 84, 80);
                g.setComposite(AlphaComposite.SrcOver);
                g.setColor(Color.white);
                g.drawRoundRect(jogo.getWidth() - 101, 59, 85, 80, 3, 3);

            }
        }
    }

    public void renderEntidade(Graphics2D g, EntidadeEvento e) {
        if (this.active) {
            g.setColor(Color.white);
            //Jogador
            if (e instanceof EntidadeJogador) {
                g.setFont(new Font("", Font.BOLD, 10));
                g.drawString(ControleJogador.getInstance().getNome(), (int) e.pos.x - 5, (int) (e.pos.y - e.pos.height));
                //Cx nivel
                g.setColor(Color.darkGray);
                g.fillRect((int) e.pos.x - 12, (int) (e.pos.y - e.pos.height + 3), 11, 9);
                g.setColor(Color.white);
                g.drawRoundRect((int) e.pos.x - 13, (int) (e.pos.y - e.pos.height + 2), 12, 10, 3, 3);
                g.setFont(new Font("", Font.BOLD, 9));
                if (ControleJogador.getInstance().getNivelAtual() < 10) {
                    g.drawString("" + ControleJogador.getInstance().getNivelAtual(), (int) e.pos.x - 8, (int) (e.pos.y - e.pos.height + 11));
                } else {
                    g.drawString("" + ControleJogador.getInstance().getNivelAtual(), (int) e.pos.x - 11, (int) (e.pos.y - e.pos.height + 11));
                }

                //Barra de vida
                int porcentagemVida = ControleJogador.getInstance().getVidaAtual() * 100 / ControleJogador.getInstance().getVidaMaxima();
                g.drawRoundRect((int) e.pos.x, (int) (e.pos.y - e.pos.height + 5), 32, 5, 2, 2);
                if (porcentagemVida >= 70) {
                    g.setColor(Color.green);
                } else if (porcentagemVida >= 30) {
                    g.setColor(Color.yellow);
                } else {
                    g.setColor(Color.red);
                }
                g.fillRect((int) e.pos.x + 2, (int) (e.pos.y - e.pos.height + 7), 29 * porcentagemVida / 100, 2);
                //Barra de mana
                g.setColor(Color.white);
                int porcentagemMana = ControleJogador.getInstance().getManaAtual() * 100 / ControleJogador.getInstance().getManaMaxima();
                g.drawRoundRect((int) e.pos.x, (int) (e.pos.y - e.pos.height + 12), 32, 5, 2, 2);
                g.setColor(Color.blue);
                g.fillRect((int) e.pos.x + 2, (int) (e.pos.y - e.pos.height + 14), 29 * porcentagemMana / 100, 2);
                //Monstro
            } else if (e instanceof EntidadeMob) {
                g.setColor(Color.red);
                g.setFont(new Font("", Font.BOLD, 10));
                g.drawString(((EntidadeMob) e).getNome(), (int) e.pos.x - 5, (int) (e.pos.y - e.pos.height));

                g.setColor(Color.darkGray);
                g.fillRect((int) e.pos.x - 12, (int) (e.pos.y - e.pos.height + 3), 11, 9);
                g.setColor(Color.white);
                g.drawRoundRect((int) e.pos.x - 13, (int) (e.pos.y - e.pos.height + 2), 12, 10, 3, 3);
                g.setFont(new Font("", Font.BOLD, 9));
                if (((EntidadeMob) e).getNivel() < 10) {
                    g.drawString("" + ((EntidadeMob) e).getNivel(), (int) e.pos.x - 8, (int) (e.pos.y - e.pos.height + 11));
                } else {
                    g.drawString("" + ((EntidadeMob) e).getNivel(), (int) e.pos.x - 11, (int) (e.pos.y - e.pos.height + 11));
                }
                int porcentagemVida = ((EntidadeMob) e).getVidaAtual() * 100 / ((EntidadeMob) e).getVidaMaxima();
                g.drawRoundRect((int) e.pos.x, (int) (e.pos.y - e.pos.height + 5), 32, 5, 2, 2);
                if (porcentagemVida >= 70) {
                    g.setColor(Color.green);
                } else if (porcentagemVida >= 30) {
                    g.setColor(Color.yellow);
                } else {
                    g.setColor(Color.red);
                }
                g.fillRect((int) e.pos.x + 2, (int) (e.pos.y - e.pos.height + 7), 29 * porcentagemVida / 100, 2);
            }
        }
    }

}
