/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeEvento;

import Classes.Abilidades.AtaqueBasico;
import Classes.Abilidades.RajadaDeVento;
import Classes.Core.ImageManager;
import static Classes.EntidadeEvento.EntidadeQueMove.ANDANDO;
import static Classes.EntidadeEvento.EntidadeQueMove.ATACANDO;
import Classes.EntidadeFuncionais.ControleJogador;
import Classes.EntidadeSimples.Entidade;
import Classes.Game.Jogo;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Manolo
 */
public class EntidadeMob extends EntidadeQueMove {

    //Alvo
    private Entidade alvo;

    //Atributos primarios
    private int nivel;
    private int expMorte;
    private int vidaAtual;
    private int vidaMaxima;
    private int atqBasico;
    private int defBasico;

    //Controladores de Atq
    private int contadorAtaque = 0;
    private int contadorDirecao = 0;

    private int cw_atq_basico = 0;
    private final int DELAY_ATQ_BASICO = 200;

    private int cw_atq_especial = 0;
    private final int DELAY_ATQ_ESPECIAL = 180;

    public int estadoMob;
    private int contadorAndando;
    private String nome;
    private String tipo;
    private Random random;

    public static final int PARADO = 0;
    public static final int PERSEGUIR = 1;
    public static final int FUGIR = 2;
    public static final int MORTO = 3;

    public EntidadeJogador jogador;

    public EntidadeMob(int IDSprite, int posX, int posY, int posWight, int posHeight, int maxSpeed) {
        super(IDSprite, posX, posY, posWight, posHeight, maxSpeed);
        init();
        vidaAtual = 100;
        vidaMaxima = 100;
        estadoMob = 0;
        this.blockEntidade = true;
        nivel = 1;
        nome = "Inimigo";
        random = new Random();
    }

    @Override
    public void init() {
        //Carregamento default dos atributos primarios
        this.nivel = 1;
        this.expMorte = 1;
        this.vidaAtual = 1;
        this.vidaMaxima = 1;
        this.atqBasico = 1;
        this.defBasico = 1;

        this.estadoMob = PARADO;
        this.contadorAndando = 0;
        this.nome = "Default";
        this.tipo = "Default";

        //this.jogador = ControleJogador.getInstance().jogador;

    }

    @Override
    public void update(int currentTick) {
        determinarEstado();
        if (estadoMob == PERSEGUIR) {
            this.perseguirAlvo();
            //Ataque basico
            this.AtaqueBasico();
            //Abilidade especial
            this.AtaqueEspecial();
        } else if (estadoMob == FUGIR) {
            this.fugirAlvo();
            //Abilidade especial
            this.AtaqueEspecial();
        } else if (estadoMob == MORTO) {
            Jogo.entidades.remove(this);
        }
        super.update(currentTick);
        if (contadorAndando >= 32) {
            contadorAndando = 0;
        }
        cw_atq_basico++;
        cw_atq_especial++;
    }

