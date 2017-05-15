package br.pucpr.imagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Resolução dos exercícios. Cada exercício está em uma função.
 */
public class ExerciciosResolvidos {

    /**
     * Garante que o valor estará no intervalo de 0 até 255.
     */
    private int clamp(int value) {
        if (value > 255) {
            value = 255;
        } else if (value < 0) {
            value = 0;
        }

        return value;
    }

    /**
     * Converte os valores de r, g e b para um objeto do tipo color.
     * Os valores podem estar fora do intervalo de 0 até 255, pois
     * a função ajusta chamando a função clamp (acima).
     */
    private Color toColor(int r, int g, int b) {
        r = clamp(r);
        g = clamp(g);
        b = clamp(b);
        return new Color(r, g, b);
    }

    /**
     * Salva a imagem no disco.
     */
    private void salvar(BufferedImage img, String name) throws IOException {
        ImageIO.write(img, "jpg", new File(name + ".jpg"));
        System.out.printf("Salvo %s.png%n", name);
    }

    /**
     * Exercício 1
     * Crie uma função BufferedImage bright(BufferedImage img, float intensity)
     * que multiplique todos a cor de todos os pixels da imagem pela intensidade passada.
     * Caso a intensidade seja positiva, deve ser multiplicada diretamente;
     * Caso a intensidade seja negativa, o valor a ser multiplicado é 1 + intensidade. Por exemplo, se a intensidade for
     * -0.2 o valor a ser multiplicado será 0.8.
     *
     * Se o resultado ainda for negativo, use 0 para a intensidade.
     * Se a após a multiplicação do pixel o valor resultante no canal de cor for maior do que 255, grave 255.
     */
    public BufferedImage bright(BufferedImage img, float intensity) {
        //Ajuste da intensidade
        if (intensity < 0.0f) {
            intensity = 1.0f + intensity;
        }

        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Le o pixel
                Color p = new Color(img.getRGB(x, y));

                //Multiplica canal por canal
                int r = (int) (p.getRed() * intensity);
                int g = (int) (p.getGreen() * intensity);
                int b = (int) (p.getBlue() * intensity);

                //Gera a cor de saída. Observe que estamos chamando a função toColor para garantir que r, g e b
                //estejam no intervalo de 0 a 255.
                Color o = toColor(r, g, b);

                //Define a cor de saída na imagem de saída (out).
                out.setRGB(x, y, o.getRGB());
            }
        }

        return out;
    }

    /**
     * Exercício 2a
     * BufferedImage grayscale(BufferedImage img) que transforme uma imagem colorida em uma imagem em preto e branco.
     * Para isso, defina os valores de r, g e b da imagem de destino iguais ao canal r da imagem de origem
     */
    private BufferedImage grayscaleR(BufferedImage img) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Le o pixel
                Color p = new Color(img.getRGB(x, y));

                //Obtém o canal R, como indicado no enunciado
                int cinza = p.getRed();

                //Gera a cor de saída com R definido em R, G e B.
                Color o = new Color(cinza, cinza, cinza);

                //Define a cor de saída na imagem de saída (out).
                out.setRGB(x, y, o.getRGB());
            }
        }

        return out;
    }

    /**
     * Exercício 2b
     * BufferedImage grayscale(BufferedImage img) que transforme uma imagem colorida em uma imagem em preto e branco.
     * Para isso, defina os valores de r, g e b da imagem de destino iguais ao canal r da imagem de origem
     */
    public BufferedImage grayscaleMedia(BufferedImage img) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Le o pixel
                Color p = new Color(img.getRGB(x, y));

                //Calcula a média de R, G e B
                int cinza = (p.getRed() + p.getGreen() + p.getBlue()) / 3;

                //Gera a cor de saída com "cinza" definido em R, G e B.
                Color o = new Color(cinza, cinza, cinza);

                //Define a cor de saída na imagem de saída (out).
                out.setRGB(x, y, o.getRGB());
            }
        }

        return out;
    }

    /**
     * Exercício 2c
     * BufferedImage grayscale(BufferedImage img) que transforme uma imagem colorida em uma imagem em preto e branco.
     * Para isso, defina os valores de r, g e b da imagem de destino iguais a 0.3*r + 0.59*g + 0.11*b da imagem de
     * origem
     */
    public BufferedImage grayscaleFormula(BufferedImage img) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Le o pixel
                Color p = new Color(img.getRGB(x, y));

                //Calcula a média ponderada de R, G e B
                int cinza = (int)(0.3f * p.getRed() + 0.59f * p.getGreen() + 0.11f * p.getBlue());

                //Gera a cor de saída com "cinza" definido em R, G e B.
                Color o = new Color(cinza, cinza, cinza);

                //Define a cor de saída na imagem de saída (out).
                out.setRGB(x, y, o.getRGB());
            }
        }

        return out;
    }

    /**
     * Exercício 2d
     * Crie a função BufferedImage threshold(BufferedImage img, int value) que pinte de branco todos os pixels
     * maiores ou iguais a value e de preto todos os demais pixels.
     *
     * Considere que img é uma imagem em tons de cinza.
     */
    public BufferedImage threshold(BufferedImage img, int value) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Le o pixel
                Color p = new Color(img.getRGB(x, y));

                //Testa o valor do canal vermelho. Como a imagem é em tons de cinza, tanto faz o canal já que seus
                //tres valores são iguais. Se o tom for maior ou igual a value, define o pixel para branco, senão preto.
                if (p.getRed() >= value) {
                    out.setRGB(x, y, Color.WHITE.getRGB());
                } else {
                    out.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }

        return out;
    }

    /**
     * Exercício 3a
     * Crie uma função BufferedImage subtract(BufferedImage img1, BufferedImage img2) que receba 2 imagens do mesmo
     * tamanho. Então:
     * Subtraia o pixel (x,y) da imagem 1 do pixel(x,y) da imagem 2;
     * Se o valor resultante no canal de cor for negativo, zere-o;
     */
    public BufferedImage subtract(BufferedImage img1, BufferedImage img2) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                //Le o pixel
                Color p1 = new Color(img1.getRGB(x, y));
                Color p2 = new Color(img2.getRGB(x, y));

                //Faz a subtração, canal a canal
                int r = p1.getRed() - p2.getRed();
                int g = p1.getGreen() - p2.getGreen();
                int b = p1.getBlue() - p2.getBlue();

                //Gera a cor de saída. Observe que estamos chamando a função toColor para garantir que r, g e b
                //estejam no intervalo de 0 a 255.
                Color o = toColor(r, g, b);

                //Define a cor de saída na imagem de saída (out).
                out.setRGB(x, y, o.getRGB());
            }
        }

        return out;
    }

    /**
     * Exercício 3b
     * Faça também uma função add, similar a subtract, mas que soma a cor dos pixels.
     * Novamente, se uma das cores for maior do que 255, force seu valor para 255.
     */
    public BufferedImage add(BufferedImage img1, BufferedImage img2) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                //Le o pixel
                Color p1 = new Color(img1.getRGB(x, y));
                Color p2 = new Color(img2.getRGB(x, y));

                //Faz a soma, canal a canal
                int r = p1.getRed() + p2.getRed();
                int g = p1.getGreen() + p2.getGreen();
                int b = p1.getBlue() + p2.getBlue();

                //Gera a cor de saída. Observe que estamos chamando a função toColor para garantir que r, g e b
                //estejam no intervalo de 0 a 255.
                Color o = toColor(r, g, b);

                //Define a cor de saída na imagem de saída (out).
                out.setRGB(x, y, o.getRGB());
            }
        }

        return out;
    }

    /**
     * Exercício 4
     * Faça uma função BufferedImage lerp(BufferedImage img1, BufferedImage img2, float percent) que receba duas
     * imagens de mesmo tamanho e aplique a seguinte fórmula em cada pixel:
     *
     * dst = p1*(1.0f-percent) + p2 * percent
     */
    public BufferedImage lerp(BufferedImage img1, BufferedImage img2, float percent) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img1.getWidth(), img1.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                //Le o pixel
                Color p1 = new Color(img1.getRGB(x, y));
                Color p2 = new Color(img2.getRGB(x, y));

                //Aplica a fórmula p1*(1.0f-percent) + p2 * percent

                int r = (int)(p1.getRed() * (1.0f-percent) + p2.getRed() * percent);
                int g = (int)(p1.getGreen() * (1.0f-percent) + p2.getGreen() * percent);
                int b = (int)(p1.getBlue() * (1.0f-percent) + p2.getBlue() * percent);

                //Gera a cor de saída. Observe que estamos chamando a função toColor para garantir que r, g e b
                //estejam no intervalo de 0 a 255.
                Color o = toColor(r, g, b);

                //Define a cor de saída na imagem de saída (out).
                out.setRGB(x, y, o.getRGB());
            }
        }

        return out;
    }

    /**
     * Exercício 5
     * Crie as funções getRGBf e setRGBf para trabalhar com os valores no intervalo de reais de até 1 ao invés de
     * inteiros de 0 até 255.
     *
     * Observe que nessas novas funções a cor (0.2, 0.5, 1.0) equivalerá a cor (51, 127, 255) das funções antigas.
     */
    public float[] getRGBf(BufferedImage img, int x, int y) {
        //Le o pixel
        Color c = new Color(img.getRGB(x, y));

        //Converte os canais para o intervalo de 0 até 1. Para isso, basta dividir por 255.
        float[] rgbf = {c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f};
        return rgbf;
    }

    public void setRGBf(BufferedImage img, int x, int y, float[] color) {
        //Converte norvamente para o intervalo de 0 até 255.
        int r = (int)(color[0] * 255);
        int g = (int)(color[1] * 255);
        int b = (int)(color[2] * 255);

        //Gera a cor de saída. Observe que estamos chamando a função toColor para garantir que r, g e b
        //estejam no intervalo de 0 a 255.
        Color o = toColor(r, g, b);

        //Define a cor na imagem passada por parametro
        img.setRGB(x, y, o.getRGB());
    }

    /**
     * Exercicio 5 (Continuação)
     *
     * Em seguida, crie a função BufferedImage multiply(BufferedImage img, float[] color) que multiplica cada
     * componente de cor dos pixels de origem pelo componente correspondente da cor passada por parâmetro;
     *
     * Obviamente, essa função deve usar os pixels no intervalo de 0 até 1, como descrito acima.
     * Teste o programa com algumas cores e tente entender o que significa o resultado;
     */
    public BufferedImage multiply(BufferedImage img, float[] color) {
        //Cria a imagem de saída
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Le o pixel
                float[] p = getRGBf(img, x, y);

                //Faz a multiplicação
                float[] o = {
                    p[0] * color[0],
                    p[1] * color[1],
                    p[2] * color[2]
                };

                //Define a cor de saída na imagem de saída (out).
                setRGBf(out, x, y, o);
            }
        }

        return out;
    }



    public void run() throws IOException {
        File PATH = new File("/Users/vinigodoy/img");

        //Carrega a imagem da tartaruga
        BufferedImage turtle = ImageIO.read(new File(PATH, "cor/turtle.jpg"));

        //Exercício 1: Brightness. Usa 2 valores. Um dobra o brilho e outro diminui em 50%
        salvar(bright(turtle, 2.0f), "ex1TurtleBright");
        salvar(bright(turtle, -0.5f), "ex1TurtleDark");

        //Exercício 2: Gera 3 tipos de grayscale diferentes e o threshold
        salvar(grayscaleR(turtle), "ex2aTurtleGrayRed");
        salvar(grayscaleMedia(turtle), "ex2bTurtleGrayMedia");
        BufferedImage turtleGray = grayscaleFormula(turtle); //Guardamos para usar no threshold
        salvar(turtleGray, "ex2cTurtleGrayFormula");
        salvar(threshold(turtleGray, 127), "ex2dTurtleTreshold120");

        //Carrega as imagens dos erros para o exercício 3
        BufferedImage erros1 = ImageIO.read(new File(PATH, "pb/errosB1.png"));
        BufferedImage erros2 = ImageIO.read(new File(PATH, "pb/errosB2.png"));

        //Exercício 3a, subtração
        BufferedImage sub1 = subtract(erros1, erros2);
        BufferedImage sub2 = subtract(erros2, erros1);
        salvar(sub1, "ex3aErrosSubtract1");
        salvar(sub2, "ex3aErrosSubtract2");

        //Exercício 3b, soma
        salvar(add(sub1, sub2), "ex3bErrosSoma");

        //Carga das imagens para o exercício 4
        BufferedImage mario = ImageIO.read(new File(PATH, "cor/mario.jpg"));
        BufferedImage sonic = ImageIO.read(new File(PATH, "cor/sonic.jpg"));

        //Aplica a função com os coeficientes solicitados
        for (int i = 1; i < 4; i++) {
            salvar(lerp(mario, sonic, 0.25f * i), "ex4Lerp" + i);
        }

        //Exercicio 5, multiplicação
        salvar(multiply(turtle, new float[]{1.0f, 0.5f, 0.5f}), "ex5TurtleOnLightRed");
        salvar(multiply(turtle, new float[]{0.5f, 1.0f, 0.5f}), "ex5TurtleOnLightGreen");
    }


    public static void main(String [] args) throws IOException {
        new ExerciciosResolvidos().run();
    }
}
