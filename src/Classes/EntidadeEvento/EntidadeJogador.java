/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeEvento;

import Classes.Abilidades.AtaqueBasico;
import Classes.Game.Jogo;
import Classes.Core.ImageManager;
import Rpg.Core.InputManager;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Manolo
 */
public class EntidadeJogador extends EntidadeQueMove {

    private int contadorAndando;
    //Controladores de ataque
    public int contadorAtaqueBasico = 0;
    private int cw_atq_basico = 40;
    private final int DELAY_ATQ_BASICO = 30;

    public EntidadeJogador(int IDSprite, int posX, int posY, int posWight, int posHeight, int maxSpeed) {
        super(IDSprite, posX, posY, posWight, posHeight, maxSpeed);
    }

    public EntidadeJogador(int IDSprite, int posX, int posY, int maxSpeed) {
        super(IDSprite, posX, posY, 32, 32, 10);
        this.maxSpeed = maxSpeed;
        this.speed = 0;
        this.faceDirecao = 0;
        this.estado = PARADO;
        this.blockEntidade = true;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(int currentTick) {
        if (this.estado == PARADO || this.estado == ANDANDO) {
            if (InputManager.getInstance().isPressed(KeyEvent.VK_SHIFT)) {
                if (InputManager.getInstance().isPressed(KeyEvent.VK_D)) {
                    this.estado = PARADO;
                    this.faceDirecao = 3;
                    this.speed = 0;
                    this.contadorAndando = 0;
                } else if (InputManager.getInstance().isPressed(KeyEvent.VK_A)) {
                    this.estado = PARADO;
                    this.faceDirecao = 1;
                    this.speed = 0;
                    this.contadorAndando = 0;
                } else if (InputManager.getInstance().isPressed(KeyEvent.VK_S)) {
                    this.estado = PARADO;
                    this.faceDirecao = 0;
                    this.speed = 0;
                    this.contadorAndando = 0;
                } else if (InputManager.getInstance().isPressed(KeyEvent.VK_W)) {
                    this.estado = PARADO;
                    this.faceDirecao = 2;
                    this.speed = 0;
                    this.contadorAndando = 0;
                } else if (InputManager.getInstance().isPressed(KeyEvent.VK_Q)) {
                    if (cw_atq_basico >= 40) {
                        cw_atq_basico = 0;
                        this.estado = ATACANDO;
                        this.speed = 0;
                        this.contadorAtaqueBasico = 0;
                        Jogo.abilidadesJogador.add(new AtaqueBasico((int) pos.x, (int) pos.y, faceDirecao, this));
                    }
                }
            } else {
                if (InputManager.getInstance().isPressed(KeyEvent.VK_Q)) {
                    if (cw_atq_basico >= 40) {
                        cw_atq_basico = 0;
                        this.estado = ATACANDO;
                        this.speed = 0;
                        this.contadorAtaqueBasico = 0;
                        Jogo.abilidadesJogador.add(new AtaqueBasico((int) pos.x, (int) pos.y, faceDirecao, this));
                    }
                } else if (InputManager.getInstance().isPressed(KeyEvent.VK_D)) {
                    this.estado = ANDANDO;
                    this.faceDirecao = 3;
                    this.speed = 1;
                    this.contadorAndando++;
                } else if (InputManager.getInstance().isPressed(KeyEvent.VK_A)) {
                    this.estado = ANDANDO;
                    this.faceDirecao = 1;
                    this.speed = 1;
                    this.contadorAndando++;
                } else if (InputManager.getInstance().isPressed(KeyEvent.VK_S)) {
                    this.estado = ANDANDO;
                    this.faceDirecao = 0;
                    this.speed = 1;
                    this.contadorAndando++;
                } else if (InputManager.getInstance().isPressed(KeyEvent.VK_W)) {
                    this.estado = ANDANDO;
                    this.faceDirecao = 2;
                    this.speed = 1;
                    this.contadorAndando++;
                } else {
                    this.estado = PARADO;
                }
            }
        }
        super.update(currentTick);
        if (contadorAndando >= 32) {
            contadorAndando = 0;
        }
        cw_atq_basico++;
        if (cw_atq_basico > 40) {
            cw_atq_basico = 40;
        }
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage img = new BufferedImage(1, 1, 1);
        //Sombra
        g.setColor(Color.darkGray);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g.fillOval((int) this.pos.x + 2, (int) this.pos.y + 18, (int) this.pos.width - 4, (int) this.pos.height - 20);
        g.setComposite(AlphaComposite.SrcOver);
        
        g.drawRect((int) pos.x, (int) pos.y, (int) pos.width, (int) pos.height);

        //ANDANDO
        if (this.estado == ANDANDO) {
            if (this.faceDirecao == FACE_BAIXO) {
                if (contadorAndando < 10) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 32, 0, 32, 48);
                } else if (contadorAndando < 16) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 64, 0, 32, 48);
                } else if (contadorAndando < 26) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 96, 0, 32, 48);
                } else if (contadorAndando < 32) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 64, 0, 32, 48);
                }
                super.render(img, g);
            } else if (this.faceDirecao == FACE_ESQUERDA) {
                if (contadorAndando < 10) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 32, 48, 32, 48);
                } else if (contadorAndando < 16) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 64, 48, 32, 48);
                } else if (contadorAndando < 26) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 96, 48, 32, 48);
                } else if (contadorAndando < 32) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 64, 48, 32, 48);
                }
                super.render(img, g);
            } else if (this.faceDirecao == FACE_CIMA) {
                if (contadorAndando < 10) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 32, 144, 32, 48);
                } else if (contadorAndando < 16) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 64, 144, 32, 48);
                } else if (contadorAndando < 26) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 96, 144, 32, 48);
                } else if (contadorAndando < 32) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 64, 144, 32, 48);
                }
                super.render(img, g);
            } else if (this.faceDirecao == FACE_DIREITA) {
                if (contadorAndando < 10) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 32, 96, 32, 48);
                } else if (contadorAndando < 16) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 64, 96, 32, 48);
                } else if (contadorAndando < 26) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 96, 96, 32, 48);
                } else if (contadorAndando < 32) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 64, 96, 32, 48);
                }
                super.render(img, g);
            }
            //ATACANDO
        } else if (this.estado == ATACANDO) {
            if (contadorAtaqueBasico < DELAY_ATQ_BASICO) {
                if (this.faceDirecao == FACE_BAIXO) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 32, 0, 32, 48);
                    super.render(img, g);
                    if (contadorAtaqueBasico < 10) {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 0, 0, 40, 40), (int) pos.x + 7, (int) pos.y, null);
                    } else if (contadorAtaqueBasico < 20) {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 40, 0, 40, 40), (int) pos.x + 7, (int) pos.y, null);
                    } else {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 80, 0, 40, 40), (int) pos.x + 7, (int) pos.y, null);
                    }
                } else if (this.faceDirecao == FACE_ESQUERDA) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 32, 48, 32, 48);
                    super.render(img, g);
                    if (contadorAtaqueBasico < 10) {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 0, 40, 40, 40), (int) pos.x - 14, (int) pos.y - 5, null);
                    } else if (contadorAtaqueBasico < 20) {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 40, 40, 40, 40), (int) pos.x - 14, (int) pos.y - 5, null);
                    } else {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 80, 40, 40, 40), (int) pos.x - 14, (int) pos.y - 5, null);
                    }
                } else if (this.faceDirecao == FACE_CIMA) {
                    if (contadorAtaqueBasico < 10) {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 0, 120, 40, 40), (int) pos.x-12, (int) pos.y - 20, null);
                    } else if (contadorAtaqueBasico < 20) {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 40, 120, 40, 40), (int) pos.x-12, (int) pos.y - 20, null);
                    } else {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 80, 120, 40, 40), (int) pos.x-12, (int) pos.y - 20, null);
                    }
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 32, 144, 32, 48);
                    super.render(img, g);
                } else if (this.faceDirecao == FACE_DIREITA) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, 32, 96, 32, 48);
                    super.render(img, g);
                    if (contadorAtaqueBasico < 10) {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 0, 80, 40, 40), (int) pos.x + 8, (int) pos.y - 5, null);
                    } else if (contadorAtaqueBasico < 20) {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 40, 80, 40, 40), (int) pos.x + 8, (int) pos.y - 5, null);
                    } else {
                        g.drawImage(ImageManager.getInstance().getImageArma(1, 80, 80, 40, 40), (int) pos.x + 8, (int) pos.y - 5, null);
                    }
                }
                contadorAtaqueBasico++;
            } else {
                contadorAtaqueBasico = 0;
                estado = PARADO;
            }
            //PARADO
        } else {
            if (this.faceDirecao == FACE_BAIXO) {
                img = ImageManager.getInstance().getImagePersonagem(IDSprite, 0, 0, 32, 48);
                super.render(img, g);
            } else if (this.faceDirecao == FACE_ESQUERDA) {
                img = ImageManager.getInstance().getImagePersonagem(IDSprite, 0, 48, 32, 48);
                super.render(img, g);
            } else if (this.faceDirecao == FACE_CIMA) {
                img = ImageManager.getInstance().getImagePersonagem(IDSprite, 0, 144, 32, 48);
                super.render(img, g);
            } else if (this.faceDirecao == FACE_DIREITA) {
                img = ImageManager.getInstance().getImagePersonagem(IDSprite, 0, 96, 32, 48);
                super.render(img, g);
            }
        }
    }

}
