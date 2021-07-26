/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeEvento;

import Classes.EntidadeFuncionais.ControleJogador;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Manolo
 */
public class EntidadeQueMove extends EntidadeEvento {

    public int faceDirecao;
    static final int FACE_BAIXO = 0;
    static final int FACE_ESQUERDA = 1;
    static final int FACE_CIMA = 2;
    static final int FACE_DIREITA = 3;

    static final int PARADO = 1;
    static final int ANDANDO = 2;
    static final int ATACANDO = 3;
    public int estado;

    //Velocidade que a unidade vai se deslocar por tickCurrent
    double speed;
    int maxSpeed;

    public EntidadeQueMove(int IDSprite, int posX, int posY, int posWight, int posHeight, int maxSpeed) {
        super(IDSprite, posX, posY);
        this.pos.width = posWight;
        this.pos.height = posHeight;
        this.maxSpeed = maxSpeed;
        this.speed = 0;
        this.faceDirecao = 0;
    }

    public EntidadeQueMove(int IDSprite, int posX, int posY, int maxSpeed) {
        super(IDSprite, posX, posY);
        this.maxSpeed = maxSpeed;
        this.speed = 0;
        this.faceDirecao = 0;
    }

    @Override
    public void update(int currentTick) {
        if (speed > 0) {
            if (faceDirecao == FACE_BAIXO) {
                if (this.colisaoEntidades[COLISAO_BAIXO] != null && this.colisaoEntidades[COLISAO_BAIXO].blockEntidade == true) {
                    if (this instanceof EntidadeMob) {
                        pos.y = colisaoEntidades[COLISAO_BAIXO].pos.y - pos.height;
                        speed = 0;
                    } else {
                        if (this.pos.getCenterX() > this.colisaoEntidades[COLISAO_BAIXO].pos.getCenterX()) {
                            pos.y = colisaoEntidades[COLISAO_BAIXO].pos.y - pos.height;
                            pos.x++;
                            speed = 0;
                        } else {
                            pos.y = colisaoEntidades[COLISAO_BAIXO].pos.y - pos.height;
                            pos.x--;
                            speed = 0;
                        }
                    }
                } else {
                    pos.y += speed;
                    speed = 0;
                }
            } else if (faceDirecao == FACE_ESQUERDA) {
                if (this.colisaoEntidades[COLISAO_ESQUERDA] != null && this.colisaoEntidades[COLISAO_ESQUERDA].blockEntidade == true) {
                    if (this instanceof EntidadeMob) {
                        pos.x = colisaoEntidades[COLISAO_ESQUERDA].pos.x + pos.width;
                        speed = 0;
                    } else {
                        if (this.pos.getCenterY() > this.colisaoEntidades[COLISAO_ESQUERDA].pos.getCenterY()) {
                            pos.x = colisaoEntidades[COLISAO_ESQUERDA].pos.x + pos.width;
                            pos.y++;
                            speed = 0;
                        } else {
                            pos.x = colisaoEntidades[COLISAO_ESQUERDA].pos.x + pos.width;
                            pos.y--;
                            speed = 0;
                        }
                    }
                } else {
                    pos.x -= speed;
                    speed = 0;
                }
            } else if (faceDirecao == FACE_CIMA) {
                if (this.colisaoEntidades[COLISAO_CIMA] != null && this.colisaoEntidades[COLISAO_CIMA].blockEntidade == true) {
                    if (this instanceof EntidadeMob) {
                        pos.y = colisaoEntidades[COLISAO_CIMA].pos.y + pos.height;
                        speed = 0;
                    } else {
                        if (this.pos.getCenterX() > this.colisaoEntidades[COLISAO_CIMA].pos.getCenterX()) {
                            pos.y = colisaoEntidades[COLISAO_CIMA].pos.y + pos.height;
                            pos.x++;
                            speed = 0;
                        } else {
                            pos.y = colisaoEntidades[COLISAO_CIMA].pos.y + pos.height;
                            pos.x--;
                            speed = 0;
                        }
                    }
                } else {
                    pos.y -= speed;
                    speed = 0;
                }
            } else if (faceDirecao == FACE_DIREITA) {
                if (this.colisaoEntidades[COLISAO_DIREITA] != null && this.colisaoEntidades[COLISAO_DIREITA].blockEntidade == true) {
                    if (this instanceof EntidadeMob) {
                        pos.x = colisaoEntidades[COLISAO_DIREITA].pos.x - colisaoEntidades[COLISAO_DIREITA].pos.width - 1;
                        speed = 0;
                    } else {
                        if (this.pos.getCenterY() > this.colisaoEntidades[COLISAO_DIREITA].pos.getCenterY()) {
                            pos.x = colisaoEntidades[COLISAO_DIREITA].pos.x - colisaoEntidades[COLISAO_DIREITA].pos.width - 1;
                            pos.y++;
                            speed = 0;
                        } else {
                            pos.x = colisaoEntidades[COLISAO_DIREITA].pos.x - colisaoEntidades[COLISAO_DIREITA].pos.width - 1;
                            pos.y--;
                            speed = 0;
                        }
                    }
                } else {
                    pos.x += speed;
                    speed = 0;
                }
            }
        }
        speed = 0;
        if (speed < 0) {
            speed = 0;
        }
    }
    
    public void obterCaminho(){
        
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }

    public void render(BufferedImage img, Graphics2D g) {
        int imgLargura = img.getWidth();
        int imgAltura = img.getHeight();
        g.drawImage(img, (int) pos.getCenterX() - (imgLargura / 2), (int) (pos.y + pos.height) - imgAltura, null);
    }
}