    public void determinarEstado() {
        if (vidaAtual > 30) {
            if (ControleJogador.getInstance().jogador.pos.x >= pos.x && ControleJogador.getInstance().jogador.pos.x - pos.x < 300) {
                if (ControleJogador.getInstance().jogador.pos.y > pos.y && ControleJogador.getInstance().jogador.pos.y - pos.y < 300) {
                    estadoMob = PERSEGUIR;
                    alvo = ControleJogador.getInstance().jogador;
                } else if (ControleJogador.getInstance().jogador.pos.y <= pos.y && pos.y - ControleJogador.getInstance().jogador.pos.y < 300) {
                    estadoMob = PERSEGUIR;
                    alvo = ControleJogador.getInstance().jogador;
                } else {
                    estado = PARADO;
                    estadoMob = PARADO;
                    alvo = null;
                }
            } else if (ControleJogador.getInstance().jogador.pos.x <= pos.x && pos.x - ControleJogador.getInstance().jogador.pos.x < 300) {
                if (ControleJogador.getInstance().jogador.pos.y >= pos.y && ControleJogador.getInstance().jogador.pos.y - pos.y < 300) {
                    estadoMob = PERSEGUIR;
                    alvo = ControleJogador.getInstance().jogador;
                } else if (ControleJogador.getInstance().jogador.pos.y <= pos.y && pos.y - ControleJogador.getInstance().jogador.pos.y < 300) {
                    estadoMob = PERSEGUIR;
                    alvo = ControleJogador.getInstance().jogador;
                } else {
                    estado = PARADO;
                    estadoMob = PARADO;
                    alvo = null;
                }
            } else {
                estado = PARADO;
                estadoMob = PARADO;
                alvo = null;
            }
        } else if (vidaAtual <= 30 && vidaAtual > 0) {
            if (alvo != null) {
                if (alvo.pos.x >= pos.x && alvo.pos.x - pos.x < 300) {
                    if (alvo.pos.y >= pos.y && alvo.pos.y - pos.y < 300) {
                        estadoMob = FUGIR;
                    } else if (alvo.pos.y <= pos.y && pos.y - alvo.pos.y < 300) {
                        estadoMob = FUGIR;
                    } else {
                        estado = PARADO;
                        estadoMob = PARADO;
                    }
                } else if (alvo.pos.x <= pos.x && pos.x - alvo.pos.x < 300) {
                    if (alvo.pos.y >= pos.y && alvo.pos.y - pos.y < 300) {
                        estadoMob = FUGIR;
                    } else if (alvo.pos.y <= pos.y && pos.y - alvo.pos.y < 300) {
                        estadoMob = FUGIR;
                    } else {
                        estado = PARADO;
                        estadoMob = PARADO;
                    }
                } else {
                    estado = PARADO;
                    estadoMob = PARADO;
                }
            } else {
                estado = PARADO;
                estadoMob = PARADO;
            }
        } else if (vidaAtual <= 0) {
            estadoMob = MORTO;
        } else {
            estadoMob = PARADO;
        }
    }

    public void perseguirAlvo() {
        if (alvo != null) {
            if (estado != ATACANDO) {
                estado = PARADO;
            }
            if (alvo.pos.x > pos.x + 32) {
                faceDirecao = FACE_DIREITA;
                speed = 0.5;
                estado = ANDANDO;
            } else if (pos.x > alvo.pos.x + 32) {
                faceDirecao = FACE_ESQUERDA;
                speed = 0.5;
                estado = ANDANDO;
            }
            if (alvo.pos.y > pos.y + 32) {
                faceDirecao = FACE_BAIXO;
                speed = 0.5;
                estado = ANDANDO;
            } else if (pos.y > alvo.pos.y + 32) {
                faceDirecao = FACE_CIMA;
                speed = 0.5;
                estado = ANDANDO;
            }
            contadorAndando++;
            if (alvo.pos.y > this.pos.y) {
                faceDirecao = FACE_BAIXO;
                if (alvo.pos.x > pos.x) {
                    if (this.pos.width + pos.x - 2 < alvo.pos.x && this.pos.y + pos.height > alvo.pos.y) {
                        faceDirecao = FACE_DIREITA;
                    }
                } else {
                    if ((alvo.pos.x + alvo.pos.width) - 2 <= pos.x && (pos.y + pos.height) > alvo.pos.y) {
                        faceDirecao = FACE_ESQUERDA;
                    }
                }
            } else {
                faceDirecao = FACE_CIMA;
                if (alvo.pos.x > pos.x) {
                    if (this.pos.width + pos.x - 2 < alvo.pos.x && this.pos.y <= (alvo.pos.y + alvo.pos.height)) {
                        faceDirecao = FACE_DIREITA;
                    }
                } else {
                    if ((alvo.pos.x + alvo.pos.width) - 2 <= pos.x && this.pos.y < (alvo.pos.y + alvo.pos.height)) {
                        faceDirecao = FACE_ESQUERDA;
                    }
                }
            }
        }
    }

