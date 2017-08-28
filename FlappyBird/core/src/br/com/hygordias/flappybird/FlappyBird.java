package br.com.hygordias.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture[] passaro;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;

    private int larguraPassaro;
    private int alturaPassaro;
    private int larguraDispositivo;
    private int alturaDispositivo;
    private int larguraCano;
    private int alturaCano;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;

    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;

	@Override
	public void create () {

        deltaTime = Gdx.graphics.getDeltaTime();

        batch = new SpriteBatch();
        passaro = new Texture[3];
        passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");

        fundo = new Texture("fundo.png");

        canoBaixo = new Texture("cano_baixo.png");
        canoTopo = new Texture("cano_topo.png");


        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();

        larguraPassaro = (int) ( (double)passaro[0].getWidth()/(double)fundo.getWidth() * larguraDispositivo);
        alturaPassaro = (int) ((double)passaro[0].getHeight()/(double)fundo.getHeight() * alturaDispositivo);

        larguraCano = (int) ( (double) canoTopo.getWidth()/(double)fundo.getWidth() * larguraDispositivo);
        alturaCano = (int) ((double)canoTopo.getHeight()/(double)fundo.getHeight() * alturaDispositivo);


        posicaoMovimentoCanoHorizontal = larguraDispositivo;
        posicaoInicialVertical = alturaDispositivo /2;
        espacoEntreCanos = (float) ((float) alturaDispositivo * 0.1953125);
	}

	@Override
	public void render () {

        velocidadeQueda ++;

        posicaoMovimentoCanoHorizontal -= 20;

        if(posicaoInicialVertical > -alturaPassaro || velocidadeQueda < 0)
            posicaoInicialVertical -= velocidadeQueda;

        if(Gdx.input.justTouched()){
            velocidadeQueda = -18;
        }

        if(posicaoMovimentoCanoHorizontal < -larguraCano){
            posicaoMovimentoCanoHorizontal = larguraDispositivo;
        }


        batch.begin();

        batch.draw(fundo, 0,0, larguraDispositivo, alturaDispositivo);
        batch.draw(canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos/2, larguraCano, alturaCano );
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - alturaCano - espacoEntreCanos/2, larguraCano, alturaCano );
        batch.draw(passaro[(int) variacao], larguraDispositivo /2 - larguraPassaro /2, posicaoInicialVertical, larguraPassaro, alturaPassaro);

        variacao += 0.2;
        if(variacao > 3) variacao= 0;



        batch.end();
	}

}