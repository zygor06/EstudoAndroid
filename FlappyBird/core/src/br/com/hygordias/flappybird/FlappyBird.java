package br.com.hygordias.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Font;
import java.util.Random;

public class FlappyBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture[] passaro;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Texture gameOver;
    private Random random;
    private BitmapFont fonte;
    private BitmapFont mensagem;
    private BitmapFont speed;
    private Circle passaroCirculo;
    private Rectangle canoTopoBounds;
    private Rectangle canoBaixoBounds;

    private int larguraPassaro;
    private int alturaPassaro;
    private float larguraDispositivo;
    private float alturaDispositivo;
    private int larguraCano;
    private int alturaCano;
    private int larguraGameOver;
    private int alturaGameOver;
    private int pontuacao = 0;

    private float variacao = 0;
    private float velocidadeQueda = 0;
    private float posicaoInicialVertical;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandomica;
    private float multiplicadorVelocidade;
    private float velocidadeCanos;
    private float deltaVel;

    private int inGame = 0;
    private boolean marcouPonto = false;

    private OrthographicCamera camera;
    private Viewport viewport;
    private final float VIRTUAL_WIDTH = 768;
    private final float VIRTUAL_HEIGHT = 1024;

	@Override
	public void create () {

        deltaTime = Gdx.graphics.getDeltaTime();
        deltaVel = (float) 10/1080;

        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(6);

        mensagem = new BitmapFont();
        mensagem.setColor(Color.WHITE);
        mensagem.getData().setScale(3);

        speed = new BitmapFont();
        speed.setColor(Color.YELLOW);
        speed.getData().setScale(3);

        passaroCirculo = new Circle();
        canoTopoBounds = new Rectangle();
        canoBaixoBounds = new Rectangle();


        batch = new SpriteBatch();
        passaro = new Texture[3];
        passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");

        fundo = new Texture("fundo.png");

        canoBaixo = new Texture("cano_baixo.png");
        canoTopo = new Texture("cano_topo.png");

        gameOver = new Texture("game_over.png");

        random = new Random();

        camera = new OrthographicCamera();
        camera.position.set(VIRTUAL_WIDTH/2,VIRTUAL_HEIGHT/2, 0);
        viewport = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);



        larguraDispositivo = VIRTUAL_WIDTH;
        alturaDispositivo = VIRTUAL_HEIGHT;

        larguraPassaro = (int) ( (double)passaro[0].getWidth()/(double)fundo.getWidth() * larguraDispositivo);
        alturaPassaro = (int) ((double)passaro[0].getHeight()/(double)fundo.getHeight() * alturaDispositivo);

        larguraCano = (int) ( (double) canoTopo.getWidth()/(double)fundo.getWidth() * larguraDispositivo);
        alturaCano = (int) ((double)canoTopo.getHeight()/(double)fundo.getHeight() * alturaDispositivo);

        larguraGameOver = (int) ( (double) gameOver.getWidth()/(double)fundo.getWidth() * larguraDispositivo);
        alturaGameOver = (int) ((double)gameOver.getHeight()/(double)fundo.getHeight() * alturaDispositivo);


        posicaoMovimentoCanoHorizontal = larguraDispositivo;
        posicaoInicialVertical = alturaDispositivo /2;
        espacoEntreCanos = 260;
        multiplicadorVelocidade = 0;
        velocidadeCanos = larguraDispositivo * deltaVel;
	}

	@Override
	public void render () {

        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        variacao += 0.2;
        if (variacao > 3) variacao = 0;

        if(inGame == 0){
            if(Gdx.input.justTouched())
                inGame = 1;
        }else {

            velocidadeQueda++;
            if (posicaoInicialVertical > -alturaPassaro || velocidadeQueda < 0)
                posicaoInicialVertical -= velocidadeQueda;

            if(inGame == 1){

                if(multiplicadorVelocidade < 5){
                    multiplicadorVelocidade += 0.005;
                }
                if(multiplicadorVelocidade < 10){
                    multiplicadorVelocidade += 0.002;
                }
                if(multiplicadorVelocidade < 15){
                    multiplicadorVelocidade += 0.001;
                }
                if(multiplicadorVelocidade > 15){
                    multiplicadorVelocidade += 0.0005;
                }
                posicaoMovimentoCanoHorizontal -= velocidadeCanos + multiplicadorVelocidade;

                if (Gdx.input.justTouched()) {
                    velocidadeQueda = -15;
                }

                if (posicaoMovimentoCanoHorizontal < -larguraCano) {
                    posicaoMovimentoCanoHorizontal = larguraDispositivo;
                    alturaEntreCanosRandomica = random.nextInt((int) (alturaDispositivo * 0.390625)) - (int) (alturaDispositivo * 0.390625) / 2;
                    marcouPonto = false;
                }

                if(posicaoMovimentoCanoHorizontal < (float) (larguraDispositivo * 0.208695)){
                    if(!marcouPonto){
                        pontuacao++;
                        marcouPonto = true;
                    }
                }
            }else {

                if (Gdx.input.justTouched()) {
                    pontuacao = 0;
                    posicaoInicialVertical = alturaDispositivo/2;
                    posicaoMovimentoCanoHorizontal = larguraDispositivo;
                    velocidadeQueda = 0;
                    multiplicadorVelocidade = 0;
                    inGame = 0;
                }

            }
        }


        batch.setProjectionMatrix( camera.combined );

        batch.begin();
            batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
            batch.draw(canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica, larguraCano, alturaCano);
            batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - alturaCano - espacoEntreCanos / 2 + alturaEntreCanosRandomica, larguraCano, alturaCano);
            batch.draw(passaro[(int) variacao], 120, posicaoInicialVertical, larguraPassaro, alturaPassaro);
            fonte.draw(batch, String.valueOf(pontuacao), larguraDispositivo/2, alturaDispositivo - 50);
            speed.draw(batch, String.format("%.2f", multiplicadorVelocidade) + " km/h", 30, alturaDispositivo - 50);

            if(inGame == 2){
                batch.draw(gameOver, larguraDispositivo/2 - larguraGameOver/2, alturaDispositivo /2, larguraGameOver, alturaGameOver);
                mensagem.draw(batch, "Toque na tela para reiniciar", larguraDispositivo/2 - 250, alturaDispositivo/2 - 25);
            }

        batch.end();

        passaroCirculo.set((float) (larguraDispositivo * 0.208695) + larguraPassaro/2, posicaoInicialVertical + alturaPassaro /2, (float)( alturaPassaro/1.5));
        canoBaixoBounds.set(posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - alturaCano - espacoEntreCanos / 2 + alturaEntreCanosRandomica, larguraCano, alturaCano);
        canoTopoBounds.set(posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica, larguraCano, alturaCano);

        //Desenhar formas
        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(passaroCirculo.x, passaroCirculo.y, passaroCirculo.radius);
            shapeRenderer.rect(canoBaixoBounds.getX(),canoBaixoBounds.getY(), canoBaixoBounds.getWidth(), canoBaixoBounds.getHeight());
            shapeRenderer.rect(canoTopoBounds.getX(),canoTopoBounds.getY(), canoTopoBounds.getWidth(), canoTopoBounds.getHeight());
            shapeRenderer.setColor(Color.RED);
        shapeRenderer.end();*/

        //Teste de colisão

        if (Intersector.overlaps(passaroCirculo, canoTopoBounds) || Intersector.overlaps(passaroCirculo, canoBaixoBounds) || posicaoInicialVertical < 0 ) {

            inGame = 2;

        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}