    public void fugirAlvo() {
        if (alvo != null) {
            if (alvo.pos.x > pos.x) {
                faceDirecao = FACE_ESQUERDA;
                speed = 0.5;
                estado = ANDANDO;
            } else if (pos.x > alvo.pos.x) {
                faceDirecao = FACE_DIREITA;
                speed = 0.5;
                estado = ANDANDO;
            }
            if (alvo.pos.y > pos.y) {
                faceDirecao = FACE_CIMA;
                speed = 0.5;
                estado = ANDANDO;
            } else if (pos.y > alvo.pos.y) {
                faceDirecao = FACE_BAIXO;
                speed = 0.5;
                estado = ANDANDO;
            }
            contadorAndando++;
        }
    }

    public void AtaqueBasico() {
        if (cw_atq_basico >= DELAY_ATQ_BASICO) {
            cw_atq_basico = 0;
            this.estado = PARADO;
            this.speed = 0;
            this.contadorAtaque = 0;
            Jogo.abilidadesMob.add(new AtaqueBasico((int) pos.x, (int) pos.y, faceDirecao, this));
        }
    }

    public void AtaqueEspecial() {
        if (alvo != null) {
            if ((random.nextInt(400)) <= 5 && cw_atq_especial >= DELAY_ATQ_ESPECIAL) {
                cw_atq_especial = 0;
                this.estado = ATACANDO;
                this.speed = 0;
                this.contadorAtaque = 0;
                //Obter direcao do alvo
                if (alvo.pos.y > this.pos.y) {
                    faceDirecao = FACE_BAIXO;
                    if (alvo.pos.x > pos.x) {
                        if (this.pos.width + pos.x - 2 < alvo.pos.x && this.pos.y + pos.height > alvo.pos.y) {
                            faceDirecao = FACE_DIREITA;
                        }
                    } else {
                        if ((alvo.pos.x + alvo.pos.width) - 2 <= pos.x && (pos.y + pos.height) > alvo.pos.y) {
                            faceDirecao = FACE_ESQUERDA;
                        }
                    }
                } else {
                    faceDirecao = FACE_CIMA;
                    if (alvo.pos.x > pos.x) {
                        if (this.pos.width + pos.x - 2 < alvo.pos.x && this.pos.y <= (alvo.pos.y + alvo.pos.height)) {
                            faceDirecao = FACE_DIREITA;
                        }
                    } else {
                        if ((alvo.pos.x + alvo.pos.width) - 2 <= pos.x && this.pos.y < (alvo.pos.y + alvo.pos.height)) {
                            faceDirecao = FACE_ESQUERDA;
                        }
                    }
                }
                Jogo.abilidadesMob.add(new RajadaDeVento((int) pos.x, (int) pos.y, faceDirecao, this));
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage img = new BufferedImage(1, 1, 1);

        int imgLargura = ImageManager.getInstance().getSpritePersonagem(IDSprite).getWidth() / 4;
        int imgAltura = ImageManager.getInstance().getSpritePersonagem(IDSprite).getHeight() / 4;

        g.setColor(Color.darkGray);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g.fillOval((int) (pos.getCenterX() - (imgLargura / 2)) + 10, (int) (pos.y + pos.height) - 20, imgLargura - 20, 20);
        g.setComposite(AlphaComposite.SrcOver);

        g.drawRect((int) pos.x, (int) pos.y, (int) pos.width, (int) pos.height);
        if (this.estado == ANDANDO) {
            if (this.faceDirecao == FACE_BAIXO) {
                if (contadorAndando < 10) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura, 0, imgLargura, imgAltura);
                } else if (contadorAndando < 16) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 2, 0, imgLargura, imgAltura);
                } else if (contadorAndando < 26) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 3, 0, imgLargura, imgAltura);
                } else if (contadorAndando < 32) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 2, 0, imgLargura, imgAltura);
                }
                super.render(img, g);
            } else if (this.faceDirecao == FACE_ESQUERDA) {
                if (contadorAndando < 10) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura, imgAltura, imgLargura, imgAltura);
                } else if (contadorAndando < 16) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 2, imgAltura, imgLargura, imgAltura);
                } else if (contadorAndando < 26) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 3, imgAltura, imgLargura, imgAltura);
                } else if (contadorAndando < 32) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 2, imgAltura, imgLargura, imgAltura);
                }
                super.render(img, g);
            } else if (this.faceDirecao == FACE_CIMA) {
                if (contadorAndando < 10) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura, imgAltura * 3, imgLargura, imgAltura);
                } else if (contadorAndando < 16) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 2, imgAltura * 3, imgLargura, imgAltura);
                } else if (contadorAndando < 26) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 3, imgAltura * 3, imgLargura, imgAltura);
                } else if (contadorAndando < 32) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 2, imgAltura * 3, imgLargura, imgAltura);
                }
                super.render(img, g);
            } else if (this.faceDirecao == FACE_DIREITA) {
                if (contadorAndando < 10) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura, imgAltura * 2, imgLargura, imgAltura);
                } else if (contadorAndando < 16) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 2, imgAltura * 2, imgLargura, imgAltura);
                } else if (contadorAndando < 26) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 3, imgAltura * 2, imgLargura, imgAltura);
                } else if (contadorAndando < 32) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura * 2, imgAltura * 2, imgLargura, imgAltura);
                }
                super.render(img, g);
            }
        } else if (estado == ATACANDO) {
            if (contadorAtaque <= 40) {
                contadorAtaque++;
                if (this.faceDirecao == FACE_BAIXO) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura, 0, imgLargura, imgAltura);
                    super.render(img, g);
                } else if (this.faceDirecao == FACE_ESQUERDA) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura, imgAltura, imgLargura, imgAltura);
                    super.render(img, g);
                } else if (this.faceDirecao == FACE_CIMA) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura, imgAltura * 3, imgLargura, imgAltura);
                    super.render(img, g);
                } else if (this.faceDirecao == FACE_DIREITA) {
                    img = ImageManager.getInstance().getImagePersonagem(IDSprite, imgLargura, imgAltura * 2, imgLargura, imgAltura);
                    super.render(img, g);
                }
            } else {
                estado = PARADO;
            }
        } else {
            if (this.faceDirecao == FACE_BAIXO) {
                img = ImageManager.getInstance().getImagePersonagem(IDSprite, 0, 0, imgLargura, imgAltura);
                super.render(img, g);
            } else if (this.faceDirecao == FACE_ESQUERDA) {
                img = ImageManager.getInstance().getImagePersonagem(IDSprite, 0, imgAltura, imgLargura, imgAltura);
                super.render(img, g);
            } else if (this.faceDirecao == FACE_CIMA) {
                img = ImageManager.getInstance().getImagePersonagem(IDSprite, 0, imgAltura * 3, imgLargura, imgAltura);
                super.render(img, g);
            } else if (this.faceDirecao == FACE_DIREITA) {
                img = ImageManager.getInstance().getImagePersonagem(IDSprite, 0, imgAltura * 2, imgLargura, imgAltura);
                super.render(img, g);
            }
        }
    }

    public void carregarMob(String fileName) {

    }

    public void setDano(int dano) {
        vidaAtual -= dano;
        if (vidaAtual < 0) {
            vidaAtual = 0;
        }
    }

    public int getVidaAtual() {
        return this.vidaAtual;
    }

    public int getVidaMaxima() {
        return this.vidaMaxima;
    }

    public int getNivel() {
        return this.nivel;
    }

    public String getNome() {
        return this.nome;
    }

}
