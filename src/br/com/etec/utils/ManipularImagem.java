package br.com.etec.utils;

import br.com.etec.log.Log;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author marcelosiedler
 */
public class ManipularImagem {
    
   private static final String TAG = "ManipularImagem";

    /*
     * Faz redimensionamento da imagem conforme os parâmetros imgLargura e imgAltura mantendo a proporcionalidade. 
     * Caso a imagem seja menor do que os parâmetros de redimensionamento, a imagem não será redimensionada. 
     *  
     * @param caminhoImg caminho e nome da imagem a ser redimensionada. 
     * @param imgLargura nova largura da imagem após ter sido redimensionada. 
     * @param imgAltura  nova altura da imagem após ter sido redimensionada. 
     *  
     * @return Não há retorno 
     * @throws Exception Erro ao redimensionar imagem 
     ************************************************************************************************************/
    public static BufferedImage setImagemDimensao(String caminhoImg, Integer imgLargura, Integer imgAltura) {
        Double novaImgLargura = null;
        Double novaImgAltura = null;
        Double imgProporcao = null;
        Graphics2D g2d = null;
        BufferedImage imagem = null, novaImagem = null;

        try {
            //--- Obtém a imagem a ser redimensionada ---
            imagem = ImageIO.read(new File(caminhoImg));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Log.e(TAG, ex.getMessage());
        }

        novaImgLargura = (double) imagem.getWidth();

        novaImgAltura = (double) imagem.getHeight();

        if (novaImgLargura >= imgLargura) {
            imgProporcao = (novaImgAltura / novaImgLargura);//calcula a proporção  
            novaImgLargura = (double) imgLargura;

            novaImgAltura = (novaImgLargura * imgProporcao);

            while (novaImgAltura > imgAltura) {
                novaImgLargura = (double) (--imgLargura);
                novaImgAltura = (novaImgLargura * imgProporcao);
            }
        } else if (novaImgAltura >= imgAltura) {
            imgProporcao = (novaImgLargura / novaImgAltura);//calcula a proporção  
            novaImgAltura = (double) imgAltura;

            while (novaImgLargura > imgLargura) {
                novaImgAltura = (double) (--imgAltura);
                novaImgLargura = (novaImgAltura * imgProporcao);
            }
        }

        novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(), BufferedImage.TYPE_INT_RGB);
        g2d = novaImagem.createGraphics();
        g2d.drawImage(imagem, 0, 0, novaImgLargura.intValue(), novaImgAltura.intValue(), null);

        return novaImagem;
    }

    public static byte[] getImgBytes(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", baos);
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }

        InputStream is = new ByteArrayInputStream(baos.toByteArray());

        return baos.toByteArray();
    }

    //Novo método para exibir imagem na tela
    //Recebe o label que queremos exibir E a imagem como array de bytes do banco
    public static void exibirImagemLabel(byte[] minhaimagem, javax.swing.JLabel label) {
        if (minhaimagem != null) {
            InputStream input = new ByteArrayInputStream(minhaimagem);
            try {
                BufferedImage imagem = ImageIO.read(input);
               
                label.setIcon(new ImageIcon(imagem));
            } catch (IOException ex) {
                Log.e(TAG, ex.getMessage());
            }
        } else {
            label.setIcon(null);
        }
    }

    public static BufferedImage aumenta(BufferedImage image, int n) {
        int w = n * image.getWidth();
        int h = n * image.getHeight();

        BufferedImage novoTamanho
                = new BufferedImage(w, h, image.getType());
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                novoTamanho.setRGB(x, y, image.getRGB(x / n, y / n));
            }
        }
        return novoTamanho;
    }
}
